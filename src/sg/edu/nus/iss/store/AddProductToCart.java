package sg.edu.nus.iss.store;
import javax.swing.JOptionPane;

import sg.edu.nus.iss.gui.*;


public class AddProductToCart {
	
	StoreApplication manager;
	Store store;
	Cart cart = new Cart();
	ProductRegister productregister;
	Category c1 = new Category("CLO ","CLOTHING");
	Product p1 = new Product("CLO/1",c1,"Centenary Jumper","A really nice momento",247,
			21.45,"1234",10,100);
			
	public AddProductToCart() {
	}
	public AddProductToCart(Store store) {
		super();
		this.store = store;
		productregister = new ProductRegister();
	}
	
	public Cart addProductsToCart(Product product,int quantity,Member member){
		boolean addProductsStatus = false;
		/*try{
			product = productregister.getProductById(productId);
			if(product==null){
				addProductsStatus = false;
				return null;
		}}catch(NullPointerException n){
			//n.printStackTrace();
			return null;
		}
		*/
		if(product==null){
			return null;
		}
		else if(product.getQuantityAvailable()<quantity){
			JOptionPane.showMessageDialog(null, "Insuffiecient products", "Insuffiecient products", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else if(product.getQuantityAvailable()<product.getThreshold()){
			JOptionPane.showMessageDialog(null, "Product is Running out of stock", "Product shortage ", JOptionPane.ERROR_MESSAGE);
			Cart c1 =cart.addCart(product, quantity, member);
			if(c1!=null)
				addProductsStatus = true;
			return c1;
		}
		else{
			Cart c1 =cart.addCart(product, quantity, member);
			if(c1!=null)
				addProductsStatus = true;
			return c1;
		}
		
	}

}
