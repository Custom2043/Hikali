package client.drawer.gui;

import gui.CustomBouton;
import gui.CustomSelecter;

import java.io.File;

import main.Main;

import org.lwjgl.input.Mouse;

import util.ScreenCoor;
import util.Translator;

public class GuiOptionsLang extends GuiDarkDirt
{
	public GuiOptionsLang()
	{
		super(new Barre("Barre", 0, ScreenCoor.screenGui(.5f, 0,0,1,148,32,4,-64)));

		this.boutons.add(new Bouton("gui.button.ended", 1, ScreenCoor.screenGui(.5f,1,0,0,-100,-26,200,20)));

		byte i = 0;
		File files[] = new File("Languages").listFiles();
		WorldButton b;
		this.barre.addMax(10);
		for (File file : files)
			if (file.getName().endsWith(".lang"))
			{
				b = new WorldButton(file.getName().substring(0, file.getName().length()-5), (i+this.boutons.size()), ScreenCoor.screenGui(.5F,0,0,0,-100,42+25*i,200,20));
				if (file.getName().equals(Translator.currentFile))
					b.activ();
				this.barre.addBoutonAndHeight(b, 25);
				i++;
			}
		this.barre.addMax(5);
	}
	public void drawOverBackground(){}
	public String getName() {return "gui.title.languages";}
	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn) 
	{
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState())
			if (bouton.id == 1) // Termin�
			{
				if (CustomSelecter.selected != null)
					Translator.loadLangage(CustomSelecter.selected.nom + ".lang");
				Main.setGui(new GuiOptions());
			}
		
	}
	protected void keyboardEvent(char carac, int keyCode) {
	}
}
