package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends Geometry
{
	protected Point3D point;
	protected double radius;
	public Sphere(double r, Point3D p)
	{
		radius = r;
		point = p;
	}
	
	
	@Override
	public Vector getNormal()
	{
		return new Point3D(point.x,point.y,new Coordinate(point.z.coord+radius)).subtract(point);
	}
	
	
	public List<Point3D> findIntsersections(Ray ray)
	{
		Vector u = new Vector((point.x.coord - ray.point.x.coord), (point.y.coord - ray.point.y.coord), (point.z.coord - ray.point.z.coord));
		double tm = (ray.vector.getHead().x.coord * u.getHead().x.coord) + (ray.vector.getHead().y.coord * u.getHead().y.coord) + (ray.vector.getHead().z.coord * u.getHead().z.coord);
		double d = Math.sqrt((u.getHead().x.coord * u.getHead().x.coord) + (u.getHead().y.coord * u.getHead().y.coord) + (u.getHead().z.coord * u.getHead().z.coord) - (tm * tm));
		if (d >= radius)
		{
			return null;
		}
		
		double th = Math.sqrt((radius * radius) - (d * d));
		double t1 = tm + th;
		double t2 = tm - th;
		if ((t1 <= 0) && (t2 <= 0))
		{
			return null;
		}
		List<Point3D> lst = new ArrayList<Point3D>();
		if (t1 > 0)
		{
			Point3D p1 = new Point3D((ray.point.x.coord + (t1 * ray.vector.getHead().x.coord)), (ray.point.y.coord + (t1 * ray.vector.getHead().y.coord)), (ray.point.z.coord + (t1 * ray.vector.getHead().z.coord)));
			lst.add(p1);
		}
		if (t2 > 0)
		{
			Point3D p2 = new Point3D((ray.point.x.coord + (t2 * ray.vector.getHead().x.coord)), (ray.point.y.coord + (t2 * ray.vector.getHead().y.coord)), (ray.point.z.coord + (t2 * ray.vector.getHead().z.coord)));
			lst.add(p2);
		}
		return lst;
	}


	public Geometry setEmission(Color color)
	{
		this.emmission=color;
		return this;
	}


	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		Vector u = new Vector((point.x.coord - ray.point.x.coord), (point.y.coord - ray.point.y.coord), (point.z.coord - ray.point.z.coord));
		double tm = (ray.vector.getHead().x.coord * u.getHead().x.coord) + (ray.vector.getHead().y.coord * u.getHead().y.coord) + (ray.vector.getHead().z.coord * u.getHead().z.coord);
		double d = Math.sqrt((u.getHead().x.coord * u.getHead().x.coord) + (u.getHead().y.coord * u.getHead().y.coord) + (u.getHead().z.coord * u.getHead().z.coord) - (tm * tm));
		if (d >= radius)
		{
			return null;
		}
		
		double th = Math.sqrt((radius * radius) - (d * d));
		double t1 = tm + th;
		double t2 = tm - th;
		if ((t1 <= 0) && (t2 <= 0))
		{
			return null;
		}
		List<GeoPoint> lst = new ArrayList<GeoPoint>();
		if (t1 > 0)
		{
			GeoPoint p1 = new GeoPoint(this.setMaterial(getMaterial()),new Point3D((ray.point.x.coord + (t1 * ray.vector.getHead().x.coord)), (ray.point.y.coord + (t1 * ray.vector.getHead().y.coord)), (ray.point.z.coord + (t1 * ray.vector.getHead().z.coord))));
			lst.add(p1);
		}
		if (t2 > 0)
		{
			GeoPoint p2 = new GeoPoint(this.setMaterial(getMaterial()),new Point3D((ray.point.x.coord + (t2 * ray.vector.getHead().x.coord)), (ray.point.y.coord + (t2 * ray.vector.getHead().y.coord)), (ray.point.z.coord + (t2 * ray.vector.getHead().z.coord))));
			lst.add(p2);
		}
		return lst;
		
	}


}