package model;

import org.newdawn.slick.Color;

import util.TextureCoor;

public class BoutonModelPart 
{
	public float[] screenCoors;
	public Color[] colors;
	public TextureCoor textureCoor;
	public float[] sizer;
	
	public BoutonModelPart(Color c){this(new float[]{0,0,1,0,1,1,0,1},new Color[]{c,c,c,c}, null, null);}
	public BoutonModelPart(float[] f, TextureCoor tc, float[] s){this(f, new Color[]{Color.white,Color.white,Color.white,Color.white}, tc, s);}
	public BoutonModelPart(float[] f, Color c, TextureCoor tc, float[] s){this(f,new Color[]{c,c,c,c},tc,s);}
	public BoutonModelPart(float[] f, Color[] c, TextureCoor tc, float[] s){screenCoors = f;colors = c;textureCoor = tc;sizer = s;}
}
