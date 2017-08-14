package packet.toClient;

import java.io.IOException;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomOutputStream;
import util.TriDouble;

public class CPacketHeadUpdate extends Packet implements PacketToClient
{
	public TriDouble head;
	public String sender;
	public CPacketHeadUpdate(){}
	public CPacketHeadUpdate(TriDouble h, String s){this.head = h;this.sender = s;}
	public void read(CustomInputStream cis) throws IOException
	{
		this.head = cis.readTriDouble();
		this.sender = cis.readString();
	}

	public void write(CustomOutputStream cos) throws IOException
	{
		cos.writeTriDouble(this.head);
		cos.writeString(this.sender);
	}

	public void handleClient()
	{
		PacketToClientHandler.handleCHeadUpdate(this);
	}
}
