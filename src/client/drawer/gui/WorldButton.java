package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;
import gui.CustomSelecter;
import util.ScreenCoor;
import client.sounds.SoundHelper;
import client.sounds.SoundPlayer;
import drawer.ShaderProgram;

public class WorldButton extends CustomSelecter
{
	public WorldButton(String n, int i, ScreenCoor c)
	{
		super(n, i, c);
	}
	public void draw()
	{
		if (this.isActiv)
		{
			Bouton.colorBoutonShader.start();
			Bouton.colorBoutonShader.loadCoor(coor.addGui(-1,-1,2,2));
			drawModel(ZoneTexte.blanc);
			Bouton.colorBoutonShader.loadCoor(coor);
			drawModel(ZoneTexte.noir);
			ShaderProgram.stop();
		}
			//drawRect(this.coor, 255,255,255);
			//drawRect(new ScreenCoor(this.coor.xScreen,this.coor.yScreen,this.coor.wScreen,this.coor.hScreen,this.coor.xGui+1,this.coor.yGui+1, this.coor.wGui-2,this.coor.hGui-2, this.coor.xFlat, this.coor.yFlat, this.coor.wFlat, this.coor.hFlat), 0,0,0);
		drawCenteredStringWithShadow(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2, this.getName());

	}
	public void extraClassicalActions(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y)
	{
		if (clicID == 0)
			if (appuie)
				if (boutonOn == this)
					if (!this.isActiv)
						SoundPlayer.playOmniscientSound(SoundHelper.BUTTON_CLIC);
	}
	public String getName()
	{
		return this.nom;
	}
	public void keyTyped(char arg0, int arg1) {}
}
