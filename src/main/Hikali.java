package main;

import java.io.IOException;
import java.net.Socket;

import packet.Packet;
import packet.toServer.SPacketHeadUpdate;
import packet.toServer.SPacketMoveUpdate;
import packet.toServer.SPacketPlayerConnect;
import packet.toServer.SPacketQuitGame;
import util.CustomTimer;
import util.EntityPosRot;
import util.MouseHelper;
import util.PacketStream;
import util.TimeSection;
import util.TriDouble;
import world.ClientWorld;
import client.drawer.gui.GuiIngame;
import client.drawer.gui.GuiMainMenu;
import client.drawer.gui.GuiWaitWorldData;
import client.options.Options;
import client.sounds.SoundHelper;
import client.sounds.SoundPlayer;
import entity.EntityPlayer;

public class Hikali
{
	public boolean debugMode = false;
	public boolean distantServer = false;
	public CustomTimer fpsCounter = new CustomTimer();
	public int fpsPerSecond = 0, fpsLastSecond = 0;
	public EntityPlayer joueur;
	public int renderMode = 0;
	/**
	 * True if game is just paused
	 */
	public boolean inGame = false;
	public ClientWorld world;
	public Options options;
	public Hikali()
	{
		this.options = new Options();
	}
	public void start(String playerName)
	{
		try
		{
			SoundHelper.init();
			SoundPlayer.updateMasterVolume();

			this.joueur = new EntityPlayer(new EntityPosRot(0,0,0,0,0,0), new TriDouble(), playerName, null);

			System.out.println("Hikali ; Successfully initialization");
		}
		catch(Exception e){e.printStackTrace();System.out.println("Hikali ; Fail to init the client");Main.stop();}
	}
	/**
	 * Manage input and package, called each FPS, every 20 ms
	 */
	public void renderTick(long dif)
	{
		this.fpsPerSecond++;
		if (this.fpsCounter.getDifference() >= 1000)
		{
			this.fpsLastSecond = this.fpsPerSecond;
			this.fpsCounter.set0();
			this.fpsPerSecond = 0;
			TimeSection.setLast();
		}
		TimeSection.beginSection(TimeSection.CLIENT_LOGIC);
		if (this.isInGame())
		{
			Packet.read(joueur.socket, joueur.stream);
			Packet p;
			while ((p = Packet.readPacket(joueur.stream)) != null)
			{
				try
				{
					if (p instanceof Packet.PacketToClient)
					{
						if (p.toWrite())
							System.out.println("Client receive packet : "+p.getClass().getName());
						((Packet.PacketToClient)p).handleClient();
					}
				}
				catch(Exception e){e.printStackTrace();}
			}
			if (!this.isPaused())
			{
				this.options.keys.setMoves(this.joueur.dep);
				if (this.joueur.headChanged)
				{
					new SPacketHeadUpdate(this.joueur.head).send(this.joueur.socket);
					this.joueur.headChanged = false;
				}
				if (this.joueur.dep.hasChanged())
					new SPacketMoveUpdate(this.joueur.dep, this.joueur.body.pos).send(this.joueur.socket);
				MouseHelper.update();
			}
			this.world.update(dif);
		}
	}
	public void setLocalIngame(String w)
	{
		this.world = new ClientWorld();
		Main.setGui(new GuiWaitWorldData(this.world));
		try
		{
			joueur.stream = new PacketStream();
			Main.server = new HikaliServer(w);
			this.joueur.socket = new Socket("localhost", Main.PORT);
			this.joueur.socket.setReceiveBufferSize(Packet.MAX_SIZE);
			this.joueur.socket.setSendBufferSize(Packet.MAX_SIZE);
			new SPacketPlayerConnect(joueur.name).send(this.joueur.socket);
		}
		catch (IOException  e) {System.out.println("Hikali ; Can't connect to Server");return;}
		Client.localServer = true;
	}
	public void setDistanceInGame(String adress)
	{
		this.world = new ClientWorld();
		Main.setGui(new GuiWaitWorldData(this.world));
		try
		{
			joueur.stream = new PacketStream();
			this.joueur.socket = new Socket(adress, Main.PORT);
			this.joueur.socket.setReceiveBufferSize(Packet.MAX_SIZE);
			this.joueur.socket.setSendBufferSize(Packet.MAX_SIZE);
			new SPacketPlayerConnect(joueur.name).send(this.joueur.socket);
		}
		catch (IOException e) {System.out.println("Hikali ; Can't connect to Server");return;}

		Client.localServer = false;
	}
	public void quitServer()
	{
		if (Client.localServer)
		{
			new SPacketQuitGame().handleServer(joueur);
			Main.server.closeServer();
			Client.localServer = false;
		} else
			new SPacketQuitGame().send(this.joueur.socket);

		this.inGame = false;
		Main.setGui(new GuiMainMenu());
	}
	public boolean isInGame()
	{
		return this.inGame;
	}
	public boolean isPaused()
	{
		return !(Main.screen instanceof GuiIngame);
	}
}
