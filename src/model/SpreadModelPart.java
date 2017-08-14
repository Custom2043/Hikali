package model;

import org.newdawn.slick.Color;

public class SpreadModelPart 
{
	public float[] screenCoors;
	public Color[] colors;
	
	public SpreadModelPart(){this(Color.white);}
	public SpreadModelPart(Color c){this(new float[]{0,0,1,0,1,1,0,1},new Color[]{c,c,c,c});}
	public SpreadModelPart(float[] f, Color[] c){screenCoors = f;colors = c;}
}
