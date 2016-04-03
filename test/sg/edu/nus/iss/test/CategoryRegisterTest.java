package sg.edu.nus.iss.test;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.Category;
import sg.edu.nus.iss.store.CategoryRegister;


/**
 * 
 * @author Surenthiran T
 *
 */
public class CategoryRegisterTest {

	private CategoryRegister category;
	
	
	@Before
    public void setUp() throws BadValueException {
		category = new CategoryRegister();
		category.addCategory("HUM", "Humans");
		category.addCategory("CLO", "Clothing");
		category.addCategory("STA", "Stationaries");

    }

    @After
    public void tearDown() {
       category = null;
      
    }

	@Test
	public void testAddCategory() throws BadValueException {
		assertTrue(category.addCategory("ELE", "Electrical items"));
		assertFalse(category.addCategory("ELE", "Items"));
	}

	
	@Test
	public void testGetCategory() {
		Category testCategory;
		testCategory = category.getCategory("CLO");
		assertEquals(testCategory.getCategoryCode(),"CLO");
		}

	

	@Test
	public void testGetCategories() {
		ArrayList<Category> categories = category.getCategories();
		assertEquals(categories.get(0).getCategoryCode(), "HUM");
		assertEquals(categories.get(1).getCategoryCode(), "CLO");
	}

}
