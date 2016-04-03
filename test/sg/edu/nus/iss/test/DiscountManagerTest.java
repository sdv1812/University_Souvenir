package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.Discount;
import sg.edu.nus.iss.store.DiscountManager;
import sg.edu.nus.iss.store.Member;

/**
 * 
 * @author Mohan
 *
 */

public class DiscountManagerTest {
	DiscountManager discountManager;
	Discount discount;
	Member member;
	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setUp() {
		discountManager = new DiscountManager();
	}
	
	@After
	public void tearDown(){
	 discountManager = null;	
	}

	@Test
	public void testAddDiscount() throws BadValueException {
	
		try {
		assertTrue(discountManager.addDiscount("test1", "test2", 20, sf.parse("2016-04-05"), 30));
		assertTrue(discountManager.addDiscount("test2", "test2", 20, sf.parse("2016-04-05"), 30));
		assertFalse(discountManager.addDiscount("test3", "test2", 20, null, 0));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("Parsing error");
		}
	}

	@Test
	public void testGetDiscount() throws BadValueException {
		try {
			assertTrue(discountManager.addDiscount("test1", "test2", 20, sf.parse("2016-04-05"), 30));
			assertTrue(discountManager.addDiscount("test2", "test2", 20));
			} catch (ParseException | BadValueException e){
				e.printStackTrace();
				fail("Parsing error");
			}
		discount = discountManager.getDiscount("test1");
		assertEquals(discount.getDiscountCode(), "test1");
	}

	@Test
	public void testGetMaxDiscountMember() throws BadValueException {
		try {
			assertTrue(discountManager.addDiscount("test1", "test2", 20, sf.parse("2016-04-04"), 30));
			assertTrue(discountManager.addDiscount("test2", "test2", 20));
			} catch (ParseException | BadValueException e) {
				e.printStackTrace();
				fail("Parsing error");
			}
		double result = discountManager.getMaxDiscount(null);
		assertNotNull("Discount amount is not null for the given Member", result);
		assertTrue("Discount amount", result > 0);
	}

	@Test
	public void testCalculateMemberDiscount(){ 
		double memberDiscount =0;
			try{
			assertTrue(discountManager.addDiscount("MEMBER_FIRST", "test2", 20));
			assertTrue(discountManager.addDiscount("SUBSEQ_MEMBER", "test2", 20));
			memberDiscount = discountManager.calculateMemberDiscount(new Member("Sanskar", "E0013519"));
			}catch(BadValueException e){
				e.printStackTrace();
				fail("Bad value");
			}
		assertTrue("Discount amount", memberDiscount > 0);
	}

	@Test
	public void testCalculateOccasionalDiscount() throws BadValueException  {
		try {
			assertTrue(discountManager.addDiscount("test1", "test2", 20, sf.parse("2016-04-04"), 30));
			assertTrue(discountManager.addDiscount("test2", "test2",20));
			} catch (ParseException | BadValueException e) {
				e.printStackTrace();
				fail("Parsing error");
			}
		double occasionalDiscount = discountManager.calculateOccasionalDiscount();
		assertNotNull("Occasional Discount is 0 if transaction Date is not applicable", occasionalDiscount);
		assertTrue("Occasional Discount ", occasionalDiscount > 0);
	}

	@Test
	public void testGetDiscounts() throws BadValueException {
		try {
			assertTrue(discountManager.addDiscount("test1", "test2", 20, sf.parse("2016-04-05"), 30));
			assertTrue(discountManager.addDiscount("test2", "test2", 20, sf.parse("2016-04-05"), 30));
			} catch (ParseException e) {
				e.printStackTrace();
				fail("Parsing error");
			}
		ArrayList<Discount> discountList = discountManager.getDiscounts();
		assertNotNull("List of Discount is not null", discountList);
		assertTrue("List of Discount returned", discountList.size() > 0);
	}
}
