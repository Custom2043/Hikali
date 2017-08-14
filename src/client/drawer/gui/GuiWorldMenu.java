package client.drawer.gui;

import gui.CustomBouton;
import gui.CustomSelecter;

import java.io.File;
import java.util.ArrayList;

import main.Client;
import main.Main;

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Mouse;

import util.ScreenCoor;

public class GuiWorldMenu extends GuiDarkDirt
{
	public GuiWorldMenu()
	{
		super(new Barre("Barre", 0, ScreenCoor.screenGui(.5f, 0,0,1,110,32,4,-96)));
		//C�t� Gauche
		this.boutons.add(new Bouton("gui.button.play", 1, ScreenCoor.screenGui(.5F,1,0,0,-154,-52,150,20), false));
		this.boutons.add(new Bouton("gui.button.rename", 2, ScreenCoor.screenGui(.5F,1,0,0,-154,-28,72,20), false));
		this.boutons.add(new Bouton("gui.button.delete", 3, ScreenCoor.screenGui(.5F,1,0,0,-76,-28,72,20), false));
		//C�t� Droit
		this.boutons.add(new Bouton("gui.button.createWorld", 4, ScreenCoor.screenGui(.5F,1,0,0,4,-52,150,20)));
		this.boutons.add(new Bouton("gui.button.ended", 5, ScreenCoor.screenGui(.5F,1,0,0,4,-28,150,20)));

		this.loadWorlds();
	}
	public void drawOverBackground()
	{

	}
	public String getName() {return "gui.title.worldChoice";}
	public void loadWorlds()
	{
		this.barre.boutons = new ArrayList<CustomBouton>();
		int i = 0;
		File files[] = new File("Saves").listFiles();
		for (File file : files)
		{
			this.barre.addBoutonAndHeight(new WorldButton(file.getName(), i+this.boutons.size(), ScreenCoor.screenGui(.5F,0,0,0,-100,42+25*i,200,20)), 25);
			i++;
		}
	}
	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (clicID == 0 && Mouse.getEventButtonState())
			if (bouton == null){}
			else if (bouton.id == 1) // Jouer
			{
				if (bouton.isActiv())
					Client.game.setLocalIngame(CustomSelecter.selected.nom);
			}
			else if (bouton.id == 2) // Renommer
			{
				if (bouton.isActiv())
					Main.setGui(new GuiRename(CustomSelecter.selected.nom));
			}
			else if (bouton.id == 3) // Supprimer
			{
				if (bouton.isActiv())
				{
					try
					{
						FileUtils.deleteDirectory(new File("Saves/"+CustomSelecter.selected.nom));
						this.boutons.get(1).desactiv();
						this.boutons.get(2).desactiv();
						this.boutons.get(3).desactiv();
					}
					catch (Exception e) {System.out.println("GuiWorldMenu ; Unable to delete repertory : "+"Saves/"+CustomSelecter.selected.nom);}
					this.loadWorlds();
				}
			}
			else if (bouton.id == 4)
				Main.setGui(new GuiCreateWorld());
			else if (bouton.id == 5)
				Main.setGui(new GuiMainMenu());
			else if (bouton.id != 0)
			{
				this.boutons.get(1).activ();
				this.boutons.get(2).activ();
				this.boutons.get(3).activ();
			}
		
	}
	@Override
	protected void keyboardEvent(char carac, int keyCode) {
		
	}
}
