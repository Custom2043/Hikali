package packet.toClient;

import java.io.IOException;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomOutputStream;

public class CPacketServerClose extends Packet implements PacketToClient
{
	public void read(CustomInputStream cis) throws IOException {

	}

	public void write(CustomOutputStream cos) throws IOException {

	}

	public void handleClient()
	{
		PacketToClientHandler.handleCServerClose();
	}
}
