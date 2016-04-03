package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.Discount;
import sg.edu.nus.iss.store.DiscountManager;
import sg.edu.nus.iss.store.Member;
import sg.edu.nus.iss.store.Store;

/**
 * 
 * @author Mohan
 *
 */

public class DiscountManagerTest {
	Store store;
	DiscountManager discountManager;
	Discount discount;
	Member member;

	@Before
	public void setUp() {
		store = new Store();
		store.initializeData();
		discountManager = store.getDiscountManager();
		member = store.getMember("e0013519");
	}

	@Test
	public void testAddDiscount() throws BadValueException {
	
		boolean check = discountManager.addDiscount("test", "test", 25, "ALWAYS", "ALWAYS");
		assertTrue(check=true);
		//assertTrue(discountManager.addDiscount("test", "test", 25, "ALWAYS", "ALWAYS"));
	}

	@Test
	public void testGetDiscount() throws BadValueException {
		discountManager.addDiscount("test1", "test1", 10, "ALWAYS", "ALWAYS");
		discountManager.addDiscount("test2", "test2", 20, "ALWAYS", "ALWAYS");
		discountManager.addDiscount("test3", "test3", 30, "ALWAYS", "ALWAYS");
		discount = discountManager.getDiscount("test1");
		assertEquals(discount.getDiscountCode(), "test1");
	}

	@Test
	public void testGetMaxDiscountMember() throws BadValueException {
		double result = discountManager.getMaxDiscount(member);
		assertNotNull("Discount amount is not null for the given Member", result);
		assertTrue("Discount amount", result > 0);
		assertFalse("Discount amount", result < 0);
	}

	@Test
	public void testCalculateMemberDiscount() throws BadValueException {
		double memberDiscount = discountManager.calculateMemberDiscount(member);
		assertNotNull("Discount amount is not null for the given Member", memberDiscount);
		assertTrue("Discount amount", memberDiscount > 0);
		assertFalse("Discount amount", memberDiscount < 0);

	}

	@Test
	public void testCalculateOccasionalDiscount() throws BadValueException  {
		double occasionalDiscount = discountManager.calculateOccasionalDiscount();
		assertNotNull("Occasional Discount is 0 if transaction Date is not applicable", occasionalDiscount);
		assertTrue("Occasional Discount ", occasionalDiscount > 0);
		assertFalse("Occasional Discount cannot be less than zero or null", occasionalDiscount < 0);
	}

	@Test
	public void testGetDiscounts() throws BadValueException {
		ArrayList<Discount> discountList = discountManager.getDiscounts();
		assertNotNull("List of Discount is not null", discountList);
		assertTrue("List of Discount returned", discountList.size() > 0);
	}
}
