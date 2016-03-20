package sg.edu.nus.iss.store;

public class Product {
	private String productId;
	private Category category;
	private String productName;
	private String description;
	private int quantity;
	private double price;
	private String barcodeNumber;
	private int threshold;
	private int orderQuantity;
	
	public Product(String id,Category category,String name,String description,int quantity,
			double price,String barcodeNumber,int threshold,int orderQuantity){
		this.productId=id;
		this.category=category;
		this.productName=name;
		this.description=description;
		this.quantity=quantity;
		this.price=price;
		this.barcodeNumber=barcodeNumber;
		this.threshold=threshold;
		this.orderQuantity=orderQuantity;
	}
	
	public String getId(){
		return productId;
	}
	
	public Category getCategory(){
		return category;
	}
	
	public String getName(){
		return productName;
	}
	
	public String getDescription(){
		return description;
	}
	public int getQuantity(){
		return quantity;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String getBarcodeNumber(){
		return barcodeNumber;
	}
	
	public int getThreshold(){
		return threshold;
	}
	
	public int getOrderQuantity(){
		return orderQuantity;
	}
	
	public void setId(String id){
		this.productId=id;
	}
	
	public void setCategory(Category category){
		this.category=category;
	}
	
	public void setName(String id){
		this.productId=id;
	}
	
	public void setDescription(String id){
		this.productId=id;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	public void setBarcodeNumber(String barcodeNumber){
		this.barcodeNumber=barcodeNumber;
	}
	
	public void setThrethold(int threthold){
		this.threshold=threthold;
	}
	
	public void setOrderQuantity(int orderQuantity){
		this.orderQuantity=orderQuantity;
	}
	
	public boolean checkBelowThrethold(){
		if(quantity<=threshold){
			return true;
		}else{
			return false;
		}	
	}
	
	public void addQuantity(int addValue){
		quantity+=addValue;
	}
}
