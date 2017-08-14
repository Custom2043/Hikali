package client.drawer.gui;

import gui.CustomBouton;
import main.Client;
import main.Main;
import util.ScreenCoor;
import value.CustomValue;
import client.options.GraphicOptions;

public class GuiOptionsGraphics extends GuiDirtyBackgroundOrTransparent
{
	public GuiOptionsGraphics()
	{
		CustomValue[] valeur = Client.game.options.graphic.options;
		this.boutons.add(new Bouton("gui.button.ended", 0, ScreenCoor.screenGui(.5F,.83F,0,0,-100,0,200,20)));
		this.boutons.add(new MultipleStateBouton("gui.button.graphics", 1, ScreenCoor.screenGui(.5F,.5F,0,0,-154,-46,150,20), valeur[GraphicOptions.GRAPHIC].getMultiple()));
		this.boutons.add(new MultipleStateBouton("gui.button.smoothLight", 2, ScreenCoor.screenGui(.5F,.5F,0,0,-154,-22,150,20), valeur[GraphicOptions.ECLAIRAGE].getMultiple()));
		this.boutons.add(new MultipleStateBouton("gui.button.guiSize", 3, ScreenCoor.screenGui(.5F,.5F,0,0,-154,2,150,20), valeur[GraphicOptions.GUISIZE].getMultiple()));
		this.boutons.add(new MultipleStateBouton("gui.button.fullScreen", 4, ScreenCoor.screenGui(.5F,.5F,0,0,-154,26,150,20), valeur[GraphicOptions.FULLSCREEN].getMultiple()));

		this.boutons.add(new Slider("gui.button.renderDist", 5, ScreenCoor.screenGui(.5F,.5F,0,0,4,-46,150,20), valeur[GraphicOptions.DISTANCE].getSlider()));
		this.boutons.add(new Slider("gui.button.fps", 6, ScreenCoor.screenGui(.5F,.5F,0,0,4,-22,150,20), valeur[GraphicOptions.FPS].getSlider()));
		this.boutons.add(new Slider("gui.button.lightning", 7, ScreenCoor.screenGui(.5F,.5F,0,0,4,2,150,20), valeur[GraphicOptions.LUMIERE].getSlider()));
		this.boutons.add(new MultipleStateBouton("gui.button.particule", 8, ScreenCoor.screenGui(.5F,.5F,0,0,4,26,150,20), valeur[GraphicOptions.PARTICULE].getMultiple()));
	}

	public String getName()
	{
		return "gui.title.graphicOptions";
	}

	public boolean renderWorld(){return Client.game.isInGame();}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,
			CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && press)
			if (bouton.id == 0)
				Main.setGui(new GuiOptions());
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
		
	}
}
