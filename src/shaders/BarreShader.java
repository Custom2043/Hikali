package shaders;

public class BarreShader extends UniCoor2DShader
{
	private int location_inUse;
	public BarreShader() 
	{
		super("gui/BarreVertex.txt", true);
	}
	protected void getAllUniformLocations() 
	{
		super.getAllUniformLocations();
		this.location_inUse = super.getUniformLocation("inUse");
	}
	public void loadInUse(boolean inUse)
	{
		this.loadBoolean(location_inUse, inUse);
	}
}
