package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

public class Scene 
{
	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;
	public List<LightSource> lights;
	public Scene (String n)
	{
		name=n;
		geometries=new Geometries();
		background= Color.BLACK;
		lights= new LinkedList<LightSource>();
	}
	public Scene setAmbientLight(AmbientLight ambientLight2) {
		ambientLight=ambientLight2;
		return this;
	}
	public Scene setBackground(Color color) {
		background=color;
		return this;
	}
	
	

}
