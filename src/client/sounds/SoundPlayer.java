package client.sounds;

import static client.sounds.SoundHelper.SONS;
import static client.sounds.SoundHelper.system;

import java.util.ArrayList;
import java.util.List;

import main.Client;
import client.options.SoundOptions;

public class SoundPlayer
{
	public static SoundOptions option = Client.game.options.sound;
	public static List<SoundSource> inPlaying = new ArrayList<SoundSource>();
	public static void playOmniscientSound(int id)
	{
		if (SONS[id].isOmniscient())
		{
			system.setVolume(SONS[id].getOmni().name, option.volume[SONS[id].categorie].getPourcent());
			if (system.playing(SONS[id].getOmni().name))
				system.stop(SONS[id].getOmni().name);
			system.play(SONS[id].getOmni().name);
		}
	}
	public static void playSound(String sourcename, int id)
	{
		
	}
	public static void updateCategorieVolume(int categorie)
	{
		for (SoundSource son : inPlaying)
			if (son.categorie == categorie)
				system.setVolume(son.sourcename, option.volume[categorie].getPourcent());
	}
	public static void updateMasterVolume()
	{
		system.setMasterVolume(option.volume[SoundOptions.GENERAL].getPourcent());
	}
}
