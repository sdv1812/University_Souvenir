package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.store.OccasionalDiscount;

/**
 * 
 * @author Mohan Karthik
 *
 */

public class OccasionalDiscountTest {

	private OccasionalDiscount od1,od2;
	
	@Before
	public void setUp() throws Exception {
		od1 = new OccasionalDiscount("ISS","ISS_students_Only","2016-03-31","10",10.21f);
		od2 = new OccasionalDiscount("COM","COM_students_Only","2016-04-02","20",20.21f);
	}

	@After
	public void tearDown() throws Exception {
		od1 = null;
		od2 = null;
	}

	@Test
	public void testGetStartDate() {
		assertEquals(od1.getStartDate(),"2016-03-31");
	}

	@Test
	public void testGetDiscountPeriod() {
		assertEquals(od2.getDiscountPeriod(),"20");
	}

	@Test
	public void testGetApplicableToMember() {
		assertEquals(od2.getApplicableToMember(),"A");
	}

	@Test
	public void testOccasionalDiscount() {
		assertEquals(od1.getDiscountCode(),"ISS");
		assertEquals(od1.getDescription(),"ISS_students_Only");
		assertEquals(od2.getStartDate(),"2016-04-02");
		assertEquals(od2.getDiscountPeriod(),"20");
	}

}
