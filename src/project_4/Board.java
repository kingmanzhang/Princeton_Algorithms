package project_4;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Board {
	
	private int[][] blocks;
	private final int N;
	
	public Board(int[][] blocks) {// construct a board from an n-by-n array of blocks
		
		if (blocks == null) {
			throw new NullPointerException();
		}
		
		//make a copy of the board
		this.N = blocks.length;
		this.blocks = new int[N][N];
		this.blocks = clone2DArray(blocks, N);
	
	}
	
   // (where blocks[i][j] = block in row i, column j)
	public int dimension() {                // board dimension n
		
		return N;
	
	}
	public int hamming() {                  // number of blocks out of place
		
		int total = 0;		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// expected number at [i, j] is j + 1 + i * N
				// alternatively, generate a goal block
				if(blocks[i][j] != 0 && blocks[i][j] != j + 1 + i * N) {
					total++;
				}
			}
		}
		return total;
	}
	
	
	public int manhattan() {                 // sum of Manhattan distances between blocks and goal
		
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
			if(blocks[i][j] != 0 && blocks[i][j] != j + 1 + i * N) {
				int iGoal = (blocks[i][j] - 1) / N;
				int jGoal = (blocks[i][j] - 1) % N; 
				total = total + Math.abs(iGoal - i) + Math.abs(jGoal - j);
			}
		}
		return total;
		
	}
	
	
	public boolean isGoal() {               // is this board the goal board?
		
		return hamming() == 0;
		
	}
	
	
	public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
		
		int[][] twinBlock = clone2DArray(blocks, N);
		int i, j, m, n;
		boolean twinFound = false;
		while (!twinFound) {
			i = StdRandom.uniform(N);
			j = StdRandom.uniform(N);
			m = StdRandom.uniform(N);
			n = StdRandom.uniform(N);
			if (twinBlock[i][j] * twinBlock[m][n] != 0 && twinBlock[i][j] != twinBlock[m][n]) {
				swap(twinBlock, i, j, m, n);
				twinFound = true;
			}
		}
		return new Board(twinBlock);
		
		/*
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (twinBlock[i][j] != 0 && twinBlock[i][j + 1] != 0) {
					
				}
			}
		}
		*/
	}
	
	private int[][] goal() {
		
		int[][] goal = new int[N][N]; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i != N || j != N) {
					goal[i][j] = i * N + j + 1;
				}
			}
		}
		goal[N - 1][N - 1] = 0;
		return goal;
		
	}
	
	
	
	public boolean equals(Object y) {       // does this board equal y?
		
		if (y == null) {
			return false;
		}
		
		if (y instanceof Board) {
			Board that = (Board) y;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (this.blocks[i][j] != that.blocks[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
		
	}
	
	
	
	public Iterable<Board> neighbors() {    // all neighboring boards
		
		ArrayList<Board> neighbors = new ArrayList<>();
		int iBlank = 0, jBlank = 0;
		
		//find the blank block
		label1:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] == 0) {
					iBlank = i;
					jBlank = j;
					break label1;
				}
			}
		}
		
		//find all neighbors
		if (iBlank - 1 >= 0) { //upper neighbor	
			int[][] upper = clone2DArray(blocks, N);
			swap(upper, iBlank, jBlank, iBlank - 1, jBlank);
			neighbors.add(new Board(upper));	
		}
		if (iBlank + 1 < N) {
			
			int[][] lower = clone2DArray(blocks, N);
			swap(lower, iBlank, jBlank, iBlank + 1, jBlank);
			neighbors.add(new Board(lower));
		}
		if (jBlank - 1 >= 0) {
			int[][] left = clone2DArray(blocks, N);
			swap(left, iBlank, jBlank, iBlank, jBlank - 1);
			neighbors.add(new Board(left));
		}
		if (jBlank + 1 < N) {
			int[][] right = clone2DArray(blocks, N);
			swap(right, iBlank, jBlank, iBlank, jBlank + 1);
			neighbors.add(new Board(right));
		}
		
		return neighbors;
		
		
	}
	
	/**
	 * A method to make a deep copy of 2D array
	 * @param blocks
	 * @param N: dimension of 
	 * @return
	 */
	private int[][] clone2DArray(int[][] blocks, int N) {
		
		
		int[][] clone = new int[N][N];
		for (int i = 0; i < N; i++) {
			clone[i] = Arrays.copyOf(blocks[i], N);
		}
		return clone;
		
	}
	
	private void swap(int[][] blocks, int i, int j, int k, int l) {
		
		int temp = blocks[i][j];
		blocks[i][j] = blocks[k][l]; //[i, j] is the blank site
		blocks[k][l] = temp; 
		
	}
	
	
	public String toString() {              // string representation of this board (in the output format specified below)
		
		String board = "" + N + "\n";
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				String oneBlock = String.format("%2d ", blocks[i][j]);
				board += oneBlock;
			}
			if(i != N - 1) {
				board += "\n";
			}	
		}
//board = board + manhattan() + "\n";
		return board;
	}
	
	public static void main(String[] args) {// unit tests (not graded)
		
		
	}

}
