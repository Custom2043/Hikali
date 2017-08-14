package shaders;

import org.lwjgl.util.vector.Vector3f;

import util.ScreenCoor;
import drawer.Matrix2DShader;

public class UniCoor2DShader extends Matrix2DShader
{
	protected final boolean textured;
	private int loX, loY, loW, loH;
	public UniCoor2DShader(String vertexName, boolean t)
	{
		super(vertexName, t ? "gui/DefaultFragment.txt" : "gui/ColorFragment.txt");
		textured = t;
	}
	protected void getAllUniformLocations() {
		super.getAllUniformLocations();
		loX = super.getUniformLocation("x");
		loY = super.getUniformLocation("y");
		loW = super.getUniformLocation("w");
		loH = super.getUniformLocation("h");
	}
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "color");
		super.bindAttribute(2, "textureCoordinates");
	}//WTF DAT SHIT BRO ?
	public void loadCoor(ScreenCoor sc)
	{
		this.loadVector(loX, new Vector3f(sc.xScreen, sc.xGui, sc.xFlat));
		this.loadVector(loY, new Vector3f(sc.yScreen, sc.yGui, sc.yFlat));
		this.loadVector(loW, new Vector3f(sc.wScreen, sc.wGui, sc.wFlat));
		this.loadVector(loH, new Vector3f(sc.hScreen, sc.hGui, sc.hFlat));
	}
}
