package client.options;

import value.KeyValue;


public class KeyCategorie
{
	private final String nom;
	private int nombreKey;
	private KeyValue touche[];
	public KeyCategorie(String no, KeyValue[] t)
	{
		this.nom = no;
		this.nombreKey = t.length;
		this.touche = t;
	}
	public int getKeyNumber()
	{
		return this.nombreKey;
	}
	public KeyValue getKeybyId(int id)
	{
		return this.touche[id];
	}
	public KeyValue[] getAllKeys()
	{
		return this.touche;
	}
	public String getName()
	{
		return this.nom;
	}
}
