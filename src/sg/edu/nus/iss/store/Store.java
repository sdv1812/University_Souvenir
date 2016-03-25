package sg.edu.nus.iss.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Store {
	private MemberRegister members;
	private CategoryRegister categories;
	private StoreKeeperRegister storeKeepers;
	private DiscountManager discounts;
	private ProductRegister pr; //xuemin
	private Cart cart;
	private ArrayList<Product> product;
	private AddProductToCart addProductsToCart;
	private Transaction transaction ;
	private ArrayList<Cart> cartList ;
	
	public Store() {
		members = new MemberRegister();
		categories = new CategoryRegister();
		storeKeepers = new StoreKeeperRegister();
		discounts = new DiscountManager();
		pr=new ProductRegister(this); //xuemin
	}
	public ProductRegister getProductReg(){ //xuemin
		return pr;
	}
	
	public ArrayList<Product> getProducts()
	{
		return pr.getProducts();
	}
	
	public CategoryRegister getCategoryReg(){ //xuemin
		return categories;
	}
	/*public void readDataFromFile() throws IOException{ //xuemin
		pr.createListFromFile();
	}*/
	
	public void writeDataToFile() throws IOException{ //xuemin
		pr.writeListToFile();
	}
	
	public void removeProduct(Product p){  //xuemin
		pr.removeProduct(p);
	}
	
	public void removeProduct(String id){  //xuemin
		pr.removeProduct(id);
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
	public void printAllCategories() {
		ArrayList<Category> temp = categories.getCategories();
		for(Category c : temp){
			System.out.println("Category Name : "+c.getCategoryName());
			System.out.println("Category Code : "+c.getCategoryCode()+"\n");
		}
	}
	
	public void printAllMembers() {
		ArrayList<Member> temp = new ArrayList<Member> ();
		temp = members.getMembers();
		for(Member m : temp){
			System.out.println(m.getCustomerName());
			System.out.println(m.getLoyaltyPoints());
			System.out.println(m.getMemberID());
			
		}
	}
	
	public ArrayList<Member> getMembers() {
		return members.getMembers();
	}
	
	@SuppressWarnings("resource")
	public void initializeData(){
		try {
			
			
			BufferedReader reader = new BufferedReader(new FileReader("StoreAppData/Members.dat"));
			String line = null;
			while ((line = reader.readLine())!= null){
				String result[] = line.split(",");
				members.addMember(result[0], result[1], Integer.parseInt(result[2]));
			}
			
			reader = new BufferedReader(new FileReader("StoreAppData/Categories.dat"));
			line = null;
			while ((line = reader.readLine())!= null){
				String result[] = line.split(",");
				categories.addCategory(result[0], result[1]);
			}
			
			reader = new BufferedReader(new FileReader("StoreAppData/StoreKeepers.dat"));
			line = null;
			while ((line = reader.readLine())!= null){
				String result[] = line.split(",");
				storeKeepers.addStoreKeeper(result[0], result[1]);
			}
			
			reader = new BufferedReader(new FileReader("StoreAppData/Discounts.dat"));
			line = null;
			while ((line = reader.readLine())!= null){
				String result[] = line.split(",");
				if (result[5].equals("M"))
				discounts.addDiscount(result[0], result[1], Float.parseFloat(result[4]), "", "");
				else if(result[5].equals("A"))
					discounts.addDiscount(result[0], result[1], Float.parseFloat(result[4]), result[2], result[3]);
			}
			try {
				pr.createListFromFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reader.close();
		}catch (Exception ex) {
			System.out.println("File not found");
			ex.printStackTrace();		
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
	public void beginCheckout(ArrayList<Cart> cartProducts) {
				transaction.addProductsToCart(cartProducts);
		
	}
	public String getTransactionTotal() {
		// TODO Auto-generated method stub
		double transactionTotal = transaction.calculateTransactionTotal();
		String total = Double.toString(transactionTotal);
		return total;
	}
	
	public void makePayment(double amountreceived, double transactiontotal,
			double discountValue, double redeemPointsValue, ArrayList<Cart> cart) {
			transaction.makePayment(amountreceived, transactiontotal, discountValue, redeemPointsValue, cart);
	}
	
	public void removeCartItem(Cart lineItem) {
		cartList.remove(lineItem);
	}
	
	public boolean  addProductsToCart(String productId,int quantity ,String memberId){
		boolean addProductStatus = false;
		System.out.println("Product id is"+productId+"Quantity is "+quantity+"Member id is"+memberId);
		Cart c1 =addProductsToCart.addProductsToCart(productId, quantity, memberId);
		//System.out.println("Product addition is "+c1.toString());
		if(c1!=null){
			cartList.add(c1);
			addProductStatus = true;
		}
		return addProductStatus;
	}


}
