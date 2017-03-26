package project_1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;


public class PercolationStats {
	
	private int N; 
	private int trials; 
	private double[] percent;
	
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if(n <= 0 || trials <= 0) 
			throw new IllegalArgumentException();
		this.N= n;
		this.trials = trials;
		this.percent = new double[trials];
		int row;
		int col;
		for (int i = 0; i < trials; i++) {
			Percolation p1 = new Percolation(N);
			while(!p1.percolates()) {
				row = StdRandom.uniform(1, N + 1);
				col = StdRandom.uniform(1, N + 1);
				p1.open(row, col);
			}
			percent[i] = p1.numberOfOpenSites() / (double) (N * N);		
		}
	}
	
   public double mean() {
   	
   	return StdStats.mean(percent);
   }
   
   public double stddev() {
   	// sample standard deviation of percolation threshold
   	return StdStats.stddev(percent);
   }
   
   public double confidenceLo() {
   	// low  endpoint of 95% confidence interval
   	
   	return mean() - 1.96 * stddev();
   }
   
   public double confidenceHi()   {
   	// high endpoint of 95% confidence interval
   	return mean() + 1.96 * stddev();
   }

   public static void main(String[] args)  {
   	// test client (described below)
   	int n;
   	int trials;
		n = Integer.parseInt(args[0]);
		trials = Integer.parseInt(args[1]);
   	PercolationStats test = new PercolationStats(n, trials);
   	System.out.println("mean                     = " + test.mean());
   	System.out.println("stddev                  = " + test.stddev());
   	System.out.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
   }
}
