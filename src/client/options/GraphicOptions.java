package client.options;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import value.CustomValue;
import value.MultipleStateValue;
import value.SliderValue;

public class GraphicOptions 
{
	public static final int GRAPHIC = 0,
							ECLAIRAGE = 1,
							GUISIZE = 2,
							FULLSCREEN = 3,
							DISTANCE = 4,
							FPS = 5,
							LUMIERE = 6,
							PARTICULE = 7,
							NOMBRE_OPTIONS = 8;
	public CustomValue options[] = {
		new MultipleStateValue(0, new String[]{"Détaillés","Rapides"}),
		new MultipleStateValue(0, new String[]{"Maximum","Minimum"}),
		new MultipleStateValue(1, new String[]{"Petite","Normal","Grande"}),
		new MultipleStateValue(0,new String[]{"Non", "Oui"}),
		new SliderValue(2,16,8),
		new SliderValue(10,150,60),
		new SliderValue(0,100,100),
		new MultipleStateValue(0, new String[]{"Maximum","Minimum"})
	};
	public GraphicOptions(FileInputStream fis)
	{
		try 
		{
			for (int i=0;i<this.options.length;i++)
				this.options[i].value=fis.read();
		} 
		catch (Exception e) {e.printStackTrace();}
	}
	public void write(FileOutputStream fos)
	{
		try 
		{
			for (CustomValue i : this.options)
				fos.write(i.value);
		} 
		catch (IOException e) {e.printStackTrace();}
	}
	public int guiSize(){return this.options[2].value;}
	public int renderDistance(){return this.options[4].value;}
}
