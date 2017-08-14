package inventory;

import item.Item;
import item.ItemBlock;
import block.BlockDirt;

public class PlayerInventory 
{
	public Item[] items = new Item[40];
	public PlayerInventory()
	{
		for (int i=0;i<9;i++)
			this.items[i] = new ItemBlock(BlockDirt.class);
	}
}
