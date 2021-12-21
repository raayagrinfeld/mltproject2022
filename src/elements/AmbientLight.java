package elements;

import primitives.Color;
import primitives.Point3D;

public class AmbientLight extends Light
{
	protected Color Ia;
	protected double Ka;
	
	public AmbientLight(Color i, double k)
	{
		super(i.reduce(1/k));
		Ia=i;
		Ka=k;
	}
	public double getDistance(Point3D point)
	{
		return Ka;
		
	}
}
