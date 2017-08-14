package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.drawHoveredButtonName;
import static client.drawer.GuiDrawer.drawInactivButtonName;
import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;
import model.BoutonModel;
import model.BoutonModelPart;
import shaders.BoutonShader;
import util.ScreenCoor;
import util.TextureCoor;
import util.Translator;
import client.drawer.GuiDrawer;
import client.sounds.SoundHelper;
import client.sounds.SoundPlayer;
import drawer.ShaderProgram;

public class Bouton extends CustomBouton
{
	public static BoutonModel model = new BoutonModel( new BoutonModelPart[]{
			new BoutonModelPart(new float[]{0,0,.5f,0,.5f,1,0,1}, new TextureCoor(0,46,0,20), new float[]{0,0,1,0,1,0,0,0}),
			new BoutonModelPart(new float[]{.5f,0,1,0,1,1,.5f,1}, new TextureCoor(200,46,0,20), new float[]{-1,0,0,0,0,0,-1,0})
		}, GuiDrawer.WIDGET);
	public static BoutonShader shader = new BoutonShader(true);
	public static BoutonShader colorBoutonShader = new BoutonShader(false);
	public Bouton(String n, int i, ScreenCoor c, boolean a)
	{
		super(n,i,c,a);
	}
	public Bouton(String n, int i, ScreenCoor c)
	{
		this(n,i,c,true);
	}
	public void draw()
	{
		shader.start();
		shader.loadCoor(this.coor);
		shader.loadState(!this.isActiv() ? 0 : this.isHovered() ? 2 : 1);
		drawModel(model);
		ShaderProgram.stop();
		if (!this.isActiv())
			drawInactivButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2,Translator.translate(this.nom));
		else if (this.isHovered())
			drawHoveredButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2,Translator.translate(this.nom));
		else
			drawCenteredStringWithShadow((int)coor.getMiddleX(),(int)coor.getMiddleY(),  Translator.translate(this.nom));	
	}
	public void click(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y)
	{
		if (clicID == 0)
			if (appuie)
				if (boutonOn == this)
					if (this.isActiv())
						SoundPlayer.playOmniscientSound(SoundHelper.BUTTON_CLIC);
	}
	public void keyTyped(char carac, int keyCode){}
	public void doClassicalMouseActions(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y) {}
}
