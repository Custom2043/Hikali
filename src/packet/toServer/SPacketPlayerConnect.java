package packet.toServer;

import java.io.IOException;

import packet.Packet;
import util.CustomInputStream;
import util.CustomOutputStream;
import entity.EntityPlayer;

public class SPacketPlayerConnect extends Packet implements Packet.PacketToServer
{
	public String name;
	
	public SPacketPlayerConnect(){}
	public SPacketPlayerConnect(String a){name = a;}
	
	@Override
	public void read(CustomInputStream cis) throws IOException 
	{
		name = cis.readString();
	}

	@Override
	public void write(CustomOutputStream cos) throws IOException 
	{
		cos.writeString(name);
	}

	@Override
	public void handleServer(EntityPlayer compte) {}

}
