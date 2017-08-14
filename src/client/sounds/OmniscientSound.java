package client.sounds;

import java.net.MalformedURLException;

import paulscode.sound.SoundSystemConfig;

public class OmniscientSound extends Sound
{
	public String name;
	public OmniscientSound(String f, int c, String n) throws MalformedURLException 
	{
		super(f, c);
		this.name = n;
		SoundHelper.getSoundSystem().newSource(true, this.name, this.fichier, ".ogg", false, 0, 0, 0, SoundSystemConfig.ATTENUATION_NONE, 0);
	}
}
