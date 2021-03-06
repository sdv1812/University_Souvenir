package sg.edu.nus.iss.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import static sg.edu.nus.iss.utils.StoreConstants.DECIMAL_FORMAT;
import static sg.edu.nus.iss.utils.StoreConstants.DATE_FORMAT;
import static sg.edu.nus.iss.utils.StoreConstants.TRANSACTION_PATH;

/**
 * @author Koushik Radhakrishnan - Transaction - Handles Transaction and saves
 *         each Transaction in file
 *
 */
public class Transaction {
	private int tranasctionId = 1;
	private String productId;
	private String memberId;
	private  int qtyPurchased;
	private String dateOfPurchase;
	private double transActionTotal;
	private double discountPercentage;
	private int loyaltyPoints;
	private double bonusPoints;
	private ArrayList<Transaction> transAction;
	private Member currentMember;


	public Transaction() {
		transAction = new ArrayList<Transaction>();
		try {
			readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Transaction(int tranasctionId, String productId, String memberId, int qtypurchased, String dateOfPurchase) {
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
		this.qtyPurchased = qtyPurchased;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double calculateTransactionTotal() {
		return transActionTotal;
	}

	public void setTransActionTotal(double transActionTotal) {
		this.transActionTotal = transActionTotal;
	}

	public double getBalance(double transActionTotal, double amountReceived) {
		return amountReceived - transActionTotal;
	}

	public double getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(double bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public ArrayList<Transaction> getAllTransactions() {

		return transAction;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	/**
	 * @param List
	 *            of cart objects
	 * @param discountPerc
	 * @return ProductAddition status
	 */
	public String addProductsToCart(List<Cart> c1, double discountPerc) {
		String addStatus = "failed";
		transActionTotal = 0;
		ArrayList<Cart> cart = new ArrayList<Cart>();
		cart.addAll(c1);
		Iterator<Cart> productIterator = cart.iterator();
		while (productIterator.hasNext()) {
			Cart lineItem = (Cart) productIterator.next();
			currentMember = lineItem.getMember();
			loyaltyPoints = (currentMember == null) ? 0 : currentMember.getLoyaltyPoints();
			int productQuantityOrdered = lineItem.getQuantity();
			Product product = lineItem.getProduct();
			transActionTotal += product.getPrice() * productQuantityOrdered;
			addStatus = "sucess";
		}
		discountPercentage = discountPerc;
		return addStatus;
	}

	/**
	 * @param amountReceived
	 * @param transActionTotal
	 * @param discount
	 * @param redeemPoints
	 * @param c1
	 * @param members
	 * @param products
	 * @return paymentStatus
	 */
	public double makePayment(double transActionTotal, double discount, double redeemPoints) {
		double temptransActionTotal = transActionTotal - ((transActionTotal * discount / 100) + (redeemPoints / 10));
		return Double.parseDouble(DECIMAL_FORMAT.format(temptransActionTotal));
	}
	
	public double calculateBalance(double receivedAmount, double totalAmount){
		if (receivedAmount >= totalAmount) {
			bonusPoints = (currentMember == null) ? 0 : totalAmount / 10;
			transActionTotal = totalAmount;
			double balance = (receivedAmount - totalAmount) ;
			return Double.parseDouble(DECIMAL_FORMAT.format(balance)); 
		} else {
			return -1;
		}
	}
	
	public void saveTransaction(double redeemPoints, ArrayList<Cart> cart, MemberRegister members, ProductRegister products){
		saveTransaction(tranasctionId, transActionTotal, cart, redeemPoints, bonusPoints, members, products);
	}

	/**DA
	 * @param tranasctionId
	 * @param transActionTotal
	 * @param c1
	 * @param redeemPoints
	 * @param bonusPoints
	 * @param members
	 * @param products
	 * @return Transaction save status
	 */
	public String saveTransaction(int tranasctionId, double transActionTotal, ArrayList<Cart> c1, double redeemPoints,
			double bonusPoints, MemberRegister members, ProductRegister products) {
		String saveTransactionStatus = "failed";
		ArrayList<Cart> cart = new ArrayList<Cart>();
		Date date = new Date();
		String currentDateString = DATE_FORMAT.format(date);
		cart.addAll(c1);
		Iterator<Cart> productIterator = cart.iterator();
		while (productIterator.hasNext()) {
			Cart c2 = (Cart) productIterator.next();
			if (c2.getMember() == null)
				memberId = "PUB";
			else {
				memberId = c2.getMember().getMemberID();
			}
			qtyPurchased = c2.getQuantity();
			Product p = c2.getProduct();
			productId = p.getProductId();
			transAction.add(new Transaction(tranasctionId, productId, memberId, qtyPurchased, currentDateString));
			saveTransactionStatus = "success";
			try {
				products.updateQuantity(productId, qtyPurchased);
				products.writeListToFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!memberId.equals("PUB"))
			members.updateRedeemPoints(memberId, redeemPoints, bonusPoints);
		setTransActionTotal(0);
		return saveTransactionStatus;
	}

	/**
	 * @param fromDate
	 * @param toDate
	 * @return List of Transactions within the date limit specified
	 * @throws ParseException
	 */
	public ArrayList<Transaction> getTransactions(String fromDate, String toDate) throws ParseException {
		ArrayList<Transaction> transactionPeriod = new ArrayList<Transaction>();
		Date fromDateTransaction = DATE_FORMAT.parse(fromDate);
		Date toDateTransaction = DATE_FORMAT.parse(toDate);
		if (!toDateTransaction.before(fromDateTransaction)) {
			for (Transaction transaction : transAction) {
				Date dateofPurchase = DATE_FORMAT.parse(transaction.getDateOfPurchase());
				if ((dateofPurchase.after(fromDateTransaction) && dateofPurchase.before(toDateTransaction))
						|| dateofPurchase.equals(toDateTransaction) || dateofPurchase.equals(fromDateTransaction))
					transactionPeriod.add(transaction);
			}
		}
		Collections.sort(transactionPeriod, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction transaction1, Transaction transaction2) {
				String productId1 = transaction1.getProductId();
				String productId2 = transaction2.getProductId();
				return productId1.compareTo(productId2);
			}
		});
		return transactionPeriod;
	}
	/**
	 * Write to File (ArrayList of contents to file)
	 */
	public void writeToFile() {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(TRANSACTION_PATH));
			for (Transaction t : transAction) {
				fileWriter.write(t.getTranasctionId() + ",");
				fileWriter.write(t.getProductId() + ",");
				fileWriter.write(t.getMemberId() + ",");
				fileWriter.write(t.getqtyPurchased() + ",");
				fileWriter.write(t.getDateOfPurchase() + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read List of Transaction from file and Save it in ArrayList
	 * @throws IOException
	 */
	public void readFromFile() throws IOException {
		ArrayList<String> contentsFromFile = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_PATH));
		String individualLine;
		while ((individualLine = br.readLine()) != null) {
			contentsFromFile.add(individualLine);
		}
		
		String line[] = contentsFromFile.toArray(new String[contentsFromFile.size()]);
		int idChecker = line.length - 1;
		for (int i = 0; i < line.length; i++) {
			String singleTransactionLine = contentsFromFile.get(i);
			String[] transactionFields = singleTransactionLine.split(",");
			if(transactionFields.length != 0){
			int transactionIde = Integer.parseInt(transactionFields[0]);
			String productId = transactionFields[1];
			String memberId = transactionFields[2];
			int qty = Integer.parseInt(transactionFields[3]);
			String date = transactionFields[4];
			transAction.add(new Transaction(transactionIde, productId, memberId, qty, date));
			if (i == idChecker) {
				this.setTranasctionId(transactionIde + 1);
			}
		}
		}
		br.close();
	}
}
