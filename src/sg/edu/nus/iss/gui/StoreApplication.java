package sg.edu.nus.iss.gui;
import java.util.ArrayList;

import sg.edu.nus.iss.store.*;
public class StoreApplication {
	private StoreWindow storeWindow;
	private Store store;
	public StoreApplication() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreApplication storeApplication = new StoreApplication();
		storeApplication.start();

	}
	
	private void start() {
		// TODO Auto-generated method stub
		storeWindow = new StoreWindow("Store Application", this);
		storeWindow.pack ();
		store = new Store();
		store.initializeData();
		//storeWindow.refresh ();
	}
	
	public boolean validate(String storeKeeperName, String password){
		return store.validate(storeKeeperName, password);
	}

	public ArrayList<Member> getMembers() {
		return store.getMembers();
	}
    public StoreWindow getMainWindow() {
        return storeWindow;
    }
	public MainPanel createMainPanel() {
		return(storeWindow.createMainPanel());
	}

	public boolean addMember(String memberName, String memberID) {
		// TODO Auto-generated method stub
		return store.addMember(memberName, memberID);
	}
	
	public boolean addCategory(String categoryCode, String categoryName) {
		return store.addCategory(categoryCode, categoryName);
	}
	
	public ArrayList<Category> getCategories() {
		return store.getCategories();
	}
	
	public void removeMember(String memberID) {
		store.removeMember(memberID);
	}

	public void removeCategory(String categoryCode) {
		// TODO Auto-generated method stub
		store.removeCategory(categoryCode);
	}
	
	public ArrayList<Discount> getDiscounts(){
		return store.getDiscounts();
	}
	
	public boolean addDiscount(String discountCode, String description, float percentage, String startDate, String discountPeriod) {
		return store.addDiscount(discountCode, description, percentage, startDate, discountPeriod);
	}

	public void removeDiscount(String discountCode) {
		store.removeDiscount(discountCode);		
	}

}
