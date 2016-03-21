package sg.edu.nus.iss.store;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiscountManager {
	ArrayList<Discount> discounts;
	public static final String DATE_FORMAT = "yyyy-mm-dd";
	private Date dNow;
	private SimpleDateFormat ft;
	
	public DiscountManager() {
		discounts = new ArrayList<Discount> ();		
		dNow = new Date();
		ft = new SimpleDateFormat("yyyy-mm-dd");
		String currentDate = ft.format(dNow);
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
	
	public Discount getDiscount(String discountCode){
		for (Discount d : discounts){
			if(d.getDiscountCode()==discountCode){
				return d;
			}
		}
		return null;
	}
	
	public float getMaxDiscount(float totalPrice, Member member) {
		return 0 ;
	}
	
	public float getMaxDiscount(float totalPrice) {
		return totalPrice;
		
	}
	

	public float calculateMemberDiscount(float totalPrice, Member member) {
		Discount d;
		float discount;
		if(member.getLoyaltyPoints()==-1){
			d= getDiscount("MEMBER_FIRST");
			discount = totalPrice*(d.getPercentage());
			return discount;
		}
		d=getDiscount("MEMBER_SUBSEQ");
		discount = totalPrice*(d.getPercentage()/100);
		return discount;
	}
	
	public float calculateOccasionalDiscount(float totalPrice) {
		for (Discount d : discounts){
			
		}
		
	}
	
	public void modifyMemberDiscount() {
		
	}

}
