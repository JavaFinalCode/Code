/**
*
*象征性的定个常量来与其编号比较！
*/

public class Master
{
	public  static final String CAT_TYPE="1";//象征
	public  static final String DOG_TYPE="2";
	
/**
*把抽象的父类放进去，
*/
	public void feed(Pet pet)
	{
		pet.eat();
	}
/**
*设返回类型为父类，返回一个子类！
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