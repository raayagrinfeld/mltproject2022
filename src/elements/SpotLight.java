package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight
{
	private Vector direction;
	
	protected SpotLight(double c, double l, double q, Color co, Point3D p, Vector v)
	{
		super(c, l, q, co, p);
		direction=v;
	}
	
	public SpotLight(Color co, Point3D p, Vector v)
	{
		super(1, 0, 0, co, p);
		direction=v;
	}
	
	public SpotLight setKl(double d) {
		kL=d;
		return this;
	}
	
	@Override
	public Vector getL(Point3D p) {
		return direction;
	}
	
	public LightSource setKq(double d) {
		kQ=d;
		return this;
	}

}
