package sg.edu.nus.iss.gui;

import java.util.Date;
import sg.edu.nus.iss.store.*;

public class Discount {
	private String  discountCode;
	private String discountDescription;
	private Date  discountStartDate;
	private int  discountNumberdays;
	private float discountPercentage;
	private String memberApplicable;
	public Discount(String discountCode, String discountDescription,
			Date discountStartDate, int discountNumberdays,
			float discountPercentage, String memberApplicable) {
		super();
		this.discountCode = discountCode;
		this.discountDescription = discountDescription;
		this.discountStartDate = discountStartDate;
		this.discountNumberdays = discountNumberdays;
		this.discountPercentage = discountPercentage;
		this.memberApplicable = memberApplicable;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getDiscountDescription() {
		return discountDescription;
	}
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
	public Date getDiscountStartDate() {
		return discountStartDate;
	}
	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}
	public int getDiscountNumberdays() {
		return discountNumberdays;
	}
	public void setDiscountNumberdays(int discountNumberdays) {
		this.discountNumberdays = discountNumberdays;
	}
	public float getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	public String getMemberApplicable() {
		return memberApplicable;
	}
	public void setMemberApplicable(String memberApplicable) {
		this.memberApplicable = memberApplicable;
	}
	public double memberDiscount(Member m1){
		/* set discount percentage *
		 * First transaction =10%
		 * else assign*/ 
		return discountPercentage;
	}
	/* Discout code -key , value ---list
	 * File write 
	 * return sucess or fail 
	 * */
	public String addDiscount(String discountCode,String discountDescription,Date discountStartDate,int discountNumberdays,float discountPercentage,String memberApplicable){
		
		return "";
	}
	/* File retrive hashmap - Remive discocunt code */
	public String removeDiscount(String discountCode){
		
			return "";
	}
	public String modifyDiscount(Discount discount){
		
				return "";
	}
	public boolean IsSpecialOccasionDiscountAppplicable(){
		
			return true;
	}
	
}
