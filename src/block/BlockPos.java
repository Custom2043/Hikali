package block;

import java.io.IOException;

import util.CustomInputStream;
import util.CustomOutputStream;
import util.EntityPosRot;
import util.TriDouble;
import world.ChunkPos;

public class BlockPos
{
	private final ChunkPos chunkPos;
	private final int x,y,z;
	public BlockPos()
	{
		chunkPos = new ChunkPos(0,0,0);
		this.x=this.y=this.z=0;
	}
	public BlockPos(int x, int y, int z)
	{
		chunkPos = new ChunkPos(Math.floorDiv(x, 16),Math.floorDiv(y, 16),Math.floorDiv(z, 16));
		this.x = x % 16;
		this.y = y % 16;
		this.z = z % 16;
	}
	public BlockPos(ChunkPos p, int x, int y, int z)
	{
		chunkPos = p;
		this.x = x % 16;
		this.y = y % 16;
		this.z = z % 16;
	}
	public void write(CustomOutputStream os) throws IOException
	{
		os.writeInt(this.getX());
		os.writeInt(this.getY());
		os.writeInt(this.getZ());
	}
	public BlockPos(CustomInputStream is) throws IOException
	{
		this(is.readInt(),is.readInt(), is.readInt());
	}
	public ChunkPos getChunkPos()
	{
		return chunkPos;
	}
	public EntityPosRot getEntityPosRot()
	{
		return new EntityPosRot(this.getX(),this.getY(),this.getZ(), 0,0,0);
	}
	public TriDouble getTriDouble()
	{
		return new TriDouble(this.getX(),this.getY(),this.getZ());
	}
	public String toString()
	{
		return "x:"+this.getX()+", y:"+this.getY()+", z:"+this.getZ();
	}
	public BlockPos add(BlockPos deux)
	{
		return new BlockPos(this.getX()+deux.getX(),this.getY()+deux.getY(),this.getZ()+deux.getZ());
	}
	public BlockPos add(ChunkPos deux)
	{
		return new BlockPos(this.chunkPos.add(deux),x,y,z);
	}
	public int getX()
	{
		return chunkPos.getX() * 16 + x;
	}
	public int getY()
	{
		return chunkPos.getY() * 16 + y;
	}
	public int getZ()
	{
		return chunkPos.getZ() * 16 + z;
	}
	public int getInternalX()
	{
		return this.x;
	}
	public int getInternalY()
	{
		return this.y;
	}
	public int getInternalZ()
	{
		return this.z;
	}
}
