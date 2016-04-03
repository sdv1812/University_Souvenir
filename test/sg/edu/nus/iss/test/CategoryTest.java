package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.Category;

/**
 * 
 * @author Surenthiran T
 *
 */

public class CategoryTest {

	private Category c1,c2;
	@Before
	public void setUp() throws Exception {
		c1 = new Category("Clothing");
		c2 = new Category("STA","Stationary");
	}

	@After
	public void tearDown() throws Exception {
		c1 = null;
		c2 = null;
	}

	@Test
	public void testCategoryString() {
		assertEquals(c1.getCategoryCode(),"CLO");
		assertEquals(c1.getCategoryName(),"Clothing");
	}

	@Test
	public void testCategoryStringString() {
		assertEquals(c2.getCategoryCode(),"STA");
		assertEquals(c2.getCategoryName(),"Stationary");
	}

	@Test
	public void testGetCategoryCode() throws BadValueException {
		Category c3 = new Category("BIS","Biscuit");
		assertEquals(c3.getCategoryCode(),"BIS");
	}

	@Test
	public void testGetCategoryName() {
		assertEquals(c2.getCategoryName(),"Stationary");
	}

}
