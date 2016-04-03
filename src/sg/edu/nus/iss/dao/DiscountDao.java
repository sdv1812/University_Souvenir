/**
 * @author Sanskar
 */

package sg.edu.nus.iss.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.Discount;
import sg.edu.nus.iss.store.MemberDiscount;
import sg.edu.nus.iss.store.OccasionalDiscount;
import sg.edu.nus.iss.utils.StoreConstants;

import static sg.edu.nus.iss.utils.StoreConstants.*;

public class DiscountDao extends BaseDao{
	private ArrayList<Discount> discountList;
	
	public  DiscountDao() {

		discountList = new ArrayList<Discount>();
	}
	
	public ArrayList<Discount> createListFromFile() throws IOException{
		ArrayList<String> fileList = new ArrayList<String>();
		fileList = super.readFromFile(StoreConstants.DISCOUNT_PATH);
		Discount discount = null;
		
		if(fileList!=null)
		{
			for(String line: fileList)
			{
				String discountData[] = line.split(",");
				if (discountData[5].equals("M"))
					try {
						discount = new MemberDiscount(discountData[0], discountData[1], Float.parseFloat(discountData[4]));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (BadValueException e1) {
						e1.printStackTrace();
					}
				else if(discountData[5].equals("A"))
					try {
						discount =new OccasionalDiscount(discountData[0], discountData[1],DATE_FORMAT.parse(discountData[2]), Integer.parseInt(discountData[3]),Float.parseFloat(discountData[4]));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (BadValueException e) {
						e.printStackTrace();
					}catch (ParseException e) {
						e.printStackTrace();
					}
				discountList.add(discount);
			}
			
		}
		return discountList;
	
	}
	
	public void writeToFile(ArrayList<Discount> storeList) throws IOException
	{
		ArrayList<StringBuffer> writeDiscount = new ArrayList<>();
		for(Discount c : storeList){
			StringBuffer discountCat = new StringBuffer();
			discountCat.append(c.getDiscountCode()+",");
			discountCat.append(c.getDescription()+",");
			if (c.getStartDate() == null) discountCat.append("ALWAYS,");
			else discountCat.append(DATE_FORMAT.format(c.getStartDate())+",");
			if(c.getDiscountPeriod() == -1) discountCat.append("ALWAYS,");
			else 	discountCat.append(c.getDiscountPeriod()+",");
			discountCat.append(c.getPercentage()+",");
			discountCat.append(c.getApplicableToMember());
			writeDiscount.add(discountCat);
		}
		super.writeToFile(writeDiscount, StoreConstants.DISCOUNT_PATH);
	}
	
}

