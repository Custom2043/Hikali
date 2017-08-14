package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import packet.Packet;
import packet.toClient.CPacketPlayerQuit;
import packet.toClient.CPacketServerClose;
import server.PlayerAccepter;
import util.CustomTimer;
import util.TimeSection;
import util.UpdateList;
import world.ChunkLoader;
import world.ServerWorld;
import entity.EntityPlayer;

public class HikaliServer
{
	public CustomTimer tickCounter = new CustomTimer();
	public int ticPerSecond = 0;
	public PlayerAccepter acc;
	private UpdateList<EntityPlayer> playersCo = new UpdateList<EntityPlayer>();
	public ServerWorld world;

	public HikaliServer(String worldName) throws IOException
	{
		this.world = new ServerWorld(worldName);
		ChunkLoader.sw = this.world;
		this.acc = new PlayerAccepter(this);
	}
	public void sendPacketToAllPlayers(Packet p)
	{
		for (EntityPlayer h : this.playersCo.getList())
			p.send(h.socket);
	}
	public void sendPacketToAllPlayersExcept(Packet p, String name)
	{
		for (EntityPlayer h : this.playersCo.getImage())
			if (!h.name.equals(name))
				p.send(h.socket);
	}
	public void tick(long dif)
	{
		this.ticPerSecond++;
		if (this.tickCounter.getDifference() >= 1000)
		{
			//System.out.println("TickPerSecond : "+ticPerSecond);
			this.tickCounter.set0();
			this.ticPerSecond = 0;
		}
		TimeSection.beginSection(TimeSection.SERVER_LOGIC);		
		for (int i=0;i<this.playersCo.getList().size();i++)
		{
			EntityPlayer ep = this.playersCo.getList().get(i);
			
			Packet.read(ep.socket, ep.stream);
			Packet p;
			while ((p = Packet.readPacket(ep.stream)) != null)
			{
				try
				{
					if (p instanceof Packet.PacketToServer)
					{
						if (p.toWrite())
							System.out.println("Server receive packet : "+p.getClass().getName());
						((Packet.PacketToServer)p).handleServer(ep);
					}
				}
				catch(Exception e){e.printStackTrace();}
			}
			this.world.update(dif);
		}
	}
	public EntityPlayer getEntityPlayerByName(String nom)
	{
		for (EntityPlayer h : this.playersCo.getList())
			if (h.name.equals(nom))
				return h;
		return null;
	}
	public void closeServer()
	{
		ChunkLoader.quit();
		try {this.acc.ss.close();}
		catch (IOException e) {e.printStackTrace();}
		this.sendPacketToAllPlayers(new CPacketServerClose());
		this.world.write();
	}
	public void addPlayer(EntityPlayer p)
	{
		this.playersCo.add(p);
		this.world.addEntity(p);
	}
	public void removePlayer(String name)
	{
		this.world.removeEntity(name);
		for (Iterator<EntityPlayer> iter = this.playersCo.getList().iterator();iter.hasNext();)
		{
			EntityPlayer pl = iter.next();
			if (pl.name.equals(name))
			{
				System.out.println(name +" a quitté la partie");
				try {pl.socket.close();}
				catch (IOException e) {e.printStackTrace();}
				iter.remove();
				Main.server.sendPacketToAllPlayers(new CPacketPlayerQuit(name));
			}
		}
	}
	public ArrayList<EntityPlayer> getPlayers()
	{
		return this.playersCo.getList();
	}
}
