package Visitor;

interface Visitor {
	public void visit(Point2d point);
	public void visit(Point3d point);
}

abstract class Point {
	private double metric = -1;
	
	public abstract void accept(Visitor v);
	
	public double getMetric () {
		return metric;
	}
	public void setMetric ( double metric ) {
		this.metric = metric;
	}
}

class Point2d extends Point {
	private double x;
	private double y;
	
	public Point2d(double x, double y) {
		this.x = x;
        this.y = y;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public double getX() { 
		return x; 
	}

	public double getY() { 
		return y; 
	}
}

class Point3d extends Point {
	private double x;
	private double y;
	private double z;
	
	public Point3d(double x, double y, double z) {
		this.x = x;
        this.y = y;
        this.z = z;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public double getX() { 
		return x;
	}
	
	public double getY() {
		return y; 
	}
	
	public double getZ() {
		return z; 
	}
}

class Euclid implements Visitor {
	public void visit(Point2d point) {
		point.setMetric(Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()));
	}
	
	public void visit(Point3d point) {
		point.setMetric(Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY() + point.getZ() * point.getZ()));
	}
}

class Chebyshev implements Visitor {
	public void visit(Point2d point) {
		double ax = Math.abs(point.getX());
		double ay = Math.abs(point.getY());
		point.setMetric( ax>ay ? ax : ay );
	}
	public void visit(Point3d point) {
		double ax = Math.abs(point.getX());
		double ay = Math.abs(point.getY());
		double az = Math.abs(point.getZ());
		double max = ax>ay ? ax : ay;
		if ( max<az )
			max = az;
		point.setMetric( max );
	}
}

public class VisitorDemo {
	public static void main(String[] args) {
		Point[] points = { new Point2d(1, 2), new Point3d(1, 2, 3)};
		Visitor[] visitors = { new Euclid(), new Chebyshev()};
		
		for (Point point: points) {
			for (Visitor v: visitors) {
				point.accept(v);
				System.out.println(point.getMetric());
			}
		}
	}
}
