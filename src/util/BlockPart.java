package util;


public class BlockPart
{
	public final TriDouble begin, angle, size, rotatePoint;
	public BlockPart[] dependantPart;
	public TextureCoor[] coors;
	public BlockPart(TriDouble b, TriDouble a, TriDouble s, TriDouble rp, TextureCoor[] c, BlockPart[] su)
	{
		this.begin = b;
		this.angle = a;
		this.size = s;
		this.rotatePoint = rp;
		this.coors = c;
		this.dependantPart = su;
	}
	public BlockPart(TriDouble b, TriDouble a, TriDouble s, TriDouble rp, TextureCoor[] c)
	{
		this(b,a,s,rp,c,new BlockPart[0]);
	}
}
