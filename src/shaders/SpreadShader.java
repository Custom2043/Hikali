package shaders;


public class SpreadShader extends UniCoor2DShader
{
	public SpreadShader() 
	{
		super("gui/SpreadVertex.txt", true);
	}

	protected void bindAttributes() 
	{
		super.bindAttributes();
	}
	public int vboNumber()
	{
		return 2;
	}
}
