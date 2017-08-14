package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.drawHoveredButtonName;
import static client.drawer.GuiDrawer.drawKeyBoutonSelected;
import static client.drawer.GuiDrawer.drawStringWithShadow;
import static drawer.CustomDrawer.drawModel;
import static drawer.CustomDrawer.getGuiSize;
import gui.CustomBouton;
import gui.CustomKeyBouton;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import util.ScreenCoor;
import util.Translator;
import value.KeyValue;
import client.drawer.GuiDrawer;
import drawer.ShaderProgram;

public class KeyBouton extends CustomKeyBouton
{
	public KeyBouton(String n, int i, ScreenCoor c, KeyValue k)
	{
		super(n, i, c, k);
	}
	public void draw()
	{
		Bouton.shader.start();
		Bouton.shader.loadCoor(coor);
		Bouton.shader.loadState(isActiv || isHovered() ? 2 : 1);
		drawModel(Bouton.model);
		ShaderProgram.stop();
		
		drawStringWithShadow(this.coor.getStartX()  - 175 * getGuiSize(),this.coor.getStartY() + this.coor.getHeight()/2, Translator.translate(this.nom));
		if (this.isActiv)
		{
			drawKeyBoutonSelected(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2, this.key.getKeyName());
		}
		else if (this.isHovered())
		{
			drawHoveredButtonName(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2, this.key.getKeyName());
		}
		else
		{
			drawCenteredStringWithShadow(this.coor.getStartX() + this.coor.getWidth()/2,this.coor.getStartY() + this.coor.getHeight()/2, this.key.getKeyName());
		}
	}
	public void keyTyped(char carac, int keyCode)
	{
		if (this.isActiv)
		{
			this.getKey().key.setValue(Keyboard.getEventKey());
			this.desactiv();
		}
	}
	public void extraClassicalActions(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y) {}
	public Texture getTexture() {return GuiDrawer.WIDGET;}
}
