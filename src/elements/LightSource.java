package elements;

import primitives.*;

public interface LightSource 
{
	public Color getIntensity(Point3D p);
	public Vector getL(Point3D p);
	double getDistance(Point3D point);
}
