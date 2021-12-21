package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import java.math.*;

public class PointLight extends Light implements LightSource
{
	private Point3D position;
	private double kC = 1;
	protected double kL = 0;
	protected double kQ = 0;
	
	protected PointLight(double c, double l, double q, Color co,Point3D p) 
	{
		super(co);
		position=p;
		kC=c;
		setkL(l);
		kQ=q;	
	}
	
	public PointLight(Color c, Point3D p)
	{
		super(c);
		kC=0;
		kL=1;
		kQ=0;
	}
	
	@Override
	public Color getIntensity(Point3D p) {
		return intensity;
	}

	@Override
	public Vector getL(Point3D p) {
		return null;
	}

	public double getkL() 
	{
		return kL;
	}

	public PointLight setkL(double kL) 
	{
		this.kL = kL;
		return this;
	} 
	
	@Override
	public double getDistance(Point3D point)
	{
		double d = Math.sqrt(((position.x.coord - point.x.coord) * (position.x.coord - point.x.coord)) + ((position.y.coord - point.y.coord) * (position.y.coord - point.y.coord)) + ((position.z.coord - point.z.coord) * (position.z.coord - point.z.coord)));
		return d;
	}

	public LightSource setKq(double d) 
	{
		kQ=d;
		return this;
	}
}
