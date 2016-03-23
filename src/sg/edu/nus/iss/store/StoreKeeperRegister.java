package sg.edu.nus.iss.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//**** Created by T. Surenthiran  *****

public class StoreKeeperRegister
{
	private ArrayList<StoreKeeper> storeKeepers =new ArrayList<StoreKeeper>();
	
	public void addStoreKeeper(String storeKeeperName, String password)
	{
		StoreKeeper storeKeeper= new StoreKeeper(storeKeeperName, password);
		storeKeepers.add(storeKeeper);
	}
	
	public StoreKeeper getStoreKeeper(String storeKeeperName)
	{
		for(StoreKeeper sk: storeKeepers)
		{
			if(storeKeeperName.equals(sk.getStoreKeeperName()))
				System.out.println(storeKeeperName);
				return sk; 
		}
	
		return null;
	}
	
	public boolean validate(String storeKeeperName,String password)
	{
		for (StoreKeeper sk: storeKeepers){
				if((storeKeeperName.compareTo(sk.getStoreKeeperName())==0)&&(password.compareTo(sk.getPassword())==0)) {
				return true;
				}
		}
		return false;
	}
	
	public void writeToFile() {
		try {
			BufferedWriter writer = new BufferedWriter (new FileWriter ("StoreAppData/StoreKeepers.dat"));
			for(StoreKeeper sk : storeKeepers){
				writer.write(sk.getStoreKeeperName()+",");
				writer.write(sk.getPassword()+"\n");
			}
			writer.close();
		}catch (IOException ex) {
			System.out.println("Cannot Write to file !");
			ex.printStackTrace();
		}
	}
	
}