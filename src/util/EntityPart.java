package util;

public class EntityPart
{
	public final TriDouble begin, angle, size, rotatePoint;
	public int x, y, xs, ys, zs;
	public EntityPart[] dependantPart;
	public boolean symX = false;
	public EntityPart(TriDouble b, TriDouble a, TriDouble s, TriDouble rp, int xPos, int yPos, int xSize, int ySize, int zSize, EntityPart[] su)
	{
		this.begin = b;
		this.angle = a;
		this.size = s;
		this.rotatePoint = rp;
		this.x = xPos;
		this.y = yPos;
		this.xs = xSize;
		this.ys = ySize;
		this.zs = zSize;
		this.dependantPart = su;
	}
	public EntityPart(TriDouble b, TriDouble a, TriDouble s, TriDouble rp, int xPos, int yPos, int xSize, int ySize, int zSize)
	{
		this(b,a,s,rp, xPos, yPos, xSize, ySize, zSize, new EntityPart[0]);
	}
	public EntityPart setSymX(boolean x)
	{
		this.symX = x;
		return this;
	}
}
