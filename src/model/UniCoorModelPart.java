package model;

import org.newdawn.slick.Color;

import util.TextureCoor;

public class UniCoorModelPart 
{
	public float[] screenCoors;
	public Color[] colors;
	public TextureCoor textureCoor;
	
	public UniCoorModelPart(TextureCoor tc){this(new float[]{0,0,1,0,1,1,0,1},tc);}
	public UniCoorModelPart(Color c){this(new float[]{0,0,1,0,1,1,0,1},new Color[]{c,c,c,c}, null);}
	public UniCoorModelPart(float[] f, TextureCoor tc){this(f, new Color[]{Color.white,Color.white,Color.white,Color.white}, tc);}
	public UniCoorModelPart(float[] f, Color c, TextureCoor tc){this(f,new Color[]{c,c,c,c},tc);}
	public UniCoorModelPart(float[] f, Color[] c, TextureCoor tc){screenCoors = f;colors = c;textureCoor = tc;}

}
