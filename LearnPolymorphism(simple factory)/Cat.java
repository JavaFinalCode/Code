/**
*
*ʵ�ָ��෽����ͨ��get��set������ȡ��˽�е�health�������䴦��
*/
public final class Cat extends Pet
{		
	@Override
	public void eat()
	{	  
		System.out.print("è�ڳԷ�");
		this.setHealth(this.getHealth()+4);
	}
}