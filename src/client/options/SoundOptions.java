package client.options;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import value.SliderValue;

public class SoundOptions 
{
	public static final int GENERAL = 0,
							MUSIQUE = 1,
							JOUEUR = 2,
							BLOCS = 3;
	public static final int NOMBRE_CATEGORY = 4;
	public SliderValue volume[] = {
			new SliderValue(0,100,50),
			new SliderValue(0,100,50),
			new SliderValue(0,100,50),
			new SliderValue(0,100,50)};
	public SoundOptions(FileInputStream fis)
	{
		try 
		{
			for (int i=0;i<this.volume.length;i++)
				this.volume[i].value=fis.read();
		} 
		catch (Exception e) {e.printStackTrace();}
	}
	public void write(FileOutputStream fos)
	{
		try 
		{
			for (SliderValue i :this.volume)
				fos.write(i.value);
		} 
		catch (IOException e) {e.printStackTrace();}
	}
}
