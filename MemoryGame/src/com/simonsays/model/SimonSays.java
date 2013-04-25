package com.simonsays.model;

import java.util.*;

//TODO Implement the SimonSays class
public class SimonSays
{
	// Instance Variables
	private final Set<ISimonSaysObserver> observers = new HashSet<ISimonSaysObserver>();
	private ArrayList<GameObject> shapeOrder;
	private ArrayList<Integer> computerSequence;
	private Score score;
	private Player player;
	private int roundNumber;
	private int numGameObjects;
	private int curIndexSeq;
	private Boolean isActive;
	
	// Constructor
	public SimonSays(Player p, int nGO)
	{
		player = p;
		roundNumber = 0;
		numGameObjects = nGO;
		computerSequence = new ArrayList<Integer>();
		shapeOrder = new ArrayList<GameObject>();
		isActive = true;
		increaseSequence();
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
	
	private void increaseSequence()
	{
		curIndexSeq = 0;
		
		// Increment the round number
		roundNumber++;
		
		// Add a random game object to the end of the sequence
		int gOID = (int)(numGameObjects * Math.random());
		computerSequence.add(gOID);
	}
	
	public void compareInput(int input){
		if(computerSequence.get(curIndexSeq) == input)
		{
			if(curIndexSeq == (roundNumber - 1))
			{
				// End round
				increaseSequence();
				notifyObservers();
			}
			else
			{
				// Increase current index
				increaseCurIndex();
			}
		}
		else
		{
			// End game
			endGame();
			notifyObservers();
		}
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
	
	public void endGame() {
		//score = new Score(player.getName(), numGameObjects, roundNumber, time);
		score = new Score(player.getName(), numGameObjects, roundNumber);
		
		isActive = false;
	}
	
	public GameObject getObject(int loc)
	{
		return shapeOrder.get(loc);
	}
	
	public Boolean getIsActive()
	{
		return isActive;
	}
	
	public void createGameObjects(ArrayList<Integer> shapes, ArrayList<Integer> colors, int nogo)
	{
		for(int i = 0; i < nogo; i++)
		{
			int shapeN = (int)(Math.random()*shapes.size());
			int colorN = (int)(Math.random()*colors.size());
			int newShape = shapes.get(shapeN);
			int newColor = colors.get(colorN);
			GameObject go = new GameObject(newShape, newColor, i);
			shapeOrder.add(go);
		}
	}
}