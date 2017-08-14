package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.getFont;
import static client.drawer.GuiDrawer.spreadShader;
import static drawer.CustomDrawer.drawModel;
import gui.CustomBouton;
import main.Client;
import main.Main;

import org.lwjgl.opengl.Display;

import util.ScreenCoor;
import util.Translator;
import world.ClientWorld;
import drawer.ShaderProgram;

public class GuiWaitWorldData extends GuiDirtyBackgroundOrTransparent
{
	public static int waiting = -1;
	public static ClientWorld cw;
	public GuiWaitWorldData(ClientWorld c)
	{
		Client.game.inGame = true;
		cw = c;
	}
	public String getName() {return "gui.title.waitingWorldData";}
	public void drawBeforeButtons()
	{
		spreadShader.start();
		spreadShader.loadCoor(ScreenCoor.AllScreen);
		drawModel(dirty);
		ShaderProgram.stop();
		
		drawCenteredStringWithShadow(Display.getWidth()/2, 30 + getFont().getHeight(Translator.translate(this.getName()))/2, Translator.translate(this.getName()));

		if (cw.chunks.size() == waiting)
			Main.setGui(new GuiIngame());
		
		System.out.println(cw.chunks.size());
	}
	public static void joinGame()
	{
		waiting = cw.getChunksAround(Client.game.joueur.getBlockPos().getChunkPos()).size();
		cw.loadChunks(Client.game.joueur.getBlockPos().getChunkPos());
	}
	protected void mouseEvent(int clicID, int X, int Y, boolean press,CustomBouton boutonOn) {}
	protected void keyboardEvent(char carac, int keyCode) {}

}
