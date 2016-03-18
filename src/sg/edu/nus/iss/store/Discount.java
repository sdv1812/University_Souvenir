package sg.edu.nus.iss.univerisity.discount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

//import java.lang.reflect.Member;

public class Discount {
	
	private String discountCode;
	private String description;
	private String startDate;
	private int numberDays;
	private String memberApplicable;
	private float percentage;
	
	public Discount (String discountCode, String description,
					 String startDate, int numberDays, 
					 String memberApplicable, float percentage){
		super();
		this.discountCode=discountCode;
		this.description=description;
		this.startDate=startDate;
		this.numberDays=numberDays;
		this.memberApplicable=memberApplicable;
		this.percentage=percentage;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(int numberDays) {
		this.numberDays = numberDays;
	}

	public String getMemberApplicable() {
		return memberApplicable;
	}

	public void setMemberApplicable(String memberApplicable) {
		this.memberApplicable = memberApplicable;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	//Call SANSKAR method
	//public String checkMember (Member mID) {
	//	if (condition) {
			
	//	}
	//	return member;
		
	//}
	
	//public double memberDiscount (Member mID){
		
	//	return discount;
		
	//}
	private ArrayList<Discount> discount_list;
	private Discount discount;
	discount_list = new ArrayList<Discount> ();
	
	public void addDiscount(String discountCode, String Description, String startDate, int numberDays, String memberApplicable, float percentage) {
	
		discount = new Discount(discountCode, Description, startDate, numberDays, memberApplicable, percentage);
		discount_list.add(discount);
	}
	
	
	public void removeDiscount (String discountCode) {
		for(Discount disc : discount_list){
			if(discountCode.compareTo(disc.getDiscountCode())==0){
				discount_list.remove(disc);
				break;
			}
		}
		writeToDiscountFile();
}

	private void writeToDiscountFile() {
		// TODO Auto-generated method stub
		try {
			String path="Data/Discounts.dat";
            File file = new File(path);
			if (!file.exists()) {
                file.createNewFile();
            }
			BufferedWriter writer = new BufferedWriter (new FileWriter (path));
			for (Discount disc : discount_list) {
				writer.write(disc.getDescription()+","+disc.getDescription()+","+disc.getStartDate()+","+disc.getNumberDays()+","+disc.getPercentage()+","+disc.getMemberApplicable()+"\n");
				}
			writer.close();
		}catch(Exception e){
            System.out.println(e);
        }		
	}
	
}
