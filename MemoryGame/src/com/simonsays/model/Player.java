package com.simonsays.model;

//TODO Confirm that all needed methods and attributes for Player class are implemented
public class Player
{
	// Instance Variables
	private String username;
	
	// Constructor
	public Player(String un)
	{
		username = un;
	}
	
	// Instance Methods
	public String getUserName()
	{
		return username;
	}
}
