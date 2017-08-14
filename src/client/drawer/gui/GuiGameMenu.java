package client.drawer.gui;

import gui.CustomBouton;
import main.Client;
import main.Main;

import org.lwjgl.input.Keyboard;

import util.ScreenCoor;

public class GuiGameMenu extends GuiDirtyBackgroundOrTransparent
{
	public GuiGameMenu()
	{
		super();
		this.boutons.add(new Bouton("gui.button.quitGame", 0,ScreenCoor.screenGui(.5F,.5F,0,0,-100,38,200,20)));
		this.boutons.add(new Bouton("gui.button.options", 1,ScreenCoor.screenGui(.5F,.5F,0,0,-100,14,200,20)));
		this.boutons.add(new Bouton("gui.button.backToGame", 2,ScreenCoor.screenGui(.5F,.5F,0,0,-100,-34,200,20)));
	}

	public String getName()
	{
		return "gui.title.gameMenu";
	}
	public boolean renderWorld(){return true;}
	public void type()
	{
		String keyName = Client.game.options.keys.getNameForValue(Keyboard.getEventKey());
		if (keyName.equals("key.pause"))
			Main.setGui(new GuiIngame());
	}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,CustomBouton boutonOn) 
	{
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && press) //Clic gauche
		{
			if (bouton.id == 0)
				Client.game.quitServer();
			if (bouton.id == 1)
				Main.setGui(new GuiOptions());
			if (bouton.id == 2)
				Main.setGui(new GuiIngame());
		}
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {		}
}
