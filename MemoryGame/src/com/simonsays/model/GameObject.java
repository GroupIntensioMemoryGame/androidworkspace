package com.simonsays.model;

//TODO Implement the GameObject class
public class GameObject
{
	// Instance Variables
	private String color;
	private int id;
	private String type;
	
	
	GameObject(String aColor, int aId, String aType){
		color = aColor;
		id = aId;
		type = aType;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getId(){
		return id;
	}
	
	public String getType(){
		return type;
	}
}