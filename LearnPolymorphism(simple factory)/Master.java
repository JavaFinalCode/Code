/**
*
*�����ԵĶ��������������űȽϣ�
*/

public class Master
{
	public  static final String CAT_TYPE="1";//����
	public  static final String DOG_TYPE="2";
	
/**
*�ѳ���ĸ���Ž�ȥ��
*/
	public void feed(Pet pet)
	{
		pet.eat();
	}
/**
*�践������Ϊ���࣬����һ�����࣡
*/	
	public Pet adopPet(String typeId)
	{
		Pet pet=null;
		if(CAT_TYPE.equals(typeId))
		{
			pet =new Cat();
		}
		if(DOG_TYPE.equals(typeId))
		{
			pet =new Dog();
		}
		return pet;
	}
}