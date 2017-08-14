package world;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import packet.toClient.CPacketChunk;
import util.CustomTimer;
import entity.EntityPlayer;

public abstract class ChunkLoader
{
	public static CustomTimer timer = new CustomTimer();
	public static ServerWorld sw;
	private static ArrayList<Commande> commandes = new ArrayList<Commande>();
	private static ArrayList<ChunkPos> toLoad = new ArrayList<ChunkPos>();
	public ChunkLoader(ServerWorld sw)
	{
		ChunkLoader.sw = sw;
	}
	public static void addCommande(ChunkPos cp, EntityPlayer ep)
	{
		commandes.add(new Commande(cp, ep));
		//System.out.println("ChunkLoader ; Player "+ep.name+" veut le chunk "+cp.toString());
	}
	public static void loadChunk(ChunkPos p)
	{
		//System.out.println("ChunkLoader ; Add chunk : "+p.toString());
		toLoad.add(p);
	}
	public static long loadChunks(long time)
	{
		timer.set0();
		while (timer.getDifference() < time && toLoad.size() > 0)
		{
			ChunkPos cp = toLoad.get(0);
			Chunk ch = readChunk(cp);
			sw.chunks.add(ch);
			for (Iterator<Commande> it = commandes.iterator();it.hasNext();)
			{
				ByteBuffer buf = null;
				boolean buffered = false;
				Commande c = it.next();
				if (c.pos.equals(cp))
				{
					if (!buffered)
					{
						buf = ch.bufferize();
						buffered = true;
					}
					new CPacketChunk(buf).send(c.player.socket);
					it.remove();
				}
			}
			toLoad.remove(0);
		}
		return timer.getDifference();
	}
	public static void quit()
	{
		toLoad.clear();
		commandes.clear();
	}
	private static Chunk readChunk(ChunkPos cp)
	{
		return new Chunk(cp);
	}
	public static class Commande
	{
		public ChunkPos pos;
		public EntityPlayer player;
		public Commande(ChunkPos cp, EntityPlayer ep)
		{
			this.pos = cp;
			this.player = ep;
		}
	}
}
