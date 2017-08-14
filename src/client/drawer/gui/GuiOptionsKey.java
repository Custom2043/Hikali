package client.drawer.gui;

import static drawer.CustomDrawer.getGuiSize;
import gui.CustomBouton;
import main.Client;
import main.Main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import util.ScreenCoor;
import util.Translator;
import value.KeyValue;
import client.drawer.GuiDrawer;
import client.options.KeyCategorie;

public class GuiOptionsKey extends GuiDarkDirt
{
	public GuiOptionsKey()
	{
		super(new Barre("Barre", 0, ScreenCoor.screenGui(.5f, 0,0,1,148,32,4,-96)));
		this.boutons.add(new Bouton("gui.button.ended", 1, ScreenCoor.screenGui(.5F, 1,0,0,-100,-30,200,20)));
		this.boutons.add(new Bouton("gui.button.allReset", 2,ScreenCoor.screenGui(.5F, 1,0,0,-100,-54,200,20)));
		int i = this.boutons.size();
		for (KeyCategorie cate : Client.game.options.keys.keys)
		{
			this.barre.addMax(24);
			for (KeyValue option : cate.getAllKeys())
			{
				this.barre.addBoutonAndHeight(new KeyBouton(option.nom, i, ScreenCoor.screenGui(.5F,0,0,0,10,this.barre.getMaxNoScale() + 32,76,20), option), 0);
				this.barre.addBoutonAndHeight(new Bouton("gui.button.reset", i+1, ScreenCoor.screenGui(.5F,0,0,0,90,this.barre.getMaxNoScale() + 32,50,20)), 22);
				i+=2;
			}
		}
	}

	public String getName()
	{
		return "gui.title.control";
	}

	public void drawOverBackground()
	{
		int y = 32;
		for (KeyCategorie cate : Client.game.options.keys.keys)
		{
			GuiDrawer.drawStringWithShadow(Display.getWidth()/2+10*Client.game.options.graphic.guiSize(),(y+15)*getGuiSize()-this.barre.getValue(),Translator.translate(cate.getName()));
			y += 22 * cate.getKeyNumber() + 24;
		}
	}

	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press, CustomBouton boutonOn) 
	{
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState())
			if (bouton.id == 1) // Annuler
				Main.setGui(new GuiOptions());
			else if (bouton.id == 2)
				for (KeyCategorie cate : Client.game.options.keys.keys)
					for (KeyValue option : cate.getAllKeys())
						option.reset();
			else if (bouton.nom.equals("gui.button.reset"))
				this.getBoutonWithID(bouton.id-1).getKey().key.reset();
		
	}

	@Override
	protected void keyboardEvent(char carac, int keyCode) {
	}
}
