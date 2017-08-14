package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import main.HikaliServer;
import main.Main;

import org.apache.commons.io.IOUtils;

import packet.Packet;
import packet.toClient.CPacketJoinGame;
import packet.toClient.CPacketPlayerConnect;
import packet.toClient.CPacketServerClose;
import packet.toServer.SPacketPlayerConnect;
import util.CustomInputStream;
import util.PacketStream;
import util.TriDouble;
import entity.EntityPlayer;

public class PlayerAccepter extends Thread
{
	public final HikaliServer server;
	public final ServerSocket ss;
	public PlayerAccepter(HikaliServer hs) throws IOException
	{
		this.server = hs;
		this.ss = new ServerSocket(Main.PORT);
		this.start();
	}
	public void run()
	{
		try
		{
			while (true)
				new ConnectWaiter(this.ss.accept(), this.server).start();
		}
		catch (IOException e)
		{
			System.out.println("PlayerAccepter ; Socket IO");
		}
	}
	public class ConnectWaiter extends Thread
	{
		public final HikaliServer server;
		public final Socket socket;
		public ConnectWaiter(Socket s, HikaliServer hs){this.socket = s;this.server = hs;}
		public void run()
		{
			try {
				this.socket.setReceiveBufferSize(Packet.MAX_SIZE);
				this.socket.setSendBufferSize(Packet.MAX_SIZE);
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
			PacketStream ps = new PacketStream();
			SPacketPlayerConnect p = Packet.waitConnectPacket(this.socket, ps);
			if (p != null)
			{
				if (this.isPlayerAlreadyCo(p.name))
				{
					System.out.println("PlayerAccepter : Le joueur "+p.name+" est dejà connecté");
					new CPacketServerClose().send(this.socket);
				}
				else
				{
					CustomInputStream cis = null;
					EntityPlayer h;
					try
					{
						cis = new CustomInputStream(new FileInputStream(new File("Saves/"+this.server.world.nom+"/Players/"+p.name+".hkp")));
						System.out.println("Player Accepter : Player found in datas : "+p.name);

						h = new EntityPlayer(p.name, cis, this.socket);
					}
					catch (Exception e) {System.out.println("PlayerAccepter ; No datas found about "+p.name);
					h = new EntityPlayer(this.server.world.spawnBlock.getEntityPosRot(), new TriDouble(), p.name, this.socket);}
					finally {if (cis != null)IOUtils.closeQuietly(cis);}

					h.stream = ps;
					new CPacketJoinGame(this.server.getPlayers(), h.body, h.head).send(h.socket);
					this.server.addPlayer(h);
					
					this.server.world.loadChunks(h.getBlockPos().getChunkPos());
					
					System.out.println("PlayerAccepter : Connexion  de "+h.name);
					this.server.sendPacketToAllPlayersExcept(new CPacketPlayerConnect(h), h.name);
				}
			}
			else
			{
				System.out.println("PlayerAccepter : Echec de la connexion");
				try {this.socket.close();}
				catch (IOException e) {e.printStackTrace();}
			}
		}
		public boolean isPlayerAlreadyCo(String nom)
		{
			for (EntityPlayer h : this.server.getPlayers())
				if (h.name.equals(nom))
					return true;
			return false;
		}
	}
}
