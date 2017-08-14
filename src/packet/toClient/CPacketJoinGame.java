package packet.toClient;

import java.io.IOException;
import java.util.ArrayList;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomOutputStream;
import util.EntityPosRot;
import util.TriDouble;
import entity.EntityHuman;
import entity.EntityPlayer;

public class CPacketJoinGame extends Packet implements PacketToClient
{
	public EntityPosRot body;
	public TriDouble head;
	public ArrayList<EntityHuman> humans = new ArrayList<EntityHuman>();
	public CPacketJoinGame(){}
	public CPacketJoinGame(ArrayList<EntityPlayer> h, EntityPosRot b, TriDouble he)
	{
		this.body = b;
		this.head = he;
		
		for (EntityPlayer p : h)
			this.humans.add(new EntityHuman(p.body, p.head, p.name));
	}
	public void read(CustomInputStream cis) throws IOException 
	{
		this.body = cis.readEntityPosRot();
		this.head = cis.readTriDouble();
		
		int nb = cis.readInt();
		for (int i=0;i<nb;i++)
			this.humans.add(new EntityHuman(cis.readEntityPosRot(), cis.readTriDouble(), cis.readString()));
	}

	public void write(CustomOutputStream cos) throws IOException 
	{
		cos.writeEntityPosRot(this.body);
		cos.writeTriDouble(this.head);
		
		cos.writeInt(this.humans.size());
		for (EntityHuman h : this.humans)
		{
			cos.writeEntityPosRot(h.body);
			cos.writeTriDouble(h.head);
			cos.writeString(h.name);
		}
	}

	public void handleClient()
	{
		PacketToClientHandler.handleCJoinGame(this);
	}
	
}
