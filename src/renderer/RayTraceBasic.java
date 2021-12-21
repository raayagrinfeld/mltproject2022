package renderer;
import scene.Scene;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import elements.LightSource;
import geometries.*;
import geometries.Intersectable.GeoPoint;

public class RayTraceBasic extends RayTraceBase
{
	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10; // may need to change according to understanding
	private static final double MIN_CALC_COLOR_K = 0.001; // important to understand for protection
	private static final double INITIAL_K = 1.0;


	public RayTraceBasic(Scene s)
	{
		super(s);
	}
	
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) 
	{
		Color color = intersection.geometry.getEmmision();
		color = color.add(calcLocalEffects(intersection, ray, k));
		return 1 == level ? color : color.add(
				calcGlobalEffects(intersection, ray.vector, level, k));
	}

	private Color calcColor(GeoPoint gp, Ray ray)
	{
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());
	}

	
	private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
		Color color = Color.BLACK;
		Vector n = gp.geometry.getNormal();
		Material material = gp.geometry.getMaterial();
		double kkr = k * material.kR;
		if (kkr > MIN_CALC_COLOR_K)
			color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
		double kkt = k * material.kT;
		if (kkt > MIN_CALC_COLOR_K)
			color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n),
					level, material.kT, kkt));
		return color;
	}

	private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx)
	{
		GeoPoint gp = findClosestIntersection(ray);
		return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}

	private Ray constructRefractedRay(Point3D point, Vector l, Vector n) {
		/*double cos1= l.dotProduct(n)/(l.length()*n.length());
		double cos2=0;
		Vector r= n.scale((cos1-cos2)+DELTA).subtract(l);*/
		return new Ray(point.add(new Vector(1, 1, 1).scale(-DELTA)), l);
	}

	private Ray constructReflectedRay(Point3D point, Vector l, Vector n) {
		// check this
		Vector r = l.subtract(n.scale(l.dotProduct(n)*2));
		return new Ray(point.add(new Vector(1, 1, 1).scale(DELTA)), r);
	}

	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k)
	{
		Color color = intersection.geometry.getEmmision();
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal();
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0) return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.getShininess();
		for (LightSource lightSource : scene.lights)
		{
		Vector l = lightSource.getL(intersection.point);
		double nl = Util.alignZero(n.dotProduct(l));
		if (nl * nv > 0) { // sign(nl) == sign(nv)
		double ktr = transparency(lightSource, l, n, intersection);
		if (ktr * k > MIN_CALC_COLOR_K)
		{
		Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
		color = color.add(calcDiffusive(material.kD, l, n, lightIntensity),
		calcSpecular(material.kS, l, n, v, nShininess, lightIntensity));
		}
		}
		}
		// ...
		return color;
		}


	private Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r= l.subtract(n.scale(2*l.dotProduct(n)));
		return lightIntensity.scale(Math.pow(Math.max(0, r.dotProduct(v.scale(-1))),nShininess));
	}

	private Color calcDiffusive(double kD, Vector l, Vector n, Color lightIntensity) 
	{
		return lightIntensity.scale(kD*Math.abs(l.dotProduct(n)));
	}

	private GeoPoint findClosestIntersection(Ray ray)
	{
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if(intersections!=null)
		{
		   return ray.findClosestGeoPoint(intersections);
		}
		return null;
	}

	@Override
	public Color traceRay(Ray r)
	{
		if (scene.geometries.findIntsersections(r) == null)
			return scene.background;
		else
		{
			GeoPoint closestPoint = findClosestIntersection(r);
			return calcColor(closestPoint, r);
			//GeoPoint  p = r.findClosestGeoPoint(scene.geometries.findGeoIntersections(r));
			//return calcColor(p,r);
			// 
			//return closestPoint == null ? scene.background : 
		}
	}

	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint)
	{
		Vector lightDirection = l.scale(-1); // from point to light source

		Ray lightRay = new Ray(geopoint.point, lightDirection, n); // new ctor required
		double lightDistance = light.getDistance(geopoint.point);
		var intersections = scene.geometries.findGeoIntersections(lightRay);

		if (intersections == null) return 1.0;

		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
			if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
				ktr *= gp.geometry.getMaterial().getKt();
				if (ktr < MIN_CALC_COLOR_K) return 0.0;
			}
		}
		return ktr;
	}

	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = 
				n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
		Point3D point = geopoint.point.add(delta);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = 
				scene.geometries.findGeoIntersections(lightRay);

		if (intersections != null)
		{
			double lightDistance = light.getDistance(geopoint.point);

			for (GeoPoint gp : intersections) 
			{
				if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) == 0)
				{
					return false;
				}
			}
		}
		return true;
	}
}
