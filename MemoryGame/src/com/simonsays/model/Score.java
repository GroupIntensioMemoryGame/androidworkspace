package com.simonsays.model;


//TODO Implement compareTo
public class Score implements Comparable<Score>
{
	// Instance Variables
	private String userName;
	private int numObjects;
	private int sequenceLength;
	private int time;
	
	// Constructor
	//public Score(String un, int no, int sl, int t)
	public Score(String un, int no, int sl)
	{
		userName = un;
		numObjects = no;
		sequenceLength = sl;
		//time = t;
	}
	
	// Instance Methods
	public int getNumObjects()
	{
		return numObjects;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public int getSequenceLength()
	{
		return sequenceLength;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void setTime(int a)
	{
		time = a;
	}

	@Override
	public int compareTo(Score o)
	{
		if(this.getSequenceLength() > o.getSequenceLength())
		{
			return 1;
		}
		else if(this.getSequenceLength() == o.getSequenceLength())
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	public String toString()
	{
		return userName + " " + sequenceLength + " " + time + " " + numObjects;
	}
}
