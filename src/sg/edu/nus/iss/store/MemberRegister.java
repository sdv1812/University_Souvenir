package sg.edu.nus.iss.store;
import java.io.*;
import java.util.ArrayList;


public class MemberRegister {
	private ArrayList<Member> members;
	private Member member;
	
	public MemberRegister() {
		members = new ArrayList<Member> ();
	}
	public void addMember(String customerName, String memberID){
		member = new Member(customerName, memberID);
		members.add(member);
	}
	
	public void addMember(String customerName, String memberID, int loyaltyPoints){
		members.add(new Member(customerName, memberID, loyaltyPoints));
	}
	
	public void removeMember(String memberID) {    //Removing by selecting memberID
		for(Member m : members){
			if(memberID.compareTo(m.getMemberID())==0){
				members.remove(m);
				break;
			}
		}
		writeToFile();
	}
	
	public ArrayList<Member> getMembers() {
		return members;
	}
	
	public Member getMember(String memberID) {   
		for(Member m : members){
			if (memberID.compareTo(m.getMemberID())==0){
				return m;
			}
		}
		return null;
	}
	
	public boolean isMemberPresent(String idx) {
			for(Member m : members){
				if (idx.compareTo(m.getMemberID())==0){
					return true;
				}
			}
		return false;
	}
	
	public void writeToFile() {
		try {
			BufferedWriter writer = new BufferedWriter (new FileWriter ("StoreAppData/Members.dat"));
			for(Member m : members){
				writer.write(m.getCustomerName()+",");
				writer.write(m.getMemberID()+",");
				writer.write(m.getLoyaltyPoints()+"\n");
			}
			writer.close();
		}catch (IOException ex) {
			System.out.println("Cannot Write to file !");
			ex.printStackTrace();
		}
	}

}
