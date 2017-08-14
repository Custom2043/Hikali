package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import client.drawer.GuiDrawer;
import client.drawer.gui.GuiHikaliServer;

public class Server extends Main
{
	public static void main(String[] args) throws LWJGLException
	{
		new Server();
	}
	public Server() throws LWJGLException
	{
		isClient = false;
		screen = new GuiHikaliServer();
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
				Main.screen.type();
		GuiDrawer.draw();
	}
	public void quit()
	{
		if (server != null)
			server.closeServer();
		GuiDrawer.quit();
	}
	public void tick(long dif)
	{
		server.tick(dif);
	}
}
