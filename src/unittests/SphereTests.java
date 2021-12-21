package unittests;

import static org.junit.Assert.*;

import java.util.List;

//import java.util.Vector;
import primitives.*;
import org.junit.Test;

import geometries.Sphere;

public class SphereTests 
{
	 @Test
    public void testFindIntersections() 
    {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        
    	
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result1 = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result1.size());
        if (result1.get(0).getX() > result1.get(1).getX())
            result1 = List.of(result1.get(1), result1.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result1);

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p3 = new Point3D(0.78, 0.87, 0.45);
        List<Point3D> result2 = sphere.findIntsersections(new Ray(new Point3D(1.42, -0.55, 0.55), new Vector(-3.42, 7.55, -0.55)));
        assertEquals("Wrong number of points", 1, result2.size());
        assertEquals("Ray crosses sphere", List.of(p3), result2);
        
        // TC04: Ray starts after the sphere (0 points)
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(-1.19, 2.89, 0), new Vector(-2.4, 2.19, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point3D p4 = new Point3D(1.44, -0.74, 0.51);
        List<Point3D> result3 = sphere.findIntsersections(new Ray(new Point3D(0.5, 0.38, 0.78), new Vector(2.73, -3.26, -0.78)));
        assertEquals("Wrong number of points", 1, result3.size());
        assertEquals("Ray crosses sphere", List.of(p4), result3);
        
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(2.67, 4.17, 1.77))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Point3D p5 = new Point3D(1, -1, 0);
        Point3D p6 = new Point3D(1, 1, 0);
        List<Point3D> result4 = sphere.findIntsersections(new Ray(new Point3D(1, -2.34, 0), new Vector(0, 5.41, 0)));
        assertEquals("Wrong number of points", 2, result4.size());
        if (result4.get(0).getX() > result4.get(1).getX())
            result4 = List.of(result4.get(1), result4.get(0));
        assertEquals("Ray crosses sphere", List.of(p5, p6), result4);
        
        // TC14: Ray starts at sphere and goes inside (1 points)
        Point3D p7 = new Point3D(0.23, 0.64, 0);
        List<Point3D> result5 = sphere.findIntsersections(new Ray(new Point3D(1.77, -0.63, 0), new Vector(-3.97, 3.28, 0)));
        assertEquals("Wrong number of points", 1, result5.size());
        assertEquals("Ray crosses sphere", List.of(p7), result5); 
        
        // TC15: Ray starts inside (1 points)
        Point3D p8 = new Point3D(1, 0, -1);
        List<Point3D> result6 = sphere.findIntsersections(new Ray(new Point3D(1, 0, -0.75), new Vector(0, 0, -1.39)));
        assertEquals("Wrong number of points", 1, result6.size());
        assertEquals("Ray crosses sphere", List.of(p8), result6); 
        
        // TC16: Ray starts at the center (1 points)
        Point3D p9 = new Point3D(1.53, -0.85, 0);
        List<Point3D> result7 = sphere.findIntsersections(new Ray(new Point3D(1, 0, 0), new Vector(2.47, -3.91, 0)));
        assertEquals("Wrong number of points", 1, result7.size());
        assertEquals("Ray crosses sphere", List.of(p9), result7); 
        
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(1, 0, 1), new Vector(0, 0, 2))));
        
        // TC18: Ray starts after sphere (0 points)
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(1, 1.53, 1), new Vector(0, -3.53, 0))));
        
        // TC20: Ray starts at the tangent point
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 0, -5))));
        
        // TC21: Ray starts after the tangent point
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(1, 2.02, -1), new Vector(0, 2.51, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull("Ray's line out of sphere", sphere.findIntsersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, 6))));
    }
	 @Test
	    public void testGetNormal()  
	    {
		 Sphere sphere = new Sphere(1, new Point3D(0, 0, 1));
		 assertEquals("getNormal() is wrong", sphere.getNormal(), new Vector(0,0,1));
	    }

}