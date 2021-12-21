package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Vector;

public class Ray 
{
	
	public Point3D point;
	public Vector vector;
	
	private static final double DELTA = 0.1;
	
	public Ray(Point3D p, Vector vec)
	{
		vector= vec.normalized();
		point=p;
		
	}
	
	public Ray(Point3D head, Vector direction, Vector normal)
	{
		if (direction.dotProduct(normal) == 0)
		{
			// Question: what happens if isZero(direction·normal) is zero?
		}
		if (direction.dotProduct(normal) > 0)
		{
			point = head.add(normal.scale(DELTA));
		}
		else point = head.add(normal.scale(-DELTA));
		
		vector = direction;
	}
	
	public Point3D findClosestPoint(List<Point3D> pl)
	{
		// return the closest point
		
		if (pl == null || pl.isEmpty()) return null;
		
		double minDistance = Double.MAX_VALUE;
		int minDistanceIndex = 0;
		for (int i = 0; i < pl.size(); i++)
		{
			if (pl.get(i).distance(this.point) < minDistance)
			{
				minDistance = pl.get(i).distance(this.point);
				minDistanceIndex = i;
			}
		}
		return pl.get(minDistanceIndex);
	}
	public GeoPoint findClosestGeoPoint(List<GeoPoint> pl)
	{
        if (pl == null || pl.isEmpty()) return null;
		
		double minDistance = Double.MAX_VALUE;
		int minDistanceIndex = 0;
		for (int i = 0; i < pl.size(); i++)
		{
			if (pl.get(i).point.distance(this.point) < minDistance)
			{
				minDistance = pl.get(i).point.distance(this.point);
				minDistanceIndex = i;
			}
		}
		return pl.get(minDistanceIndex);
	}

	public Vector getDir() {
		// TODO Auto-generated method stub
		return null;
	}
}
