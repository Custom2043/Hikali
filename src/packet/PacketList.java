package packet;

import packet.toClient.CPacketChunk;
import packet.toClient.CPacketHeadUpdate;
import packet.toClient.CPacketJoinGame;
import packet.toClient.CPacketMoveUpdate;
import packet.toClient.CPacketPlayerConnect;
import packet.toClient.CPacketPlayerQuit;
import packet.toClient.CPacketServerClose;
import packet.toServer.SPacketAskChunk;
import packet.toServer.SPacketHeadUpdate;
import packet.toServer.SPacketMoveUpdate;
import packet.toServer.SPacketPlayerConnect;
import packet.toServer.SPacketQuitGame;
import util.IDList;

public class PacketList
{
	public static IDList<Class <? extends Packet>> packet = new IDList<Class <? extends Packet>>();

	public static Class <? extends Packet> getPacketForID(int id)
	{
		return packet.getObjectForID(id);
	}

	public static int getIDForPacket(Class <? extends Packet> c)
	{
		return packet.getIDForObject(c);
	}
	static
	{
		packet.add(SPacketPlayerConnect.class, 0);
		packet.add(SPacketMoveUpdate.class, 1);
		packet.add(CPacketMoveUpdate.class, 2);
		packet.add(CPacketPlayerConnect.class, 3);
		packet.add(CPacketJoinGame.class, 4);
		packet.add(CPacketPlayerQuit.class, 5);
		packet.add(SPacketQuitGame.class, 6);
		packet.add(CPacketServerClose.class, 7);
		packet.add(CPacketHeadUpdate.class, 8);
		packet.add(SPacketHeadUpdate.class, 9);
		packet.add(CPacketChunk.class, 10);
		packet.add(SPacketAskChunk.class, 11);
	}
}
