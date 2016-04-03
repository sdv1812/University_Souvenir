package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.store.MemberDiscount;

public class MemberDiscountTest {
	
/**
 * 
 * @author Mohan Karthik
 *
 */
	
	private MemberDiscount md1,md2;
	@Before
	public void setUp() throws Exception {
		md1= new MemberDiscount("ISS","ISS_students_Only",10);
		md2= new MemberDiscount("COM","COM_students_Only",20);
	}

	@After
	public void tearDown() throws Exception {
		md1 = null;
		md2 = null;
	}

	@Test
	public void testGetStartDate() {
		assertEquals(md1.getStartDate(),null);
	}

	@Test
	public void testGetDiscountPeriod() {
		assertEquals(md2.getDiscountPeriod(),-1);
	}

	@Test
	public void testGetApplicableToMember() {
		assertEquals(md1.getApplicableToMember(),"M");
	}

	//@SuppressWarnings("deprecation")
	@Test
	public void testMemberDiscount() {
		assertEquals(md1.getDescription(),"ISS_students_Only");
		assertEquals(md1.getDiscountCode(),"ISS");
	}

}
