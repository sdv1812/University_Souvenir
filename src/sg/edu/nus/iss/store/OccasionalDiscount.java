package sg.edu.nus.iss.store;

/*
 * OccasionalDiscount class: Sub-class of Discount class .
 * Author: Sanskar Deepak
 */
public class OccasionalDiscount extends Discount {
		private String startDate;
		private String discountPeriod;
		private String applicableToMember;
		

	public OccasionalDiscount(String discountCode, String description, String startDate, String discountPeriod,
			float percentage) {
		super(discountCode, description, percentage);
		this.startDate = startDate;
		this.discountPeriod = discountPeriod;
		applicableToMember = "A";
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDiscountPeriod() {
		return discountPeriod;
	}

	public void setDiscountPeriod(String discountPeriod) {
		this.discountPeriod = discountPeriod;
	}

	public String getApplicableToMember() {
		return applicableToMember;
	}

	public void setApplicableToMember(String applicableToMember) {
		this.applicableToMember = applicableToMember;
	}


}
