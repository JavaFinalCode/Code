/**
*ѧϰ��̬��
*����Pet��
*/

public abstract class Pet
{	
	private int health=60;
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void setHealth(int health)
	{
		this.health=health;	
	}
	
	public abstract void eat();
	
}