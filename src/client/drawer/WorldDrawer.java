package client.drawer;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LEQUAL;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor4ub;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex3i;
import main.Hikali;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import shaders.UniCoor3DShader;
import util.CustomMover;
import util.EntityPart;
import util.Matrix;
import util.TextureCoor;
import util.TriDouble;
import world.Chunk;
import block.Block;
import drawer.CustomDrawer;
import drawer.ShaderProgram;
import drawer.TextureManager;
import entity.Entity;

public class WorldDrawer extends CustomDrawer
{
	private static float oldFov = 0;
	private static UniCoor3DShader shader;
	public static float addFov = 0;
	public static Hikali game;
	public static TextureManager text = new TextureManager("Ressources/textures/", "PNG");
	public static final Texture DIRT = text.loadTexture("blocks/dirt.png"),
								SKIN = text.loadTexture("Skin.png");
	public static void draw()
	{  
		prepare();

		shader.start();
		
		if (game.joueur.dep.getMove(CustomMover.SPRINTING) && game.joueur.dep.isMoving())
			addFov += 3f;
		else
			addFov -=3f;
		if (addFov < 0)
			addFov = 0;
		if (addFov > 15)
			addFov = 15;

		if (game.options.fov.value + addFov != oldFov)
		{
			oldFov = game.options.fov.value + addFov;
			shader.loadProjectionMatrix(game.options.fov.value + addFov);
		}

		/*if (game.renderMode != 0)
			translateCamera(new TriDouble(0,0,4));
		if (game.renderMode == 2)
			turnCamera(new TriDouble(0,180,0));

		turnCamera(game.joueur.body.angle.add(game.joueur.head));
		translateCamera(game.joueur.body.pos);
		translateCamera(EntityHuman.eyes);
		translateCamera(new TriDouble(- Math.sin(Math.toRadians(game.joueur.walkHelper.state)) * .1,-Math.sin(Math.toRadians(Math.abs(game.joueur.walkHelper.state))) * .1,0));

		if (game.renderMode != 0)
			drawEntity(game.joueur);*/

		for (Entity h : game.world.getEntities())
			if (h != game.joueur)
				drawEntity(h);
		
		Block b;
		for (Chunk c : game.world.chunks)
			for (int i=0;i<c.data.length;i++)
			{
				b = c.data[i];
				if (b.getTexture() != null)
				{
					shader.loadViewMatrix(Matrix.createTransformationMatrix(b.pos.getTriDouble(), new TriDouble(0,0,0)));
					drawModel(b.getModel());
					//drawBlock(b.getMain(c.getBlockPos(i)), b.getTexture());
				}
			}
		ShaderProgram.stop();
		//drawCube(1,1,1,-2,-2,-2,0,0,0);
		load2D();
	}
	public static void load(Hikali game)
	{
		WorldDrawer.game = game;
		shader = new UniCoor3DShader();
	}
	public static void drawEntity(Entity e)
	{
		drawEntityPart(e.getMainPart(), e.getTexture());
	}
	public static void drawEntityPart(EntityPart e, Texture t)
	{
		glPushMatrix();

		glTranslated(e.rotatePoint.x, e.rotatePoint.y, e.rotatePoint.z);

		glRotated(- e.angle.y,0,1,0);
		glRotated(- e.angle.x,1,0,0);
		glRotated(- e.angle.z,0,0,1);

		glTranslated(e.begin.x, e.begin.y, e.begin.z);

		if (e.dependantPart.length > 0)
			glPushMatrix();

		glScaled(e.size.x, e.size.y, e.size.z);

		drawFace(t, new TextureCoor(e.x+e.zs, e.y+e.zs, e.xs, e.ys), e.symX);
		glTranslated(1,0,0);
		glRotated(-90,0,1,0);
		drawFace(t, new TextureCoor(e.x+e.xs+e.zs, e.y+e.zs, e.zs, e.ys), e.symX);
		glTranslated(1,0,0);
		glRotated(-90,0,1,0);
		drawFace(t, new TextureCoor(e.x+2*e.zs+e.xs, e.y+e.zs, e.xs, e.ys), e.symX);
		glTranslated(1,0,0);
		glRotated(-90,0,1,0);
		drawFace(t, new TextureCoor(e.x,e.y+e.zs,e.zs,e.ys), e.symX);
		glTranslated(1,0,0);
		glRotated(-90,0,1,0);
		glTranslated(0,0,1);
		glRotated(-90,1,0,0);
		drawFace(t, new TextureCoor(e.x+e.zs+e.xs, e.y, e.xs, e.zs), e.symX);
		glTranslated(0,0,1);
		glRotated(-180,1,0,0);
		glTranslated(0,-1,0);
		drawFace(t, new TextureCoor(e.x+e.zs, e.y, e.xs, e.zs), e.symX);

		if (e.dependantPart.length > 0)
			glPopMatrix();

		for (EntityPart ep : e.dependantPart)
			drawEntityPart(ep, t);

        glPopMatrix();
	}
	public static void drawFace(Texture text, TextureCoor textureCoor)
	{
		drawFace(text, textureCoor, false);
	}
	public static void drawFace(Texture text, TextureCoor textureCoor, boolean sym)
	{
		text.bind();

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glColor4ub((byte)255, (byte)255, (byte)255, (byte)255);

		GL11.glBegin(GL11.GL_QUADS);

		glTexCoord2f(sym ? textureCoor.getXEnd(text) : textureCoor.getXStart(text), textureCoor.getYStart(text));
		glVertex3i(0,1,0);
		glTexCoord2f(sym ? textureCoor.getXStart(text) : textureCoor.getXEnd(text), textureCoor.getYStart(text));
		glVertex3i(1,1,0);
		glTexCoord2f(sym ? textureCoor.getXStart(text) : textureCoor.getXEnd(text), textureCoor.getYEnd(text));
		glVertex3i(1,0,0);
		glTexCoord2f(sym ? textureCoor.getXEnd(text) : textureCoor.getXStart(text),textureCoor.getYEnd(text));
		glVertex3i(0,0,0);

		GL11.glEnd();
	}
	public static void prepare()
	{
		glClear(GL_DEPTH_BUFFER_BIT);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glEnable(GL_CULL_FACE);
	}
}