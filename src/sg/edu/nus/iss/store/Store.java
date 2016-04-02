package sg.edu.nus.iss.store;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


public class Store {
	private MemberRegister members;
	private CategoryRegister categories;
	private StoreKeeperRegister storeKeepers;
	private DiscountManager discounts;
	private VendorRegister vendors;
	private ProductRegister products; //xuemin
	private Cart cart;
	private ArrayList<Product> product;
	private AddProductToCart addProductsToCart;
	private Transaction transaction ;
	private ArrayList<Cart> cartList ;
	private ArrayList<Transaction> transactionList;

	public Store() {
		members = new MemberRegister();
		categories = new CategoryRegister();
		storeKeepers = new StoreKeeperRegister();
		discounts = new DiscountManager();
		vendors = new VendorRegister();
		cartList = new ArrayList<Cart>();
		transaction = new Transaction();
		transactionList = new ArrayList<Transaction>();
		addProductsToCart = new AddProductToCart();
		products=new ProductRegister(this); //xuemin
	}
	public ProductRegister getProductReg(){ //xuemin
		return products;
	}

	public ArrayList<Product> getProducts()
	{
		return products.getProducts();
	}
	//add
	public ArrayList<Product> getProductsBelowThreshold(){
		return products.checkProductsBelowThreshold();
	}


	public CategoryRegister getCategoryReg(){ //xuemin
		return categories;
	}
	/*public void readDataFromFile() throws IOException{ //xuemin
		products.createListFromFile();
	}*/

	public void writeDataToFile() throws IOException{ //xuemin
		products.writeListToFile();
	}

	public void removeProduct(Product p) throws IOException{  //xuemin
		products.removeProduct(p);
	}

	public void removeProduct(String id) throws IOException{  //xuemin
		products.removeProduct(id);
	}

	public void addStoreKeeper(String storeKeeperName, String storeKeeperPassword){
		storeKeepers.addStoreKeeper(storeKeeperName, storeKeeperPassword);
		storeKeepers.writeToFile();
	}

	public boolean validate(String storeKeeperName, String password){
		return storeKeepers.validate(storeKeeperName, password);
	}

	public boolean addMember(String memberName, String memberID){
		boolean b = members.addMember(memberName, memberID);
		members.writeToFile();
		return b;
	}

	public boolean addCategory(String categoryCode, String categoryName) {
		boolean b= categories.addCategory(categoryCode, categoryName);
		categories.writeToFile();
		return b;
	}

	public ArrayList<Category> getCategories() {
		return categories.getCategories();
	}

	public void removeMember(String memberID) {
		members.removeMember(memberID);
		members.writeToFile();
	}

	public ArrayList<Member> getMembers() {
		return members.getMembers();
	}

	public void initializeData(){
		try {
			storeKeepers.createListFromFile();
			members.createListFromFile();
			categories.createListFromFile();
			discounts.createListFromFile();
			vendors.readVendorPerCategoryFromFile(getCategories());
			vendors.readVendorFromFile();
			products.createListFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			e.printStackTrace();
		}


	}

	public void removeCategory(String categoryCode) {
		// TODO Auto-generated method stub
		categories.removeCategory(categoryCode);
		categories.writeToFile();
	}

	public ArrayList<Discount> getDiscounts() {
		// TODO Auto-generated method stub
		return discounts.getDiscounts();
	}

	public boolean addDiscount(String discountCode, String description, float percentage, String startDate, String discountPeriod) {
		boolean b = discounts.addDiscount(discountCode, description, percentage, startDate, discountPeriod);
		discounts.writeToFile();
		return b;
	}

	public void removeDiscount(String discountCode) {
		discounts.removeDiscount(discountCode);

	}

	public String getDiscount() {
		double discountValue = transaction.getDiscountPercentage();
		String discount = Double.toString(discountValue);
		return discount;
	}

	public void modifyDiscount(String discountCode, float percentage) {
		discounts.modifyDiscount(discountCode, percentage);
	}

	public ArrayList<Cart> getProductsAddedInCart() {
		// TODO Auto-generated method stub
		return cartList;
	}

	public String getTransactionTotal() {
		// TODO Auto-generated method stub
		double transactionTotal = transaction.calculateTransactionTotal();
		String total = Double.toString(transactionTotal);
		return total;
	}

	public void makePayment(double amountreceived, double transactiontotal,
			double discountValue, double redeemPointsValue, ArrayList<Cart> cart) {
		transaction.makePayment(amountreceived, transactiontotal, discountValue, redeemPointsValue, cart, members, products);
		transaction.writeToFile();
	}


	public void removeCartItem(Cart lineItem) {
		cartList.remove(lineItem);
	}

	public boolean  addProductsToCart(Product product,int quantity ,Member member){
		boolean addProductStatus = false;
		Cart c1 =addProductsToCart.addProductsToCart(product, quantity, member);
		if(c1!=null){
			cartList.add(c1);
			addProductStatus = true;
		}
		return addProductStatus;
	}

	public ArrayList<Vendor> getVendors() {
		return vendors.getVendors();
	}

	public boolean addVendor(String vendorName, String vendorDescription, Category category) {
		return vendors.addVendor(vendorName, vendorDescription, category);
	}
	public ArrayList<Vendor> getVendorsPerCategory(Category category) {
		return vendors.getVendorsPerCategory(category);
	}
	public void removeVendor(String vendorName) {
		vendors.removeVendor(vendorName);
	}

	public ArrayList<Transaction> getTransaction(){
		ArrayList<Transaction> t1 = transaction.getAllTransactions();
		transactionList.addAll(t1);
		return transactionList;
	}
	public Member getMember(String memberIdentity) {
		return members.getMember(memberIdentity);
	}
	public Product getProductByID(String productIdentity) {
		return products.getProductById(productIdentity);
	}
	public Category getCategory(String categoryCode) {
		return categories.getCategory(categoryCode);
	}
	public Vendor getVendor(String vendorName) {
		return vendors.getVendor(vendorName);
	}
	public Category getCategoryByName(String categoryName) {
		// TODO Auto-generated method stub
		return categories.getCategorybyName(categoryName);
	}

	public AbstractTableModel getCategoryTableModel() {
		return categories.getCategoryTableModel();
	}

	public AbstractTableModel getMemberTableModel() {
		return members.getMemberTableModel();
	}

	public AbstractTableModel getProductTableModel() {
		return products.getProductTableModel();
	}

	public String getLoyaltyPoints() {
		int loyaltyPoints = transaction.getLoyaltyPoints();
		String loyalPoints = Integer.toString(loyaltyPoints);
		return loyalPoints;
	}
	
	public String beginCheckout(List<Cart> cartProducts) {//CHANGE 27-3
		double discountPerc = discounts.getMaxDiscount(cartProducts.get(0).getMember());
		String cartStatus = transaction.addProductsToCart(cartProducts,discountPerc);
		return cartStatus;
	}

	public double getBonusPoints() {
		return transaction.getBonusPoints();
	}

	public ArrayList<Transaction> getTransactions(String fromDate, String toDate) throws ParseException {
		return transaction.getTransactions(fromDate, toDate);
	}
	
}
