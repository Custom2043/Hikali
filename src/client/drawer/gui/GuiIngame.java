package client.drawer.gui;

import static client.drawer.GuiDrawer.drawIngameGui;
import main.Client;
import main.Main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import util.MouseHelper;
import util.TriDouble;
import entity.EntityPlayer;
import gui.CustomBouton;

public class GuiIngame extends Gui
{
	public EntityPlayer player = Client.game.joueur;
	public GuiIngame()
	{
		MouseHelper.grab();
	}
	public String getName()
	{
		return null;
	}
	public void quit()
	{
		MouseHelper.releaseMouse();
	}

	public void drawBeforeButtons()
	{
		drawIngameGui(this.player);
	}
	public void type()
	{
		String keyName = Client.game.options.keys.getNameForValue(Keyboard.getEventKey());
		if (keyName.equals("key.pause"))
			Main.setGui(new GuiGameMenu());
		if (keyName.equals("key.renderMode"))
		{
			Client.game.renderMode++;
			if (Client.game.renderMode > 2)
				Client.game.renderMode = 0;
		}
	}
	public boolean renderWorld(){return true;}
	@Override
	protected void mouseEvent(int clicID, int X, int Y, boolean press,
			CustomBouton boutonOn) {
		int x = MouseHelper.getDXMouse(), y = MouseHelper.getDYMouse();
		if (x != 0 || y != 0)
			this.player.headChanged = true;
		TriDouble headAngle = this.player.head;
		headAngle.y += (x / 5d);
		headAngle.x +=(y / 5d);
		if (headAngle.x > 90)
			headAngle.x = 90;
		if (headAngle.x < -90)
			headAngle.x = -90;
		if (Mouse.getEventDWheel() != 0)
		{
			this.player.itemPick += Mouse.getEventDWheel() > 0 ? 1 : -1;
			if (this.player.itemPick < 0)
				this.player.itemPick = 8;
			if (this.player.itemPick > 8)
				this.player.itemPick = 0;
		}
		
	}
	@Override
	protected void keyboardEvent(char carac, int keyCode) {
		
	}
}
