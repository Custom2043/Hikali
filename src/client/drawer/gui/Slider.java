package client.drawer.gui;

import static client.drawer.GuiDrawer.drawHoveredButtonName;
import static client.drawer.GuiDrawer.drawInactivButtonName;
import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;
import gui.CustomSlider;
import util.MouseHelper;
import util.ScreenCoor;
import util.Translator;
import value.SliderValue;
import client.sounds.SoundHelper;
import client.sounds.SoundPlayer;
import drawer.ShaderProgram;

public class Slider extends CustomSlider
{
	public Slider(String n, int i, ScreenCoor c, SliderValue b)
	{
		super(n, i, c, b);
	}
	public void draw()
	{
		Bouton.shader.start();
		Bouton.shader.loadCoor(coor);
		Bouton.shader.loadState(0);
		drawModel(Bouton.model);
		Bouton.shader.loadState(1);
		Bouton.shader.loadCoor(coor.addXGui((int)((this.coor.wGui-2*LARGEUR)*this.getPos())).setWGui(2*LARGEUR));
		drawModel(Bouton.model);
		ShaderProgram.stop();
		if (this.coor.isIn(MouseHelper.getXMouse(), MouseHelper.getYMouse()))
			drawHoveredButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2,this.getName());
		else
			drawInactivButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2, this.getName());
	}
	public String getName()
	{
		return Translator.translate(this.nom)+" : "+this.barre.value;
	}
	public void extraClassicalActions(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y)
	{
		if (clicID == 0)
			if (!appuie)
				if (this.isActiv)
					SoundPlayer.playOmniscientSound(SoundHelper.BUTTON_CLIC);
	}
}
