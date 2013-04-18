package com.simonsays.model;


//TODO Implement compareTo
public class Score implements Comparable<Score>
{
	// Instance Variables
	private int numObjects;
	private int sequenceLength;
	private int time;
	private int value;
	
	// Constructor
	public Score(int no, int sl, int t)
	{
		numObjects = no;
		sequenceLength = sl;
		time = t;
	}
	
	// Instance Methods
	public int getNumObjects()
	{
		return numObjects;
	}
	
	public int getSequenceLength()
	{
		return sequenceLength;
	}
	
	public int getTime()
	{
		return time;
	}

	@Override
	public int compareTo(Score o)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Score addScore(Score aScore){
		
	}
}
