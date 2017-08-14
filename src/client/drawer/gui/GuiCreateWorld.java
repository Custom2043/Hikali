package client.drawer.gui;

import gui.CustomBouton;
import main.Client;
import main.Main;
import util.ScreenCoor;
import util.Translator;

public class GuiCreateWorld extends GuiDirtyBackgroundOrTransparent
{
	public GuiCreateWorld()
	{
		this.boutons.add(new Bouton("gui.button.createWorld", 0, ScreenCoor.screenGui(.5F,1,0,0,-154,-28,150,20)));
		this.boutons.add(new Bouton("gui.button.cancel", 1, ScreenCoor.screenGui(.5F,1,0,0,4,-28,150,20)));
		this.boutons.add(new ZoneTexte(Translator.translate("gui.button.newWorld"),(byte)2, ScreenCoor.screenGui(.5F,0,0,0,-100,60,200,20)));
	}
	public String getName() {return "gui.title.createWorld";}
	
	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn) 
	{
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && press) //Clic gauche
		{
			if (bouton.id == 0)
				Client.game.setLocalIngame(this.boutons.get(2).getTexte().texte);
			if (bouton.id == 1)
				Main.setGui(new GuiWorldMenu());
		}
	}
	@Override
	protected void keyboardEvent(char carac, int keyCode) {
		
	}
}
