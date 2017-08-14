package client.drawer.gui;

import gui.CustomBouton;

import java.io.File;

import main.Main;

import org.lwjgl.input.Mouse;

import util.ScreenCoor;

public class GuiRename extends GuiDirtyBackgroundOrTransparent
{
	public String name;
	public GuiRename(String worldName)
	{
		super();
		this.name = worldName;
		this.boutons.add(new Bouton("gui.button.cancel", 0, ScreenCoor.screenGui(.5F,1,0,0,4,-28,150,20)));
		this.boutons.add(new Bouton("gui.button.ended", 1, ScreenCoor.screenGui(.5F,1,0,0,-154,-28,150,20)));
		this.boutons.add(new ZoneTexte(this.name,2, ScreenCoor.screenGui(.5F,0,0,0,-100,60,200,20)));
	}

	public String getName()
	{
		return "gui.title.rename";
	}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState())
			if (bouton.id == 0)
				Main.setGui(new GuiWorldMenu());
			else if (bouton.id == 1)
			{
				new File("Saves/"+this.name).renameTo(new File("Saves/"+this.boutons.get(2).getTexte().texte));
				Main.setGui(new GuiWorldMenu());
			}
		
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
	}

}
