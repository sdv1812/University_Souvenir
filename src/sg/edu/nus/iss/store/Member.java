package sg.edu.nus.iss.store;

public class Member extends Customer{
	private String memberID;
	private int loyaltyPoints;
	
	public Member(String customerName, String memberID) {
		super(customerName);
		this.memberID = memberID;
		loyaltyPoints = -1;
	}
	public Member(String customerName, String memberID, int loyaltyPoints) {
		super(customerName);
		this.memberID = memberID;
		this.loyaltyPoints = loyaltyPoints;
	}
	
	
	public String getMemberID() {
		return memberID;
	}
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
<<<<<<< HEAD

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
=======
	public void updateLoyaltyPoints(int points){
		if (isFirstTime())
		loyaltyPoints = points;
		else loyaltyPoints += points;
>>>>>>> 485d676e8e9f90b8a3190fc74b9fd398933e9c37
	}
	public boolean isFirstTime(){
		if(loyaltyPoints < 0)
		return true;
		return false;
	}
	

}
