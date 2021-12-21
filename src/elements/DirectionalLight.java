package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource
{
	private Vector direction;
	public DirectionalLight(Color c, Vector v) 
	{
		super(c);
		direction=v;
	}

	@Override
	public Color getIntensity(Point3D p) {
		return getIntensity();
	}

	@Override
	public Vector getL(Point3D p) {
		return direction;
	} 
	
	@Override
	public double getDistance(Point3D point)
	{
		return Double.POSITIVE_INFINITY;
	}
}
