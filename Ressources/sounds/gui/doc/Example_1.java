package main;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import paulscode.sound.codecs.CodecJOrbis;

public class Main {
	SoundSystem soundSystem = new SoundSystem();
	public Main() throws SoundSystemException
	{
		System.out.println(SoundSystem.libraryCompatible(LibraryLWJGLOpenAL.class));
		try
		{
			
			SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class );
			SoundSystemConfig.setCodec("ogg", CodecJOrbis.class );
		}
		
		catch( SoundSystemException e )
		{
			System.err.println("Erreur de l'installation des plug-in sons");
		}
		Class currentLibrary = SoundSystem.currentLibrary();
		String title = SoundSystemConfig.getLibraryTitle( currentLibrary );
		String description = SoundSystemConfig.getLibraryDescription( currentLibrary );
		
		soundSystem.backgroundMusic("Musique", "Ressources/Bouton.ogg", true);
		sleep(5);
		soundSystem.cleanup();
	}
	public void sleep( long seconds )
	{
		try
		{
		Thread.sleep( 1000 * seconds );
		}
		catch( InterruptedException e ){}
	}
	
	public static void main(String[] args) throws SoundSystemException 
	{
		new Main();
	}
}
