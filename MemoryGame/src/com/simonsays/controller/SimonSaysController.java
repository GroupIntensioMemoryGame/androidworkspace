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
		highScores.add(new Score("Steve", 4, 800));
		highScores.add(new Score("Bob", 4, 780));
		highScores.add(new Score("", 16, 300));
		highScores.add(new Score("Steve", 16, 100));
		highScores.add(new Score("Steve", 4, 2));
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
	
	public Player login(String username)
	{
		Player login = null;
		
		for(int i = 0; i < players.size(); i++)
		{
			if(players.get(i).getName().equals(username))
			{
				login = players.get(i);
				break;
			}
		}
		
		if(login != null)
		{
			return login;
		}
		else
		{
			return null;
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
	
	public String getHighScores()
	{
		String out = "";
		
		for(Score o: highScores)
		{
			out += o + "\n";
		}
		
		return out;
	}
}