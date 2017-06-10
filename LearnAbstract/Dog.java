public class Dog extends Pet 
{
	private String strain;
	
	public String getStrain()
	{
		return this.strain;
	}
	
	public Dog(){}
	
	public Dog(String name,int health,int love,String strain)
	{
		this.name=name ;
		this.health=health;
		this.love=love;
		this.strain=strain;
	}
	
	@Override
	public void print()
	{		
		System.out.print("名字："+name+"\t亲密值："+love+"\t健康值："+health+"\t种类："+strain);	
	}	
}
