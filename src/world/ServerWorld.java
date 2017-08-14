package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import main.Main;

import org.apache.commons.io.IOUtils;

import util.CustomInputStream;
import util.CustomOutputStream;
import block.BlockPos;
import entity.Entity;
import entity.EntityHuman;
import entity.EntityPlayer;

public class ServerWorld extends World
{
	public final String nom;

	public BlockPos spawnBlock = new BlockPos(0,10,0);

	public ServerWorld(String n)
	{
		this.nom = n;
		this.read();
	}
	public void write()
	{
		CustomOutputStream cos = null;
		try
		{
			if (!new File("Saves/"+this.nom+"/Players").exists())
				new File("Saves/"+this.nom+"/Players").mkdirs();

			cos = new CustomOutputStream(new FileOutputStream(new File("Saves/"+this.nom+"/WorldInfo.hkw")));

			this.spawnBlock.write(cos);

			int size = 0;
			for (Entity e : this.entities)
				if (!(e instanceof EntityPlayer))
					size++;
			cos.writeInt(size);
			for (Entity e : this.entities)
				if (e instanceof EntityPlayer)
					this.writeHumanData((EntityHuman)e);
				else
					e.write(cos);
		}
		catch (Exception e) {System.out.println("World ; Fail in save of "+this.nom);}
		finally {if (cos != null)IOUtils.closeQuietly(cos);}
	}

	public void read()
	{
		CustomInputStream cis = null;
		try
		{
			cis = new CustomInputStream(new FileInputStream(new File("Saves/"+this.nom+"/WorldInfo.hkw")));

			this.spawnBlock = new BlockPos(cis);

			int nbEntite = cis.readInt();
			for (int i=0;i<nbEntite;i++)
				Entity.readFromFile(cis);
		}
		catch (Exception e) {System.out.println("World ; Fail in read of "+this.nom);}
		finally {if (cis != null)IOUtils.closeQuietly(cis);}
	}
	public void writeHumanData(EntityHuman e)
	{
		CustomOutputStream cos2 = null;
		try
		{
			cos2 = new CustomOutputStream(new FileOutputStream(new File("Saves/"+this.nom+"/Players/"+e.name+".hkp")));
			e.write(cos2);
		}
		catch (Exception e1) {System.out.println("World ; Fail to save player "+e.name);}
		finally {if (cos2 != null)IOUtils.closeQuietly(cos2);}
	}
	public void removeEntity(String n)
	{
		for (Iterator<Entity> iter = this.entities.iterator() ; iter.hasNext() ; )
		{
			Entity e = iter.next();
			if (e.name.equals(n))
			{
				if (e instanceof EntityHuman)
					this.writeHumanData((EntityHuman)e);
				iter.remove();
			}
		}
	}
	public void askForChunk(ChunkPos c) {
		ChunkLoader.loadChunk(c);
	}
	public void update(long dif)
	{
		ChunkPos old = new ChunkPos(0,0,0);
		for (Entity e : this.entities)
		{
			if (e instanceof EntityPlayer)
				old = new ChunkPos(e.getBlockPos().getChunkPos());
			e.update(dif, this);
			if (e instanceof EntityPlayer)
				if (!old.equals(e.getBlockPos().getChunkPos()))
					this.manageChunks(e.getBlockPos().getChunkPos(), old);
		}
	}
	public void manageChunks(ChunkPos playerPos, ChunkPos old)
	{
		for (Iterator<ChunkPos> cl = this.getChunksAround(old).iterator(); cl.hasNext();)
		{
			boolean charge = false;
			for (Iterator<EntityPlayer> iter = Main.server.getPlayers().iterator() ; iter.hasNext() && !charge; )
				if (iter.next().getBlockPos().getChunkPos().getDist(cl.next()) <= Main.getDist())
					charge = true;
			if (!charge)
				cl.remove();
		}
		this.loadChunks(playerPos, old);
	}
}
