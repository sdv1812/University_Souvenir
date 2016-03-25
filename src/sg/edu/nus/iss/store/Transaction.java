package sg.edu.nus.iss.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class Transaction implements Comparable {
	int tranasctionId = 1;
	String productId;
	String memberId;
	int qtyPurchased;
	Date dateOfPurchase;
	double transActionTotal;
	double discountPercentage;
	ArrayList<Transaction> transAction;
	MemberRegister member = new MemberRegister();
	ProductRegister product = new ProductRegister();
	DiscountManager discountManager = new DiscountManager();
	
	public Transaction() {
		transAction = new ArrayList<Transaction>();
	}
	public Transaction(int tranasctionId, String productId, String memberId,
			int qtypurchased, Date dateOfPurchase) {
		super();
		this.tranasctionId = tranasctionId;
		this.productId = productId;
		this.memberId = memberId;
		qtyPurchased = qtypurchased;
		this.dateOfPurchase = dateOfPurchase;
	}
	public int getTranasctionId() {
		return tranasctionId;
	}
	public void setTranasctionId(int tranasctionId) {
		this.tranasctionId = tranasctionId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getqtyPurchased() {
		return qtyPurchased;
	}
	public void setQtypurchased(int qtyPurchased) {
		qtyPurchased = qtyPurchased;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String  addProductsToCart(ArrayList<Cart> c1){
		String productsAdded = "success";
		transActionTotal = 0;
		ArrayList<Cart> cart = new ArrayList<Cart>();
		cart.addAll(c1);
		Iterator productIterator = cart.iterator();
		while(productIterator.hasNext()){
			Cart lineItem = (Cart)productIterator.next();
			memberId = lineItem.getMemberId();//get Member object and send it to discount
			Member currentMember = member.getMember(memberId);
			int productQuantityOrdered =lineItem.getQuantity();
			Product product =lineItem.getProduct();
			if(product.getQuantityAvailable() > productQuantityOrdered){ //re order quanity check
				transActionTotal += transActionTotal + product.getPrice()*productQuantityOrdered;
			}else{
				productsAdded ="failed";
			}
			discountPercentage = discountManager.getMaxDiscount(transActionTotal, currentMember);
		}
		//call to discount 
		return productsAdded;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public double calculateTransactionTotal(){
				return transActionTotal;
	}
	public double getBalance(double transActionTotal,double amountReceived){
				return amountReceived-transActionTotal;
	}
	public ArrayList<Transaction> getAllTransactions(){
		/* connect to text file */
				//convert each row to transaction and add it to list
				return  transAction;
	}
	public String makePayment(double amountReceived,double transActionTotal,double discount,double redeemPoints,ArrayList<Cart> c1 ){
		String paymentStatus ="success";
		if(amountReceived>transActionTotal){
		transActionTotal = transActionTotal -(transActionTotal*discount)/100 - redeemPoints;
		double  bonusPoints = transActionTotal/100;
		saveTransaction( tranasctionId ,transActionTotal, c1,redeemPoints,bonusPoints);
		}
		paymentStatus = "failed";
		return paymentStatus;
	}
	/*public void updateRedeemPoints(String  memberId, double redeemPoints  ) {
		// TODO Auto-generated method stub
			Member member = getMember(memberId);
			double loyaltyPoints = member.getLoyaltyPoints - redeemPoints;
			member.setLoyaltyPoints(loyaltyPoints);
		}*/
	public String saveTransaction(int tranasctionId, double transActionTotal, ArrayList<Cart> c1, double redeemPoints,double bonusPoints ){
		String saveTransactionStatus = "";
		ArrayList<Cart> cart = new ArrayList<Cart>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String  currentDateString = dateFormat.format(date);
		 try {
			dateOfPurchase = dateFormat.parse(currentDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cart.addAll(c1);
		Iterator productIterator = cart.iterator();
		while(productIterator.hasNext()){
			Cart c2 = (Cart)productIterator.next();
			 memberId = c2.getMemberId();
			 qtyPurchased = c2.getQuantity();
			 Product p =c2.getProduct();
			 productId = p.getProductId();
			 transAction.add(new Transaction( tranasctionId,  productId,  memberId,
					 qtyPurchased, dateOfPurchase)) ;
			 System.out.println("transAction arraylist is"+transAction.toString());
			 product.updateQuantity(productId,qtyPurchased);
		}
		 member.updateRedeemPoints(memberId,redeemPoints,bonusPoints);
		 setTranasctionId(tranasctionId+1);
		 setTransActionTotal(0);
		return "";
}
	public double getTransActionTotal() {
		return transActionTotal;
	}
	public void setTransActionTotal(double transActionTotal) {
		this.transActionTotal = transActionTotal;
	}
	/*public void updateQuantity(String productId, int qtyPurchased2) {
		// TODO Auto-generated method stub
			Product product = getProduct(productId);
			int updatedQuantity = product.getQuantityAvailable() - qtyPurchased2;
			product.setQuantityAvailable(updatedQuantity);
		
	}*/
	public ArrayList<Transaction> getTransactions(Date fromDate , Date toDate){
				ArrayList<Transaction> transactionPeriod = new ArrayList<Transaction>() ;
				for(Transaction transaction :transAction){
							int fromDateComparison = fromDate.compareTo(transaction.getDateOfPurchase());
							int toDateComparison = 	toDate.compareTo(transaction.getDateOfPurchase());
							if(fromDateComparison >=0 && toDateComparison<= 0)
								transactionPeriod.add(transaction);
						
				}
				Collections.sort(transactionPeriod,Transaction.transProductId);
				return transactionPeriod;
	}
	public static Comparator<Transaction> transProductId = new Comparator<Transaction>(){

		@Override
		public int compare(Transaction transaction1, Transaction transaction2) {
				String productId1 = transaction1.getProductId();
				String productId2 = transaction2.getProductId();
			return productId1.compareTo(productId2);
		}
		
	};

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void writeToFile(ArrayList<Transaction> transactionList){
		try{
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter("StoreAppData/Transaction.dat"));
			for(Transaction t :transactionList){
				fileWriter.write(t.getTranasctionId()+",");
				fileWriter.write(t.getProductId()+",");
				fileWriter.write(t.getMemberId()+",");
				fileWriter.write(t.getqtyPurchased()+",");
				fileWriter.write(t.getDateOfPurchase()+"\n");
			}
			fileWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}

	
