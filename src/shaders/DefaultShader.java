package shaders;

import drawer.ColoredScreenCoorShader;

public abstract class DefaultShader extends ColoredScreenCoorShader
{
	private final boolean textured;
	public DefaultShader(String vertexName, boolean t) {
		super(vertexName, t ? "gui/DefaultFragment.txt" : "gui/ColorFragment.txt");
		textured = t;
	}

	protected void getAllUniformLocations() {
		super.getAllUniformLocations();
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "x");
		super.bindAttribute(1, "y");
		super.bindAttribute(2, "color");
		super.bindAttribute(3, "textureCoordinates");
	}
	public int vboNumber()
	{
		return textured ? 4 : 3;
	}
}
