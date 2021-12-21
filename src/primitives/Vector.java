package primitives;

import primitives.Point3D;
import primitives.Vector;

public class Vector
{
	protected Point3D head;
	
	public Vector(Coordinate x, Coordinate y, Coordinate z) throws IllegalArgumentException
	{
			this.head = new Point3D(x, y, z);
			if(head.equals(new Point3D(0,0,0)))
			{
				throw new IllegalArgumentException("the vector can't be a zero");
			}
	}
	
	public Vector(double x, double y, double z) throws IllegalArgumentException
	{
			this.head = new Point3D(x, y, z);
			if(head.equals(new Point3D(0,0,0)))
			{
		      throw new IllegalArgumentException("the vector can't be a zero");
			}
	}
	
	public Vector(Point3D h) throws IllegalArgumentException
	{
			if(h.equals(new Point3D(0,0,0)))
			{
		      throw new IllegalArgumentException("the vector can't be a zero");
			}
		
		this.head=h;
	}
	
	public Point3D getHead()
	{
		return this.head;
	}
	
	@Override
	public boolean equals(Object obj) // is it right?
	{
      if (this == obj) return true;
      if (obj == null) return false;
      if (!(obj instanceof Vector)) return false;
      Vector other = (Vector)obj;
      return this.head.equals(other.head);
    }

	
	public Vector add(Vector v)
	{
		Vector v2 = new Vector(this.head.add(v));
		return v2;
	}
	
	public Vector subtract(Vector v)
	{
		return this.head.subtract(v.getHead());
	}
	
	public Vector scale(double d)
	{
		double x = this.head.x.coord * d;
		double y = this.head.y.coord * d;
		double z = this.head.z.coord * d;
		return new Vector(x, y, z);
	}
	
	public Vector crossProduct(Vector v)
	{
		Coordinate x = new Coordinate(this.head.y.coord * v.head.z.coord - this.head.z.coord*v.head.y.coord);
		Coordinate y = new Coordinate(this.head.z.coord * v.head.x.coord - this.head.x.coord * v.head.z.coord);
		Coordinate z = new Coordinate(this.head.x.coord * v.head.y.coord - this.head.y.coord * v.head.x.coord);
		return new Vector(x, y, z);
	}
	
	public double dotProduct(Vector v)
	{
		return this.head.x.coord * v.head.x.coord + this.head.y.coord * v.head.y.coord
				+ this.head.z.coord * v.head.z.coord;
	}
	
	public double lengthSquared()
	{
		return head.distanceSquared(new Point3D(0,0,0));
	}

	public double length()
	{
		return head.distance(new Point3D(0,0,0));
	}
	
	public Vector normalize()
	{
		head = this.normalized().head;
		return this;
	}
	
	public Vector normalized()
	{
		return this.scale(1/this.length());
	}
	
}