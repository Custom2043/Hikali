package shaders;

import org.lwjgl.util.vector.Vector3f;

import util.TriDouble;
import block.BlockPos;

public class UniCoor3DShader extends Shader3D
{
	private int location_worldPosition;
	public UniCoor3DShader() {
		super("world/UniCoorVertex.txt", "gui/DefaultFragment.txt");
	}

	protected void bindAttributes() 
	{
		this.bindAttribute(0, "position");
		this.bindAttribute(1, "color");
		this.bindAttribute(2, "textureCoordinates");
	}

	protected void getAllUniformLocations()
	{
		super.getAllUniformLocations();
		location_worldPosition = this.getUniformLocation("worldPosition");
	}
	public void loadWorldPosition(TriDouble td)
	{
		this.loadVector(this.location_worldPosition, new Vector3f((float)td.x,(float)td.y,(float)td.z));
	}
	public void loadWorldPosition(BlockPos i){this.loadWorldPosition(i.getTriDouble());}
	public int vboNumber()
	{
		return 3;
	}

}
