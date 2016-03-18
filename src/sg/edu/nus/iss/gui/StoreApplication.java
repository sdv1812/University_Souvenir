package sg.edu.nus.iss.gui;
import java.util.ArrayList;

import sg.edu.nus.iss.store.*;
public class StoreApplication {
	private StoreWindow storeWindow;
	private Store store;
	public StoreApplication() {
		storeWindow = new StoreWindow("Store Application", this);
		storeWindow.pack ();
		store = new Store();
		store.initializeData();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreApplication storeApplication = new StoreApplication();
		//storeApplication.start();

	}
	
	public ArrayList<Member> getMembers() {
		store.printAllMembers();
		return store.getMembers();
	}
    public StoreWindow getMainWindow() {
        return storeWindow;
    }
	public MainPanel createMainPanel() {
		return(storeWindow.createMainPanel());
	}
}
