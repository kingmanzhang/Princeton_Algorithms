
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
	
	//private LineSegment[] lineSegments;
	private ArrayList<LineSegment> lineSegments;
	private Point[] points;
	
	public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
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
	
	}
	
   public int numberOfSegments() {        // the number of line segments
   	
   	return lineSegments.size();
   	
   }
   public LineSegment[] segments() {               // the line segments
   	
   	int i = 0; 
   	do {
   		Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());
   		int colineared = 1;
   		int j = i + 2;
   		double reference = points[i].slopeTo(points[i + 1]);
   		while (j < points.length && isEqual(points[i].slopeTo(points[j]), reference)) {
   			colineared++;
   			j++;
   		}
   		if (colineared >= 4) {
   			lineSegments.add(new LineSegment(points[i], points[i + colineared - 1]));
   		}
   		i += colineared;
   	} while (i < points.length - 3);
    	
   	LineSegment[] toReturn = new LineSegment[lineSegments.size()];
   	i = 0;
   	for (LineSegment seg: lineSegments) {
   		toReturn[i++] = seg;
   	}
  System.out.println("toReturn size: " + toReturn.length);
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
   	if(x == Double.POSITIVE_INFINITY && y == Double.POSITIVE_INFINITY) {
   		return true;
   	}
   	return x - y > -0.0001 && x - y < 0.0001;
   }
}
