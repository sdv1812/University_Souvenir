package sg.edu.nus.iss.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.dao.CategoryDao;

/*
 * CategoryRegister class:Manager class to manage Category Object
 * Author: Sanskar Deepak
 */

public class CategoryRegister {
	private ArrayList<Category> categories;
	private Category category;
	private CategoryDao catDao;
	
	public CategoryRegister() {
		categories = new ArrayList<Category> ();
		catDao = new CategoryDao();
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
				System.out.println("catgeorcode"+categoryCode);
				System.out.println("list catego code"+c.getCategoryCode());
				if (categoryCode.equalsIgnoreCase(c.getCategoryCode())){
					return c;
				}
			}
		}
		return null;
	}
	
	public Category getCategorybyName(String categoryName) {
		for(Category c :categories) {
			if (c.getCategoryName().equals(categoryName)) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	
	public void createListFromFile() throws IOException
	{
		categories = catDao.createListFromFile();
	}
	
	public void writeToFile() {
		try {
			catDao.writeToFile(categories);
		}catch (IOException ex) {
			System.out.println("Cannot Write to file !");
			ex.printStackTrace();
		}
	}
}
