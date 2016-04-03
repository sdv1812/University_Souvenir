package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.OccasionalDiscount;

/**
 * 
 * @author Mohan Karthik
 *
 */

public class OccasionalDiscountTest {

	private OccasionalDiscount od1,od2;
	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void setUp() throws Exception {
		od1 = new OccasionalDiscount("ISS","ISS_students_Only",sf.parse("2016-03-31"),10,10.21f);
		od2 = new OccasionalDiscount("COM","COM_students_Only",sf.parse("2016-04-01"),0,20.21f);
	}

	@After
	public void tearDown() throws Exception {
		od1 = null;
		od2 = null;
	}

	@Test
	public void testGetStartDate() throws ParseException {
		assertEquals(od1.getStartDate(),sf.parse("2016-03-31"));
	}

	@Test
	public void testGetDiscountPeriod() {
		assertEquals(od2.getDiscountPeriod(),0);
	}

	@Test
	public void testGetApplicableToMember() {
		assertEquals(od2.getApplicableToMember(),"A");
	}

	@Test
	public void testOccasionalDiscount() {
		assertEquals(od1.getDiscountCode(),"ISS");
		assertEquals(od1.getDescription(),"ISS_students_Only");
		assertNotEquals(od2.getStartDate(),"2016-04-02");
		assertEquals(od2.getDiscountPeriod(),0);
	}
	
	@Test
	public void testEqual() throws BadValueException, ParseException{
		OccasionalDiscount od = new OccasionalDiscount("ISS","I",sf.parse("2016-02-02"),10,10.21f);
		assertTrue(isEqual(od1,od));
	}

	public boolean isEqual(OccasionalDiscount od1, OccasionalDiscount o){
		
		if(od1.getDiscountCode().equals(o.getDiscountCode()))
			return true;
		return false;
	}
}
