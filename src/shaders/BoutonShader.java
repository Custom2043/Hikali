package shaders;

public class BoutonShader extends UniCoor2DShader
{
	private int location_state;
	public BoutonShader(boolean t)
	{
		super("gui/BoutonVertex.txt", t);
	}
	protected void getAllUniformLocations() {
		super.getAllUniformLocations();
		location_state = super.getUniformLocation("state");
	}
	protected void bindAttributes() {
		super.bindAttributes();
		super.bindAttribute(3, "sizer");
	}
	public void loadState(float state)
	{
		this.loadFloat(this.location_state, state);
	}
}
