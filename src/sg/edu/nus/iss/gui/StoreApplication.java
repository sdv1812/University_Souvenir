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

	public void addMember(String memberName, String memberID) {
		// TODO Auto-generated method stub
		store.addMember(memberName, memberID);
		
	}
}
