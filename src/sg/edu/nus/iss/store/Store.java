package sg.edu.nus.iss.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Store {
	private MemberRegister members;
	private CategoryRegister categories;
	private StoreKeeperRegister storeKeepers;
	
	public Store() {
		members = new MemberRegister();
		categories = new CategoryRegister();
		storeKeepers = new StoreKeeperRegister();
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
	
	public void addCategory() {
		categories.addCategory("HUM", "Humans");
		categories.addCategory("PER", "Persons");
		categories.addCategory("CLO", "Clothes");
		categories.writeToFile();
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
			reader.close();
		}catch (Exception ex) {
			System.out.println("File not found");
			ex.printStackTrace();		
		}
	}

}
