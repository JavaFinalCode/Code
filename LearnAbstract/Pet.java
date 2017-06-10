public abstract class Pet
{
	private String name;
	private int health;
	private int love;
	
	Pet()
	{}
	
	Pet(String name,int health,int love)
	{
		this.name=name;
		this.health=health;
		this.love=love;
	}
	
	public String getName()
	{
		return this.name;
	}
	

	public int getHealth()
	{
		return this.health;
	}
	

	public int getLove()
	{
		return this.love;
	}
	

	public abstract void print();

}   