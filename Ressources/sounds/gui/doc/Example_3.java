import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;
import paulscode.sound.libraries.LibraryJavaSound;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.codecs.CodecJOgg;

/**
 *  Demonstrates turning the listener.
 **/
public class Example_3
{
    public static void main( String[] args )
    {
        new Example_3();
    }

    public Example_3()
    {

        // Load some library and codec pluggins:
        try
        {
            SoundSystemConfig.addLibrary( LibraryLWJGLOpenAL.class );
            SoundSystemConfig.addLibrary( LibraryJavaSound.class );
            SoundSystemConfig.setCodec( "wav", CodecWav.class );
            SoundSystemConfig.setCodec( "ogg", CodecJOgg.class );
        }
        catch( SoundSystemException e )
        {
            System.err.println("error linking with the pluggins" );
        }
        // Instantiate the SoundSystem:
        SoundSystem mySoundSystem = new SoundSystem();

        // play looping explosions to the right:
        mySoundSystem.quickPlay( false, "explosion.wav", true,
                                 20, 0, 0,
                                 SoundSystemConfig.ATTENUATION_ROLLOFF,
                                 SoundSystemConfig.getDefaultRolloff() );

        // Turn the listener around:
        for( float angle = 0; angle < (float) Math.PI * 2; angle += .02 )
        {
            mySoundSystem.setListenerAngle( angle );
            sleep( 20 );
        }

        // Shut down:
        mySoundSystem.cleanup();
    }

    public void sleep( long milliseconds )
    {
        try
        {
            Thread.sleep( milliseconds );
        }
        catch( Exception e )
        {}
    }
}
