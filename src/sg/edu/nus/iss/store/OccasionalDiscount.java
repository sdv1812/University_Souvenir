package sg.edu.nus.iss.store;

public class OccasionalDiscount extends Discount {
		private String startDate;
		private int discountPeriod;
		private String applicableToMember;
		

	public OccasionalDiscount(String discountCode, String description, String startDate, int discountPeriod,
			float percentage) {
		super(discountCode, description, percentage);
		this.startDate = startDate;
		this.discountPeriod = discountPeriod;
		setApplicableToMember("A");
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
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
