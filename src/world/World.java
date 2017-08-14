package world;

import java.util.ArrayList;
import java.util.Iterator;

import main.Main;
import entity.Entity;
import entity.EntityHuman;

public abstract class World
{
	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();

	ArrayList<Entity> entities = new ArrayList<Entity>();

	public World()
	{
		
	}
	public void addEntity(Entity e)
	{
		this.entities.add(e);
	}
	public void removeEntity(String n)
	{
		for (Iterator<Entity> iter = this.entities.iterator() ; iter.hasNext() ; )
			if (iter.next().name.equals(n))
				iter.remove();
	}
	public void loadChunks(ChunkPos playerPos, ChunkPos old)
	{
		for (ChunkPos cp : this.getChunksAround(playerPos))
			if (cp.getDist(old) > Main.getDist())
				this.askForChunk(cp);
	}
	public void loadChunks(ChunkPos playerPos)
	{
		for (ChunkPos cp : this.getChunksAround(playerPos))
			this.askForChunk(cp);
	}
	public abstract void manageChunks(ChunkPos playerPos, ChunkPos old);
	public ArrayList<Entity> getEntities()
	{
		return this.entities;
	}
	public abstract void update(long dif);
	public EntityHuman getEntityHumanByName(String nom)
	{
		for (Entity h : this.entities)
			if (h.name.equals(nom))
				if (h instanceof EntityHuman)
					return (EntityHuman)h;
		return null;
	}
	public abstract void askForChunk(ChunkPos c);
	public Chunk getChunk(ChunkPos cp)
	{
		for (Chunk c : this.chunks)
			if (c.pos.equals(cp))
				return c;
		return null;
	}
	public ArrayList<ChunkPos> getChunksAround(ChunkPos playerPos)
	{
		int dist = Main.getDist();
		ArrayList<ChunkPos> chunks = new ArrayList<ChunkPos>();
		for (int x = -dist + playerPos.getX();x<=dist+playerPos.getX();x++)
			for (int y = -dist+ playerPos.getY();y<=dist+ playerPos.getY();y++)
				for (int z = -dist+ playerPos.getZ();z<=dist+ playerPos.getZ();z++)
					if (y>-1 && y < 16)
						if (playerPos.getDist(new ChunkPos(x,y,z)) <= dist)
							chunks.add(new ChunkPos(x,y,z));
		return chunks;
	}
}
