package project_1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UFTest {

	@Test
	public void testConnected() {
		UF test = new UF(10);
		assertEquals(test.connected(1, 2), false);
		test.union(1, 2);
		assertEquals(test.connected(1, 2), true);
	}


}
