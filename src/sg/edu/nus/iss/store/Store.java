package sg.edu.nus.iss.store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Store {
	private MemberRegister members;
	private CategoryRegister categories;
	
	public Store() {
		members = new MemberRegister();
		categories = new CategoryRegister();
	}
	
	/*public static void main (String args[]){
		Store store = new Store();
		
		//store.addMember();
		store.addCategory();
		store.initializeData();
		
		store.printAllMembers();
		store.printAllCategories();
	}*/
	
	public void addMember(){
		members.addMember("Sanskar Deepak", "e0013519");
		members.addMember("Srishti", "e0013313");
		members.addMember("Mohan", "e0013313");
		members.writeToFile();
	}
	
	public void addCategory() {
		categories.addCategory("HUM", "Humans");
		categories.addCategory("PER", "Persons");
		categories.addCategory("CLO", "Clothes");
		categories.writeToFile();
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
			reader.close();
		}catch (Exception ex) {
			System.out.println("File not found");
			ex.printStackTrace();		
		}
	}

}
