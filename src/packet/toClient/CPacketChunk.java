package packet.toClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

import packet.Packet;
import packet.Packet.PacketToClient;
import util.CustomInputStream;
import util.CustomOutputStream;
import world.Chunk;
import world.ChunkPos;
import block.BlockList;
import block.BlockPos;

public class CPacketChunk extends Packet implements PacketToClient
{
	public Chunk ch;
	public ByteBuffer chunk;
	public CPacketChunk(){}
	public CPacketChunk(ByteBuffer c)
	{
		this.chunk = c;
	}

	public void read(CustomInputStream cis) throws IOException 
	{	
		byte[] bit = new byte[Chunk.getBufferizedSize()];
		this.chunk = ByteBuffer.wrap(bit);
		cis.read(bit);
		
		this.ch = new Chunk(new ChunkPos(this.chunk.getInt(), this.chunk.getInt(), this.chunk.getInt()));
		
		for (int i = 0;i<this.ch.data.length;i++)
			try {
				BlockList.getBlockForID(this.chunk.getShort()).getConstructor(BlockPos.class).newInstance(this.ch.getBlockPos(i));
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {e.printStackTrace();}
	}

	public void write(CustomOutputStream cos) throws IOException {
		cos.write(this.chunk.array());
	}

	public void handleClient() {
		PacketToClientHandler.handleCChunk(this);
	}
}
