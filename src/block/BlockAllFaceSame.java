package block;

import util.TextureCoor;

public abstract class BlockAllFaceSame extends Block
{
	public BlockAllFaceSame(BlockPos p) {super(p);}
	public static final TextureCoor co = new TextureCoor(0,0,16,16);
	public TextureCoor getGauche(){return co;}
	public TextureCoor getDroite(){return co;}
	public TextureCoor getHaut(){return co;}
	public TextureCoor getBas(){return co;}
	public TextureCoor getArriere(){return co;}
	public TextureCoor getFace(){return co;}
}
