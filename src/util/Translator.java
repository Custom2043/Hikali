package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.IOUtils;

public class Translator 
{
	public static String currentFile = "English.lang";
	public static Hashtable<String, String> table = new Hashtable<String, String>();
	public Translator(FileInputStream fis2) throws IOException
	{
		String key = "";
		int carac = 0;
		carac = fis2.read();
		while (carac != '+' && carac != -1)
		{
			key+=String.valueOf((char)carac);
			carac = fis2.read();
		}
		fis2.read();
		Translator.loadLangage(key);
	}
	public static void loadLangage(String lang)
	{
		table.clear();
		String key = "";
		String trad = "";
		int carac = 0;
		FileInputStream fis = null;
		try 
		{
			fis = new FileInputStream(new File("Languages/"+lang));
			currentFile = lang;
			while (carac != -1)
			{
				key = "";
				trad = "";
				carac = fis.read();
				while (carac != -1 && carac != '=')
				{
					key+=Character.toString((char)carac);
					carac = fis.read();
				}
				carac = fis.read();
				while (carac != -1 && carac != 13)
				{
					trad+=Character.toString((char)carac);
					carac = fis.read();
				}
				carac = fis.read();
				table.put(key, trad);
			}
			System.out.println("Translator ; Successfully load langage : "+currentFile);
		}
		catch(IOException e){System.out.println("Translator ; Can't load langage : "+lang);}
		finally{
			if (fis != null)
				IOUtils.closeQuietly(fis);}
	}
	public static void write(FileOutputStream fos) throws IOException
	{
		for (int i=0;i<currentFile.length();i++)
			fos.write(currentFile.charAt(i));
		fos.write('+');
	}
	public static String translate(String key)
	{
		if (table.containsKey(key))
			return table.get(key);
		else
		{
			System.out.println("Translator ; Can't translate : "+key);
			return key;
		}
	}
}
