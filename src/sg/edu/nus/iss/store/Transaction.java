package sg.edu.nus.iss.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.util.List;

/**
 * @author koushik 
 *
 */
public class Transaction implements Comparable {
	int tranasctionId = 1;
	String productId;
	String memberId;
	int qtyPurchased;
	String dateOfPurchase;
	double transActionTotal;
	double discountPercentage;
	ArrayList<Transaction> transAction;
	//MemberRegister member = new MemberRegister();
	//ProductRegister product = new ProductRegister();
	//DiscountManager discountManager = new DiscountManager();
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");;

	public Transaction() {
		transAction = new ArrayList<Transaction>();
		try {
			readFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Transaction(int tranasctionId, String productId, String memberId,
			int qtypurchased, String dateOfPurchase) {
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

	public double getBalance(double transActionTotal, double amountReceived) {
		return amountReceived - transActionTotal;
	}

	public ArrayList<Transaction> getAllTransactions() {
		/* connect to text file */
		// convert each row to transaction and add it to list
		System.out.println("TransactionArrayList size is" + transAction.size());
		return transAction;
	}

	public String addProductsToCart(List<Cart> c1, double discountPerc) {
		String productsAdded = "success";
		transActionTotal = 0;
		ArrayList<Cart> cart = new ArrayList<Cart>();
		cart.addAll(c1);
		Iterator productIterator = cart.iterator();
		while (productIterator.hasNext()) {
			Cart lineItem = (Cart) productIterator.next();
			Member currentMember = lineItem.getMember();
			//System.out.println("Member object is"+currentMember.toString());
			int productQuantityOrdered = lineItem.getQuantity();
			Product product = lineItem.getProduct();
			if (product.getQuantityAvailable() > productQuantityOrdered) {
				System.out.println("Transaction total before addition"
						+ transActionTotal);// re order quanity check
				transActionTotal += transActionTotal + product.getPrice()
						* productQuantityOrdered;
				System.out
						.println("Transaction total inside addProductsToCart method is"
								+ transActionTotal);
				System.out.println("Transaction total is " + transActionTotal);
			} else {
				productsAdded = "failed";
			}
			discountPercentage = discountPerc;
		}
		// call to discount
		return productsAdded;
	}

	public String makePayment(double amountReceived, double transActionTotal,
			double discount, double redeemPoints, ArrayList<Cart> c1) {
		String paymentStatus = "success";
		if (amountReceived > transActionTotal) {
			transActionTotal = transActionTotal - (transActionTotal * discount)
					/ 100 - redeemPoints;
			double bonusPoints = transActionTotal / 100;
			System.out.println("Transaction id is" + tranasctionId);
			System.out.println("Transaction total is" + transActionTotal);
			System.out.println("Cartobject is" + c1.toString());
			System.out.println("Redeem points is" + redeemPoints);
			System.out.println("Discount is " + discount);
			saveTransaction(tranasctionId, transActionTotal, c1, redeemPoints,
					bonusPoints);
		}
		paymentStatus = "failed";
		return paymentStatus;
	}

	public String saveTransaction(int tranasctionId, double transActionTotal,
			ArrayList<Cart> c1, double redeemPoints, double bonusPoints) {
		String saveTransactionStatus = "";
		ArrayList<Cart> cart = new ArrayList<Cart>();
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDateString = formatter.format(date);
		System.out.println("Current date string is" + currentDateString);
		cart.addAll(c1);
		Iterator productIterator = cart.iterator();
		while (productIterator.hasNext()) {
			Cart c2 = (Cart) productIterator.next();
			System.out
					.println("Cart object inside iterator is" + c2.toString());
			if(c2.getMember()==null)
				memberId= "PUB";
			else {
			memberId = c2.getMember().getMemberID();
			}
			qtyPurchased = c2.getQuantity();
			Product p = c2.getProduct();
			productId = p.getProductId();
			transAction.add(new Transaction(tranasctionId, productId, memberId,
					qtyPurchased, currentDateString));
			System.out.println("Transaction arraylist is"
					+ transAction.toString());
			//product.updateQuantity(productId, qtyPurchased);
		}
		//member.updateRedeemPoints(memberId, redeemPoints, bonusPoints);
		// writeToFile();
		System.out.println("Transaction initialisations");
		setTransActionTotal(0);
		setTranasctionId(this.tranasctionId+1);
		System.out.println("Transaction total is" + transActionTotal);

		return "";
	}

	public void setTransActionTotal(double transActionTotal) {
		this.transActionTotal = transActionTotal;
	}

	public ArrayList<Transaction> getTransactions(String fromDate, String toDate)
			throws ParseException {
		ArrayList<Transaction> transactionPeriod = new ArrayList<Transaction>();
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDateTransaction = formatter.parse(fromDate);
		Date toDateTransaction = formatter.parse(toDate);
		for (Transaction transaction : transAction) {
			Date dateofPurchase = formatter.parse(transaction
					.getDateOfPurchase());
			int fromDateComparison = fromDateTransaction
					.compareTo(dateofPurchase);
			int toDateComparison = toDateTransaction.compareTo(dateofPurchase);
			if (fromDateComparison >= 0 && toDateComparison <= 0)
				transactionPeriod.add(transaction);

		}
		Collections.sort(transactionPeriod, Transaction.transProductId);
		return transactionPeriod;
	}

	public static Comparator<Transaction> transProductId = new Comparator<Transaction>() {

		@Override
		public int compare(Transaction transaction1, Transaction transaction2) {
			String productId1 = transaction1.getProductId();
			String productId2 = transaction2.getProductId();
			return productId1.compareTo(productId2);
		}

	};

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	public void writeToFile(ArrayList<Transaction> transactionList) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(
					"StoreAppData/Transaction.dat"));
			for (Transaction t : transactionList) {
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

	public void readFromFile() throws IOException {
		ArrayList<String> contentsFromFile = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(
				"StoreAppData/Transaction.dat"));
		String individualLine;
		while ((individualLine = br.readLine()) != null) {
			contentsFromFile.add(individualLine);
		}
		String line[] = contentsFromFile.toArray(new String[contentsFromFile
				.size()]);
		int idChecker = line.length - 1;
		for (int i = 0; i < line.length; i++) {
			String singleTransactionLine = contentsFromFile.get(i);
			String[] transactionFields = singleTransactionLine.split(",");
			int transactionIde = Integer.parseInt(transactionFields[0]);
			String productId = transactionFields[1];
			String memberId = transactionFields[2];
			int qty = Integer.parseInt(transactionFields[3]);
			String date = transactionFields[4];
			transAction.add(new Transaction(transactionIde, productId,
					memberId, qty, date));
			if (i == idChecker) {
				this.setTranasctionId(transactionIde + 1);
			}
		}
	}

	/*
	 * private Date convertToDate(String transactionField) { SimpleDateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd"); String dateInString =
	 * transactionField; Date date; date = new Date (dateInString); return date;
	 * }
	 */
}