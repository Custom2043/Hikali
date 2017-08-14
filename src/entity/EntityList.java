package entity;

import util.IDList;

public class EntityList
{
	public static IDList<Class <? extends Entity>> entity = new IDList<Class <? extends Entity>>();

	public static Class <? extends Entity> getEntityForID(int id)
	{
		return entity.getObjectForID(id);
	}

	public static int getIDForEntity(Class <? extends Entity> c)
	{
		return entity.getIDForObject(c);
	}
	static
	{
		entity.add(EntityPlayer.class, 0);
	}
}
