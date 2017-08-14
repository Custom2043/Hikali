package packet.toClient;

import java.io.IOException;

import main.Client;
import main.Hikali;
import main.Main;
import client.drawer.gui.GuiMainMenu;
import client.drawer.gui.GuiWaitWorldData;
import entity.EntityHuman;
import entity.EntityPlayer;

public class PacketToClientHandler
{
	private static Hikali game = Client.game;
	private static EntityPlayer player = game.joueur;
	public static void handleCMoveUpdate(CPacketMoveUpdate p)
	{
		EntityHuman h = game.world.getEntityHumanByName(p.name);
		h.dep = p.mover;
		h.body.pos = p.pos;
	}
	public static void handleCPlayerConnect(CPacketPlayerConnect p)
	{
		System.out.println(p.humain.name+" se connecte");
		game.world.addEntity(p.humain);
	}
	public static void handleCJoinGame (CPacketJoinGame p)
	{
		game.joueur.body.copyFrom(p.body);
		game.joueur.head = p.head;
		game.world.addEntity(game.joueur);
		for (EntityHuman j : p.humans)
		{
			System.out.println(j.name+" connecté");
			game.world.addEntity(j);
		}
		GuiWaitWorldData.joinGame();
	}
	public static void handleCQuitGame(CPacketPlayerQuit p)
	{
		game.world.removeEntity(p.name);
	}
	public static void handleCServerClose()
	{
		System.out.println("SERVEUR CLOSE");
		if (game.isInGame())
		{
			try {player.socket.close();}
			catch (IOException e) {e.printStackTrace();}
			game.inGame = false;
			Main.setGui(new GuiMainMenu());
		}
	}
	public static void handleCHeadUpdate(CPacketHeadUpdate p)
	{
		game.world.getEntityHumanByName(p.sender).head = p.head;
	}
	public static void handleCChunk(CPacketChunk p)
	{
		game.world.chunks.add(p.ch);
	}
}
