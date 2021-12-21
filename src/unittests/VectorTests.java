/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
//import java.util.Vector;
import primitives.*;
import org.junit.Test;


public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-6, -3, -2);
    Vector v3 = new Vector(8, 0.5, -3);
	
    @Test
	public void testAdd() {
    	Vector vr = v1.add(v2);
    	assertTrue("add() resuly is not accurate", vr.equals(new Vector(-5, -1, 1)));
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract()
	{
		Vector vr= v1.subtract(v2);
		assertTrue("subtract() result is not accurate", vr.equals(new Vector(7.0, 5.0, 5)));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale()
	{
		Vector vr = v1.scale(3);
		assertEquals("scale() result not accurate", v1.length()*3, vr.length(), 0.00001);
		// if the scalar equals zero, an exception will be thrown from Vector constructor
	}

	/**
	Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", (vr.dotProduct(v1)==0));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", (vr.dotProduct(v2)==0));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v3));
        try {
             v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }


	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		double product = v1.dotProduct(v3);
		assertEquals("dotProduct() result is not accurate", product, 0,0.000000001);
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		assertEquals("LengthSquared() result not accutate", v1.lengthSquared(), 14, 0.000000001);
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		assertEquals("Length() result not accutate", v1.length(),(double)7, 0.000000001);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		assertEquals("normalize() result not accurate", v2.normalize(), new Vector((double)-6/7, (double)-3/7,(double)-2/7));
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		assertEquals("normalized() result not accurate", v2.normalize(), new Vector((double)-6/7, (double)-3/7,(double)-2/7));
	}

}