package com.simonsays.controller;

import java.util.ArrayList;

import com.simonsays.model.GameObject;
import com.simonsays.model.Player;
import com.simonsays.model.SimonSays;

public class SimonSaysController {
	
	private SimonSays ss;
	private ArrayList<Player> players;
	
	public SimonSaysController(){
		// Read players from file
		players = new ArrayList<Player>();
		
		// Read high scores from file
	}
	
	public SimonSays getGame(){
		return ss;
	}
	
	public void play(Player p, ArrayList<Integer> shapes, ArrayList<Integer> colors, int numObjects){
		ss = new SimonSays(p, numObjects);
		ss.createGameObjects(shapes, colors, numObjects);
		ss.increaseSequence();
	}
	
	public GameObject getGameObject(int a)
	{
		return ss.getObject(a);
	}
	
	public void compareSequence(int input){
		if(ss.compareInput(input)){
			ss.increaseCurIndex();
		}
		else{
			ss.endGame(ss.getRoundNumber());
		}
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
}