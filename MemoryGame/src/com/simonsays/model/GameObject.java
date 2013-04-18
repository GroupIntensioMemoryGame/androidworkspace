package com.simonsays.model;

//TODO Implement the GameObject class
public class GameObject
{
	// Instance Variables
	private int size;
	private String color;
	private int id;
	private int type;
	
	
	GameObject(int aSize, String aColor, int aId, int aType){
		size = aSize;
		color = aColor;
		id = aId;
		aType = type;
	}
	
	public int getSize(){
		return size;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getId(){
		return id;
	}
	
	public int getType(){
		return type;
	}
}