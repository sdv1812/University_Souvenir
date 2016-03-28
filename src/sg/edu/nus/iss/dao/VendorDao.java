package sg.edu.nus.iss.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import sg.edu.nus.iss.store.Category;
import sg.edu.nus.iss.store.Vendor;

public class VendorDao extends BaseDao{
	private static final String FILE_NAME = "StoreAppData/";
	private ArrayList<Vendor> vendorList;
	
	public VendorDao()
	{
		vendorList = new ArrayList<Vendor>();
	}
	
	
	public HashMap<Category,ArrayList<Vendor>> readVendorPerCategoryFromFile(ArrayList<Category> categories) throws IOException
	{
		ArrayList<String> vendorListPer = new ArrayList<String>();
		HashMap<Category,ArrayList<Vendor>> vendorMap = new HashMap<>();
		for(Category c: categories) {
			String fileName = "Vendors"+c.getCategoryCode()+".dat";
			vendorListPer = super.readFromFile(FILE_NAME+fileName);
			ArrayList<Vendor> vendorListPerCat = new ArrayList<Vendor>();
		for(String line : vendorListPer)
		{
			String list[] = line.split(",");
			if(list != null){
				vendorListPerCat.add(new Vendor(list[0], list[1]));
				vendorMap.put(c, vendorListPerCat);
				
			}
		}
	}
		return vendorMap;
	}
	
	public ArrayList<Vendor> readVendorFromFile() throws IOException
	{
		ArrayList<String> vendorListPer = new ArrayList<String>();
		vendorListPer = super.readFromFile(FILE_NAME+"Vendors.dat");
		for(String line : vendorListPer)
		{
			String list[] = line.split(",");
			if(list != null){
				vendorList.add(new Vendor(list[0], list[1]));
			}
		}
		return vendorList;
	}

	
	public void writeToFile(HashMap<Category,ArrayList<Vendor>> vendorMap,ArrayList<Vendor> vendors) throws IOException
	{
		ArrayList<StringBuffer> list = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> listVendors = new ArrayList<StringBuffer>();
		for (Category c : vendorMap.keySet()) {
			String fileName = "Vendors"+c.getCategoryCode()+".dat";
				for(Vendor v: vendorMap.get(c)){
					StringBuffer write = new StringBuffer();
					write.append(v.getVendorName()+",");
					write.append(v.getDescription());
					list.add(write);
				}
			super.writeToFile(list, FILE_NAME+fileName);	
			}
		for (Vendor v : vendors) {
				StringBuffer write = new StringBuffer();
				write.append(v.getVendorName()+",");
				write.append(v.getDescription());
				listVendors.add(write);
			}
		super.writeToFile(listVendors, FILE_NAME+"Vendors.dat");	
		}
	}
	
