package sg.edu.nus.iss.store;

import sg.edu.nus.iss.gui.*;

import java.util.ArrayList;
import java.util.List;

public class storeApplication {
	private Store store;
	private TransactionWindow transActionWindow;
	
	public storeApplication(){
		store = new Store();
		transActionWindow = new TransactionWindow(this);
	}
	public void start(){
		transActionWindow.refresh();
		transActionWindow.setVisible(true);
	}
	public TransactionWindow getTransactionWindow(){
				return transActionWindow;
	}
	public void shutdown () {
        System.exit(0);
    }
	public ArrayList<Product> getProducts(){
				return store.getProducts();
	}
	public void addProductsToCart(String productId,int quantity,String memberId){
		store.addProductsToCart( productId, quantity, memberId);
		transActionWindow.refreshCart();
		//refresh cart panel
	}
	
	public ArrayList<Cart> getProductsAddedInCart() {
		// TODO Auto-generated method stub
		return store.getProductsAddedInCart();
	}
	public void beginCheckout(ArrayList<Cart> cart){
				store.beginCheckout(cart);
	}
	
	public String getTransactionTotal() {
		// TODO Auto-generated method stub
		return store.getTransactionTotal();
	}
	public String getDiscount() {
		return store.getDiscount();
	}
	public void makePayment(double amountreceived, double transactiontotal,
			double discountValue, double redeemPointsValue, ArrayList<Cart> cart) {
				store.makePayment(amountreceived,transactiontotal,discountValue,redeemPointsValue,cart);
		// TODO Auto-generated method stub
		
	}
	public void removeSelectedProductinCart(){
				//Product selected = transActionWindow.get
		
	}
	public static void main(String args[]){
		storeApplication manager = new  storeApplication();
		/*Product p = new Product ("book", 128, "1");
		ArrayList<Product> p1=s.addProduct("book", 128, "1");
		//System.out.println(s.getProducts1());
		System.out.println("Product array list"+p1);*/
		manager.start();
		
	}

}
