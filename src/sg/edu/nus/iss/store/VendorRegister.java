package sg.edu.nus.iss.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.dao.VendorDao;

public class VendorRegister {

	private HashMap<Category, ArrayList<Vendor>> vendorMap;
	private ArrayList<Vendor> vendors;
	private VendorDao vDao;
	private static final String[] COLUMN_NAMES = {"Vendor Name", "Description"};
	private AbstractTableModel vendorTableModel;

	public VendorRegister() {
		vendorMap = new HashMap<Category, ArrayList<Vendor>>();
		vendors = new ArrayList<Vendor>();
		vDao = new VendorDao();
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
		if(vendors == null) { // First Entry
			vendors.add(vendorNew);
			System.out.println("Inside first Entry   " + vendorNew.getVendorName());
		} else {
			for(Vendor v : vendors) {
				System.out.println("Inside for loop   " + vendorNew.getVendorName());

				if(vendorName.equals(v.getVendorName())) 
					break;
				else	vendors.add(vendorNew);
			}
		}
		writeToFile();
		return true;
	}

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

	public void readVendorPerCategoryFromFile(ArrayList<Category> categories) throws IOException{

		vendorMap = vDao.readVendorPerCategoryFromFile(categories);	
	}

	public void readVendorFromFile() throws IOException{
		vendors = vDao.readVendorFromFile();
	}

	public void writeToFile() {
		try{
			vDao.writeToFile(vendorMap, vendors);
		}catch(IOException e){
			System.out.println("File not found!");
			e.printStackTrace();

		}

	}
	

	public AbstractTableModel getVendorTableModel() {
		if (vendorTableModel != null) 
			return vendorTableModel;
		else {
			vendorTableModel = new AbstractTableModel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String getColumnName(int column) {
					return COLUMN_NAMES[column];
				}

				@Override
				public int getRowCount() {
					return vendors.size();
				}

				@Override
				public int getColumnCount() {
					return COLUMN_NAMES.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					Vendor vendor = vendors.get(rowIndex);
					switch (columnIndex) {
					case 0: return vendor.getVendorName();
					case 1: return vendor.getDescription();
					default: return null;
					}
				}
			};

			return vendorTableModel;
		}
	}


}