package packet.toClient;

import java.io.IOException;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomOutputStream;

public class CPacketPlayerQuit extends Packet implements PacketToClient
{
	public String name;
	public CPacketPlayerQuit(){}
	public CPacketPlayerQuit(String n){this.name = n;}

	public void read(CustomInputStream cis) throws IOException {
		this.name = cis.readString();
	}
	public void write(CustomOutputStream cos) throws IOException {
		cos.writeString(this.name);
	}
	public void handleClient() {
		PacketToClientHandler.handleCQuitGame(this);
	}
	
}
