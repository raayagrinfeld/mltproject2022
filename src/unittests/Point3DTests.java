/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
//import java.util.Vector;
import org.junit.Test;
import primitives.*;

/**
 * @author Shirah Mandelbaum
 * @author Raaya Greenfeld
 *
 */
public class Point3DTests {
	
	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Point3D)}.
	 */
    Point3D p1 = new Point3D(1, 2, 3);
    Point3D p2 = new Point3D(-6, -3, -2);
    Point3D p3 = new Point3D(8, 0.5, -3);
    Vector v2 = new Vector(-6.0, -3.0, -2.0);

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract()
	{
		Vector v = p1.subtract(p2);
		assertTrue("subtract() result is not accurate", v.equals(new Vector(7.0, 5.0, 5)));
	}

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Point3D pt = p1.add(v2);
    	assertTrue("add() resuly is not accurate", pt.equals(new Point3D(-5, -1, 1)));
	}

	/**
	 * Test method for {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {
		assertEquals("distanceSqured() result is not accurate", p1.distanceSquared(p2), (double)95);
	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {
		assertEquals("distance() result is not accurate", p1.distance(p2), Math.sqrt(95));
	}

}