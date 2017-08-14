package client.drawer.gui;

import gui.CustomBouton;
import main.Client;
import main.Main;

import org.lwjgl.input.Mouse;

import util.ScreenCoor;

public class GuiOptions extends GuiDirtyBackgroundOrTransparent
{
	public GuiOptions()
	{
		this.boutons.add(new Slider("gui.button.fov", 0, ScreenCoor.screenGui(.5F,.1667F,0,0,-154,-10,150,20),Client.game.options.fov));
		this.boutons.add(new Slider("gui.button.sensi", 1, ScreenCoor.screenGui(.5F,.1667F,0,0, 4,-10,150,20),Client.game.options.sensibilite));
		this.boutons.add(new Bouton("gui.button.musiqueNsound", 2, ScreenCoor.screenGui(.5F,.5F,0,0,-154, -22,150,20)));
		this.boutons.add(new Bouton("gui.button.language", 3, ScreenCoor.screenGui(.5F,.5F,0,0,-154, 2,150,20)));
		this.boutons.add(new Bouton("gui.button.graphicParam", 4, ScreenCoor.screenGui(.5F,.5F,0,0,4, -22,150,20)));
		this.boutons.add(new Bouton("gui.button.control", 5, ScreenCoor.screenGui(.5F,.5F,0,0,4,2,150,20)));
		this.boutons.add(new Bouton("gui.button.ended", 6, ScreenCoor.screenGui(.5F,.83F,0,0,-100,0,200,20)));
	}

	public String getName()
	{
		return "gui.title.options";
	}
	public void quit()
	{
		Client.game.options.write();
	}
	public boolean renderWorld(){return Client.game.isInGame();}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,
			CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState()) //Clic gauche
		{
			if (bouton.id == 2)
				Main.setGui(new GuiOptionsSound());
			if (bouton.id == 3)
				Main.setGui(new GuiOptionsLang());
			if (bouton.id == 4)
				Main.setGui(new GuiOptionsGraphics());
			if (bouton.id == 5)
				Main.setGui(new GuiOptionsKey());
			if (bouton.id == 6) // Ternimé
				if (Client.game.isInGame())
					Main.setGui(new GuiGameMenu());
				else
					Main.setGui(new GuiMainMenu());
		}
		
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
		// TODO Auto-generated method stub
		
	}
}
