package block;

import org.newdawn.slick.opengl.Texture;

import util.BlockPart;
import util.TextureCoor;
import util.TriDouble;
import client.drawer.WorldDrawer;

public class BlockDirt extends BlockAllFaceSame
{
	public BlockDirt(BlockPos p) {super(p);}
	public Texture getTexture()
	{
		return WorldDrawer.DIRT;
	}
	public static BlockPart getMain2(int x, int y, int z)
	{
		return new BlockPart(new TriDouble(-.5,-.5,-.5), new TriDouble(0,0,0), new TriDouble(1,1,1), new TriDouble(.5+x,.5+y,.5+z), new TextureCoor[]{TextureCoor.allPicture,TextureCoor.allPicture,TextureCoor.allPicture,TextureCoor.allPicture,TextureCoor.allPicture,TextureCoor.allPicture});
	}
	public static Texture text()
	{
		return WorldDrawer.DIRT;
	}
}
