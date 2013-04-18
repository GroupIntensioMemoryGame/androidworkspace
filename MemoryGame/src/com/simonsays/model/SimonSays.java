package com.simonsays.model;

import java.util.ArrayList;

//TODO Implement the SimonSays class
public class SimonSays
{
	// Instance Variables
	private ArrayList<Integer> computerSequence;
	private int roundNumber;
	private int numGameObjects;
	private Score score;
	
	// Constructor
	public SimonSays(int nGO)
	{
		roundNumber = 0;
		numGameObjects = nGO;
		computerSequence = new ArrayList<Integer>();
	}
	
	// Instance Methods
	public ArrayList<Integer> getComputerSequence()
	{
		return computerSequence;
	}
	
	public int getRoundNumber()
	{
		return roundNumber;
	}
	
	public int getNumGameObjects()
	{
		return numGameObjects;
	}
	
	public void increaseSequence()
	{
		// Increment the round number
		roundNumber++;
		
		// Add a random game object to the end of the sequence
		int gOID = (int)(numGameObjects * Math.random());
		computerSequence.add(gOID);
	}
}