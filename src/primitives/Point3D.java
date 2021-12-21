package primitives;

import primitives.Point3D;
import primitives.Vector;

public class Point3D 
{
	public static final Point3D ZERO =  new Point3D(0,0,0);
	public Coordinate x;
	public Coordinate y;
	public Coordinate z;
	
	
	public Point3D(Coordinate x1, Coordinate y1, Coordinate z1)
	{
		x = x1;
		y = y1;
		z = z1;
	}
	
	public Point3D(double x1, double y1, double z1)
	{
		x = new Coordinate(x1);
		y = new Coordinate(y1);
		z = new Coordinate(z1);
	}

	public Vector subtract(Point3D p)
	{
		Vector v = new Vector(this.x.coord - p.x.coord, this.y.coord - p.y.coord, this.z.coord - p.z.coord);
		return v;
	}
	public Point3D add(Vector v)
	{
		Point3D p = new Point3D(this.x.coord + v.head.x.coord, this.y.coord + v.head.y.coord, this.z.coord + v.head.z.coord);
		return p;
	}
	public double distanceSquared(Point3D p)
	{
		double helpX = this.x.coord - p.x.coord;
		helpX = helpX * helpX;
		double helpY = this.y.coord - p.y.coord;
		helpY = helpY * helpY;
		double helpZ = this.z.coord - p.z.coord;
		helpZ = helpZ * helpZ;
		return helpX + helpY + helpZ;
	}
	public double distance(Point3D p)
	{
		double help = this.distanceSquared(p);
		return Math.sqrt(help);
	}
	
	@Override
	public boolean equals(Object obj) 
	{
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point3D)) return false;
	      Point3D other = (Point3D)obj;
	      return this.x.equals(other.x) && this.y.equals(other.y) && this.z.equals(other.z);
    }
	@Override
	public String toString()
	{
		String s = "x =" + this.x.toString() + ", y = " + this.y.toString() + ", z =" + this.z.toString();
		return s;
	}

	public double getX() {
		return x.coord;
	}
}