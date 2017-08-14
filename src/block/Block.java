package block;

import model.CubeModel;

import org.newdawn.slick.opengl.Texture;

import util.BlockPart;
import util.TextureCoor;
import util.TriDouble;

public abstract class Block
{	
	public final BlockPos pos;
	public final short blockId;
	public Block(BlockPos p)
	{
		pos = p;
		blockId = BlockList.getIDForBlock(this.getClass());
	}
	public boolean isOpaque(){return false;}
	public BlockPart getMain(BlockPos bp){return this.getMain(bp.getX(), bp.getY(), bp.getZ());}
	public BlockPart getMain(int x, int y, int z)
	{
		return new BlockPart(new TriDouble(-.5,-.5,-.5), new TriDouble(0,0,0), new TriDouble(1,1,1), new TriDouble(.5+x,.5+y,.5+z), this.getCoors());
	}
	public final CubeModel getModel()
	{
		return BlockList.models[blockId];
	}
	public abstract Texture getTexture();
	public abstract TextureCoor getGauche();
	public abstract TextureCoor getDroite();
	public abstract TextureCoor getHaut();
	public abstract TextureCoor getBas();
	public abstract TextureCoor getArriere();
	public abstract TextureCoor getFace();
	public TextureCoor[] getCoors()
	{
		return new TextureCoor[]
				{
					this.getFace(),
					this.getGauche(),
					this.getArriere(),
					this.getDroite(),
					this.getHaut(),
					this.getBas(),
				};
	}
}
