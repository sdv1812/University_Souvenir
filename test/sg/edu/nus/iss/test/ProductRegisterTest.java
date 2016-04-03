package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.*;


/*
 * author:Wang Xuemin
 */

public class ProductRegisterTest {
	private ProductRegister pr;
	private ArrayList<Product> products;
	private Category category1;
	private Category category2;
	private Product product1;
	private Product product2;
	public ProductRegisterTest() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws Exception {
		category1=new Category("CLO","clothes");
		category2=new Category("STA","station");
		product1=new Product("CLO/1",category1, "Centenary Jumper", "A really nice momento", 101, 21.45, "1234", 10, 100);
		product2=new Product("CLO/2",category1, "levis", "US Jeans Brand", 967, 65.0, "12345", 650, 500);
		pr=new ProductRegister();
		products=pr.getProducts();
		products.add(product1);
		products.add(product2);
	}

	@After
	public void tearDown() throws Exception {
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
		assertEquals("CLO/2", result.getProductId());
	}
	
	@Test
	public void testRemoveProductById() throws IOException{
		pr.removeProduct("CLO/2");
		assertEquals(1, pr.getProducts().size());
		assertEquals(null, pr.getProductById("CLO/2"));
	}
	
	@Test
	public void testAddProduct() throws IOException, BadValueException{
		String id="CLO/3";
		String name = "Name of new product";
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
        
        pr.addProduct(category1,name, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
        
        // after adding
        result = pr.getProductById(id);
        //there is a product which id is CLO/3
        assertNotEquals(null, result);
        //product list have 3 products
        assertEquals(3, pr.getProducts().size());
        
        //all attributes are same as set
        assertEquals(id,result.getProductId());
        assertEquals(name,result.getName());
        assertEquals(category1,result.getCategory());
        assertEquals(briefDescription,result.getDescription());
        assertEquals(quantityAvailable,result.getQuantityAvailable());
        assertEquals(price,result.getPrice(),0.01);
        assertEquals(barCode,result.getBarcodeNumber());
        assertEquals(threshold,result.getThreshold());
        assertEquals(orderQuantity,result.getOrderQuantity());
        
	}
	

}
