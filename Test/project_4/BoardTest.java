package project_4;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.princeton.cs.algs4.MinPQ;

public class BoardTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		
		int[][] initialBlock = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
		Board initial = new Board(initialBlock);
		//System.out.print(initial);
		
	}
	@Test
	public void testDimension() {
		int[][] initialBlock = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
		Board initial = new Board(initialBlock);
		assertEquals(initial.dimension(), 3);
		
		initialBlock = new int[][] {};
		initial = new Board(initialBlock);
		assertEquals(initial.dimension(), 0);
	}

	@Test
	public void testHamming() {
		int[][] initialBlock = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
		Board initial = new Board(initialBlock);
		assertEquals(initial.hamming(), 5);
	}

	@Test
	public void testManhattan() {
		int[][] initialBlock = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
		Board initial = new Board(initialBlock);
		assertEquals(initial.manhattan(), 10);
	}

	@Test
	public void testIsGoal() {
		int[][] initialBlock = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		Board initial = new Board(initialBlock);
		assertEquals(initial.isGoal(), true);
		
		initialBlock = new int[][] {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
		initial = new Board(initialBlock);
		assertEquals(initial.isGoal(), false);
	}

	@Test
	public void testTwin() {
		int[][] initialBlock = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		Board initial = new Board(initialBlock);
		System.out.println("initial\n" + initial);
		System.out.println("\n" + initial.twin());
		System.out.println("\n" + initial.twin());
		System.out.println("\n" + initial.twin());
	}

	@Test
	public void testEqualsObject() {
		int[][] initialBlock1 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		Board initial1 = new Board(initialBlock1);
		
		int[][] initialBlock2 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		Board initial2 = new Board(initialBlock2);
		
		int[][] initialBlock3 = new int[][] {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
		Board initial3 = new Board(initialBlock3);
		
		assertEquals(initial1.equals(initial2), true);
		assertEquals(initial1.equals(initial3), false);
	}

	@Test
	public void testNeighbors() {
		int[][] initialBlock = new int[][] {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
		Board initial = new Board(initialBlock);
		Iterator<Board> itr = initial.neighbors().iterator();
		System.out.println("initial board: \n" + initial + "\n");
		while(itr.hasNext()) {
			System.out.println("neighbor: \n" + itr.next() + "\n\n");
		}
		
		MinPQ<Integer> numQueue = new MinPQ<>();
		numQueue.insert(1);
		numQueue.insert(5);
		numQueue.insert(10);
		numQueue.insert(4);
		numQueue.insert(0);
		
		System.out.println(numQueue.delMin());
		System.out.println(numQueue.delMin());
		System.out.println(numQueue.delMin());
		System.out.println(numQueue.delMin());
	}

}
