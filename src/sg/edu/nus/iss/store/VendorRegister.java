package sg.edu.nus.iss.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VendorRegister {

	private HashMap<Category, ArrayList<Vendor>> vendorMap;
	private ArrayList<Vendor> vendors;

	public VendorRegister() {
		vendorMap = new HashMap<Category, ArrayList<Vendor>>();
		vendors = new ArrayList<Vendor>();
	}

	public boolean addVendor(String vendorName, String vendorDescription, Category category) {
		ArrayList<Vendor> temp = vendorMap.get(category);
		if (temp == null) { //new entry
			temp = new ArrayList<Vendor>();
		} else {
			for (Vendor v : temp) {
				if (vendorName.equals(v.getVendorName())) {
					return false;
				}
			}
		}
		Vendor vendorNew = new  Vendor(vendorName,vendorDescription);
		temp.add(vendorNew);
		vendorMap.put(category, temp);
		for(Vendor v : vendors) {
			if(vendorName.equals(v.getVendorName())) 
				break;
		else	vendors.add(vendorNew);
		}
		writeToFile();
		return true;
	}

/*	public boolean addVendor(String vendorName, String vendorDescription) {
		for(Vendor v : vendors) {
			if(vendorName.equals(v.getVendorName())) 
				return false; 
		}
		vendors.add(new Vendor(vendorName, vendorDescription));
		writeToFile();
		return true;
	}*/

	//Remove Vendor by Vendor Name
	public void removeVendor(String vendorName) {
		ArrayList<Vendor> temp;
		if(vendorMap!=null) {
			for(Category c : vendorMap.keySet()) {
				temp = vendorMap.get(c);
				for (Vendor v : vendorMap.get(c)) {
					if(vendorName.equals(v.getVendorName())) {
						temp.remove(v);
						vendorMap.put(c,temp);
						break;
					}
				}
			}
			for(Vendor v : vendors) {
				if(vendorName.equals(v.getVendorName())) {
					vendors.remove(v);
					break;
				}
			}
		}
		writeToFile();
	}

	public ArrayList<Vendor> getVendors() {
		return vendors;
	}

	public ArrayList<Vendor> getVendorsPerCategory(Category category) {
		return vendorMap.get(category);
	}

	public void writeToFile() {
		System.out.println(vendorMap.keySet());
		for (Category c : vendorMap.keySet()) {
			String fileName = "Vendors"+c.getCategoryCode()+".dat";
			try {
				BufferedWriter writer = new BufferedWriter (new FileWriter ("StoreAppData/"+fileName));
				for(Vendor v: vendorMap.get(c)){
					writer.write(v.getVendorName()+","+v.getDescription()+"\n");
				}
				writer.close();
			}catch (IOException ex) {
				System.out.println("Cannot Write to file !");
				ex.printStackTrace();
			}
		}
		for (Vendor v : vendors) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter ("StoreAppData/Vendors.dat"));
				writer.write(v.getVendorName()+","+v.getDescription()+"\n");
				writer.close();
			}catch (IOException ex) {
				System.out.println("Cannot Write to file !");
				ex.printStackTrace();
			}
		}

	}

}