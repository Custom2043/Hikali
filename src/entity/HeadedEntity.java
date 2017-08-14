package entity;

import java.io.IOException;

import util.CustomInputStream;
import util.CustomMover;
import util.CustomOutputStream;
import util.EntityPosRot;
import util.TriDouble;
import world.World;

public abstract class HeadedEntity extends Entity
{
	public boolean headChanged = false;
	public CustomMover dep = new CustomMover();
	public TriDouble head;
	HeadedEntity(String name, CustomInputStream cis) throws IOException
	{
		super(name, cis);
		this.head = cis.readTriDouble();
	}
	public HeadedEntity(EntityPosRot l, TriDouble h, String n)
	{
		super(l, n);
		this.head = h;
	}
	public void write(CustomOutputStream cos) throws IOException
	{
		super.write(cos);
		cos.writeTriDouble(this.head);
	}
	public void update(long dif, World w)
	{
		double vitesse = 0.0043;
		//vitesse = 0.013; Ralentissement sur terre
		if (this.dep.getMove(CustomMover.SPRINTING))
			vitesse = 0.0056;
		vitesse *= dif;
		if (this.dep.getMove(CustomMover.SNEAKING))
			this.body.pos.y -= vitesse;
		if (this.dep.getMove(CustomMover.JUMPING))
			this.body.pos.y += vitesse;
		if (this.dep.getMove(CustomMover.FORWARD))
		{
			this.body.pos.z += (-vitesse * Math.cos(Math.toRadians(this.head.y)));
			this.body.pos.x += ( vitesse * Math.sin(Math.toRadians(this.head.y)));
		}
		if (this.dep.getMove(CustomMover.BACKWARD))
		{
			this.body.pos.z += ( vitesse * Math.cos(Math.toRadians(this.head.y)));
			this.body.pos.x += (-vitesse * Math.sin(Math.toRadians(this.head.y)));
		}
		if (this.dep.getMove(CustomMover.LEFT))
		{
			this.body.pos.z += (-vitesse * Math.sin(Math.toRadians(this.head.y)));
			this.body.pos.x += (-vitesse * Math.cos(Math.toRadians(this.head.y)));
		}
		if (this.dep.getMove(CustomMover.RIGHT))
		{
			this.body.pos.z += ( vitesse * Math.sin(Math.toRadians(this.head.y)));
			this.body.pos.x += ( vitesse * Math.cos(Math.toRadians(this.head.y)));
		}
		if (this.dep.isMoving())
			this.walkHelper.walk();
		else
			this.walkHelper.repose();
			//this.body.angle.y = Main.approach(this.head.angle.y, this.body.angle.y, 10);
	}
}
