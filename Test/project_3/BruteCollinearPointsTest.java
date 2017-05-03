package project_3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class BruteCollinearPointsTest {

	
	@Test
	public void testBruteCollinearPoints() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(3, 3);
		Point point4 = new Point(4, 4);
		Point[] points = new Point[] {point1, point2, point3, point4};
		BruteCollinearPoints test = new BruteCollinearPoints(points);
	}

	@Test
	public void testNumberOfSegments() {
		Point point1 = new Point(1, 1);
		Point point2 = new Point(2, 2);
		Point point3 = new Point(3, 3);
		Point point4 = new Point(4, 4);
		Point point5 = new Point(5, 6);
		Point point6 = new Point(4, 6);
		Point point7 = new Point(4, 7);
		Point point8 = new Point(4, 8);
		Point[] points = new Point[] { point1, point2, point3, point4, point5,  point6, point7, point8};
		BruteCollinearPoints test = new BruteCollinearPoints(points);
		test.segments();
		assertEquals(8, points.length);
		assertEquals(2, test.numberOfSegments());
		
	}

}
