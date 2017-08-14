package model;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import util.BlockPart;
import util.TextureCoor;
import drawer.Model;
import drawer.VAOLoader;

public class CubeModel extends Model
{
	public final BlockPart part;
	public CubeModel[] dependant;
	public CubeModel(BlockPart p, Texture text) 
	{
		super(24);
		texture = text;
		this.enableVertexArray(0);this.enableVertexArray(1);this.enableVertexArray(2);
		part = p;
		dependant = new CubeModel[p.dependantPart.length];
		int i=0;
		for (BlockPart bp : p.dependantPart)
		{
			dependant[i] = new CubeModel(bp, text);
			i++;
		}
		storeDatas();
		VAOLoader.unbind();
	}
	public void storeDatas() 
	{
		ByteBuffer buf = BufferUtils.createByteBuffer(288); // Position
		buf.putFloat(0).putFloat(0).putFloat(0); // Arriere
		buf.putFloat(0).putFloat(1).putFloat(0);
		buf.putFloat(1).putFloat(1).putFloat(0);
		buf.putFloat(1).putFloat(0).putFloat(0);
		
		buf.putFloat(0).putFloat(1).putFloat(0); // Haut
		buf.putFloat(0).putFloat(1).putFloat(1);
		buf.putFloat(1).putFloat(1).putFloat(1);
		buf.putFloat(1).putFloat(1).putFloat(0);
		
		buf.putFloat(0).putFloat(1).putFloat(1); // Devant
		buf.putFloat(0).putFloat(0).putFloat(1);
		buf.putFloat(1).putFloat(0).putFloat(1);
		buf.putFloat(1).putFloat(1).putFloat(1);
		
		buf.putFloat(0).putFloat(0).putFloat(1); // Bas
		buf.putFloat(0).putFloat(0).putFloat(0);
		buf.putFloat(1).putFloat(0).putFloat(0);
		buf.putFloat(1).putFloat(0).putFloat(1);
		
		buf.putFloat(0).putFloat(0).putFloat(0); // Gauche
		buf.putFloat(0).putFloat(0).putFloat(1);
		buf.putFloat(0).putFloat(1).putFloat(1);
		buf.putFloat(0).putFloat(1).putFloat(0);
		
		buf.putFloat(1).putFloat(0).putFloat(0); // Droite
		buf.putFloat(1).putFloat(1).putFloat(0);
		buf.putFloat(1).putFloat(1).putFloat(1);
		buf.putFloat(1).putFloat(0).putFloat(1);
		
		VAOLoader.storeBufferInAttributeList(0, 3, buf, GL11.GL_FLOAT);
		
		buf = BufferUtils.createByteBuffer(96);
		for (int i=0;i<96;i++)
			buf.put((byte)255);
		VAOLoader.storeBufferInAttributeList(1, 4, buf, GL11.GL_UNSIGNED_BYTE);
		
		buf = BufferUtils.createByteBuffer(192); // Position
		for (TextureCoor tc : part.coors)
		{
			for (float f : tc.inFloatArray(texture))
				buf.putFloat(f);
		}
		VAOLoader.storeBufferInAttributeList(2, 2, buf, GL11.GL_FLOAT);
	}
}
