package sg.edu.nus.iss.store;
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
	}
	
	public Cart addProductsToCart(String productId,int quantity,String memberId){
		boolean addProductsStatus = false;
		System.out.println("Before Product addition  is ");
		Product product=null;
		try{
			product = productregister.getProductById(productId);
			if(product==null){
				addProductsStatus = false;
				return null;
		}}catch(NullPointerException n){
			//n.printStackTrace();
			return null;
		}
		
		
		System.out.println("Product is "+p1);
		Cart c1 =cart.addCart(p1, quantity, memberId);
		if(c1!=null)
			addProductsStatus = true;
		return c1;
	}

}
