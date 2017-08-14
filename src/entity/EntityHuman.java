package entity;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;

import util.CustomInputStream;
import util.CustomOutputStream;
import util.EntityPart;
import util.EntityPosRot;
import util.TriDouble;
import client.drawer.WorldDrawer;

public class EntityHuman extends HeadedEntity
{
	public static TriDouble eyes = new TriDouble(0,1.63,0);
	EntityHuman(String name, CustomInputStream cis) throws IOException{super(name, cis);}
	public EntityHuman(EntityPosRot l, TriDouble h, String n)
	{
		super(l, h, n);
	}
	public Texture getTexture()
	{
		return WorldDrawer.SKIN;
	}
	public void write(CustomOutputStream cos) throws IOException
	{
		super.write(cos);
		cos.close();
	}
	public EntityPart getMainPart() {
		return //Corps
				new EntityPart(new TriDouble(-0.225,-.3375,-.1125), new TriDouble(this.body.angle.x, this.body.angle.y, this.body.angle.z), new TriDouble(0.45,0.675,0.225), new TriDouble(0+this.body.pos.x,1.0125+this.body.pos.y- Math.sin(Math.toRadians(Math.abs(this.walkHelper.state))*.1),0+this.body.pos.z), 16, 16, 8, 12, 4, new EntityPart[]{
				//Tete
				new EntityPart(new TriDouble(-.225,0,-.225), new TriDouble(this.head.x,this.head.y,this.head.z),new TriDouble(.45,.45,.45),new TriDouble(.225,.675,.1125),0,0,8,8,8),
				//Bras
				new EntityPart(new TriDouble(-.1125,-.5625,-.1125), new TriDouble(this.walkHelper.state,0,0), new TriDouble(0.225,0.675,0.225), new TriDouble(-.1125,.5625,.1125), 40, 16, 4, 12, 4),
				new EntityPart(new TriDouble(-.1125,-.5625,-.1125), new TriDouble(-this.walkHelper.state,0,0), new TriDouble(0.225,0.675,0.225), new TriDouble(.5625,.5625,.1125), 40, 16, 4, 12, 4).setSymX(true),
				//Jambes
				new EntityPart(new TriDouble(-.1125,-.5625,-.1125), new TriDouble(-this.walkHelper.state*2,0,0), new TriDouble(0.225,0.675,0.225), new TriDouble(.1125,-.1125,.1125), 0, 16, 4, 12, 4),
				new EntityPart(new TriDouble(-.1125,-.5625,-.1125), new TriDouble(this.walkHelper.state*2,0,0), new TriDouble(0.225,0.675,0.225), new TriDouble(.3375,-.1125,.1125), 0, 16, 4, 12, 4).setSymX(true),
				});
	}
}
