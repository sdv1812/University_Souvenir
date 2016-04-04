package sg.edu.nus.iss.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Sanskar Deepak
 *
 */
public final class StoreConstants {

	public static final String DIRECTORY_PATH = "data/";
	public static final String VENDOR_PATH = DIRECTORY_PATH + "Vendors.dat";
	public static final String STOREKEEPER_PATH = DIRECTORY_PATH +"StoreKeepers.dat";
	public static final String MEMBER_PATH = DIRECTORY_PATH +"Members.dat";
	public static final String CATEGORY_PATH = DIRECTORY_PATH +"Category.dat";
	public static final String PRODUCT_PATH = DIRECTORY_PATH +"Products.dat";
	public static final String DISCOUNT_PATH = DIRECTORY_PATH +"Discounts.dat";
	public static final String TRANSACTION_PATH = DIRECTORY_PATH+"Transactions.dat";
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
	
	
}
