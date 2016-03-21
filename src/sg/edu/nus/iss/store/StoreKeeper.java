package sg.edu.nus.iss.store;

//**** Created by T. Surenthiran  *****
public class StoreKeeper {
	
	private String storeKeeperName;
	private String password;
	
		public StoreKeeper(String storeKeeperName, String password)
		{
			this.storeKeeperName = storeKeeperName;
			this.password = password;
		}

		public String getStoreKeeperName() {
			return storeKeeperName;
		}

		public String getPassword() {
			return password;
		}

		public String toString()
		{
			return this.storeKeeperName + " " + this.password;
		}

}
