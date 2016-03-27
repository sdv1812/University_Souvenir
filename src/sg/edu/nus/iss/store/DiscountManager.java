package sg.edu.nus.iss.store;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import sg.edu.nus.iss.dao.DiscountDao;

/*
 * DiscountManager class: Manager class to manage Discount Objects.
 * Author: Sanskar Deepak
 */
public class DiscountManager {
	private ArrayList<Discount> discounts;
	private Date dNow;
	private SimpleDateFormat ft;
	private DiscountDao discountDao;

	public DiscountManager() {
		discounts = new ArrayList<Discount> ();	
		discountDao = new DiscountDao();
		dNow = new Date(System.currentTimeMillis());
		ft = new SimpleDateFormat("yyyy-MM-dd");
		//ft.format(dNow);
	}

	public boolean addDiscount(String discountCode, String description, float percentage, String startDate, String discountPeriod) {
			for(Discount d : discounts){
				if(d.getDiscountCode().equalsIgnoreCase(discountCode)){
					return false;
				}
			} 
		
		if (startDate == null)
			discounts.add(new MemberDiscount(discountCode, description, percentage));
		else
			discounts.add(new OccasionalDiscount(discountCode, description, startDate, discountPeriod, percentage));
		return true;
	}

	public Discount getDiscount(String discountCode){
		for (Discount d : discounts){
			if(d.getDiscountCode().equalsIgnoreCase(discountCode)){
				return d;
			}
		}
		return null;
	}

	public double getMaxDiscount(double totalPrice) {
		return 0 ;
	}

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

	public double calculateMemberDiscount(Member member) {
		Discount d;
		if(member.getLoyaltyPoints()==-1){
			d= getDiscount("MEMBER_FIRST");
			//discount = totalPrice*(d.getPercentage());
			System.out.println("member first "+d);
			return d.getPercentage();
		}
		d=getDiscount("MEMBER_SUBSEQ");
		//	discount = totalPrice*(d.getPercentage()/100);
		return d.getPercentage();
	}

	public double calculateOccasionalDiscount() {
		double percentage = 0;
		for (Discount d : discounts){
			String s= d.getStartDate();
			System.out.println("start date before loop "+s);
			if (d.getApplicableToMember().equalsIgnoreCase("A")){
				System.out.println("INSIDE OCCASIONAL");
				try {
					Date startDate = ft.parse(s);
					System.out.println("start date after parsing "+startDate);
					Calendar c = Calendar.getInstance();
					c.setTime(startDate);
					c.add(Calendar.DATE, Integer.parseInt(d.getDiscountPeriod()));
					String output = ft.format(c.getTime());
					System.out.println("output date "+output);
					Date endDate = ft.parse(output);
					System.out.println("end date after parsing "+endDate);
					if ((dNow.after(startDate)&&dNow.before(endDate))||dNow.equals(endDate)||dNow.equals(startDate)){
						System.out.println("INSIDE if else");
						if(d.getPercentage()>percentage) {
							
							percentage = d.getPercentage();
							System.out.println("INSIDE percentage" + percentage);
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return percentage;

	}

	public void modifyDiscount(String discountCode, float percentage) {
		getDiscount(discountCode).setPercentage(percentage);
		writeToFile();
	}

	public  ArrayList<Discount> getDiscounts() {
		// TODO Auto-generated method stub
		return discounts;
	}
	
	public void writeToFile() {
		try {
			discountDao.writeToFile(discounts);
		}catch (IOException ex) {
			System.out.println("Cannot Write to file !");
			ex.printStackTrace();
		}
	}
	
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
		writeToFile();
		}
	}
	
}
