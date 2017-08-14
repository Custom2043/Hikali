package client.drawer.gui;

import gui.CustomBouton;
import main.Client;
import main.Main;

import org.lwjgl.input.Mouse;

import util.ScreenCoor;
import value.SliderValue;
import client.options.SoundOptions;
import client.sounds.SoundPlayer;

public class GuiOptionsSound extends GuiDirtyBackgroundOrTransparent
{
	public GuiOptionsSound()
	{
		SliderValue[] volume = Client.game.options.sound.volume;
		this.boutons.add(new Slider("gui.button.generalVolume", 0, ScreenCoor.screenGui(.5F,.5F,0,0,-155,-34,310,20), volume[SoundOptions.GENERAL]));
		this.boutons.add(new Slider("gui.button.musicVolume", 1, ScreenCoor.screenGui(.5F,.5F,0,0,-155,-10,150,20), volume[SoundOptions.MUSIQUE]));
		this.boutons.add(new Slider("gui.button.playerVolume", 2, ScreenCoor.screenGui(.5F,.5F,0,0,-155,14,150,20), volume[SoundOptions.JOUEUR]));
		this.boutons.add(new Slider("gui.button.blocsVolume", 3, ScreenCoor.screenGui(.5F,.5F,0,0,5,-10,150,20), volume[SoundOptions.BLOCS]));
		this.boutons.add(new Bouton("gui.button.ended", 4, ScreenCoor.screenGui(.5F,.83F,0,0,-100,0,200,20)));
	}

	public String getName()
	{
		return "gui.title.musicNSound";
	}

	public boolean renderWorld(){return Client.game.isInGame();}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (clicID == 0 && !Mouse.getEventButtonState())
			for (int i = 0;i<this.boutons.size()-1;i++)
				if(this.boutons.get(i).isActiv())
					if (i == 0)
						SoundPlayer.updateMasterVolume();
					else
						SoundPlayer.updateCategorieVolume(i);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState())
			if (bouton.id == 4)
				Main.setGui(new GuiOptions());
		
	}
	@Override
	protected void keyboardEvent(char carac, int keyCode) {
	}
}
