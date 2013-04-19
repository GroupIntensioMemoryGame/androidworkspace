package com.simonsays.visual.textual;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.simonsays.model.*;
import com.simonsays.controller.*;


public class SimonSays implements ISimonSaysObserver {
	
	private static final String LINESEP = System.getProperty("line.separator");
	private static final BufferedReader f_in = new BufferedReader(
			new InputStreamReader(System.in));
	
	private SimonSaysController simonctrl;
	private boolean playingGame = false;
	private static Player player;
	private int numObjects;
	
	public static void main(String[] args) throws IOException{
		out.println("################################");
		out.println("Welcome to Simon Says!");
		out.println("################################");
		out.println("Playing Memory Game");	
		out.println();
		
		SimonSays ss = new SimonSays();
		ss.simonctrl = new SimonSaysController();
		
		ss.playGame();
	}
	
	private void playGame() throws IOException{
		int numObjects = getNumOfObjects();
		
		out.println("Enter name of player");
		String temp = getPlayerName();
		player = new Player(temp);
		
		this.simonctrl.play(player, numObjects);
		
		
		
	}

	@Override
	public void update(com.simonsays.model.SimonSays ss) {
		// TODO Auto-generated method stub
		
	}
	
	private int getNumOfObjects() throws IOException{
		int numRounds = -1;
		
		while(numRounds == -1){
			out.print("Enter number of objects");
			String commandline = readLineFromConsole();
			try {
				numRounds = Integer.parseInt(commandline);
			}
			catch (NumberFormatException e) {
				numRounds = -1;
			}
		}
		return numRounds;
	}

	private static String readLineFromConsole() throws IOException {
		out.print(" ");
		return f_in.readLine();
	}
	
	private String getPlayerName() throws IOException {
		
		String name = new String();
		
		while (name.length() < 1 || name.length() > 50)  {
			out.print("  Player name:");
			String commandline = readLineFromConsole();
			name = commandline.trim();
		}

		return name;
	}

}
