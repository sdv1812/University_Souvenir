package sg.edu.nus.iss.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CategoryRegister {
	private ArrayList<Category> categories;
	private Category category;
	
	public CategoryRegister() {
		categories = new ArrayList<Category> ();
	}
	
	public boolean addCategory(String categoryCode, String categoryName) {
		for(Category c : categories){
			if(c.getCategoryCode().equalsIgnoreCase(categoryCode)){
				return false;
			}
		}
		category = new Category(categoryCode, categoryName);
		categories.add(category);
		return true;
	}
	
	public void removeCategory(String categoryCode) {   // Removing by selecting category code
		if(categories!=null){
			for(Category c : categories){
				if(categoryCode.compareTo(c.getCategoryCode())==0){
					categories.remove(c);
					break;
				}
			}
		writeToFile();
		}
	}
	
	public Category getCategory(String categoryCode) {
		if(categories!=null){
			
			for(Category c : categories){
				if (categoryCode.compareTo(c.getCategoryCode())==0){
					return c;
				}
			}
		}
		return null;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	public boolean isCategoryCodePresent(String categoryCode) {// to check if category code already exists
		if(categories!=null){
			for(Category c : categories){
				if (categoryCode.compareTo(c.getCategoryCode())==0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCategoryPresent(String categoryName) {
		if(categories!=null){
			for(Category c : categories){
				if (categoryName.compareTo(c.getCategoryName())==0){
					return true;
				}
			}
		}
		return false;
	}
	
	public void writeToFile() {
		try {
			BufferedWriter writer = new BufferedWriter (new FileWriter ("StoreAppData/Categories.dat"));
			for(Category c : categories){
				writer.write(c.getCategoryCode()+",");
				writer.write(c.getCategoryName()+"\n");
			}
			writer.close();
		}catch (IOException ex) {
			System.out.println("Cannot Write to file !");
			ex.printStackTrace();
		}
	}
}
