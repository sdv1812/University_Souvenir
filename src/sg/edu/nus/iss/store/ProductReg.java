package sg.nus.iss.ft6.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductReg {
	private HashMap<Category, ArrayList<Product>> products;
	
	public ProductReg(){
		products=new HashMap<>();
	}
	
	public void addProduct(String id,Category category,String name,String description,int quantity,
			double price,String barcodeNumber,int threshold,int orderQuantity){
		Product newProduct=new Product(id, category, name, description, quantity, price, barcodeNumber, threshold, orderQuantity);
		ArrayList<Product> productOfCategory=products.get(category);
		productOfCategory.add(newProduct);
		
	}
	
	public HashMap<Category, ArrayList<Product>> getProducts(){
		return products;
	}
	
	public ArrayList<Product> getProductsByCategory(Category category){
		 return products.get(category);
	}
	
	
}
