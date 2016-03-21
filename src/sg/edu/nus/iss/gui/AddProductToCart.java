package sg.edu.nus.iss.gui;
import sg.edu.nus.iss.store.*;

public class AddProductToCart {
	
	storeApplication manager;
	Cart cart = new Cart();
	Product product;
	public AddProductToCart() {
	}
	public AddProductToCart(storeApplication manager) {
		super();
		this.manager = manager;
	}
	
	public boolean addProductsToCart(String productId,int quantity,String memberId){
		boolean addProductsStatus = false;
		Product product = product.getProduct(productId);
		Cart c1 =cart.addCart(product, quantity, memberId);
		if(c1!=null)
			addProductsStatus = true;
		return addProductsStatus;
	}
}
