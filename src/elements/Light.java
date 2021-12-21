package elements;

import primitives.*;

abstract class Light 
{
	protected Color intensity =Color.BLACK;;
	protected Light(Color c)
	{
		intensity= c;
	}
	public Color getIntensity() {return intensity;}
	

}
