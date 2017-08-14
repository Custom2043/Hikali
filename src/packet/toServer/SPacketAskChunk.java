package packet.toServer;

import java.io.IOException;

import main.Main;
import packet.Packet;
import packet.Packet.PacketToServer;
import packet.toClient.CPacketChunk;
import util.CustomInputStream;
import util.CustomOutputStream;
import world.Chunk;
import world.ChunkLoader;
import world.ChunkPos;
import entity.EntityPlayer;

public class SPacketAskChunk extends Packet implements PacketToServer
{
	public ChunkPos pos;
	public SPacketAskChunk(){}
	public SPacketAskChunk(ChunkPos p){this.pos = p ;}
	public void read(CustomInputStream cis) throws IOException {
		this.pos = new ChunkPos(cis.readInt(), cis.readInt(), cis.readInt());
	}

	public void write(CustomOutputStream cos) throws IOException {
		cos.writeInt(this.pos.getX());
		cos.writeInt(this.pos.getY());
		cos.writeInt(this.pos.getZ());
	}

	public void handleServer(EntityPlayer sender) 
	{
		Chunk c = Main.server.world.getChunk(pos);
		if (c != null)
			new CPacketChunk(c.bufferize()).send(sender.socket);
		else
			ChunkLoader.addCommande(pos, sender);

	}

}
