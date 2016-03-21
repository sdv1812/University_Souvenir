package sg.edu.nus.iss.store;

import java.util.ArrayList;
import java.util.Date;

public class DiscountManager {
	ArrayList<Discount> discounts;
	public static final String DATE_FORMAT = "yyyy-mm-dd";
	
	public DiscountManager() {
		discounts = new ArrayList<Discount> ();		
	}
	
	public boolean addDiscount(String discountCode, String description, float percentage, String startDate, int discountPeriod) {
		
		for(Discount d : discounts){
			if(d.getDiscountCode().equalsIgnoreCase(discountCode)){
				return false;
			}
		} 
		if (startDate != null)
		discounts.add(new MemberDiscount(discountCode, description, percentage));
		else
			discounts.add(new OccasionalDiscount(discountCode, description, startDate, discountPeriod, percentage));
		return true;
	}
	
	public float getMaxDiscount() {
		return 0 ;
	}
	

	public float calculateMemberDiscount(float totalPrice) {
		return 0;
	}
	
	public float calculateOccasionalDiscount() {
		return 0;
		
	}
	
	public void modifyMemberDiscount() {
		
	}

}
