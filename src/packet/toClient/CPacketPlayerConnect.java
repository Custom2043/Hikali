package packet.toClient;

import java.io.IOException;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomOutputStream;
import util.TriDouble;
import entity.EntityHuman;

public class CPacketPlayerConnect extends Packet implements PacketToClient
{
	public EntityHuman humain;
	public CPacketPlayerConnect(){}
	public CPacketPlayerConnect(EntityHuman h){this.humain = h;}
	public void read(CustomInputStream cis) throws IOException 
	{
		this.humain = new EntityHuman(cis.readEntityPosRot(), new TriDouble(), cis.readString());
	}

	public void write(CustomOutputStream cos) throws IOException 
	{
		cos.writeEntityPosRot(this.humain.body);
		cos.writeString(this.humain.name);
	}

	public void handleClient() 
	{
		PacketToClientHandler.handleCPlayerConnect(this);
	}
}
