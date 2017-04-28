package project_3;

public class BruteCollinearPoints {
	
	private int numSeg;
	private Point[] points;
	
   public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
   	this.numSeg = 0;
   	this.points = points;
   
   
   }
   
   public int numberOfSegments() {        // the number of line segments
   	
   	return numSeg;
   	
   }
   
   public LineSegment[] segments() {                // the line segments
   	
   	LineSegment[] lineSegments = new LineSegment[points.length / 4];
   	for (int i = 0; i < points.length - 3; i++) {
   		for (int j = i + 2; j < points.length - 1; j++) {
   			if ((points[i].slopeTo(points[i + 1]) - points[i].slopeTo(points[j])) < 0.001) {
   				for (int k = j + 1; k < points.length; k++) {
   					if ((points[i].slopeTo(points[i + 1]) - points[i].slopeTo(points[k])) < 0.001) {
   						lineSegments[numSeg++] = new LineSegment(points[i], points[k]);
   					}
   				}
   			}
   		}
   	}
   	return lineSegments;
   	
   }
   
}
