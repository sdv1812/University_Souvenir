/**
 *  @author Sanskar Deepak
 */

package sg.edu.nus.iss.dao;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.Member;
import sg.edu.nus.iss.utils.StoreConstants;

public class CustomerDao extends BaseDao{
	private ArrayList<String> customerList;
	private ArrayList<Member> memberList;
	
	public CustomerDao()
	{
		customerList = new ArrayList<String>();
		memberList = new ArrayList<Member>();
	}
	
	
	public ArrayList<Member> readFromFile() throws IOException
	{
		customerList = super.readFromFile(StoreConstants.MEMBER_PATH);
		for(String line : customerList)
		{
			String list[] = line.split(",");
			if(list != null){
			try {
				memberList.add(new Member(list[0], list[1], Integer.parseInt(list[2])));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (BadValueException e) {
				e.printStackTrace();
			}
			}
		}
		return memberList;
	}

	
	public void writeToFile(ArrayList<Member> memberList) throws IOException
	{
		ArrayList<StringBuffer> list = new ArrayList<StringBuffer>();
		for(Member m : memberList){
			StringBuffer line = new StringBuffer() ;
			line.append(m.getCustomerName());
			line.append(",");
			line.append(m.getMemberID());
			line.append(",");
			line.append(m.getLoyaltyPoints());
			list.add(line);
		}	
		super.writeToFile(list, StoreConstants.MEMBER_PATH);
	}
	
}

