package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.store.Category;
import sg.edu.nus.iss.store.Product;
import sg.edu.nus.iss.store.ProductRegister;
import sg.edu.nus.iss.store.Store;

/*
 * author:Wang Xuemin
 */

public class ProductRegTest {
	private ProductRegister pr;
	
	@Before
	public void setUp() throws Exception {
		
		pr= new ProductRegister();
	}

	@After
	public void tearDown() throws Exception {
		pr = null;
	}

	@Test
	public void testGenerateProductId() {
		assertEquals("CLO/3", pr.generateProductId(pr.getProducts().get(0).getCategory()));
	}
	
	@Test
	public void testGetProductsByBarcodenumber(){
		Product result=pr.getProductsByBarcodenumber("1234");
		assertEquals("1234",result.getBarcodeNumber());
	}
	
	@Test
	public void testGetProductById(){
		Product result=pr.getProductById("CLO/2");
		assertEquals("CLO/2", result.getId());
	}
	
	@Test
	public void testRemoveProductById(){
		pr.removeProductById("CLO/2");
		assertEquals(1, pr.getProducts().size());
		assertEquals(null, pr.getProductById("CLO/2"));
	}
	
	@Test
	public void testAddProduct(){
		String id="CLO/3";
		String name = "Name of new product";
        Category category = testStore.getCategoryReg().getCategoryByCode("CLO");
        String briefDescription = "new shirt";
        int quantityAvailable = 100;
        double price = 15.2;
        String barCode = "5566";
        int threshold = 30;
        int orderQuantity = 50;
        
     // before addition
        Product result = pr.getProductById(id);
        //there is no such a product which id is CLO/3
        assertEquals(null, result);
        //product list have 2 products
        assertEquals(2, pr.getProducts().size());
        
        pr.addProduct(category,name,  briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
        
        // after adding
        result = pr.getProductById(id);
        //there is a product which id is CLO/3
        assertNotEquals(null, result);
        //product list have 3 products
        assertEquals(3, pr.getProducts().size());
        
        //all attributes are same as set
        assertEquals(id,result.getId());
        assertEquals(name,result.getName());
        assertEquals(category,result.getCategory());
        assertEquals(briefDescription,result.getDescription());
        assertEquals(quantityAvailable,result.getQuantity());
        assertEquals(price,result.getPrice(),0.01);
        assertEquals(barCode,result.getBarcodeNumber());
        assertEquals(threshold,result.getThreshold());
        assertEquals(orderQuantity,result.getOrderQuantity());
        
	}

}
