package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.store.StoreKeeper;

/**
 * 
 * @author Surenthiran T
 *
 */
public class StoreKeeperTest {
	private StoreKeeper s1,s2; 
	@Before
	public void setUp() throws Exception {
		s1 = new StoreKeeper("suren","suren");
		s2 = new StoreKeeper("mohan","mohan");
	}

	@After
	public void tearDown() throws Exception {
		s1 = null;
		s2 = null;
	}

	@Test
	public void testStoreKeeper() {
		assertEquals(s1.getStoreKeeperName(),"suren");
		assertEquals(s2.getPassword(),"mohan");
	}

	@Test
	public void testGetStoreKeeperName() throws BadValueException {
		StoreKeeper s3 = new StoreKeeper("sanskar","sanskar");
		assertEquals(s3.getStoreKeeperName(),"sanskar");
	}

	@Test
	public void testGetPassword() {
		assertEquals(s1.getPassword(),"suren");
	}

}
