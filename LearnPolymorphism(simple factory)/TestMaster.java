public class TestMaster
{
	public static void main(String[] args)
	{				
		Master master=new Master();
/**
*这就是多态
*Pet pet=new Cat();
*/		
		Pet pet=master.adopPet(Master.CAT_TYPE);
		//Pet pet=new Cat();
		master.feed(pet);
		System.out.print("猫的健康值是："+pet.getHealth());
	}
}