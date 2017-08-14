package client.drawer.gui;

import gui.CustomBouton;
import main.Client;
import main.Main;
import util.ScreenCoor;

public class GuiConnectIP extends GuiDirtyBackgroundOrTransparent
{
	public GuiConnectIP()
	{
		super();
		this.boutons.add(new Bouton("gui.button.connect", 0,ScreenCoor.screenGui(.5F,1,0,0,-154,-28,150,20)));
		this.boutons.add(new Bouton("gui.button.cancel", 1,ScreenCoor.screenGui(.5F,1,0,0,4,-28,150,20)));
		this.boutons.add(new ZoneTexte("",2, ScreenCoor.screenGui(.5F,0,0,0,-100,60,200,20)));
	}

	public String getName()
	{
		return "gui.title.connectIP";
	}

	public void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn)
	{
		if (boutonOn != null && clicID == 0 && press)
			if (boutonOn.id != -1)
			{
				if (boutonOn.id == 0)
					Client.game.setDistanceInGame(this.getBoutonWithID(2).getTexte().texte);
				if (boutonOn.id == 1)
					Main.setGui(new GuiMenuServer());
			}
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
	}
}
