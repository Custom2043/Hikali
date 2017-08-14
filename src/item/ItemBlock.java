package item;

import org.newdawn.slick.opengl.Texture;

import block.Block;
import block.BlockList;
import client.drawer.GuiDrawer;

public class ItemBlock extends Item
{
	public final int blockId;
	public ItemBlock(Class<? extends Block> c)
	{
		this.blockId = BlockList.getIDForBlock(c);
	}
	public void draw()
	{
		GuiDrawer.drawItemBlock(this);
	}
	public Texture getTexture(){return BlockList.models[blockId].texture;}
}
