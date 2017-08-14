package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.drawHoveredButtonName;
import static client.drawer.GuiDrawer.drawInactivButtonName;
import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;
import gui.CustomMultipleStateBouton;
import util.QuadColor;
import util.ScreenCoor;
import util.TextureCoor;
import util.Translator;
import value.MultipleStateValue;
import client.drawer.GuiDrawer;
import client.sounds.SoundHelper;
import client.sounds.SoundPlayer;
import drawer.ScreenCoorModel;
import drawer.ShaderProgram;

public class MultipleStateBouton extends CustomMultipleStateBouton
{
	public MultipleStateBouton(String n, int i, ScreenCoor c, MultipleStateValue o)
	{
		super(n, i, c, o);
	}
	public void draw()
	{
		Bouton.shader.start();
		Bouton.shader.loadState(!isActiv ? 0 : isHovered() ? 2 : 1);
		Bouton.shader.loadCoor(coor);
		drawModel(Bouton.model);
		ShaderProgram.stop();
		if (!this.isActiv)
			drawInactivButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2,this.getName());
		else if (this.isHovered())
			drawHoveredButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2,this.getName());
		else
			drawCenteredStringWithShadow(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2,this.getName());	
	}
	public void extraClassicalActions(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y)
	{
		if (clicID == 0)
			if (appuie)
				if (boutonOn == this)
					SoundPlayer.playOmniscientSound(SoundHelper.BUTTON_CLIC);
	}
	public String getName() {return Translator.translate(this.nom) + " : "+this.options.noms[this.options.value];}
	public void keyTyped(char arg0, int arg1) {}
	public ScreenCoorModel getModel() 
	{
		return new ScreenCoorModel(
				new ScreenCoor[]{this.coor.addWGui(-this.coor.wGui/2), this.coor.addWGui(-this.coor.wGui/2).addXGui(this.coor.wGui/2)},
				new QuadColor[]{new QuadColor(), new QuadColor()},
				new TextureCoor[]{new TextureCoor(0,46,this.coor.wGui/2,20), new TextureCoor(200-this.coor.wGui/2,46,this.coor.wGui/2,20)},
				GuiDrawer.WIDGET);	
	}
	public void clickOn() {}
}
