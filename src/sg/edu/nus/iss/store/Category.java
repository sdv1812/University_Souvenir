package sg.edu.nus.iss.store;
import java.io.*;
public class Category {
	private String categoryCode;
	private String categoryName;
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
		categoryCode = (categoryName.substring(0, 2)).toUpperCase();
		createVendorFile();
	}
	
	public Category(String categoryCode, String categoryName){
		this.categoryName = categoryName;
		this.categoryCode = categoryCode.toUpperCase();
		createVendorFile();
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	@SuppressWarnings("resource")
	public void createVendorFile() {
		String fileName = "Vendors"+categoryCode+".dat";
		try {
			new FileWriter("StoreAppData/"+fileName);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
