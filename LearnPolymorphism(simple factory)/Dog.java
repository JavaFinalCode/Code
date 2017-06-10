/**
*
*实现父类方法。通过get。set方法获取到私有的health，并对其处理。
*/
public final class Dog extends Pet
{		
	@Override
	public void eat()
	{	  
		System.out.print("狗的健康值为：");
		this.setHealth(this.getHealth()+2);
	}
}