package unittests;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

//import java.util.Vector;
import primitives.*;
import org.junit.Test;

import geometries.Plane;

public class PlaneTests {
	@Test
	public void testGetNormal()
	{
		//TC0- nothing is wrong
		Plane p= new Plane(new Point3D(0,0,1),new Point3D(1,0,0),new Point3D(0,1,0));
		assertEquals("getNormal() is wrong", p.getNormal(), new Vector(1/Math.sqrt(3),1/Math.sqrt(3),1/Math.sqrt(3)));
		//TC1- two points on the same Ray
		Plane p1= new Plane(new Point3D(0,0,0),new Point3D(1,0,0),new Point3D(0,1,0));
		assertEquals("getNormal() is wrong- two points on the same Ray", p1.getNormal(), new Vector(0,0,1));
		//TC2- the first two points are same
		//Plane p2= new Plane(new Point3D(1,0,0),new Point3D(1,0,0),new Point3D(0,1,0));
		//assertThrows("getNormal() is wrong-two points are same", p2.getNormal(), new Vector(0,0,1));
	}
	@Test
	public void testFindIntersactions()
	{
		//EP-TC0- Ray intersects the plane
		Plane p= new Plane(new Point3D(0,0,1),new Point3D(1,0,0),new Point3D(0,1,0));
		assertEquals("FindIntersactions0() is wrong", p.findIntsersections(new Ray(new Point3D(-2,0,0), new Vector(3.89,-1.43,0))),new LinkedList<Point3D>().add(new Point3D(2.74,-1.74,0))) ;
		
		//EP-TC1-Ray does not intersect the plane
		assertEquals("FindIntersactions1() is wrong", p.findIntsersections(new Ray(new Point3D(-2,0,0), new Vector(-0.77,-0.9,0))),new LinkedList<Point3D>()) ;
		
		//BVA-Ray is parallel to the plane- included in the plane
		assertEquals("FindIntersactions2() is wrong", p.findIntsersections(new Ray(new Point3D(0,1,0), new Vector(1,-1,0))),new LinkedList<Point3D>()) ;
		
		//BVA-Ray is parallel to the plane- not included in the plane
		assertEquals("FindIntersactions3() is wrong", p.findIntsersections(new Ray(new Point3D(0,2,0), new Vector(2,-2,0))),new LinkedList<Point3D>()) ;
		
		//BVA-Ray is orthogonal to the plane-before the plane
		assertEquals("FindIntersactions4() is wrong", p.findIntsersections(new Ray(new Point3D(-2,2,2), new Vector(-2,-2,-2))),new LinkedList<Point3D>().add(new Point3D(-1,1,1))) ;
		
		//BVA-Ray is orthogonal to the plane-after the plane
		assertEquals("FindIntersactions5() is wrong", p.findIntsersections(new Ray(new Point3D(-2,0,0), new Vector(-1.83,-1.83,-1.83))),new LinkedList<Point3D>()) ;
			
		//BVA-Ray is orthogonal to the plane-in the plane
		assertEquals("FindIntersactions6() is wrong", p.findIntsersections(new Ray(new Point3D(-1,1,1), new Vector(-2.83,-2.83,-2.83))),new LinkedList<Point3D>().add(new Point3D(-1,1,1))) ;
		
		//BVA- Ray is neither orthogonal nor parallel to and begins at the plane
		assertEquals("FindIntersactions7() is wrong", p.findIntsersections(new Ray(new Point3D(-1,1,1), new Vector(9,2,1))),new LinkedList<Point3D>().add(new Point3D(-1,1,1))) ;
		
		//BVA- Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane 
				assertEquals("FindIntersactions8() is wrong", p.findIntsersections(new Ray(new Point3D(1,0,0), new Vector(7,3,2))),new LinkedList<Point3D>().add(new Point3D(1,0,0))) ;
				
		
	}

}
