/**
*
*ʵ�ָ��෽����ͨ��get��set������ȡ��˽�е�health�������䴦��
*/
public final class Dog extends Pet
{		
	@Override
	public void eat()
	{	  
		System.out.print("���Ľ���ֵΪ��");
		this.setHealth(this.getHealth()+2);
	}
}