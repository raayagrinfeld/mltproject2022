package geometries;
import java.util.List;

import primitives.*;

public class Triangle extends Polygon
{
	protected Point3D ver1;
	protected Point3D ver2;
	protected Point3D ver3;
	
	
	public Triangle(Point3D v1, Point3D v2, Point3D v3)
	{
		super(v1,v2,v3);
	}
	
	public boolean equals(Object obj) 
	{
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point3D)) return false;
	      Triangle other = (Triangle)obj;
	      return this.ver1.equals(other.ver1) && this.ver2.equals(other.ver2) && this.ver3.equals(other.ver3);
    }
	
	public String toString()
	{
		String s = "vertex 1 =" + this.ver1.toString() + ", vertex 2 = " + this.ver2.toString() + ", vertex 3 =" + this.ver3.toString();
		return s;
	}
	
	public List<Point3D> findIntsersections(Ray ray)
	{
		List<Point3D> lst = super.findIntsersectionsTriangle(ray);
		
		if (lst == null) return null;

		return lst;
		
	}

	public Geometry setEmission(Color color) {
		emmission=color;
		return this;
	}

}