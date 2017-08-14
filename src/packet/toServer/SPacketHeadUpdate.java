package packet.toServer;

import java.io.IOException;

import main.Main;
import packet.Packet;
import packet.Packet.PacketToServer;
import packet.toClient.CPacketHeadUpdate;
import util.CustomInputStream;
import util.CustomOutputStream;
import util.TriDouble;
import entity.EntityPlayer;

public class SPacketHeadUpdate extends Packet implements PacketToServer
{
	public TriDouble head;
	public SPacketHeadUpdate(){}
	public SPacketHeadUpdate(TriDouble h){this.head = h;}
	public void read(CustomInputStream cis) throws IOException 
	{
		this.head = cis.readTriDouble();
	}

	public void write(CustomOutputStream cos) throws IOException 
	{
		cos.writeTriDouble(this.head);
	}

	public void handleServer(EntityPlayer sender) 
	{
		sender.head = head;
		Main.server.sendPacketToAllPlayersExcept(new CPacketHeadUpdate(head, sender.name), sender.name);

	}

}
