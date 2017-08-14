package block;

import org.newdawn.slick.opengl.Texture;

public class BlockAir extends BlockAllFaceSame
{
	public BlockAir(BlockPos p) {super(p);}
	public Texture getTexture() {return null;}
	public boolean isOpaque(){return false;}
}
