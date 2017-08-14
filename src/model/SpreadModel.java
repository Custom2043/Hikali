package model;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import drawer.Model;
import drawer.VAOLoader;

public class SpreadModel extends Model
{
	public SpreadModelPart[] parts;
	public SpreadModel(SpreadModelPart p, Texture text) {this (new SpreadModelPart[]{p}, text);}
	public SpreadModel(SpreadModelPart[] p, Texture text) 
	{
		super(p.length * 4);
		texture = text;
		this.enableVertexArray(0);this.enableVertexArray(1);
		parts = p;
		storeDatas();
		VAOLoader.unbind();
	}
	public void storeDatas() 
	{
		ByteBuffer buf = BufferUtils.createByteBuffer(vertexNumber * 8); // ScreenCoors
		for (SpreadModelPart gmp : parts)
			for (float i : gmp.screenCoors)
				buf.putFloat(i);
		VAOLoader.storeBufferInAttributeList(0, 2, buf, GL11.GL_FLOAT);
		buf = BufferUtils.createByteBuffer(vertexNumber * 4); // Couleurs
		for (SpreadModelPart gmp : parts)
			for (Color c : gmp.colors)
			{
				buf.put((byte)c.getRed());
				buf.put((byte)c.getGreen());
				buf.put((byte)c.getBlue());
				buf.put((byte)c.getAlpha());
			}
		VAOLoader.storeBufferInAttributeList(1, 4, buf, GL11.GL_UNSIGNED_BYTE);
	}

}
