package packet.toClient;

import java.io.IOException;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomMover;
import util.CustomOutputStream;
import util.TriDouble;

public class CPacketMoveUpdate extends Packet implements PacketToClient
{
	public TriDouble pos;
	public CustomMover mover;
	public String name;
	public CPacketMoveUpdate(){}
	public CPacketMoveUpdate(String n,CustomMover m, TriDouble p)
	{
		this.name = n;
		this.mover = m;
		this.pos = p;
	}
	public void read(CustomInputStream cis) throws IOException
	{
		this.name = cis.readString();
		this.pos = cis.readTriDouble();
		this.mover = new CustomMover();
		for(int i=0;i<CustomMover.MOVES_NUMBER;i++)
			this.mover.setMove(cis.readBoolean(), i);
	}
	public void write(CustomOutputStream cos) throws IOException
	{
		cos.writeString(this.name);
		cos.writeTriDouble(this.pos);
		for(int i=0;i<CustomMover.MOVES_NUMBER;i++)
			cos.writeBoolean(this.mover.getMove(i));
	}
	public void handleClient()
	{
		PacketToClientHandler.handleCMoveUpdate(this);
	}
}
