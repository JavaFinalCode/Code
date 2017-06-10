/**
*
*实现父类方法。通过get。set方法获取到私有的health，并对其处理。
*/
public final class Cat extends Pet
{		
	@Override
	public void eat()
	{	  
		System.out.print("猫在吃饭");
		this.setHealth(this.getHealth()+4);
	}
}