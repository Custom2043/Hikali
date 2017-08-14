package client.drawer;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glTranslated;
import item.ItemBlock;
import main.Client;
import main.Main;
import model.UniCoorModel;
import model.UniCoorModelPart;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;

import shaders.GuiShader;
import shaders.SpreadShader;
import shaders.UniCoor2DShader;
import util.Matrix;
import util.QuadColor;
import util.ScreenCoor;
import util.TextureCoor;
import util.TimeSection;
import drawer.CustomDrawer;
import drawer.Matrix2DShader;
import drawer.ScreenCoorModel;
import drawer.ShaderProgram;
import drawer.TextureManager;
import drawer.VAOLoader;
import entity.EntityPlayer;

public class GuiDrawer extends CustomDrawer
{
	public static ScreenCoorModel hotBar;
	public static SpreadShader spreadShader;
	public static GuiShader defaultShader;
	public static GuiShader colorShader;
	public static UniCoorModel itemSelectModel;
	public static UniCoor2DShader itemSelectShader;
	public static TextureManager text = new TextureManager("Ressources/textures/", "PNG");
	public static final Texture FONDMAINMENU = text.loadTexture("gui/Panorama.png"),
								WIDGET = text.loadTexture("gui/Widget.png"),
								HIKALI_TITLE = text.loadTexture("gui/Hikali_Title.png"),
								OPTION_BACKGROUND = text.loadTexture("gui/Option_Background.png"),
								OPTION_BACKGROUND_DARK = text.loadTexture("gui/Option_Background_Dark.png")
								;
	private static AngelCodeFont petite, normal, grande;
	public static void init() throws LWJGLException
	{
		ShaderProgram.setVertexFolder("Ressources/shaders/");
		ShaderProgram.setFragmentFolder("Ressources/shaders/");
		defaultShader = new GuiShader(true);
		colorShader = new GuiShader(false);
		spreadShader = new SpreadShader();
		itemSelectShader = new UniCoor2DShader("gui/UniCoorVertex.txt", true);

		try
		{
			petite = new AngelCodeFont("Ressources/textures/font/Minecraft_9.fnt","Ressources/textures/font/Minecraft_9.png");
			normal = new AngelCodeFont("Ressources/textures/font/Minecraft_18.fnt","Ressources/textures/font/Minecraft_18.png");
			grande = new AngelCodeFont("Ressources/textures/font/Minecraft_36.fnt","Ressources/textures/font/Minecraft_36.png");
		}
		catch (SlickException e) {e.printStackTrace();}

		hotBar = new ScreenCoorModel(
				new ScreenCoor[]{ScreenCoor.screenGui(.5f,.5f,0,0,-4,-4,9,9), ScreenCoor.screenGui(.5f,1,0,0,-91,-22,182,22)},
				new QuadColor[]{new QuadColor(), new QuadColor()},
				new TextureCoor[]{new TextureCoor(243,3,9,9), new TextureCoor(0,0,182,22)},
				WIDGET);
				
		itemSelectModel = new UniCoorModel(new UniCoorModelPart(new TextureCoor(0,22,24,22)), WIDGET);

	}
	public static void draw()
	{
		prepare();
		Main.getGui().draw();
		CustomDrawer.updateScreenSize();
		if (GuiDrawer.hasScreenOrGuiBeenModified())
			loadProjectionMatrix();
		if (Client.game.debugMode)
			Main.getGui().debugMode();
		updateGuiSize();
		update();
	}
	public static void updateGuiSize()
	{
		int optionSize = Client.game.options.graphic.guiSize();
		if (optionSize == 2 && Display.getWidth() >= 1280 && Display.getHeight() >= 960)
			setGui(4);
		else if (optionSize != 0 && Display.getWidth() >= 640 && Display.getHeight() >= 480)
			setGui(2);
		else
			setGui(1);
	}
	public static AngelCodeFont getFont() {
		if (getGuiSize() == 4)
			return grande;
		if (getGuiSize() == 2)
			return normal;
		return petite;
	}
	/**
	 * X,Y,texte
	 */
	public static void drawStringWithShadow(float X, float Y,  String text)
	{
		CustomDrawer.drawString(X+getGuiSize(), Y+getGuiSize(), false, true, text, getFont(), new Color(56,56,56));
		CustomDrawer.drawString(X, Y, false, true, text, getFont(), Color.white);
	}
	/**
	 * X,Y,texte
	 */
	public static void drawCenteredStringWithShadow(float X, float Y,  String text)
	{
		CustomDrawer.drawString(X+getGuiSize(), Y+getGuiSize(), true, true, text, getFont(), new Color(56,56,56));
		CustomDrawer.drawString(X, Y, true, true, text, getFont(), Color.white);
	}
	/**
	 * X,Y,texte
	 */
	public static void drawHoveredButtonName(float X, float Y, String text)
	{
		getFont().drawString(centralX(X,text)+1*getGuiSize(),centralY(Y,text)+1*getGuiSize(), text, new Color(56,56,35));
		getFont().drawString(centralX(X,text),centralY(Y,text), text, new Color(255,255,160));
	}
	public static void drawKeyBoutonSelected(float X, float Y, String text)
	{
		getFont().drawString(centralX(X,text)+1*getGuiSize(),centralY(Y,text)+1*getGuiSize(), text, new Color(56,56,0));
		getFont().drawString(centralX(X,text),centralY(Y,text), text, new Color(255,255,0));
	}
	public static void drawInactivButtonName(float X, float Y,  String text)
	{
		getFont().drawString(centralX(X,text)+1*getGuiSize(),centralY(Y,text)+1*getGuiSize(), text, new Color(35,35,35));
		getFont().drawString(centralX(X,text),centralY(Y,text), text, new Color(160,160,160));
	}
	public static void drawHoveredStringWithShadow(float X, float Y,  String text)
	{
		CustomDrawer.drawString(X+getGuiSize(), Y+getGuiSize(), true, true, text, getFont(), new Color(200, 200, 200));
		CustomDrawer.drawString(X, Y, true, true, text, getFont(), Color.blue);
	}
	public static float centralX(float X,  String text)
	{
		return X - getFont().getWidth(text)/2;
	}
	public static float centralY(float Y,  String text)
	{
		return Y - getFont().getHeight(text)/2;
	}
	public static void quit()
	{
		text.quit();
		ShaderProgram.quit();
		VAOLoader.quit();
		Display.destroy();
	}
	public static void drawIngameGui(EntityPlayer player)
	{
		defaultShader.start();
		drawModel(hotBar);
		ShaderProgram.stop();
		itemSelectShader.start();
		itemSelectShader.loadCoor(ScreenCoor.screenGui(.5f,1,0,0,-92 + 20 * player.itemPick,-23,24,22));
		drawModel(itemSelectModel);
		ShaderProgram.stop();
		ScreenCoor hotBar = ScreenCoor.screenGui(.5f,1,0,0,-91,-22,182,22);
		glPushMatrix();
		glTranslated(hotBar.getStartX() + getGuiSize(), hotBar.getStartY(), 0);
		for (int i = 0; i<9;i++)
		{
			if (player.inventory.items[i] != null)
				player.inventory.items[i].draw();
			glTranslated(20*getGuiSize(),0,0);
		}
		glPopMatrix();
	}
	public static void drawItemBlock(ItemBlock b)
	{
		glColor4f(1,1,1,1);
		b.getTexture().bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		glBegin(GL_QUADS);

	/*	glTexCoord2f(b.block.getHaut().getXStart(b.getTexture()), b.block.getHaut().getYStart(b.getTexture()));
		glVertex2d(10*getGuiSize(),3.25*getGuiSize());
		glTexCoord2f(b.block.getHaut().getXEnd(b.getTexture()), b.block.getHaut().getYStart(b.getTexture()));
		glVertex2d(17*getGuiSize(),6.75*getGuiSize());
		glTexCoord2f(b.block.getHaut().getXEnd(b.getTexture()), b.block.getHaut().getYEnd(b.getTexture()));
		glVertex2d(10*getGuiSize(),10.25*getGuiSize());
		glTexCoord2f(b.block.getHaut().getXStart(b.getTexture()), b.block.getHaut().getYEnd(b.getTexture()));
		glVertex2d(3*getGuiSize(),6.75*getGuiSize());

		glColor4f(.7f,.7f,.7f,1);
		glTexCoord2f(b.block.getFace().getXStart(b.getTexture()), b.block.getFace().getYStart(b.getTexture()));
		glVertex2d(10*getGuiSize(),10.25*getGuiSize());
		glTexCoord2f(b.block.getFace().getXEnd(b.getTexture()), b.block.getFace().getYStart(b.getTexture()));
		glVertex2d(10*getGuiSize(),18.75*getGuiSize());
		glTexCoord2f(b.block.getFace().getXEnd(b.getTexture()), b.block.getFace().getYEnd(b.getTexture()));
		glVertex2d(3*getGuiSize(),15.75*getGuiSize());
		glTexCoord2f(b.block.getFace().getXStart(b.getTexture()), b.block.getFace().getYEnd(b.getTexture()));
		glVertex2d(3*getGuiSize(),6.75*getGuiSize());

		glColor4f(.4f,.4f,.4f,1);
		glTexCoord2f(b.block.getDroite().getXStart(b.getTexture()), b.block.getDroite().getYStart(b.getTexture()));
		glVertex2d(10*getGuiSize(),10.25*getGuiSize());
		glTexCoord2f(b.block.getDroite().getXEnd(b.getTexture()), b.block.getDroite().getYStart(b.getTexture()));
		glVertex2d(17*getGuiSize(),6.75*getGuiSize());
		glTexCoord2f(b.block.getDroite().getXEnd(b.getTexture()), b.block.getDroite().getYEnd(b.getTexture()));
		glVertex2d(17*getGuiSize(),15.75*getGuiSize());
		glTexCoord2f(b.block.getDroite().getXStart(b.getTexture()), b.block.getDroite().getYEnd(b.getTexture()));
		glVertex2d(10*getGuiSize(),18.75*getGuiSize());*/

		glEnd();
	}
	public static void drawSections()
	{
		int somme = 0;
		for (long i : TimeSection.last)
			somme += i;
		int already = 0;
		for (int i=0;i<TimeSection.SECTION_NUMBER;i++)
		{
			int temps = TimeSection.last[i];
			drawStringWithShadow(Display.getWidth() - 5 - getFont().getWidth(TimeSection.sectionNames[i]+" : "+temps), i*20+10, TimeSection.sectionNames[i]+" : "+temps);
			Color c = TimeSection.getColor(i);
			drawRect(ScreenCoor.screenGui(1,1,0,0,- 105+already,-25,Math.round(Main.dividef(100*temps, somme)),20), c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
			already += Math.round(Main.dividef(100*temps, somme));
		}
		drawStringWithShadow(Display.getWidth() - 5 - getFont().getWidth("Somme : "+somme), TimeSection.SECTION_NUMBER*20+10, "Somme : "+somme);
	}
	public static void loadProjectionMatrix()
	{
		Matrix2DShader.setMatrix(Matrix.createOrthographicMatrix(Display.getWidth(), Display.getHeight()));
		Matrix2DShader.setScreenData(Display.getWidth(), Display.getHeight(), (int)getGuiSize());
	}
	public static void prepare()
	{
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_CULL_FACE);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(1, 1, 1, 1);
	}
}
