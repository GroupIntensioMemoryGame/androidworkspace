package com.simonsays.controller;

import java.util.ArrayList;

import com.simonsays.model.*;

public class SimonSaysController {
	
	private SimonSays ss;
	private ArrayList<Player> players;
	private ArrayList<Score> highScores;
	
	public SimonSaysController(){
		// Read players from file
		players = new ArrayList<Player>();
		
		// Read high scores from file
		highScores = new ArrayList<Score>();
	}
	
	public SimonSays getGame(){
		return ss;
	}
	
	public void play(Player p, ArrayList<Integer> shapes, ArrayList<Integer> colors, int numObjects, ISimonSaysObserver iO){
		ss = new SimonSays(p, numObjects);
		ss.createGameObjects(shapes, colors, numObjects);
		ss.addObserver(iO);
	}
	
	public GameObject getGameObject(int a)
	{
		return ss.getObject(a);
	}
	
	public void compareSequence(int input){
		ss.compareInput(input);
	}
	
	public Boolean login(String username)
	{
		Boolean isValid = false;
		
		for(int i = 0; i < players.size(); i++)
		{
			if(players.get(i).getName().equals(username))
			{
				isValid = true;
			}
		}
		
		if(isValid)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Player createUser(String input) {
		boolean isUsed = false;
		
		for(int i = 0; i < players.size(); i++)
		{
			if (input.equals(players.get(i).getName())) {
				isUsed = true;
				break;
			}
		}
		
		if(input.length() < 13 && !isUsed)
		{
			players.add(new Player(input));
			return players.get(players.size() - 1);
		}
		else
		{
			return null;
		}
	}

	public void storeScore(Score s) {
		if(highScores.size() < 5)
		{
			highScores.add(s);
		}
	}
}