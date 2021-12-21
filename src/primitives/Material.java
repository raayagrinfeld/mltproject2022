package primitives;

import java.util.List;

import geometries.Intersectable;

public class Material implements Intersectable
{
	public double kD;
	public double kS;
	public int nShininess;
	
	public double kT = 0.0;  // שקיפות מקדם
	public double kR = 0.0;  // השתקפות מקדם
	
	public Material()
	{
		kD=0;
		kS=0;
		nShininess=0;
	}
	
	public Material(double d, double s, int n)
	{
		kD=d;
		kS=s;
		nShininess=n;
	}
	
	public int getShininess() {
		return nShininess;
	}
	
	public double getKd() {
		return kD;
	}
	
	public double getKs() {
		return kS;
	}
	
	
	public Material setKd(double d) {
		kD=d;
		return this;
	}
	
	public Material setKs(double d) {
		kS=d;
		return this;
	}
	
	public Material setShininess(int i) 
	{
		nShininess=i;
		return this;
	}
	
	public Material setKt(double t)
	{
		kT = t;
		return this;
	}
	
	public Material setKr(double r)
	{
		kR = r;
		return this;
	}

	public double getKt() {
		return kT;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
