package unittest;
import static org.junit.Assert.*;
import org.junit.Test;



import renderer.ImageWriter;
import primitives.*;

public class ImageWriterTest
{
	@Test
	public void write()
	{
	ImageWriter iw = new ImageWriter("imagetest1", 800, 500);
	for (int i = 0; i < 800; i++)
	{
		for (int j = 0; j < 500; j++)
		{
			if (i % 50 == 0)
			{
				iw.writePixel(i, j, new Color(232, 156, 240));
			}
			else if (j % 50 == 0)
			{
				iw.writePixel(i, j, new Color(247, 242, 242));
				
			}
			else
			{
				iw.writePixel(i, j, new Color(109, 103, 110));
			}
		}
	}
	iw.writeToImage();
	}
}
