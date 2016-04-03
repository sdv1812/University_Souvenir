package sg.edu.nus.iss.store;

import java.util.Date;

import sg.edu.nus.iss.exceptions.BadValueException;

/**
 * MemberDiscount class: Sub-class of Discount Class.
 * Author: Sanskar Deepak
 */

public class MemberDiscount extends Discount {
	private Date startDate;
	private int discountPeriod;
	String applicableToMember;

	public MemberDiscount(String discountCode, String description,float percentage) throws BadValueException {
		super(discountCode, description, percentage);
		startDate = null;
		discountPeriod = -1;
		applicableToMember = "M";
		}

	public Date getStartDate() {
		return startDate;
	}

	public int getDiscountPeriod() {
		return discountPeriod;
	}

	@Override
	public String getApplicableToMember() {
		return applicableToMember;
	}

}
