package client.drawer.gui;

import static client.drawer.GuiDrawer.colorShader;
import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.drawIngameGui;
import static client.drawer.GuiDrawer.getFont;
import static client.drawer.GuiDrawer.spreadShader;
import static drawer.CustomDrawer.drawModel;
import main.Client;
import model.SpreadModel;
import model.SpreadModelPart;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import util.QuadColor;
import util.ScreenCoor;
import util.Translator;
import client.drawer.GuiDrawer;
import drawer.ColoredScreenCoorModel;
import drawer.ShaderProgram;

public abstract class GuiDirtyBackgroundOrTransparent extends Gui
{
	public static SpreadModel dirty = new SpreadModel(new SpreadModelPart(), GuiDrawer.OPTION_BACKGROUND);
	public static ColoredScreenCoorModel black = new ColoredScreenCoorModel(ScreenCoor.AllScreen, new QuadColor(new Color(0,0,0,0.5f)));
	public boolean transparent;
	public GuiDirtyBackgroundOrTransparent()
	{
		this.transparent = Client.game.isInGame();
	}
	public void drawBeforeButtons()
	{
		if (this.transparent)
		{
			drawIngameGui(Client.game.joueur);
			colorShader.start();
			drawModel(black);
			ShaderProgram.stop();
		} 
		else
		{
			spreadShader.start();
			spreadShader.loadCoor(ScreenCoor.AllScreen);
			drawModel(dirty);
			ShaderProgram.stop();
		}
		this.drawOverBackground();
	}
	public void drawOverBackground()
	{
		drawCenteredStringWithShadow(Display.getWidth()/2, 30 + getFont().getHeight(Translator.translate(this.getName()))/2, Translator.translate(this.getName()));
	}
	public abstract String getName();
}
