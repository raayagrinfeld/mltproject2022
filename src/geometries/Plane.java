package geometries;
import java.util.List;
import  java.util.ArrayList;

import primitives.*;

public class Plane extends Geometry 
{
	protected Point3D q0;
	protected Vector normal;
	public Plane(Point3D x, Point3D y, Point3D z)
	{
		q0=x;
		Vector v1= x.subtract(y);
		Vector v2= x.subtract(z);
		normal=v1.crossProduct(v2).normalize();
	}
	
	public Plane(Point3D q, Vector v)
	{
		q0=q;
		normal=v;
	}
	
	@Override
	public Vector getNormal() {
		return normal;
	}
	
	public List<Point3D> findIntsersections(Ray ray)
	{
		double help1 = (normal.getHead().x.coord * (q0.x.coord - ray.point.x.coord)) + (normal.getHead().y.coord * (q0.y.coord - ray.point.y.coord)) + (normal.getHead().z.coord * (q0.z.coord - ray.point.z.coord));
		double help2 = (normal.getHead().x.coord * ray.vector.getHead().x.coord) + (normal.getHead().y.coord * ray.vector.getHead().y.coord) + (normal.getHead().z.coord * ray.vector.getHead().z.coord);
		double t = help1 / help2;
		if (t <= 0)
		{
			return null;
		}
		List<Point3D> lst = new ArrayList<Point3D>();
		Point3D p = new Point3D((ray.point.x.coord + (t * ray.vector.getHead().x.coord)), (ray.point.y.coord + (t * ray.vector.getHead().y.coord)), (ray.point.z.coord + (t * ray.vector.getHead().z.coord)));
		lst.add(p);
		return lst;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		double help1 = (normal.getHead().x.coord * (q0.x.coord - ray.point.x.coord)) + (normal.getHead().y.coord * (q0.y.coord - ray.point.y.coord)) + (normal.getHead().z.coord * (q0.z.coord - ray.point.z.coord));
		double help2 = (normal.getHead().x.coord * ray.vector.getHead().x.coord) + (normal.getHead().y.coord * ray.vector.getHead().y.coord) + (normal.getHead().z.coord * ray.vector.getHead().z.coord);
		double t = help1 / help2;
		if (t <= 0)
		{
			return null;
		}
		List<GeoPoint> lst = new ArrayList<GeoPoint>();
		GeoPoint p = new GeoPoint(this.setMaterial(this.getMaterial()),new Point3D((ray.point.x.coord + (t * ray.vector.getHead().x.coord)), (ray.point.y.coord + (t * ray.vector.getHead().y.coord)), (ray.point.z.coord + (t * ray.vector.getHead().z.coord))));
		lst.add(p);
		return lst;
	}


}