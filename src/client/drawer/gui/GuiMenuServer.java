package client.drawer.gui;

import gui.CustomBouton;
import main.Main;

import org.lwjgl.input.Mouse;

import util.ScreenCoor;

public class GuiMenuServer extends GuiDarkDirt
{
	public GuiMenuServer()
	{
		super(new Barre("Barre", 0, ScreenCoor.screenGui(.5f, 0,0,1,110,32,4,-96)));
		this.boutons.add(new Bouton("gui.button.play", 1, ScreenCoor.screenGui(.5F,1,0,0,-154,-52,150,20), false));
		this.boutons.add(new Bouton("gui.button.rename", 2, ScreenCoor.screenGui(.5F,1,0,0,-154,-28,72,20), false));
		this.boutons.add(new Bouton("gui.button.delete", 3, ScreenCoor.screenGui(.5F,1,0,0,-76,-28,72,20), false));
		this.boutons.add(new Bouton("gui.button.connectByIP", 4, ScreenCoor.screenGui(.5F,1,0,0,4,-52,150,20)));
		this.boutons.add(new Bouton("gui.button.ended", 5, ScreenCoor.screenGui(.5F,1,0,0,4,-28,150,20)));

	}

	public String getName() {
		return "gui.title.serverChoice";
	}

	@Override
	public void drawOverBackground() {
	}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,
			CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState()) //Clic gauche
		{
			if (bouton.id == 4)
				Main.setGui(new GuiConnectIP());
			if (bouton.id == 5)
				Main.setGui(new GuiMainMenu());
		}
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
	}
}
