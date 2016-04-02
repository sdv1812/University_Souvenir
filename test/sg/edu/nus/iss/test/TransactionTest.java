package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.iss.gui.StoreApplication;

/**
 * @author koushik - Transaction JUnit Test Class
 *
 */
public class TransactionTest {
	Transaction transaction;
	ArrayList<Cart> cartList;
	MemberRegister members;
	ProductRegister products;
	StoreApplication manager;
	Store store;
	ArrayList<Transaction> transactionList;

	@Before
	public void setUp() throws Exception {
		manager = new StoreApplication();
		store = new Store();
		store.initializeData();
		members = store.getMemberRegister();
		products = store.getProductReg();
		cartList = new ArrayList<Cart>();
		transaction = store.getTransaction();
		transactionList = new ArrayList<Transaction>();
		Category c1 = new Category("CLO", "CLOTHING");
		Product p1 = new Product("CLO/1", c1, "Centenary Jumper", "A really nice momento", 235, 21.45, "wqd", 4, 5);
		Member m1 = new Member("sanskar", "e0013519", -1);
		Cart cart = new Cart(p1, m1, 4);
		cartList.add(cart);
	}

	@Test
	public void testAddProductsToCart() {
		String result = transaction.addProductsToCart(cartList, 10);
		assertEquals("sucess", result);
	}

	@Test
	public void testMakePayment() {
		double amountReceived = 20;
		double transactionTotal = 15;
		double discount = 10;
		double redeemPoints = 100;
		String paymentStatus = transaction.makePayment(amountReceived, transactionTotal, discount, redeemPoints,
				cartList, members, products);
		assertEquals("Payment is successful when amount received greater or equal to Transaction total ", "success",
				paymentStatus);
		amountReceived = 12;
		paymentStatus = transaction.makePayment(amountReceived, transactionTotal, discount, redeemPoints, cartList,
				members, products);
		assertEquals("Payment is failed when amount received less than Transaction total", "failed", paymentStatus);

	}

	/*
	 * All the transactions are saved in the Transaction list when the Payment
	 * is done
	 */
	@Test
	public void testSaveTransaction() {
		transactionList = transaction.getAllTransactions();
		assertNotNull("Whenever Payment is successfull corresponding Transaction is saved in List and written to file",
				transactionList);
	}

	@Test
	public void testGetTransactionsWithinPeriod() throws ParseException {
		ArrayList<Transaction> t1 = transaction.getTransactions("2016-03-30", "2016-03-31");
		assertNotNull("List of Transactions returned is not null ", t1);
		assertTrue("Getting list of transactions within the specified date", t1.size() > 0);
	}

	/**
	 * Getting all Transactions from the file
	 */
	@Test
	public void getAllTransactions() {
		ArrayList<Transaction> transactionlist = transaction.getAllTransactions();
		assertNotNull("List of Transactions is not null", transactionlist);
		assertTrue("List of Transactions returned", transactionlist.size() > 0);
	}

}
