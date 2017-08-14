package shaders;

public class SliderShader extends UniCoor2DShader
{
	private int location_state;
	public SliderShader() 
	{
		super("gui/SliderVertex.txt", true);
	}
	protected void getAllUniformLocations() {
		location_state = super.getUniformLocation("state");
		super.getAllUniformLocations();
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
