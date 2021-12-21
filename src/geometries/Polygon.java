package geometries;
import  java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point3D> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point3D... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		try
		{
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (int i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public Vector getNormal() {
		return plane.getNormal();
	}
	
	public List<Point3D> findIntsersections(Ray ray)
	{
		List<Point3D> lst = new ArrayList<Point3D>();
		for (int i = 1; i < vertices.size()-1; i++)
		{
			Polygon t = new Polygon(vertices.get(0), vertices.get(i), vertices.get(i+1));
			lst = t.findIntsersectionsTriangle(ray);
			if (lst != null)
			{
				return lst;
			}
		}
		return null;
	}
	
	public List<Point3D> findIntsersectionsTriangle(Ray ray)
	{
		// check intersection with plane
		Plane trianglePlane = new Plane(this.vertices.get(0), this.vertices.get(1), this.vertices.get(2));
		List<Point3D> lst = trianglePlane.findIntsersections(ray);
		
		if (lst == null) return null;
		
		// else - check if the point is inside triangle
		Vector v1 = this.vertices.get(0).subtract(ray.point);
		Vector v2 = this.vertices.get(1).subtract(ray.point);
		Vector v3 = this.vertices.get(2).subtract(ray.point);
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		// check if point inside. yes: return 
		if (Math.signum(ray.vector.dotProduct(n1)) == Math.signum(ray.vector.dotProduct(n2))
				&& Math.signum(ray.vector.dotProduct(n2)) == Math.signum(ray.vector.dotProduct(n3)))
		{
			return lst;
		}
		return null;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// check intersection with plane
				Plane trianglePlane = new Plane(this.vertices.get(0), this.vertices.get(1), this.vertices.get(2));
				List<GeoPoint> lst = trianglePlane.findGeoIntersections(ray);
				
				if (lst == null) return null;
				
				// else - check if the point is inside triangle
				Vector v1 = this.vertices.get(0).subtract(ray.point);
				Vector v2 = this.vertices.get(1).subtract(ray.point);
				Vector v3 = this.vertices.get(2).subtract(ray.point);
				
				Vector n1 = v1.crossProduct(v2).normalize();
				Vector n2 = v2.crossProduct(v3).normalize();
				Vector n3 = v3.crossProduct(v1).normalize();
				// check if point inside. yes: return 
				if (Math.signum(ray.vector.dotProduct(n1)) == Math.signum(ray.vector.dotProduct(n2))
						&& Math.signum(ray.vector.dotProduct(n2)) == Math.signum(ray.vector.dotProduct(n3)))
				{
					return lst;
				}
				return null;
	}
	
}