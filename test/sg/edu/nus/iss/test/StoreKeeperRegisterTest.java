package sg.edu.nus.iss.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.StoreKeeper;
import sg.edu.nus.iss.store.StoreKeeperRegister;

/**
 * 
 * @author Surenthiran T
 *
 */
public class StoreKeeperRegisterTest {
	StoreKeeperRegister storeKeeper;
	
	@Before
    public void setUp() throws BadValueException {
		storeKeeper = new StoreKeeperRegister();
		storeKeeper.addStoreKeeper("suren", "suren");
		storeKeeper.addStoreKeeper("koush", "koush");
		storeKeeper.addStoreKeeper("sansky", "sansky");
    }

    @After
    public void tearDown() {
    	storeKeeper = null;
      
    }

	@Test
	public void testAddStoreKeeper() throws BadValueException {
		storeKeeper.addStoreKeeper("suren", "suren");
		assertEquals(storeKeeper.getStoreKeeper("suren").getStoreKeeperName(),"suren");
		
	}

	@Test
	public void testGetStoreKeeper() {
		StoreKeeper storekeepertest;
		storekeepertest = storeKeeper.getStoreKeeper("suren");
		assertEquals(storekeepertest.getStoreKeeperName(),"suren");
		
	}

	@Test
	public void testValidate() {
		boolean result = storeKeeper.validate("sansky", "sansky");
		Assert.assertTrue(result);
	}

}
