package geometries;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;
//import static primitives.Ray.*;
//import static primitives.Vector*;
//import static primitives.Piont3D*;
public abstract class Geometry implements  Intersectable
{
	protected Color emmission;
	protected Material material;
	abstract public  Vector getNormal();
	public abstract List<GeoPoint> findGeoIntersections(Ray ray);
	public Color getEmmision() {return emmission;}
	public Color setEmmissiom(Color color)
	{
		emmission=color;
		return emmission;
	}
	public Material getMaterial() {return material;}
	public Geometry setMaterial(Material m) 
	{
		material=m;
		return this;}
}
