package project_5;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	
	private BST<Point2D, Integer> points; // Integer is used to fulfill the requirement
	
   public PointSET() {
   	
   	this.points = new BST<>();
 	
   }
   public boolean isEmpty() { // is the set empty? 
   	
   	return points.isEmpty();
   }
   public int size() { // number of points in the set 
   	
   	return points.size();
   }
   public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
   	
   	if (!points.contains(p)) {
   		points.put(p, 0);
   	}
   	
   	
   }
   public boolean contains(Point2D p) {  // does the set contain point p? 
   	
   	return points.contains(p);
   	
   }
   public void draw() {                         // draw all points to standard draw 
   	
   	for (Point2D point : points.keys()) {
   		point.draw();
   	}
   	
   }
   
   public Iterable<Point2D> range(RectHV rect) {  // all points that are inside the rectangle 
   	
   	Queue<Point2D> inRange = new Queue<>();
		if (!isEmpty()) {
			for (Point2D point : points.keys()) {
				if (rect.contains(point)) {
					inRange.enqueue(point);
				}
			}
   	}
   	return inRange;
   	
   }
   public Point2D nearest(Point2D p) {  // a nearest neighbor in the set to point p; null if the set is empty 
   	
   	if (points.isEmpty()) {
   		return null;
   	}
   	
   	double distance = Double.MAX_VALUE;
   	Point2D nearest = null;
   	for (Point2D point : points.keys()) {
   		if (p.distanceTo(point) < distance) {
   			nearest = point;
   			distance = p.distanceTo(point);
   		}
   	}
   	return nearest;
   	
   }

   public static void main(String[] args) {                  // unit testing of the methods (optional) 
   	
   	
   }
   
   

}
