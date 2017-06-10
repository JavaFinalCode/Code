package com.cheer.domain;

// Domain Model
// POJO
public class Pet
{
	private int id;

	private String name;

	public Pet(){}

	public Pet(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public String toString()
	{
		return "id:" + id + "\t" + "name:" + name;
	}
}