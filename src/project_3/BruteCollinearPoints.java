package project_3;


import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	
	//private Point[] points;
	private ArrayList<LineSegment> lineSegments;
	
   public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
   	
   	if (points == null) {
   		throw new NullPointerException();
   	}
   	for (int i = 0; i < points.length; i++) {
   		if (points[i] == null) {
   			throw new NullPointerException();
   		}
   	}
   	if(this.hasDuplicate(points))
   		throw new IllegalArgumentException();
   
   	
   	//this.points = points;
   	this.lineSegments = new ArrayList<>();
   
   	for (int p = 0; p < points.length - 3; p++) {
   		for (int q = p + 1; q < points.length - 2; q++) { 			
   			for (int r = q + 1; r < points.length - 1; r++) { 				
   					for (int s = r + 1; s < points.length; s++) {
   						double slopePQ = points[p].slopeTo(points[q]);
   						double slopePR = points[p].slopeTo(points[r]);
   						double slopePS = points[p].slopeTo(points[s]);
   						if (isEqual(slopePQ, slopePR) && isEqual(slopePQ, slopePS)) {
   							Point[] colineared = new Point[] {points[p], points[q], points[r], points[s]};
   							Arrays.sort(colineared);
//System.out.println("colineared points: " + points[p] + points[q] + points[r] + points[s]);
      						lineSegments.add(new LineSegment(colineared[0], colineared[3]));	
   						}
   						}
   					}
   			}
   		}
//   System.out.println("done with constructor");
   }
   
   public int numberOfSegments() {        // the number of line segments
   	
   	return lineSegments.size();
   	
   }
   
   public LineSegment[] segments() {                // the line segments
   	
   	
  //System.out.println("line segments size: " + lineSegments.size()); 	
   	LineSegment[] toReturn = new LineSegment[lineSegments.size()];
   	int i = 0;
   	for (LineSegment seg: lineSegments) {
   		toReturn[i++] = seg;
   	}
  //System.out.println("toReturn size: " + toReturn.length);
   	return toReturn;	
   }
   
   private boolean hasDuplicate(Point[] points) {
   	Point[] clone = points.clone();
   	Arrays.sort(clone);
   	for (int i = 0; i < clone.length - 1; i++) {
   		if (clone[i].compareTo(clone[i + 1]) == 0) {
   			return true;
   		}
   	}
   	return false;
   	
   }
   
   private boolean isEqual(double x, double y) {
   	if ((x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY) 
   			&& (y == Double.POSITIVE_INFINITY || y == Double.NEGATIVE_INFINITY)) {
   		return true;
   	}
   	return x - y > -0.0001 && x - y < 0.0001;
   }
   
}
