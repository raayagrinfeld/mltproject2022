package elements;
import primitives.*;

public class Camera
{
	Point3D p0;
	Vector vRight;
	Vector vUp;
	Vector vTo;
	protected double width;
	protected double height;
	protected double distance;
	
	
	
	public Camera(Point3D p, Vector vu, Vector vt)
	{
		try
		{
			if (vu.dotProduct(vt) != 0)
			{
				throw new IllegalArgumentException("vUp not orthogonal to vTo");
			}
			this.vTo = vu.normalize();
			this.vUp = vt.normalize();
			this.vRight = vt.crossProduct(vu).normalize();
			this.p0 = p;
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public Camera setViewPlaneSize(double w, double h)
	{
		this.width = w;
		this.height = h;
		return this;
	}
	
	public Camera setDistance(double d)
	{
		// distance between view plane and camera
		this.distance = d;
		return this;
	}
	
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
	{
		//Vector pC = new Vector(p0).add((this.vTo.scale(this.distance)));
		Point3D pC = p0.add(this.vTo.scale(-this.distance));
		
		double rY = this.height / nY;
		double rX = this.width / nX;
		
		
		double yI = -(i-(nY-1)/2)*rY;
		double xJ = (j-(nX-1)/2)*rX;
		Point3D pIJ = pC.add(this.vRight.scale(xJ)
				.add(this.vUp.scale(-yI)));
		
		Vector vIJ = p0.subtract(pIJ);
		return new Ray(p0,vIJ);
	}
	
	public Point3D getP0()
	{
		return this.p0;
	}
	
	public Vector getVRight()
	{
		return this.vRight;
	}
	
	public Vector getVUp()
	{
		return this.vUp;
	}
	
	public Vector getVTo()
	{
		return this.vTo;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public double getDistance()
	{
		return this.distance;
	}
}