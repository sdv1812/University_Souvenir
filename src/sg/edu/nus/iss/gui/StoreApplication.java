package sg.edu.nus.iss.gui;



public class StoreApplication {
	private StoreWindow storeWindow;
	public StoreApplication() {
		storeWindow = new StoreWindow("Store Application", this);
		storeWindow.pack ();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreApplication storeApplication = new StoreApplication();
		storeApplication.start();

	}
	
	public void start() {
		
	}
    public StoreWindow getMainWindow() {
        return storeWindow;
    }
	public void refresh() {
		storeWindow.refresh();
	}

}
