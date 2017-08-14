package world;

import java.io.IOException;

import util.CustomInputStream;
import util.CustomOutputStream;

public class ChunkPos
{
	private int x,y,z;
	public ChunkPos(ChunkPos cp)
	{
		this.x = cp.x;
		this.y = cp.y;
		this.z = cp.z;
	}
	public ChunkPos(int x, int y, int z)
	{
		this.x = x;
		this.y = this.checkY(y);
		this.z = z;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getZ() {
		return this.z;
	}
	private int checkY(int y)
	{
		if (y < 0)
			return 0;
		if (y > 15)
			return 15;
		return y;
	}
	public void write(CustomOutputStream os) throws IOException
	{
		os.writeInt(this.x);
		os.writeInt(this.y);
		os.writeInt(this.z);
	}

	public void read(CustomInputStream is) throws IOException
	{
		this.x = is.readInt();
		this.y = is.readInt();
		this.z = is.readInt();
	}
	public int getDist(ChunkPos d)
	{
		return (this.x-d.x)*(this.x-d.x)+(this.y-d.y)*(this.y-d.y)+(this.z-d.z)*(this.z-d.z);
	}
	public boolean equals(ChunkPos d)
	{
		return d.x == this.x && d.y == this.y && d.z == this.z;
	}
	public String toString()
	{
		return "x:"+this.x+", y:"+this.y+", z:"+this.z;
	}
	public ChunkPos add(ChunkPos deux)
	{
		return new ChunkPos(x+deux.getX(), y+deux.getY(), z+deux.getZ());
	}
}
