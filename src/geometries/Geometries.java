package geometries;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class Geometries implements Intersectable 
{
	protected List<Intersectable> shapesList;
	public Geometries()
	{
		shapesList= new LinkedList<Intersectable>();
	}
	public Geometries(Intersectable... geometries)
	{
		shapesList= List.of(geometries);
	}
	public void add(Intersectable... geometries)
	{
		shapesList= List.of(geometries);
	}

	public List<Point3D> findIntsersections(Ray ray) {
		return null;
	}
	public List<GeoPoint> findGeoIntersections(Ray ray) 
	{
	 List<GeoPoint> intersections = null;
	for (Intersectable geometry: shapesList)
	{
		 List<GeoPoint> geoIntersections = (geometry).findGeoIntersections(ray);
	     if( geoIntersections != null)
	    	 intersections = geoIntersections;
	}
	return intersections;
}

}