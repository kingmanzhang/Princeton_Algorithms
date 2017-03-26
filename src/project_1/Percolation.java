package project_1;

public class Percolation {
	
	private int n;
	private boolean[] status;
	private int[] id;
	private int[] sz;
	private int openCount;
	
   public Percolation(int n)  {// create n-by-n grid, with all sites blocked
   	if (n <= 0) 
   		throw new IllegalArgumentException();
   	this.n = n;
   	this.openCount = 0;
   	this.id = new int[n * n + 1];
   	this.sz = new int[n * n + 1];
   	this.status= new boolean[n * n + 1];
   	for (int i = 0; i < n * n + 2; i++) {
   		id[i] = i;
   		sz[i] = 1;
   		status[i] = false;
   	}
   	status[0] = true;
   	status[n * n + 1] = true;
   
   }
   
   private int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	private boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	private void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}	
	}
   
   public void open(int row, int col) {    // open site (row, col) if it is not open already
   
   	if (row > n || row < 1 || col > n || col < 1)
   		throw new IndexOutOfBoundsException();
   	
   	if (!isOpen(row, col)) {
   		status[row * (row - 1) + col] = true ;
   		if(col + 1 < n && isOpen(row, col + 1)) {
   			union((row - 1) * n + col, (row - 1) * n + col + 1);
   		} 
   		if(col > 1 && isOpen(row, col -1)) {
   			union ((row - 1) * n + col, (row -1) * n + col - 1);
   		}
   		if(row + 1 < n && isOpen(row + 1, col)) {
   			union((row - 1) * n + col, row * n + col);
   		}
   		if(row > 1 && isOpen(row - 1, col)) {
   			union((row - 1) * n + col, (row - 2) * n + col);
   		}
   		if(row == 1) {
   			union((row - 1) * n + col, 0);
   		}
   		if(row == n) {
   			union((row - 1) * n + col, n * n + 1);
   		}
   		openCount++;
   	}
   		
   	
   	
   }
   public boolean isOpen(int row, int col) { // is site (row, col) open?
   	if (row > n || row < 1 || col > n || col < 1)
   		throw new IndexOutOfBoundsException();
   		
   	return status[(row - 1) * n + col];
   }	
   
   public boolean isFull(int row, int col) { // is site (row, col) full?
   	if (row > n || row < 1 || col > n || col < 1)
   		throw new IndexOutOfBoundsException();
   	 	
   	return connected(row * (n - 1) + col, 0);
   
   }
   public int numberOfOpenSites() {// number of open sites
  
   	return openCount;
   
   }
   public boolean percolates() {// does the system percolate?
   
   	return connected(0, n * n + 1);
   
   }
   
 
   public static void main(String[] args) {
   	// test client (optional)
   }
}

