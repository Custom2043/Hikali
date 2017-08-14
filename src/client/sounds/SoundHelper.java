package client.sounds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryJavaSound;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import client.options.SoundOptions;

public class SoundHelper 
{
	public static SoundSystem system;
	public static final int BUTTON_CLIC = 0,
							NOMBRE_SON = 1;
	public static Sound SONS[] = new Sound[NOMBRE_SON]; 
	public static void init() throws FileNotFoundException, IOException
	{
		try
		{
			if (SoundSystem.libraryCompatible(LibraryLWJGLOpenAL.class))
				SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
			else if (SoundSystem.libraryCompatible(LibraryJavaSound.class))
				SoundSystemConfig.addLibrary(LibraryJavaSound.class);
			else
				System.out.println("SoundHelper ; Aucun moteur de son utilisable !");
			SoundSystemConfig.setCodec("ogg", CodecJOrbis.class );
		}
		catch( SoundSystemException e )
		{
			System.err.println("SoundHelper ; Erreur de l'installation des plug-in sons");
		}
		system = new SoundSystem();
		
		SONS[BUTTON_CLIC] = loadSound("gui/Bouton.ogg", SoundOptions.MUSIQUE, "Bouton");
	}
	public static Sound loadSound(String nom, int c) throws IOException
	{
		System.out.println("SoundHelper ; Load Sound : "+new File("Ressources/sounds/"+nom).getAbsolutePath());
		system.loadSound(new File("Ressources/sounds/"+nom).toURI().toURL(), ".ogg");
		return new Sound(nom, c);
	}
	public static Sound loadSound(String nom, int c, String name) throws IOException
	{
		System.out.println("SoundHelper ; Load Omniscient Sound : "+new File("Ressources/sounds/"+nom).getAbsolutePath());
		system.loadSound(new File("Ressources/sounds/"+nom).toURI().toURL(), ".ogg");
		return new OmniscientSound(nom, c, name);
	}
	public static SoundSystem getSoundSystem()
	{
		return system;
	}
	public static void quit()
	{
		system.cleanup();
	}
}
