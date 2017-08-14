package client.drawer.gui;

import static drawer.CustomDrawer.drawModel;
import gui.CustomBarre;
import gui.CustomBouton;

import java.util.ArrayList;

import model.UniCoorModel;
import model.UniCoorModelPart;

import org.newdawn.slick.Color;

import shaders.BarreShader;
import util.ScreenCoor;
import util.TextureCoor;
import client.drawer.GuiDrawer;
import drawer.ShaderProgram;

public class Barre extends CustomBarre
{
	public static BarreShader barreShader = new BarreShader();
	public static UniCoorModel haut = new UniCoorModel(new UniCoorModelPart(new float[]{0,0,1,0,1,1,0,1}, new TextureCoor(182,0,6,2)), GuiDrawer.WIDGET),
							   bas = new UniCoorModel(new UniCoorModelPart(new float[]{0,0,1,0,1,1,0,1}, new TextureCoor(182,3,6,2)), GuiDrawer.WIDGET),
							   centre = new UniCoorModel(new UniCoorModelPart(new float[]{0,0,1,0,1,1,0,1}, new TextureCoor(182,6,6,14)), GuiDrawer.WIDGET),
							   noir = new UniCoorModel(new UniCoorModelPart(new float[]{0,0,1,0,1,1,0,1} ,Color.black, TextureCoor.allPicture), GuiDrawer.WIDGET);
	public Barre(String n, int i, ScreenCoor c, ArrayList<CustomBouton> l, int m)
	{
		super(n, i, c, l, m);
	}
	public Barre(String n, int i, ScreenCoor c)
	{
		super(n, i, c);
	}
	public void drawBarre()
	{
		if (this.getMax() > this.coor.getHeight())
		{
			barreShader.start();
			barreShader.loadCoor(this.coor);
			barreShader.loadInUse(this.isActiv());
			drawModel(noir); // Noir
			barreShader.loadCoor(this.coor.addYFlat(this.getPosY()).setH(0, 2, 0));
			drawModel(haut); // Haut
			barreShader.loadCoor(this.coor.addY(0, 2, this.getPosY()).setH(0,-4, this.getBarreHeight()));
			drawModel(centre); // Centre
			barreShader.loadCoor(this.coor.addY(0, -2, this.getPosY() + this.getBarreHeight()).setH(0, 2, 0));
			drawModel(bas); // Bas
			ShaderProgram.stop();
		}
	}
}
