package sg.edu.nus.iss.store;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import sg.edu.nus.iss.dao.CategoryDao;
import sg.edu.nus.iss.exceptions.BadValueException;

/**
 * CategoryRegister class:Manager class to manage Category Object
 * Author: Sanskar Deepak
 */

public class CategoryRegister {
	private ArrayList<Category> categories;
	private Category category;
	private CategoryDao catDao;
	private static final String[] COLUMN_NAMES = {"Code", "Category Name"};
	private AbstractTableModel categoryTableModel;
	
	public CategoryRegister() {
		categories = new ArrayList<Category> ();
		catDao = new CategoryDao();
	}
	
	/**
	 * Add new category
	 * @param categoryCode
	 * @param categoryName
	 * @return boolean to check if category already exists
	 * @throws BadValueException
	 */
	public boolean addCategory(String categoryCode, String categoryName) throws BadValueException {
		for(Category c : categories){
			if(c.getCategoryCode().equalsIgnoreCase(categoryCode)){
				return false;
			}
		}
		category = new Category(categoryCode, categoryName);
		categories.add(category);
		return true;
	}
	
	/**
	 * Remove category by category code
	 * @param categoryCode
	 */
	public void removeCategory(String categoryCode) {   // Removing by selecting category code
		if(categories!=null){
			for(Category c : categories){
				if(categoryCode.compareTo(c.getCategoryCode())==0){
					categories.remove(c);
					break;
				}
			}
		}
	}
	
	/**
	 * Get category by  category code
	 * @param categoryCode
	 * @return category
	 */
	public Category getCategory(String categoryCode) {
		if(categories!=null){
			for(Category c : categories){
				if (categoryCode.equalsIgnoreCase(c.getCategoryCode())){
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get category by category by name
	 * @param categoryName
	 * @return category
	 */
	public Category getCategorybyName(String categoryName) {
		for(Category c :categories) {
			if (c.getCategoryName().equals(categoryName)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * @return List of category
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	/**
	 * Read from file and save it to category list	
	 * @throws IOException
	 */
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
	
	/**
	 * creates an abstract table model for table (returns data for table in GUI)
	 * @return Abstract table  model
	 */
	public AbstractTableModel getCategoryTableModel() {
		if (categoryTableModel != null) 
			return categoryTableModel;
		else {
			categoryTableModel = new AbstractTableModel() {

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
					return categories.size();
				}

				@Override
				public int getColumnCount() {
					return COLUMN_NAMES.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					Category category = categories.get(rowIndex);
					switch (columnIndex) {
					case 0: return category.getCategoryCode();
					case 1: return category.getCategoryName();
					default: return null;
					}
				}
			};

			return categoryTableModel;
		}
	}
}
