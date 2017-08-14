package world;

import java.util.Iterator;

import main.Client;
import packet.toServer.SPacketAskChunk;
import entity.Entity;

public class ClientWorld extends World
{
	public ClientWorld()
	{
		super();
	}
	public void askForChunk(ChunkPos c) {
		new SPacketAskChunk(c).send(Client.game.joueur.socket);
	}
	public void manageChunks(ChunkPos playerPos, ChunkPos old)
	{
		for (Iterator<Chunk> iter = this.chunks.iterator(); iter.hasNext();)
			if (iter.next().pos.getDist(playerPos) > this.getDist()*this.getDist())
				iter.remove();
		
		this.loadChunks(playerPos, old);
	}
	public void update(long dif)
	{
		ChunkPos old = Client.game.joueur.getBlockPos().getChunkPos();
		for (Entity e : this.entities)
			e.update(dif, this);
		if (!Client.game.joueur.getBlockPos().getChunkPos().equals(old))
			this.manageChunks(Client.game.joueur.getBlockPos().getChunkPos(), old);
	}
	public int getDist()
	{
		return Client.game.options.graphic.renderDistance();
	}
}
