package com.simonsays.model;

import java.util.*;

//TODO Implement the SimonSays class
public class SimonSays
{
	// Instance Variables
	private ArrayList<Integer> computerSequence;
	private int roundNumber;
	private int numGameObjects;
	private Score score;
	private int curIndexSeq;
	private Player player;
	private final Set<ISimonSaysObserver> observers = new HashSet<ISimonSaysObserver>();
	
	// Constructor
	public SimonSays(Player p, int nGO)
	{
		player = p;
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
	
	public Score getScore()
	{
		return score;
	}
	
	public void increaseSequence()
	{
		curIndexSeq = 0;
		// Increment the round number
		roundNumber++;
		// Add a random game object to the end of the sequence
		int gOID = (int)(numGameObjects * Math.random());
		computerSequence.add(gOID);
		notifyObservers();
	}
	
	public boolean compareInput(int input){
		return computerSequence.get(curIndexSeq) == input;
	}
	
	public void increaseCurIndex(){
		curIndexSeq++;
	}
	
	private void notifyObservers(){
		for(ISimonSaysObserver o: observers)
		{
			o.update(this);
		}
	}
	
	public void removeObserver(ISimonSaysObserver o) {
		if (o == null) {
			return;
		}
		observers.remove(o);
	}
	
	public void addObserver(ISimonSaysObserver o) {
		if (o == null) {
			return;
		}
		observers.add(o);
	}
	
	public Set<ISimonSaysObserver> getObservers()
	{
		return new HashSet<ISimonSaysObserver>(observers);
	}
	
	public void endGame(int time) {
		score = new Score(player.getName(), numGameObjects, roundNumber, time);
		notifyObservers();
	}
}