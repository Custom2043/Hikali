package client.drawer.gui;

import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;
import main.Main;

import org.lwjgl.input.Mouse;

import util.QuadColor;
import util.ScreenCoor;
import util.TextureCoor;
import client.drawer.GuiDrawer;
import drawer.ScreenCoorModel;

public class GuiMainMenu extends Gui
{
	private ScreenCoorModel panorama, title;
	public GuiMainMenu()
	{
		super();
		panorama = new ScreenCoorModel(ScreenCoor.AllScreen, new QuadColor(), TextureCoor.allPicture, GuiDrawer.FONDMAINMENU);
		title = new ScreenCoorModel(ScreenCoor.screenGui(.5f,0,0,0,-GuiDrawer.HIKALI_TITLE.getImageWidth()/2,30,GuiDrawer.HIKALI_TITLE.getImageWidth(),GuiDrawer.HIKALI_TITLE.getImageHeight()), new QuadColor(), TextureCoor.allPicture, GuiDrawer.HIKALI_TITLE);
		this.boutons.add(new Bouton("gui.button.solo", 0, ScreenCoor.screenGui(0.5F,0.25F,0,0,-100,48,200,20)));
		this.boutons.add(new Bouton("gui.button.multi", 1, ScreenCoor.screenGui(0.5F,0.25F,0,0,-100,72,200,20)));
		this.boutons.add(new Bouton("gui.button.options", 2, ScreenCoor.screenGui(0.5F,0.25F,0,0,-100,108,96,20)));
		this.boutons.add(new Bouton("gui.button.quit", 3, ScreenCoor.screenGui(0.5F,0.25F,0,0,4,108,96,20)));
	}
	public void drawBeforeButtons()
	{
		drawModel(panorama);
		drawModel(title);
	}
	public String getName() {return "gui.title.mainMenu";}
	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,
			CustomBouton boutonOn) {
		CustomBouton bouton = this.getBoutonWithCoor(X, Y);
		if (bouton != null && clicID == 0 && Mouse.getEventButtonState())
			if (bouton.id == 0)
				Main.setGui(new GuiWorldMenu());
			else if (bouton.id == 1)
				Main.setGui(new GuiMenuServer());
			else if (bouton.id == 2)
				Main.setGui(new GuiOptions());
			else if (bouton.id == 3)
				Main.stop();
	}
	@Override
	protected void keyboardEvent(char carac, int keyCode) {
	}
}
