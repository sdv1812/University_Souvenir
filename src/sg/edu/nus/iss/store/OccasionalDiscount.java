package sg.edu.nus.iss.store;

import java.util.Date;

import sg.edu.nus.iss.exceptions.BadValueException;

/**
 * OccasionalDiscount class: Sub-class of Discount class .
 * Author: Sanskar Deepak
 */
public class OccasionalDiscount extends Discount {
		private Date startDate;
		private int discountPeriod;
		private String applicableToMember;
		

	public OccasionalDiscount(String discountCode, String description, Date startDate, int discountPeriod,
			float percentage) throws BadValueException {
		super(discountCode, description, percentage);
		String error = null;
		if (startDate == null) 
			error = "start Date is null";
		if(error != null) 
			throw new BadValueException(error);
		this.startDate = startDate;
		this.discountPeriod = discountPeriod;
		applicableToMember = "A";
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDiscountPeriod() {
		return discountPeriod;
	}

	public void setDiscountPeriod(int discountPeriod) {
		this.discountPeriod = discountPeriod;
	}

	public String getApplicableToMember() {
		return applicableToMember;
	}

	public void setApplicableToMember(String applicableToMember) {
		this.applicableToMember = applicableToMember;
	}


}
