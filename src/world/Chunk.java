package world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;

import util.CustomInputStream;
import util.CustomOutputStream;
import block.Block;
import block.BlockAir;
import block.BlockDirt;
import block.BlockList;
import block.BlockPos;

public class Chunk
{
	public final ChunkPos pos;
	public Block[] data = new Block[4096];

	public Chunk(ChunkPos p)
	{
		this.pos = p;
		for (int i=0;i<this.data.length;i++)
			this.data[i] = new BlockAir(getBlockPos(i));
		this.data[0] = new BlockDirt(new BlockPos(0+p.getX()*16,0+p.getY()*16,0+p.getZ()*16));
		this.data[15] = new BlockDirt(new BlockPos(0+p.getX()*16,0+p.getY()*16,15+p.getZ()*16));
		this.data[3840] = new BlockDirt(new BlockPos(15+p.getX()*16,0+p.getY()*16,0+p.getZ()*16));
		this.data[3855] = new BlockDirt(new BlockPos(15+p.getX()*16,0+p.getY()*16,15+p.getZ()*16));
	}
	public BlockPos getBlockPos(int id)
	{
		return new BlockPos(pos, (id & 3840) >> 8,(id & 240) >> 4, id & 16);
	}
	public Block getBlock(int x, int y, int z)
	{
		if (x>-1 && x <16 && y>-1 && y<16 && z>-1 && z<16)
			return this.data[x*256+y*16+z];
		return null;
	}
	public void write(String worldName)
	{
		CustomOutputStream os = null;
		try
		{
			if (!new File("Saves/"+worldName+"/Chunks").exists())
				new File("Saves/"+worldName+"/Chunks").mkdirs();

			os = new CustomOutputStream(new FileOutputStream(new File("Saves/"+worldName+"/chunk"+this.pos.getX()+this.pos.getY()+this.pos.getZ())));
		}
		catch (Exception e) {System.out.println("Chunk ; Fail in save of "+worldName);}
		finally {if (os != null)IOUtils.closeQuietly(os);}
	}

	public void read(String worldName)
	{
		CustomInputStream is = null;
		try
		{
			is = new CustomInputStream(new FileInputStream(new File("Saves/"+worldName+"/chunk"+this.pos.getX()+this.pos.getY()+this.pos.getZ())));
		}
		catch (Exception e) {System.out.println("Chunk ; Fail in read of "+worldName);}
		finally {if (is != null)IOUtils.closeQuietly(is);}
	}
	public int getIDOfBlock(BlockPos p)
	{
		return (p.getInternalX() << 8) + (p.getInternalY() << 4) + p.getInternalZ();
	}
	public BlockPos getBlockPos(int x, int y, int z)
	{
		return new BlockPos(this.pos.getX()*16+x, this.pos.getY()*16+y, this.pos.getZ()*16+z);
	}
	public static int getBufferizedSize()
	{
		return 4*3 + 4096*2;
	}
	public ByteBuffer bufferize()
	{
		byte[] array = new byte[getBufferizedSize()];
		ByteBuffer buffer = ByteBuffer.wrap(array);
		buffer.putInt(this.pos.getX());
		buffer.putInt(this.pos.getY());
		buffer.putInt(this.pos.getZ());
		for (Block b : this.data)
			buffer.putShort(BlockList.getIDForBlock(b.getClass()));
		buffer.flip();
		return buffer;
	}
}
