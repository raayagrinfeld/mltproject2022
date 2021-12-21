package unittests;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import primitives.*;
import geometries.*;
/**
 * @author Raaya Greenfeld
 * @author Shirah Mandelbaum
**/
public class TriangleTests {
	
	Point3D x= new Point3D(1,2,3);
	Point3D y= new Point3D(6,9,7);
	Point3D z= new Point3D(4,6,0);
	Triangle t= new Triangle(x,y,z);
	
	@Test
	 public void testGetNormal()
	 {
		Vector v1 = new Vector(5,7,4);//vector of points x & y
		 Vector v2 = new Vector(3,4,-3);//vector of points x & z
		 Vector v3= v1.crossProduct(v2).normalize();
	     assertEquals("getNormal() wrong result of the Plane", t.getNormal(),v3);
	 }
	
	@Test
	public void testFindIntersactions()
	{
		Triangle T = new Triangle(new Point3D(-3,0,0),new Point3D(0,0,3),new Point3D(0,3,0));
		//TC0 - the point inside polygon/triangle
		assertEquals("FindIntersactions() is wrong", t.findIntsersections(new Ray(new Point3D(0,0,0.35), new Vector(-0.59,1.65,0.76))),new LinkedList<Point3D>().add(new Point3D(-0.59,1.65,0.76)));
		
		//TC01- Outside against edge
		assertEquals("FindIntersactions() is wrong", t.findIntsersections(new Ray(new Point3D(-50,-50,-50), new Vector(3,3 ,0))),new LinkedList<Point3D>());
		
		//TC02- On edge's continuation
		assertEquals("FindIntersactions() is wrong", t.findIntsersections(new Ray(new Point3D(9,0,9), new Vector(3,0 ,3))),new LinkedList<Point3D>());
		
		//TC03- Outside against vertex
		
		
		//TC04- On edge
		assertEquals("FindIntersactions() is wrong", t.findIntsersections(new Ray(new Point3D(-1.57,1.43,0), new Vector(-0.53,-1.89,0))),new LinkedList<Point3D>().add(new Point3D(-1.57,1.43,0)));
		
		//TC05-In vertex
		assertEquals("FindIntersactions() is wrong", t.findIntsersections(new Ray(new Point3D(0,0,3), new Vector(2.47,0.54,-3))),new LinkedList<Point3D>().add(new Point3D(0,0,3)));
	}

}