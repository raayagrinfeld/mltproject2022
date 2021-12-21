package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

public abstract class RayTraceBase
{
	protected Scene scene;
	
	
	public RayTraceBase(Scene s)
	{
		this.scene = s;
	}
	
	
	public Color traceRay(Ray ray) 
	{
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null) return scene.background;
		GeoPoint closestPoint = ray. findClosestGeoPoint(intersections);
		//return calcColor(closestPoint, ray);
		return Color.BLACK;
	}
}

