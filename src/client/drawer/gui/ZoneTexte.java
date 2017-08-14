package client.drawer.gui;

import static client.drawer.GuiDrawer.drawCenteredStringWithShadow;
import static client.drawer.GuiDrawer.drawHoveredStringWithShadow;
import static drawer.CustomDrawer.drawModel;
import static drawer.CustomDrawer.getGuiSize;
import gui.CustomBouton;
import gui.CustomZoneTexte;
import model.BoutonModel;
import model.BoutonModelPart;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;

import util.ScreenCoor;
import client.drawer.GuiDrawer;
import drawer.ShaderProgram;

public class ZoneTexte extends CustomZoneTexte
{
	public static BoutonModel blanc = new BoutonModel(new BoutonModelPart(Color.white), GuiDrawer.WIDGET),
							   noir = new BoutonModel(new BoutonModelPart(Color.black), GuiDrawer.WIDGET),
							   gris = new BoutonModel(new BoutonModelPart(Color.gray ), GuiDrawer.WIDGET);
	public ZoneTexte(String t, int i, ScreenCoor c)
	{
		super(t, i, c, "", true);
	}
	public void draw()
	{
		Bouton.colorBoutonShader.start();
		Bouton.colorBoutonShader.loadCoor(this.coor.addGui(-1,-1,2,2));
		drawModel(blanc);
		Bouton.colorBoutonShader.loadCoor(this.coor);
		drawModel(noir);
		Bouton.colorBoutonShader.loadCoor(ScreenCoor.guiFlat(0,this.coor.yGui+4,0,12,this.coor.getStartX() + ECART*getGuiSize() + this.getFont().getWidth(this.texte.substring(0, this.getDebut())), 0,this.getFont().getWidth(this.texte.substring(this.getDebut(), this.getFin())),0));
		drawModel(blanc);
		Bouton.colorBoutonShader.loadCoor(ScreenCoor.guiFlat(0,this.coor.yGui+4,1,12,this.coor.getStartX()+ECART*getGuiSize()+this.getFont().getWidth(this.texte.substring(0, this.curseur)),0,0,0));
		if (this.visbleCurseur)
			if (this.isSelected())
				drawModel(gris);
			else
				drawModel(blanc);
		ShaderProgram.stop();
		
		if (this.isSelected())
		{
			drawCenteredStringWithShadow(this.coor.getStartX() + ECART*getGuiSize() + this.getFont().getWidth(this.texte.substring(0,this.getDebut()))/2, this.coor.getStartY()+this.coor.getHeight()/2, this.texte.substring(0,this.getDebut()));
			drawHoveredStringWithShadow(this.coor.getStartX() + ECART*getGuiSize() + this.getFont().getWidth(this.texte.substring(0,this.getDebut())) + this.getFont().getWidth(this.texte.substring(this.getDebut(),this.getFin()))/2, this.coor.getStartY()+this.coor.getHeight()/2, this.texte.substring(this.getDebut(), this.getFin()));
			drawCenteredStringWithShadow(this.coor.getStartX() + ECART*getGuiSize() + this.getFont().getWidth(this.texte.substring(0,this.getFin())) + this.getFont().getWidth(this.texte.substring(this.getFin(),this.texte.length()))/2, this.coor.getStartY()+this.coor.getHeight()/2, this.texte.substring(this.getFin(),this.texte.length()));
		}
		else
			drawCenteredStringWithShadow(this.coor.getStartX() + ECART*getGuiSize() + this.getFont().getWidth(this.texte)/2, this.coor.getStartY()+this.coor.getHeight()/2, this.texte);
		if (this.isActiv)
			if (this.timer.getNumberOfTicks(500) > 0)
			{
				this.switchCurseurState();
				this.timer.resetUnderATick(500);
			}
	}
	public AngelCodeFont getFont()
	{
		return GuiDrawer.getFont();
	}
	public void extraClassicalActions(CustomBouton boutonOn, boolean appuie, int clicID, int X, int Y) {}
}
