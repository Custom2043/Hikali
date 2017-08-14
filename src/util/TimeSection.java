package util;

import org.newdawn.slick.Color;

public class TimeSection 
{
	private static int section;
	public static final String[] sectionNames = new String[]{"World render","Gui render", "ChunkLoader", "Client logic", "Server logic", "Sleep time"};
	private static CustomTimer timer = new CustomTimer();
	public static int WORLD_RENDER = 0,
					  GUI_RENDER = 1,
					  CHUNK_LOADER = 2,
					  CLIENT_LOGIC = 3,
					  SERVER_LOGIC = 4,
					  REPOS = 5,
					  SECTION_NUMBER = 6;
	public static int[] times = new int[SECTION_NUMBER];
	public static int[] last = new int[SECTION_NUMBER];
	public static void beginSection(int sec)
	{
		
		if (section != -1)
			times[section] += timer.getDifference();
		section = sec;
		timer.set0();
	}
	public static Color getColor(int sec)
	{
		if (sec == WORLD_RENDER)
			return Color.green;
		else if (sec == GUI_RENDER)
			return Color.red;
		else if (sec == CHUNK_LOADER)
			return Color.orange;
		else if (sec == CLIENT_LOGIC)
			return Color.blue;
		else if (sec == SERVER_LOGIC)
			return Color.yellow;
		else if (sec == REPOS)
			return Color.gray;
		else
			return Color.white;
	}
	public static void setLast()
	{
		times[section] += timer.getDifference();
		timer.set0();
		last = times.clone();
		for (int i=0;i<SECTION_NUMBER;i++)
			times[i] = 0;
	}
}
