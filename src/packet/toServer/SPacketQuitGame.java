package packet.toServer;

import java.io.IOException;

import main.Main;
import packet.Packet;
import packet.Packet.PacketToServer;
import util.CustomInputStream;
import util.CustomOutputStream;
import entity.EntityPlayer;

public class SPacketQuitGame extends Packet implements PacketToServer
{

	public void read(CustomInputStream cis) throws IOException 
	{
	}

	public void write(CustomOutputStream cos) throws IOException 
	{
	}

	public void handleServer(EntityPlayer sender) 
	{
		Main.server.removePlayer(sender.name);
	}
}
