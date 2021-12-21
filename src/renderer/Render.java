
package renderer;


import primitives.Color;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import elements.*;
import scene.*;

public class Render
{
	public ImageWriter imageWriter;
	public Scene scene;
	public Camera camera;
	public RayTraceBasic rayTracer;

	public Render setImageWriter(ImageWriter imageWr)
	{
		this.imageWriter = imageWr;
		return this;
	}

	public Render setScene(Scene s)
	{
		this.scene = s;
		return this;
	}

	public Render setCamera(Camera c)
	{
		this.camera = c;
		return this;
	}

	public Render setRayTracer(RayTraceBasic rayTr)
	{
		this.rayTracer = rayTr;
		return this;
	}

	public void renderImage()
	{
		try {
			//if (this.scene == null)
			//throw new MissingResourceException("scene can't be null", new String(), new String());

			if (this.imageWriter == null)
				throw new MissingResourceException("imageWriter can't be null", new String(), new String());

			if (this.rayTracer == null)
				throw new MissingResourceException("rayTracer can't be null", new String(), new String());

			if (this.camera == null)
				throw new MissingResourceException("camera can't be null", new String(), new String());
		}
		catch(MissingResourceException msg)
		{
			System.out.println(msg.getMessage());
		}


	}

	public void printGrid(int interval, Color color)
	{
		try {
			if (this.imageWriter == null)
				throw new MissingResourceException("imageWriter can't be null", new String(), new String());
		}
		catch(MissingResourceException msg)
		{
			System.out.println(msg.getMessage());
		}

		ImageWriter iw = new ImageWriter("imagetest2", 800, 500);
		for (int i = 0; i < 800; i++)
		{
			for (int j = 0; j < 500; j++)
			{
				if ((i % interval == 0)||(i % interval == interval-1))
				{
					iw.writePixel(i, j, color);
				}
				if ((j % interval == 0)||(j % interval == interval-1))
				{
					iw.writePixel(i, j, color);
				}
			}
		}
		iw.writeToImage();
	}

	public void writeToImage()
	{
		try {
			if (this.imageWriter == null)
				throw new MissingResourceException("imageWriter can't be null", new String(), new String());
		}
		catch(MissingResourceException msg)
		{
			System.out.println(msg.getMessage());
		}
	}


}




