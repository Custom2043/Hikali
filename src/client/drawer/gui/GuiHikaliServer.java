package client.drawer.gui;

import gui.CustomBouton;
import main.Main;
import util.ScreenCoor;

public class GuiHikaliServer extends Gui
{
	public GuiHikaliServer()
	{
		this.boutons.add(new ServerBouton("Quitter", 0, ScreenCoor.screenGui(.5f,.5f,0,0,-100,-10,200,20)));
	}

	public String getName()
	{
		return "Server";
	}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,
			CustomBouton boutonOn) {
		CustomBouton b = this.getBoutonWithCoor(X, Y);
		if (clicID == 0 && b != null)
			if (b.id == 0)
				Main.stop();
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
		// TODO Auto-generated method stub
		
	}

}
