package sg.edu.nus.iss.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Store {
	private MemberRegister members;
	private CategoryRegister categories;
	private StoreKeeperRegister storeKeepers;
	private DiscountManager discounts;
	
	public Store() {
		members = new MemberRegister();
		categories = new CategoryRegister();
		storeKeepers = new StoreKeeperRegister();
		discounts = new DiscountManager();
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
	
	/*public Discount getDiscount(String discountCode) {
		return discounts.getDiscount(discountCode);
	}*/
	
	public void modifyDiscount(String discountCode, float percentage) {
		discounts.modifyDiscount(discountCode, percentage);
		
	}

}
