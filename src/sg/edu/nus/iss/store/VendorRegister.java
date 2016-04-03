package sg.edu.nus.iss.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import sg.edu.nus.iss.dao.VendorDao;
import sg.edu.nus.iss.exceptions.BadValueException;
/**
 * 
 * @author Sanskar Deepak
 *
 */
public class VendorRegister {

	private HashMap<Category, ArrayList<Vendor>> vendorMap;
	private ArrayList<Vendor> vendors;
	private VendorDao vDao;

	public VendorRegister() {
		vendorMap = new HashMap<Category, ArrayList<Vendor>>(); // to map category with vendor list
		vendors = new ArrayList<Vendor>();
		vDao = new VendorDao();
	}

	/**
	 * @param vendorName
	 * @param vendorDescription
	 * @param category
	 * @return boolean to check for duplicity while adding Vendor to vendor list.
	 * @throws BadValueException
	 */
	public boolean addVendor(String vendorName, String vendorDescription, Category category) throws BadValueException {
		ArrayList<Vendor> temp = vendorMap.get(category);
		int count = 0;
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
		if(!vendors.isEmpty()) {
			for(Vendor v : vendors) {
				if(vendorName.equals(v.getVendorName())) 
					count++;
			}
		} 
		if(count ==0)
		vendors.add(vendorNew);
		writeToFile();
		return true;
	}

	/**
	 * Remove Vendor by Vendor Name
	 * @param vendorName
	 */
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
	
	/** 
	 * @return List of Vendors
	 */
	public ArrayList<Vendor> getVendors() {
		return vendors;
	}

	/**
	 * 
	 * @param category
	 * @return list of Vendors for each category
	 */
	public ArrayList<Vendor> getVendorsPerCategory(Category category) {
		return vendorMap.get(category);
	}

	/**
	 * @param vendorName
	 * @return vendor for given vendor name (null if not present)
	 */
	public Vendor getVendor(String vendorName) {
		for(Vendor v : vendors) {
			if (vendorName.equalsIgnoreCase(v.getVendorName())) {
				return v;
			}
		}
		return null;
	}

	/**
	 * read vendors per category from file
	 * @param categories
	 * @throws IOException
	 */
	public void readVendorPerCategoryFromFile(ArrayList<Category> categories) throws IOException{

		vendorMap = vDao.readVendorPerCategoryFromFile(categories);	
	}

	/**
	 * Read vendors from file
	 * @throws IOException
	 */
	public void readVendorFromFile() throws IOException{
		vendors = vDao.readVendorFromFile();
	}

	/**
	 * write all vendors to files using Dao classes
	 */
	public void writeToFile() {
		try{
			vDao.writeToFile(vendorMap, vendors);
		}catch(IOException e){
			System.out.println("File not found!");
			e.printStackTrace();

		}

	}

}