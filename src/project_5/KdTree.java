package project_5;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	
	private class KdNode<Point2D> {
		private KdNode<Point2D> left;
		private KdNode<Point2D> right;
		private Point2D value;
		private RectHV rect;
		
		public KdNode (Point2D point, RectHV rect) {
			this.value = point;
			this.left = null;
			this.right = null;
			this.rect = rect;
		
		}
		
		public void setRight(KdNode<Point2D> right) {
			this.right = right;
		}
		
		public void setLeft(KdNode<Point2D> left) {
			this.left = left;
		}
		
		public KdNode<Point2D> getRight() {
			return right;
		}
		
		public KdNode<Point2D> getLeft() {
			return left;
		}
		
		public Point2D getPoint() {
			return value;
		}
		
		public void setRect(RectHV rect) {
			this.rect = rect;
		}
		
		public RectHV getRect() {
			return rect;
		}
	}
	
	private KdNode<Point2D> root;
	private int numCount;
	private Queue<Point2D> keys;
	
	public KdTree() {
		this.root = null;
		this.numCount = 0;
		this.keys = new Queue<>();
	}

	public boolean isEmpty() { // is the set empty? 
	   	
	   	return numCount == 0;
   }
	
   public int size() { // number of points in the set 
   	
   	return numCount;
   }
   
   public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
   	
   	if (isEmpty()) {
   		root = new KdNode(p, new RectHV(0.0, 0.0, 1.0, 1.0));
   		numCount++;
   	}
   	root = insert(p, root, root.getRect(), true);
   	
   }
   
   private KdNode<Point2D> insert(Point2D p, KdNode<Point2D> r, RectHV rect, Boolean dimX) {

   	if (dimX) { // use X dimension
   		
   		if (r == null) {
   			keys.enqueue(p);
   			numCount++;
   			return new KdNode(p, rect);
   		}
   		
   		if (p.x() < r.getPoint().x()) {
   			rect = new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
   			r.setLeft(insert(p, r.getLeft(), rect, !dimX));
   			return r;
   		} else {
   			rect = new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax());
   			r.setRight(insert(p, r.getRight(), rect, !dimX));
   			return r;
   		}
   		
   	} else {
   		
   		if (r == null) {
   			return new KdNode(p, rect);
   		}
   		
   		if (p.y() < r.getPoint().y()) {
   			rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
   			r.setLeft(insert(p, r.getLeft(), rect, !dimX));
   			return r;
   		} else {
   			rect = new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
   			r.setRight(insert(p, r.getRight(), rect, !dimX));
   			return r;
   		}
   		
   	}
   }
   
   
   public boolean contains(Point2D p) {  // does the set contain point p? 
   	
   	return contains(p, root, true);
   	
   }
   
   private boolean contains(Point2D p, KdNode<Point2D> r, boolean dimX) {
   	
   	if (r == null) {
   		return false;
   	} else {
   		
   		if (dimX) {
   			if (isEqual(p.x(), r.getPoint().x())) {
   				return true;
   			}
   			if (p.x() < r.getPoint().x()) {
   				return contains(p, r.getLeft(), !dimX);
   			} else {
   				return contains(p, r.getRight(), !dimX);
   			}
   		} else {
   			if (isEqual(p.y(), r.getPoint().y())) {
   				return true;
   			}
   			if (p.y() < r.getPoint().y()) {
   				return contains(p, r.getLeft(), !dimX);
   			} else {
   				return contains(p, r.getRight(), !dimX);
   			}
   			
   		}
   		
   	}
   	
   	
   }
   
   private boolean isEqual(double x, double y) {
   	return (x - y > -0.00000000001) && (x - y < 0.00000000001);
   }
   
   public void draw() {                         // draw all points to standard draw 
   	
   	for (Point2D point : keys) {
   		point.draw();
   	}
   	
   }
   
   public Iterable<Point2D> range(RectHV rect) {  // all points that are inside the rectangle 
   	
   	Queue<Point2D> inRange = new Queue<>();
   	inRange = range(rect, root, true, inRange);
   	return inRange;
   }
   
   private Queue<Point2D> range(RectHV rect, KdNode<Point2D> r, boolean dimX, Queue<Point2D> inrange) {
   	
   	if (r == null) {
   		return inrange;
   	}
   	
   	if (!rect.contains(r.getPoint())) {
   		if (dimX) {
   			if (rect.xmax() < r.getPoint().x()) {
   				return range(rect, r.getLeft(), !dimX, inrange);
   			} else {
   				return range(rect, r.getRight(), !dimX, inrange);
   			}	
   		} else {
   			if (rect.ymax() < r.getPoint().y()) {
   				return range(rect, r.getLeft(), !dimX, inrange);
   			} else {
   				return range(rect, r.getRight(), !dimX, inrange);
   			}	
   		}
   		
   	} else {
   		inrange.enqueue(r.getPoint());
			inrange = range(rect, r.getLeft(), !dimX, inrange);
			inrange = range(rect, r.getRight(), !dimX, inrange);
			return inrange;	
   	}
   	
   }
   public Point2D nearest(Point2D p) {  // a nearest neighbor in the set to point p; null if the set is empty 
   	
   	if (isEmpty()) {
   		return null;
   	}
   	
   	KdNode<Point2D> nearest = root;
   	nearest = nearest(p, root, root, true);
   	return nearest.getPoint();
   	
   }
   
   private KdNode<Point2D> nearest(Point2D p, KdNode<Point2D> r, KdNode<Point2D> min, boolean dimX) {
   	// p: query point
   	// r: current root
   	// min: current nearest point
   	// dimX: use X for seperation
   	
   	KdNode<Point2D> nearest = min;
   	
   	if (r == null) {
   		return nearest;
   	}
   	if (p.distanceSquaredTo(r.getPoint()) < p.distanceSquaredTo(nearest.getPoint())) {
   		nearest = r; // query closer to current node, update the nearest
   	}
   	if (dimX) {
   		if (p.x() < r.getPoint().x()) { // query point at the left of current root
   			nearest = nearest(p, r.getLeft(), nearest, !dimX);
   			if (r.getRight() != null && 
   					p.distanceSquaredTo(nearest.getPoint()) > r.getRight().getRect().distanceSquaredTo(p)){
   				nearest = nearest(p, r.getRight(), nearest, !dimX);
   			}
   			return nearest;
   		} else {
   			nearest = nearest(p, r.getRight(), nearest, !dimX);
   			if (r.getLeft() != null && 
   					p.distanceSquaredTo(nearest.getPoint()) > r.getLeft().getRect().distanceSquaredTo(p)){
   				nearest = nearest(p, r.getLeft(), nearest, !dimX);
   			}
   		}
   		
   	} else {
   		if (p.y() < r.getPoint().y()) {
   			nearest = nearest(p, r.getLeft(), nearest, !dimX);
   			if (r.getRight() != null && 
   					p.distanceSquaredTo(nearest.getPoint()) > r.getRight().getRect().distanceSquaredTo(p)){
   				nearest = nearest(p, r.getRight(), nearest, !dimX);
   			}
   		} else {
   			nearest = nearest(p, r.getRight(), nearest, !dimX);
   			if (r.getLeft() != null && 
   					p.distanceSquaredTo(nearest.getPoint()) > r.getLeft().getRect().distanceSquaredTo(p)){
   				nearest = nearest(p, r.getLeft(), nearest, !dimX);
   			}
   			
   		}
   	}
   	return nearest;	
   }
	
	   public static void main(String[] args) {                  // unit testing of the methods (optional) 
	   	
	   	
	   }
	
}
