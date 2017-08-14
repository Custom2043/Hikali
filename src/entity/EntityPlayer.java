package entity;

import inventory.PlayerInventory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import util.CustomInputStream;
import util.EntityPosRot;
import util.PacketStream;
import util.TriDouble;
import world.ChunkPos;
import world.World;

public class EntityPlayer extends EntityHuman
{
	public ArrayList<ChunkPos> chunksToLoad = new ArrayList<ChunkPos>();
	public PlayerInventory inventory = new PlayerInventory();
	public int itemPick = 0;
	public Socket socket;
	public PacketStream stream;
	public EntityPlayer(String name, CustomInputStream cis, Socket s) throws IOException{super(name, cis);this.socket = s;}
	public EntityPlayer(EntityPosRot l, TriDouble h, String n, Socket s)
	{
		super(l, h, n);
		this.socket = s;
	}
	public void update(long dif, World w)
	{
		super.update(dif, w);
	}
}
