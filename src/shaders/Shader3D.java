package shaders;

import org.lwjgl.util.vector.Matrix4f;

import util.Matrix;
import drawer.ShaderProgram;

public abstract class Shader3D extends ShaderProgram
{
	private int location_projectionMatrix, location_viewMatrix;
	public Shader3D(String vertexFile, String fragmentFile) 
	{
		super(vertexFile, fragmentFile);
	}
	protected void getAllUniformLocations()
	{
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	public void loadProjectionMatrix(float fov)
	{
		this.loadMatrix(location_projectionMatrix, Matrix.createProjectionMatrix(fov));
	}
	public void loadViewMatrix(Matrix4f m)
	{
		this.loadMatrix(location_viewMatrix, m);
	}
}
