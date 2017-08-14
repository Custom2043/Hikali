package item;

import util.IDList;

public class ItemList 
{
	public static IDList<Class <? extends Item>> items = new IDList<Class <? extends Item>>();
	
	public static Class <? extends Item> getItemForID(int id)
	{
		return items.getObjectForID(id);
	}
	
	public static int getIDForItem(Class <? extends Item> c)
	{
		return items.getIDForObject(c);
	}
	static
	{
		
	}
}
