package client.options;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import util.CustomMover;
import value.KeyValue;

public class KeyOptions 
{
	public KeyCategorie[] keys = new KeyCategorie[]{
		new KeyCategorie("key.misc",new KeyValue[]{
				new KeyValue(0, "key.fullScreen"),
				new KeyValue(0, "key.renderMode"),
				new KeyValue(0, "key.pause"),
				new KeyValue(0, "key.debugMode"),
				}),
		new KeyCategorie("key.move",new KeyValue[]{
			new KeyValue(0, "key.forward"),
			new KeyValue(0, "key.backward"),
			new KeyValue(0, "key.left"),
			new KeyValue(0, "key.right"),
			new KeyValue(0, "key.sneak"),
			new KeyValue(0, "key.jump"),
			new KeyValue(0, "key.run")
			}),
		new KeyCategorie("key.gameplay",new KeyValue[]{
			new KeyValue(0, "key.break"),
			new KeyValue(0, "key.put"),
			new KeyValue(0, "key.take"),
			new KeyValue(0, "key.chat"),
			}),
		new KeyCategorie("key.inventory",new KeyValue[]{
			new KeyValue(0, "key.inventory"),
			new KeyValue(0, "key.inventoryCase1"),
			new KeyValue(0, "key.inventoryCase2"),
			new KeyValue(0, "key.inventoryCase3"),
			new KeyValue(0, "key.inventoryCase4"),
			new KeyValue(0, "key.inventoryCase5"),
			new KeyValue(0, "key.inventoryCase6"),
			new KeyValue(0, "key.inventoryCase7"),
			new KeyValue(0, "key.inventoryCase8"),
			new KeyValue(0, "key.inventoryCase9"),
			}),
		};
	public KeyOptions(){}
	public KeyOptions(FileInputStream fis)
	{
		try
		{
			for (KeyCategorie cate : this.keys)
				for (KeyValue value : cate.getAllKeys())
					value.setValue(fis.read());
		} 
		catch (IOException e) {e.printStackTrace();}
	}
	public void write(FileOutputStream fos)
	{
		try
		{
			for (KeyCategorie cate : this.keys)
				for (KeyValue value : cate.getAllKeys())
					fos.write(value.getValue());
		} 
		catch (IOException e) {e.printStackTrace();}
	}
	public KeyValue getRenderModeKey(){return this.keys[0].getKeybyId(1);}
	public KeyValue getForwardKey(){return this.keys[1].getKeybyId(0);}
	public KeyValue getBackwardKey(){return this.keys[1].getKeybyId(1);}
	public KeyValue getLeftKey(){return this.keys[1].getKeybyId(2);}
	public KeyValue getRightKey(){return this.keys[1].getKeybyId(3);}
	public KeyValue getSneakingKey(){return this.keys[1].getKeybyId(4);}
	public KeyValue getJumpingKey(){return this.keys[1].getKeybyId(5);}
	public KeyValue getSprintingKey(){return this.keys[1].getKeybyId(6);}
	public boolean isKeyPressed(KeyValue v)
	{
		if (v.isMouseValue())
			return Mouse.isButtonDown(v.getMouseValue());
		return Keyboard.isKeyDown(v.getValue());
	}
	public void setMoves(CustomMover moves)
	{
		moves.setMove(this.isKeyPressed(this.getForwardKey()), CustomMover.FORWARD);
		moves.setMove(this.isKeyPressed(this.getBackwardKey()), CustomMover.BACKWARD);
		moves.setMove(this.isKeyPressed(this.getLeftKey()), CustomMover.LEFT);
		moves.setMove(this.isKeyPressed(this.getRightKey()), CustomMover.RIGHT);
		moves.setMove(this.isKeyPressed(this.getSneakingKey()), CustomMover.SNEAKING);
		moves.setMove(this.isKeyPressed(this.getJumpingKey()), CustomMover.JUMPING);
		moves.setMove(this.isKeyPressed(this.getSprintingKey()), CustomMover.SPRINTING);
	}
	public String getNameForValue(int value)
	{
		for (KeyCategorie kc : this.keys)
			for (KeyValue kv : kc.getAllKeys())
				if (kv.getValue() == value)
					return kv.nom;
		return "";
	}
}
