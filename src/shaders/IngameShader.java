package shaders;


public class IngameShader extends DefaultShader
{
	private int location_pos;
	public IngameShader() {
		super("gui/IngameVertex.txt", true);
	}

	protected void getAllUniformLocations() {
		super.getAllUniformLocations();
		location_pos = super.getUniformLocation("position");
	}

	protected void bindAttributes() {
		super.bindAttributes();
	}
	public void laodPosition(float pos)
	{
		this.loadFloat(location_pos, pos);
	}
}
