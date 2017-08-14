package block;

import org.newdawn.slick.opengl.Texture;

import model.CubeModel;
import util.BlockPart;

public class BlockList
{
	public static Class <? extends Block>[] blocks = new Class[blockNumber()];
	public static BlockPart[] parts = new BlockPart[blockNumber()];
	public static CubeModel[] models = new CubeModel[blockNumber()];
	
	public static Class <? extends Block> getBlockForID(short id)
	{
		return blocks[id];
	}

	public static short getIDForBlock(Class <? extends Block> c)
	{
		for (short i=0;i<blocks.length;i++)
			if (blocks[i] == c)
				return i;
		return 0;
	}
	static
	{
		add(BlockAir.class, 0, null, null);
		add(BlockDirt.class, 1, BlockDirt.getMain2(0, 0, 0), BlockDirt.text());
	}
	public static int blockNumber(){return 2;}
	private static void add(Class <? extends Block> cb, int id, BlockPart model, Texture text)
	{
		blocks[id] = cb;
		parts[id] = model;
		models[id] = model == null ? null : new CubeModel(model, text);
	}
}
