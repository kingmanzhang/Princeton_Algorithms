package project_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class PatternRecognition {
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
//System.out.println("Point: " + points[i]);
	    }
// StdOut.println("num of points in file: " + points.length);


	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();	  
	    
	    // print and draw the line segments
	   // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    //FastCollinearPoints collinear = new FastCollinearPoints(points);
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
StdOut.print("total lines: " + collinear.numberOfSegments());
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
        segment.draw();
	    }
       StdDraw.show();
	    
	    
	}
}
