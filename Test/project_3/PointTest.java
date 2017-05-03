package project_3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PointTest {

	
	@Test
	public void testSlopeTo() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(1, 2);
		Point point3 = new Point(1, 3);
		System.out.println("slope: " + point1.slopeTo(point2));
		assertEquals(point1.slopeTo(point2) - point1.slopeTo(point3), 0, 0.001);
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

}
