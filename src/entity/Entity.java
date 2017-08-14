package entity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.newdawn.slick.opengl.Texture;

import util.CustomInputStream;
import util.CustomOutputStream;
import util.EntityPart;
import util.EntityPosRot;
import world.World;
import block.BlockPos;

public abstract class Entity
{
	public WalkHelper walkHelper = new WalkHelper(this.getAdd(), this.getMax());
	public final String name;
	public final EntityPosRot body;

	public Entity(String name, CustomInputStream cis) throws IOException
	{
		this.name = name;
		this.body = cis.readEntityPosRot();
	}
	public Entity (EntityPosRot b, String n)
	{
		this.body = b;
		this.name = n;
	}
	public abstract Texture getTexture();
	public BlockPos getBlockPos()
	{
		return new BlockPos((int)Math.floor(this.body.pos.x), (int)Math.floor(this.body.pos.y),(int)Math.floor(this.body.pos.z));
	}
	private float getAdd(){return 3.5f;}
	private float getMax(){return 30f;}
	public void write(CustomOutputStream cos) throws IOException
	{
		cos.writeEntityPosRot(this.body);
	}
	public static Entity readFromFile(CustomInputStream cis)
	{
		try {return EntityList.getEntityForID(cis.readInt()).getConstructor(CustomInputStream.class).newInstance(cis.readString(), cis);}
		catch (InstantiationException | IllegalAccessException | IOException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
		{e.printStackTrace();}
		return null;
	}
	public abstract EntityPart getMainPart();
	public abstract void update(long dif, World w);
}
