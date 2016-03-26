package sg.edu.nus.iss.store;
import java.io.*;
import java.util.ArrayList;

import sg.edu.nus.iss.dao.CustomerDao;

/*
 * MemberRegister class: Manager class to manage Member objects.
 * Author: Sanskar Deepak
 */

public class MemberRegister {
	private ArrayList<Member> members;
	private Member member;
	private CustomerDao custDao;
	
	public MemberRegister() {
		members = new ArrayList<Member> ();
		custDao = new CustomerDao();
	}
	public boolean addMember(String customerName, String memberID){
		for(Member m: members){
			if(m.getMemberID().equals(memberID)){
				return false;
			}
		}
		member = new Member(customerName, memberID);
		members.add(member);
		return true;
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
	
	
	public void createListFromFile() throws IOException
	{
		members = custDao.readFromFile();
	}
	
	public void writeToFile() {
		try {
			 custDao.writeToFile(members);
		}catch(IOException exception)
		{
			System.out.println("Cannot write to file");
		}
			
	}
	public void updateRedeemPoints(String memberId, double redeemPoints, double bonusPoints) {
		// TODO Auto-generated method stub
		
	}

}
