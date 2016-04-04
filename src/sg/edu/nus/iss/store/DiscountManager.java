package sg.edu.nus.iss.store;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.dao.DiscountDao;
import sg.edu.nus.iss.exceptions.BadValueException;
import static sg.edu.nus.iss.utils.StoreConstants.DATE_FORMAT;

/**
 * DiscountManager class: Manager class to manage Discount Objects.
 * Author: Mohan Karthik
 */
public class DiscountManager {
	private ArrayList<Discount> discounts;
	private Date dNow;
	private DiscountDao discountDao;

	public DiscountManager() {
		discounts = new ArrayList<Discount> ();	
		discountDao = new DiscountDao();
		dNow = new Date(System.currentTimeMillis());
	}

	/**
	 * add member to member list (members)
	 * @param discountCode
	 * @param description
	 * @param percentage
	 * @param startDate
	 * @param discountPeriod
	 * @return boolean to check if member already exists
	 * @throws BadValueException
	 */
	public boolean addDiscount(String discountCode, String description, float percentage, Date startDate, int discountPeriod) throws BadValueException, ParseException {
		if(startDate != null){	
		for(Discount d : discounts){
				if(d.getDiscountCode().equalsIgnoreCase(discountCode)){
					return false;
				}
			}
			DATE_FORMAT.format(startDate);
			discounts.add(new OccasionalDiscount(discountCode, description, startDate, discountPeriod, percentage));
		return true;
	}
		else{
			return false;
		}
	}
	
	public boolean addDiscount(String discountCode, String description, float percentage) throws BadValueException {
		for(Discount d : discounts){
			if(d.getDiscountCode().equalsIgnoreCase(discountCode)){
				return false;
			}
		} 
		discounts.add(new MemberDiscount(discountCode, description, percentage));
		return true;
	}

	/**
	 * Get discount by discount code
	 * @param discountCode
	 * @return Discount 
	 */
	public Discount getDiscount(String discountCode){
		for (Discount d : discounts){
			if(d.getDiscountCode().equalsIgnoreCase(discountCode)){
				return d;
			}
		}
		return null;
	}


	/**
	 * Get maximum discount from the available discounts
	 * @param member
	 * @return maximum discount percentage
	 */
	public double getMaxDiscount(Member member) {
		double oDiscount=0.0;
		double mDiscount=0.0;
		if(member == null) {
			oDiscount = calculateOccasionalDiscount();
		} else {
		mDiscount = calculateMemberDiscount(member);
		oDiscount = calculateOccasionalDiscount();
		}
		if (mDiscount>oDiscount)
			return mDiscount;

		return oDiscount;
	}

	/**
	 * Calculate the discount for members
	 * @param member
	 * @return member discount percentage
	 */
	public double calculateMemberDiscount(Member member) {
		Discount d;
		float value = 0;
		if(member.getLoyaltyPoints()==-1){
			d= getDiscount("MEMBER_FIRST");
			value = (d!=null) ? d.getPercentage() : 0;
			return value;
		}
		d=getDiscount("MEMBER_SUBSEQ");
		return (d!=null) ? d.getPercentage() : 0;
	}

	/**
	 * Get the occasional discount percentage by checking the current date
	 * @return occasional discount percentage
	 */
	public double calculateOccasionalDiscount() {
		double percentage = 0;
		for (Discount d : discounts){
			if (d.getApplicableToMember().equalsIgnoreCase("A")){
				try {
					Date startDate = d.getStartDate();
					DATE_FORMAT.format(startDate);
					Calendar c = Calendar.getInstance();
					c.setTime(startDate);
					c.add(Calendar.DATE, d.getDiscountPeriod());
					String output = DATE_FORMAT.format(c.getTime());
					Date endDate = DATE_FORMAT.parse(output);
					if ((dNow.after(startDate)&&dNow.before(endDate))||dNow.equals(endDate)||dNow.equals(startDate)){
						if(d.getPercentage()>percentage) {
							percentage = d.getPercentage();
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return percentage;

	}

	/**
	 * modify discount percentage
	 * @param discountCode
	 * @param percentage
	 */
	public void modifyDiscount(String discountCode, float percentage) {
		getDiscount(discountCode).setPercentage(percentage);
	}

	public  ArrayList<Discount> getDiscounts() {
		return discounts;
	}
	
	/**
	 * write discount list to file using dao class
	 */
	public void writeToFile() {
		try {
			discountDao.writeToFile(discounts);
		}catch (IOException ex) {
			System.out.println("Cannot Write to file !");
			ex.printStackTrace();
		}
	}
	
	/**
	 * read discounts from file and store it to list using dao class
	 * @throws IOException
	 */
	public void createListFromFile() throws IOException{
		discounts = discountDao.createListFromFile();
	}

	public void removeDiscount(String discountCode) {
		if(discounts!=null){
			for(Discount d : discounts){
				if(discountCode.compareTo(d.getDiscountCode())==0){
					discounts.remove(d);
					break;
				}
			}
		}
	}
	
}
