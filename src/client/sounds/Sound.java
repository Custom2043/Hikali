package client.sounds;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound 
{
	public URL fichier;
	public int categorie;
	public Sound(String f, int c) throws MalformedURLException
	{
		this.fichier = new File(f).toURI().toURL();
		this.categorie = c;
	}
	public boolean isOmniscient()
	{
		return this instanceof OmniscientSound;
	}
	public OmniscientSound getOmni()
	{
		return (OmniscientSound)this;
	}
}
