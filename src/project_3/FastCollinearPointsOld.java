package project_3;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPointsOld {
	
	//private LineSegment[] lineSegments;
	private ArrayList<LineSegment> lineSegments;
	private Point[] points;
	
	public FastCollinearPointsOld(Point[] points) {     // finds all line segments containing 4 or more points
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
   	
   	this.points = points;
   	this.lineSegments = new ArrayList<>();
   	
   	solve();
   	
	}
	private void solve(){
	
		for (int i = 0; i < points.length; i++) {
System.out.println("points: " + points.length + " " + points[i]);
   		Point[] otherPoints = new Point[points.length - 1];
   		System.arraycopy(points, 0, otherPoints, 0, i);
   		System.arraycopy(points, i + 1, otherPoints, i, points.length - 1 - i);
System.out.println("otherPoints: " + otherPoints.length);
for (int j = 0; j < otherPoints.length; j++) {
	System.out.print(otherPoints[j] + " ");
}
System.out.println("\n");
   		Arrays.sort(otherPoints, points[i].slopeOrder());
for (int j = 0; j < otherPoints.length; j++) {
	System.out.print(otherPoints[j] + " ");
}
System.out.println("\n");
   		findPoints(points[i], otherPoints);
   		}
	}
	
	private void findPoints(Point reference, Point[] sortedpoints) {
		
		int i = 0; 
		int numColineared = 2;
		while (i < sortedpoints.length - 1) {
			i++;
			if (!isEqual(reference.slopeTo(sortedpoints[i - 1]), reference.slopeTo(sortedpoints[i]))) {
				if (numColineared >= 4) {
					Point[] colineared = new Point[numColineared];
					colineared[0] = reference;
			//System.out.println("reference of line: " + reference);
					System.arraycopy(sortedpoints, i - numColineared + 1, colineared, 1, numColineared - 1);
					addSegments(lineSegments, colineared);
				}
				numColineared = 2;
			
			} else {
				numColineared++;
				if(i == sortedpoints.length - 1 && numColineared >= 4) {
					Point[] colineared = new Point[numColineared];
					colineared[0] = reference;
					System.arraycopy(sortedpoints, i - numColineared + 1, colineared, 1, numColineared - 1);
					addSegments(lineSegments, colineared);
					numColineared = 2;
				}
	
	//System.out.println("numColineared: " + numColineared);
   //System.out.println("colineared points " + reference + " " + sortedpoints[i - 1] + " " + sortedpoints[i]);
			}
	//System.out.println("i is: " + i);
		}
		
	}
	
	private void addSegments(ArrayList<LineSegment> linesegments, Point[] colineared) {
		
		Arrays.sort(colineared);
		LineSegment newSegment = new LineSegment(colineared[0], colineared[colineared.length - 1]);
System.out.println("linesegment intended to add: " + newSegment);
		boolean existed = false;
		for (LineSegment existingSeg : lineSegments) {
			if (existingSeg.toString().equals(newSegment.toString())) {
				existed = true;
				break;
			}
		}
		if (!existed) {
			lineSegments.add(newSegment);
System.out.println("linesegment added: " + newSegment);
		}	
		
	}
   		
	
	
   public int numberOfSegments() {        // the number of line segments
   	
   	return lineSegments.size();
   	
   }
   public LineSegment[] segments() {               // the line segments
   	
   	LineSegment[] toReturn = new LineSegment[lineSegments.size()];
   	int i = 0;
   	for (LineSegment seg: lineSegments) {
   		toReturn[i++] = seg;
   	}
//  System.out.println("toReturn size: " + toReturn.length);
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
   	if (x == Double.POSITIVE_INFINITY 
   			&& y == Double.POSITIVE_INFINITY) {
   		return true;
   	}
   	return x - y > -0.0001 && x - y < 0.0001;
   }
}
