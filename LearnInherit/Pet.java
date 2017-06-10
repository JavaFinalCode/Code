public class Pet
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
	
//	public void setName(String name)
//	{
//		this.name=name;
//	}
	
	public int getHealth()
	{
		return this.health;
	}
	
//	public void setHealth(int health)
//	{
//		this.health=health;	
//	}

	public int getLove()
	{
		return this.love;
	}
	
//	public void setLove(int love)
//	{
//		this.love=love;
//	} 	
	
	public void print()
	{
		System.out.print("Ãû×Ö£º "+name+"\t½¡¿µÖµ£º "+health+"\tÇ×ÃÜ¶È£º "+love);
	}
}