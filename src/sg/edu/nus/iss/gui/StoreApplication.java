package sg.edu.nus.iss.gui;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import sg.edu.nus.iss.store.*;
import sg.edu.nus.iss.utils.ConfirmDialog;



public class StoreApplication {
	private StoreWindow storeWindow;
	private Store store;
	//private TransactionWindow transActionWindow;
	public StoreApplication() {
		store = new Store();
		//transActionWindow = new TransactionWindow(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreApplication storeApplication = new StoreApplication();
		storeApplication.start();

	}
	public Store getStore(){ 
		return this.store;
	}

	private void start() {
		// TODO Auto-generated method stub
		storeWindow = new StoreWindow("Store Application", this);
		storeWindow.pack ();
		store.initializeData();
		//storeWindow.refresh ();
	}

	public void shutdown () {
		System.exit(0);
	}

	public boolean validate(String storeKeeperName, String password){
		return store.validate(storeKeeperName, password);
	}

	public ArrayList<Member> getMembers() {
		return store.getMembers();
	}
	public StoreWindow getMainWindow() {
		return storeWindow;
	}
	public MainPanel createMainPanel() {
		return(storeWindow.createMainPanel());
	}

	public boolean addMember(String memberName, String memberID) {
		// TODO Auto-generated method stub
		return store.addMember(memberName, memberID);
	}

	public boolean addCategory(String categoryCode, String categoryName) {
		return store.addCategory(categoryCode, categoryName);
	}

	public ArrayList<Category> getCategories() {
		return store.getCategories();
	}

	public void removeMember(String memberID) {
		store.removeMember(memberID);
	}

	public void removeCategory(String categoryCode) {
		// TODO Auto-generated method stub
		store.removeCategory(categoryCode);
	}

	public ArrayList<Discount> getDiscounts(){
		return store.getDiscounts();
	}

	public boolean addDiscount(String discountCode, String description, float percentage, String startDate, String discountPeriod) {
		return store.addDiscount(discountCode, description, percentage, startDate, discountPeriod);
	}

	public void removeDiscount(String discountCode) {
		store.removeDiscount(discountCode);		
	}
	//public Discount getDiscount(String discountCode) {
	//	return store.getDiscount(discountCode);
	//}

	public void modifyDiscount(String discountCode, float percentage) {
		store.modifyDiscount(discountCode, percentage);
	}

	public boolean  addProductsToCart(Product product,int quantity,Member member){
		boolean addProductStatus =store.addProductsToCart( product, quantity, member);
		storeWindow.refreshCart();
		return addProductStatus;
		//refresh cart panel
	}

	public ArrayList<Cart> getProductsAddedInCart() {
		// TODO Auto-generated method stub
		return store.getProductsAddedInCart();
	}

	public ArrayList<Product> getProducts(){
		return store.getProducts();
	}


	public void removeProduct(String id){
		store.removeProduct(id);
	}

	public String getTransactionTotal() {
		// TODO Auto-generated method stub
		return store.getTransactionTotal();
	}
	public void beginCheckout(ArrayList<Cart> cart){
		store.beginCheckout(cart);
	}



	public void removeSelectedItem() {
		final Cart lineItem = storeWindow.getSelectedCartItem();
		if(lineItem==null){
			return;
		}
		String title = "Remove Product";
		String msg = "Do you really want to remove Product from Cart " + lineItem.toString() + " ?";
		ConfirmDialog confirm = new ConfirmDialog(storeWindow,title,msg){
			private static final long serialVersionUID = 1L;
			@Override
			protected boolean performOkAction() {
				store.removeCartItem(lineItem);
				storeWindow.refreshCart();
				return true;
			}

		};
		confirm.pack();
		confirm.setVisible(true);	
	}

	public void makePayment(double amountreceived, double transactiontotal,
			double discountValue, double redeemPointsValue, ArrayList<Cart> cart) {
		store.makePayment(amountreceived,transactiontotal,discountValue,redeemPointsValue,cart);

	}
	public String getDiscount() {
		return store.getDiscount();
	}

	public ArrayList<Vendor> getVendors() {
		return store.getVendors();
	}

	
	public boolean addVendor(String vendorName, String vendorDescription, Category category) {
		return store.addVendor(vendorName, vendorDescription, category);
	}

	public ArrayList<Vendor> getVendorsPerCategory(Category category) {
		return store.getVendorsPerCategory(category);
	}

	public void removeVendor(String vendorName) {
		store.removeVendor(vendorName);
	}

	public Member getMember(String memberIdentity) {
		return store.getMember(memberIdentity);
	}

	public Product getProductByID(String productIdentity) {
		return store.getProductByID(productIdentity);
	}
	public Category getCategory(String categoryCode) {
		return store.getCategory(categoryCode);
	}
	public Vendor getVendor(String vendorName) {
		return store.getVendor(vendorName);
	}

	public Category getCategoryByName(String categoryName) {
		return store.getCategoryByName(categoryName);
	}
	
	public AbstractTableModel getCategoryTableModel() {
		return store.getCategoryTableModel();
	}
	
	public AbstractTableModel getMemberTableModel() {
		return store.getMemberTableModel();
	}
}
