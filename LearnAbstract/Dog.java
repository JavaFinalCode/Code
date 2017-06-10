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
		System.out.print("���֣�"+name+"\t����ֵ��"+love+"\t����ֵ��"+health+"\t���ࣺ"+strain);	
	}	
}
