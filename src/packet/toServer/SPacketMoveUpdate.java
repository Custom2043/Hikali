package packet.toServer;

import java.io.IOException;

import main.Main;
import entity.EntityPlayer;
import packet.Packet;
import packet.Packet.PacketToServer;
import packet.toClient.CPacketMoveUpdate;
import util.CustomInputStream;
import util.CustomMover;
import util.CustomOutputStream;
import util.TriDouble;

public class SPacketMoveUpdate extends Packet implements PacketToServer
{
	public TriDouble pos;
	public CustomMover mover;
	public SPacketMoveUpdate(){}
	public SPacketMoveUpdate(CustomMover m, TriDouble p)
	{
		this.mover = m;
		this.pos = p;
	}
	public void read(CustomInputStream cis) throws IOException
	{
		this.mover = new CustomMover();
		for(int i=0;i<CustomMover.MOVES_NUMBER;i++)
			this.mover.setMove(cis.readBoolean(), i);
		this.pos = cis.readTriDouble();
	}
	public void write(CustomOutputStream cos) throws IOException
	{
		for(int i=0;i<CustomMover.MOVES_NUMBER;i++)
			cos.writeBoolean(this.mover.getMove(i));
		cos.writeTriDouble(this.pos);
	}
	public void handleServer(EntityPlayer sender)
	{
		sender.dep = mover;
		sender.body.pos = pos;
		Main.server.sendPacketToAllPlayersExcept(new CPacketMoveUpdate(sender.name, mover, pos), sender.name);

	}
}
