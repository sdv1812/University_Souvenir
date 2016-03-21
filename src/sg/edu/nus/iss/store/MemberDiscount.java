package sg.edu.nus.iss.store;

public class MemberDiscount extends Discount {
	private String startDate;
	private String discountPeriod;
	String applicableToMember;

	public MemberDiscount(String discountCode, String description,float percentage) {
		super(discountCode, description, percentage);
		startDate = "ALWAYS";
		discountPeriod = "ALWAYS";
		applicableToMember = "M";
		}

	public String getStartDate() {
		return startDate;
	}

	public String getDiscountPeriod() {
		return discountPeriod;
	}

	@Override
	public String getApplicableToMember() {
		// TODO Auto-generated method stub
		return applicableToMember;
	}

}
