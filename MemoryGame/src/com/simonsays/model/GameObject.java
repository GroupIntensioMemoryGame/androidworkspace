package com.simonsays.model;

//TODO Implement the GameObject class
public class GameObject
{
	// Final Static Variables
	final public int SQUARE = 0;
	final public int TRIANGLE = 1;
	final public int CIRCLE = 2;
	
	final public int RED = 0;
	final public int BLUE = 1;
	final public int GREEN = 2;
	final public int PURPLE = 3;
	final public int ORANGE = 4;
	final public int YELLOW = 5;
	
	// Instance Variables
	private int color;
	private int id;
	private int shape;
	
	
	GameObject(int aShape, int aColor, int aId){
		color = aColor;
		id = aId;
		shape = aShape;
	}
	
	public int getColor(){
		return color;
	}
	
	public int getId(){
		return id;
	}
	
	public int getShape(){
		return shape;
	}
}