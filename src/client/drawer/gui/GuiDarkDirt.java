package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.getFont;
import static client.drawer.GuiDrawer.spreadShader;
import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;

import java.util.Iterator;

import model.SpreadModel;
import model.SpreadModelPart;

import org.lwjgl.opengl.Display;

import util.ScreenCoor;
import util.Translator;
import client.drawer.GuiDrawer;
import drawer.ShaderProgram;

public abstract class GuiDarkDirt extends Gui
{
	public static SpreadModel devant =  new SpreadModel(new SpreadModelPart[]{
									new SpreadModelPart(),
									}, GuiDrawer.OPTION_BACKGROUND),
							derriere = new SpreadModel(new SpreadModelPart[]{
									new SpreadModelPart()
									}, GuiDrawer.OPTION_BACKGROUND_DARK);
	public Barre barre;
	public GuiDarkDirt(Barre b)
	{
		this.barre = b;
		this.boutons.add(this.barre);
	}
	public void draw()
	{
		spreadShader.start();
		spreadShader.loadCoor(ScreenCoor.screenFlat(0,0,1,0,0,this.barre.coor.getStartY(),0,this.barre.coor.getHeight())); // Centre
		drawModel(derriere);
		ShaderProgram.stop();
		
		this.barre.draw();
		
		this.drawOverBackground();
		
		spreadShader.start();
		spreadShader.loadCoor(ScreenCoor.screenFlat(0,0,1,0,0,0,0,this.barre.coor.getStartY())); // Haut
		drawModel(devant);
		spreadShader.loadCoor(ScreenCoor.screenFlat(0,0,1,1,0,this.barre.coor.getEndY(),0,-this.barre.coor.getEndY())); // Bas
		drawModel(devant);
		ShaderProgram.stop();
		drawCenteredStringWithShadow(Display.getWidth()/2,(int)(this.barre.coor.getStartY())-(getFont().getHeight(Translator.translate(this.getName()))),Translator.translate(this.getName()));

		for (Iterator<CustomBouton> iter = this.boutons.iterator();iter.hasNext();)
		{
			CustomBouton b = iter.next();
			if(!b.isBarre())
				b.draw();
		}
	}
	public abstract void drawOverBackground();
	public abstract String getName();
}
