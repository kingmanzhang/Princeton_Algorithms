
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
	private ArrayList<LineSegment> lineSegments;
	
	public FastCollinearPoints(Point[] points) {
	
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
   	this.lineSegments = new ArrayList<>();
   	solve(points);

	}
	
	private void solve(Point[] points) {
		
		for (int i = 0; i < points.length; i++) {
			Point reference = points[i]; // set a reference point

			Point[] otherPoints = new Point[points.length - 1]; // get all other points
			System.arraycopy(points, 0, otherPoints, 0, i); // copy first part
			System.arraycopy(points, i + 1, otherPoints, i, points.length - 1 - i); // copy second part
			findLine(reference, otherPoints);
		}
	}
	
	private void findLine(Point reference, Point[] otherPoints) {
//System.out.println("reference point: " + reference);	
//printPoints(otherPoints);
		Arrays.sort(otherPoints, reference.slopeOrder()); // sort points by slope
//System.out.println("ordered by slope");		
//printPoints(otherPoints);
		double[] slopes = new double[otherPoints.length]; // make a slope array to avoid multiple computation
		for (int i = 0; i < otherPoints.length; i++) {
			slopes[i] = reference.slopeTo(otherPoints[i]);
		}
		// now find same neighbors 
		int numColineared = 2; // any two points form a line
		for (int i = 0; i < slopes.length - 1; i++) {
			if (isEqual(slopes[i], slopes[i + 1])) { //current = next
				numColineared++; 
				// if current is the second last point, then need to test line
				if (i == slopes.length - 2 && numColineared >= 4) {
					addLine(reference, otherPoints, otherPoints.length - 1, numColineared);
					numColineared = 2;
				}
			} else { //current != next
				if (numColineared >= 4) { // previous points form a line. stop position is i.
					addLine(reference, otherPoints, i, numColineared);
				}
				numColineared = 2; // reset
			}
		}
		
	}
	
	private void addLine(Point reference, Point[] otherPoints, int stopPosition, int numCollineared) {
		
		Point[] linedPoints = new Point[numCollineared];
		linedPoints[0] = reference;
		System.arraycopy(otherPoints, stopPosition - numCollineared + 2, linedPoints, 1, numCollineared - 1);
		Arrays.sort(linedPoints);
//System.out.print("lined points: ");
//printPoints(linedPoints);
		LineSegment newSeg = new LineSegment(linedPoints[0], linedPoints[numCollineared - 1]);
		boolean isExisted = false;
		for (LineSegment seg : lineSegments) {
			if (seg.toString().equals(newSeg.toString())) {
				isExisted = true;
				break;
			}
		}
		if (!isExisted) {
			lineSegments.add(newSeg);
		}
		
	}
	
	private void printPoints(Point[] points) {
		for (int i = 0; i < points.length; i++) {
			System.out.print(points[i] + " ");
		}
		System.out.println("");
	}

   public int numberOfSegments() {
   	
   	return lineSegments.size();
   	
   }
   
   
   public LineSegment[] segments() {
   	
   	LineSegment[] seg = new LineSegment[lineSegments.size()];
   	int i = 0;
   	for (LineSegment linesegment : lineSegments) {
   		seg[i++] = linesegment;
   	}
   	return seg;
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
   	return x - y > -0.000000001 && x - y < 0.000000001;
   }
}
