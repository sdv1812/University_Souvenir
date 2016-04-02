package sg.edu.nus.iss.store;
import java.io.*;

/*
 * Category class:to to create Category Object
 * Author: Sanskar Deepak
 */

public class Category {
	private String categoryCode;
	private String categoryName;
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
		categoryCode = (categoryName.substring(0, 2)).toUpperCase();
	}
	
	public Category(String categoryCode, String categoryName){
		this.categoryName = categoryName;
		this.categoryCode = categoryCode.toUpperCase();
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

}
