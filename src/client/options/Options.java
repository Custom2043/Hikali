package client.options;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import util.Translator;
import value.SliderValue;

public class Options 
{
	public static final String OPTIONFILE = "Options.sco";
	public SliderValue fov = new SliderValue(30,110,70), sensibilite = new SliderValue(50,200,100);
	public SoundOptions sound;
	public GraphicOptions graphic;
	public KeyOptions keys;
	public Translator trans;
	public Options()
	{
		try 
		{
			FileInputStream fis = new FileInputStream(new File(OPTIONFILE));
			this.fov.value = fis.read();
			this.sensibilite.value = fis.read();
			this.sound = new SoundOptions(fis);
			this.graphic = new GraphicOptions(fis);
			this.keys = new KeyOptions(fis);
			this.trans = new Translator(fis);
			IOUtils.closeQuietly(fis);
		} 
		catch (Exception e) {e.printStackTrace();}
	}
	public void write()
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream(new File(OPTIONFILE));
			fos.write(this.fov.value);
			fos.write(this.sensibilite.value);
			this.sound.write(fos);
			this.graphic.write(fos);
			this.keys.write(fos);
			Translator.write(fos);
			IOUtils.closeQuietly(fos);
		} 
		catch (IOException e) {e.printStackTrace();}
	}
}
