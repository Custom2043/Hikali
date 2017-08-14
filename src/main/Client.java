package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import util.TimeSection;
import client.drawer.GuiDrawer;
import client.drawer.WorldDrawer;
import client.drawer.gui.GuiMainMenu;
import client.sounds.SoundHelper;

public class Client extends Main
{
	public static boolean localServer = false;
	public static Hikali game;
	public static void main(String[] args) throws LWJGLException
	{
		new Client(args);
	}
	public Client(String[] args) throws LWJGLException
	{
		isClient = true;
		game = new Hikali();
		game.start(args[0]);
		WorldDrawer.load(game);
		GuiDrawer.updateGuiSize();
		screen = new GuiMainMenu();
		this.run();
	}
	public void renderTick(long dif)
	{
		if (Display.isCloseRequested())
			Main.stop();
		while (Mouse.next())
			Main.screen.click();
		while (Keyboard.next())
			if (Keyboard.getEventKeyState())
				if (game.options.keys.getNameForValue(Keyboard.getEventKey()).equals("key.debugMode"))
					game.debugMode = !game.debugMode;
				else
					Main.screen.type();
		game.renderTick(dif);
		if (screen.renderWorld())
		{
			TimeSection.beginSection(TimeSection.WORLD_RENDER);
			WorldDrawer.draw();
		}
		TimeSection.beginSection(TimeSection.GUI_RENDER);
		GuiDrawer.draw();
	}
	public void quit()
	{
		if (game.isInGame())
			game.quitServer();
		WorldDrawer.text.quit();
		GuiDrawer.quit();
		SoundHelper.quit();
	}
	public void tick(long dif)
	{
		if (localServer)
			server.tick(dif);
	}
}
