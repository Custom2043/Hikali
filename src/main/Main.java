package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import util.CustomTimer;
import util.TimeSection;
import world.ChunkLoader;
import client.drawer.GuiDrawer;
import client.drawer.gui.Gui;
import drawer.CustomDrawer;

public abstract class Main
{
	public static final int PORT = 2043;

	public static final byte milliEntreRender = 20; // 50 par secondes
	public static final byte milliEntreTick = 50; // 20 par secondes

	public static boolean running = true;
	public static HikaliServer server = null;

	public static boolean isClient;

	public static CustomTimer drawerTimer;
	public static CustomTimer ticker;

	protected static Gui screen;
	/**
	 *  Client = If should open the game ; Server = If information of game has to be transmitted to an internal server
	 */
	public Main() throws LWJGLException
	{
		Keyboard.enableRepeatEvents(true);
		CustomDrawer.createDisplay(854, 480, "Hikali Editor");
		GuiDrawer.init();
		CustomDrawer.load2D();
		ticker = new CustomTimer();
		drawerTimer = new CustomTimer();
	}
	public void run()
	{
		while (running)
		{
			if (drawerTimer.getDifference() >= milliEntreRender)
				try
				{
					long dif = drawerTimer.getDifference();
					drawerTimer.resetUnderATick(milliEntreRender);
					this.renderTick(dif);
				}
				catch(Exception e){e.printStackTrace();System.out.println("Main ; Fail in drawer render");Main.stop();}
			if (ticker.getDifference() >= milliEntreTick)
				try
				{
					long dif = ticker.getDifference();
					ticker.resetUnderATick(milliEntreTick);
					this.tick(dif);
				}
				catch(Exception e){e.printStackTrace();System.out.println("Main ; Fail server in run loop");Main.stop();}
			sleep(Math.max(0, Math.min(milliEntreRender - drawerTimer.getDifference(), milliEntreTick - ticker.getDifference())));
		}
		this.quit();
	}
	public static int getDist()
	{
		if (isClient)
			return Client.game.options.graphic.renderDistance() * Client.game.options.graphic.renderDistance();
		else
			return 256;
	}
	public abstract void tick(long dif);
	public abstract void quit();
	public abstract void renderTick(long dif);
	public static void sleep(long milli)
	{
		try
		{
			if (!isClient || Client.localServer)
			{
				TimeSection.beginSection(TimeSection.CHUNK_LOADER);
				long chunksTime = ChunkLoader.loadChunks(milli);
				TimeSection.beginSection(TimeSection.REPOS);
				Thread.currentThread();
				Thread.sleep(milli - chunksTime);
			}
			else
			{
				TimeSection.beginSection(TimeSection.REPOS);
				Thread.currentThread();
				Thread.sleep(milli);
			}
		}
		catch(Exception e){}
	}
	public static float dividef(int a, int b)
	{
		return ((float)a)/((float)b);
	}
	public static double divided(long a, long b)
	{
		return ((double)a)/((double)b);
	}
	public static void stop()
	{
		running = false;
	}
	public static double approach(double cible, double base, double maxMove)
	{
		if (cible > base)
		{
			if (cible > base + maxMove)
				return base+maxMove;
			else
				return cible;
		} else if (cible < base - maxMove)
			return base-maxMove;
		else
			return base;
	}
	public static void setGui (Gui nouveau)
	{
		screen.quit();
		screen = nouveau;
	}
	public static Gui getGui(){return screen;}
}
