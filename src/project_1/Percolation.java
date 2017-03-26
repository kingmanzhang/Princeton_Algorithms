package project_1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int N;
	private boolean[] status;
	private WeightedQuickUnionUF grid;
	private int openCount;
	
   public Percolation(int n)  { // create n-by-n grid, with all sites blocked
   	if (n <= 0) 
   		throw new IllegalArgumentException();
   	this.N = n;
   	this.openCount = 0;
   	this.grid = new WeightedQuickUnionUF(n * n + 2);
   	this.status= new boolean[n * n + 2];
   	for (int i = 1; i < n * n + 1; i++) {
   		status[i] = false;
   	}
   	status[0] = true;  //first site reserved for top virtual site
   	status[n * n + 1] = true; //last site reserved for bottom virtual site
   
   }
   
   
   public void open(int row, int col) {    // open site (row, col) if it is not open already
   
   	if (row > N || row < 1 || col > N || col < 1)
   		throw new IndexOutOfBoundsException();
   	
   	if (!isOpen(row, col)) {
   		
   		status[(row - 1) * N + col] = true ; //change status
   		openCount++; // update counts of open site
   		// union with its open neighbors 
   		// first deal with its right neighbor 
   		if(col < N && isOpen(row, col + 1)) {
   			grid.union((row - 1) * N + col, (row - 1) * N + col + 1);
   		}
   		
   		// deal with left neighbor
   		if(col > 1 && isOpen(row, col -1)) {
   			grid.union ((row - 1) * N + col, (row -1) * N + col - 1);
   		}
   		
   		// deal with bottom neighbor
   		if(row < N && isOpen(row + 1, col)) {
   			grid.union((row - 1) * N + col, row * N + col);
   		}
   		
   		// deal with upper bottom
   		if(row > 1 && isOpen(row - 1, col)) {
   			grid.union((row - 1) * N + col, (row - 2) * N + col);
   		}
   		
   		// deal with special case
   		// if open a site in the first row, it should union with virtual point
   		if(row == 1) {
   			grid.union(col, 0);
   		}
   		if(row == N) {
   			grid.union((row - 1) * N + col, N * N + 1);
   		}  		
   	} 	
   }
   public boolean isOpen(int row, int col) { // is site (row, col) open?
   	if (row > N || row < 1 || col > N || col < 1)
   		throw new IndexOutOfBoundsException();
   		
   	return status[(row - 1) * N + col];
   }	
   
   public boolean isFull(int row, int col) { // is site (row, col) full?
   	if (row > N || row < 1 || col > N || col < 1)
   		throw new IndexOutOfBoundsException();
   	 	
   	return grid.connected((row - 1) * N + col, 0);
   
   }
   public int numberOfOpenSites() { // number of open sites
  
   	return openCount;
   
   }
   public boolean percolates() { // does the system percolate?
   
   	return grid.connected(0, N * N + 1);
   
   }
   
 
   public static void main(String[] args) {
   	
   	Percolation test = new Percolation(4);
   	System.out.println("Percolation? " + test.percolates());
   	test.open(1, 2);
   	System.out.println("Percolation? " + test.percolates());
   	test.open(2, 2);
   	test.open(4, 2);
   	System.out.println("Percolation? " + test.percolates());
   	test.open(3, 2);
   	System.out.println("Percolation? " + test.percolates());
   	System.out.println("open sites: " + test.openCount);
   }
}

