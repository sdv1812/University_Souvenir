package sg.edu.nus.iss.store;

import iss.tranasction.pos.AddProductToCart;
import iss.tranasction.pos.Cart;
import iss.tranasction.pos.Product;
import iss.tranasction.pos.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Store {
	private Cart cart;
	private ArrayList<Product> product;
	private AddProductToCart addProductsToCart;
	private Transaction transaction ;
	
	public Store(){
			cart = new Cart();
			product= new ArrayList<Product>();
			//addProductsToCart = new AddProductToCart();
			transaction = new Transaction();
	}
	public ArrayList<Product> getProducts(){
		//ArrayList<Product> result;
        //result = new ArrayList<Product>(product);
		/*Product p1 = new Product("Koushik",10,"12");
		Product p2 = new Product("book",12,"14");
		product.add(p1);
		product.add(p2);*/
        System.out.println("result is "+product.toString());
        return (product);
	}
	
	/*public ArrayList<Product> getProducts1(){
		//ArrayList<Product> result;
       // result = new ArrayList<Product>(product);
        System.out.println("result is !!!!!!!!!!!!!!!!!!!!!!!"+product);
        return (product);
	}*/
	public ArrayList<Product> addProduct(String productName, double price, String productId){
			Product p = new Product(productName,price,productId);
			product.add(p);
			System.out.println("array list is "+product.toString());
			return product;
	}
	public void addProductsToCart(String productId,int quantity,String memberId){
		boolean productStatus =addProductsToCart.addProductsToCart(productId, quantity, memberId);
		System.out.println("Product addition is "+productStatus);
	}
	public ArrayList<Cart> getProductsAddedInCart() {
		// TODO Auto-generated method stub
		return cart.getCart();
	}
	public void beginCheckout(ArrayList<Cart> cartProducts) {
				transaction.addProductsToCart(cartProducts);
		
	}
	public String getTransactionTotal() {
		// TODO Auto-generated method stub
		double transactionTotal = transaction.calculateTransactionTotal();
		String total = Double.toString(transactionTotal);
		return total;
	}
	public String getDiscount() {
		double discountValue = transaction.getDiscountPercentage();
		String discount = Double.toString(discountValue);
		return discount;
	}
	public void makePayment(double amountreceived, double transactiontotal,
			double discountValue, double redeemPointsValue, ArrayList<Cart> cart) {
			transaction.makePayment(amountreceived, transactiontotal, discountValue, redeemPointsValue, cart);
	}
}
