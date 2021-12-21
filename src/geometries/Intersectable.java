package geometries;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;


public interface Intersectable
{
	public static class GeoPoint 
	{
		public Geometry geometry;
		public Point3D point;

		public GeoPoint (Geometry g, Point3D p)
		{
			geometry=g;
			point=p;
		}

		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof GeoPoint)) return false;
			GeoPoint other = (GeoPoint)obj;
			return this.geometry.equals(other.geometry) && this.point.equals(other.point);
		}
	}

	public List<GeoPoint> findGeoIntersections(Ray ray);
}

