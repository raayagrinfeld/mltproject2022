package unittests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


public class RayTests {

	@Test
	public void testFindClosestPoint() 
	{		
		// fail("Not yet implemented");
		Ray ray = new Ray(new Point3D(-1.13, -6.34, 4.27), new Vector(3.87, 10.59, -4.27));
		Point3D p1 = new Point3D(0.47, -1.98, 2.51);
		Point3D p2 = new Point3D(1.6, 01.13, 01.26);
		Point3D p3 = new Point3D(2.23, 2.84, 0.57);
		Point3D p4 = new Point3D(3.21, 5.54, -0.52);
		// points lists
		List<Point3D> pl1 = Arrays.asList(p2, p1, p3, p4); // 1st case
		List<Point3D> pl2 = new ArrayList<>(); // 2nd case
		List<Point3D> pl3 = Arrays.asList(p1, p2, p3, p4); // 3rd case
		List<Point3D> pl4 = Arrays.asList(p4, p2, p3, p1); // 4th case
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: a point in the middle of the list is the closest
		assertEquals("closest point found is not the closest.", ray.findClosestPoint(pl1), p1);
		
		// =============== Boundary Values Tests ==================
		// TC02: list is empty (should return null)
		assertEquals("no closest point", ray.findClosestPoint(pl2), null);
		
		// TC03: first point in the list is the closest
		assertEquals("closest point found is not the closest.", ray.findClosestPoint(pl3), p1);
		
		// TC04: last point in the list is the closest
		assertTrue("closest point found is not the closest.", ray.findClosestPoint(pl4).equals(p1));
	
	}

}