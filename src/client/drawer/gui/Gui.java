package client.drawer.gui;

import static client.drawer.GuiDrawer.defaultShader;
import static client.drawer.GuiDrawer.drawSections;
import static client.drawer.GuiDrawer.drawStringWithShadow;
import gui.CustomGui;
import main.Client;
import drawer.ShaderProgram;

public abstract class Gui extends CustomGui
{
	public int ticks = 0;
	public void quit(){}
	public void draw()
	{
		defaultShader.start();
		this.drawBeforeButtons();
		ShaderProgram.stop();
		this.drawButtons();
		this.drawAfterButtons();
	}
	public void drawBeforeButtons(){}
	public void drawAfterButtons(){}
	public boolean renderWorld(){return false;}
	public void debugMode()
	{
		drawStringWithShadow(5,10,"Chunk position : "+Client.game.joueur.getBlockPos().getChunkPos());
		drawStringWithShadow(5,30,"Block position : "+Client.game.joueur.getBlockPos());
		drawStringWithShadow(5,50,"FPS : "+Client.game.fpsLastSecond);
		drawStringWithShadow(5,70,"Ticker : "+this.ticks++);
		drawStringWithShadow(5,90,"Angle X : "+Math.round(Client.game.joueur.head.x*1000) / 1000d+", Angle Y : "+Math.round(Client.game.joueur.head.y*1000) / 1000d);
		drawSections();
	}
}
