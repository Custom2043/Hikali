package model;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import drawer.Model;
import drawer.VAOLoader;

public class BoutonModel extends Model
{
	public BoutonModelPart[] parts;
	
	public BoutonModel(BoutonModelPart p, Texture text) {this(new BoutonModelPart[]{p}, text);}
	public BoutonModel(BoutonModelPart[] p, Texture text) {
		super(p.length*4);
		texture = text;
		this.enableVertexArray(0);this.enableVertexArray(1);this.enableVertexArray(2);this.enableVertexArray(3);
		parts = p;
		storeDatas();
		VAOLoader.unbind();
	}

	public void storeDatas() 
	{
		ByteBuffer buf = BufferUtils.createByteBuffer(vertexNumber * 8); // ScreenCoors
		for (BoutonModelPart gmp : parts)
			for (float i : gmp.screenCoors)
				buf.putFloat(i);
		VAOLoader.storeBufferInAttributeList(0, 2, buf, GL11.GL_FLOAT);
		buf = BufferUtils.createByteBuffer(vertexNumber * 4); // Couleurs
		for (BoutonModelPart gmp : parts)
			for (Color c : gmp.colors)
			{
				buf.put((byte)c.getRed());
				buf.put((byte)c.getGreen());
				buf.put((byte)c.getBlue());
				buf.put((byte)c.getAlpha());
			}
		VAOLoader.storeBufferInAttributeList(1, 4, buf, GL11.GL_UNSIGNED_BYTE);
		buf = BufferUtils.createByteBuffer(vertexNumber * 8); // Textures
		for (BoutonModelPart gmp : parts)
			if (gmp.textureCoor != null)
				for (float i : gmp.textureCoor.inFloatArray(texture))
					buf.putFloat(i);
		VAOLoader.storeBufferInAttributeList(2, 2, buf, GL11.GL_FLOAT);
		
		buf = BufferUtils.createByteBuffer(vertexNumber * 8); // Sizer
		for (BoutonModelPart gmp : parts)
			if (gmp.sizer != null)
				for (float i : gmp.sizer)
					buf.putFloat(i);
		VAOLoader.storeBufferInAttributeList(3, 2, buf, GL11.GL_FLOAT);
	}
}
