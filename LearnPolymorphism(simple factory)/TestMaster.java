public class TestMaster
{
	public static void main(String[] args)
	{				
		Master master=new Master();
/**
*����Ƕ�̬
*Pet pet=new Cat();
*/		
		Pet pet=master.adopPet(Master.CAT_TYPE);
		//Pet pet=new Cat();
		master.feed(pet);
		System.out.print("è�Ľ���ֵ�ǣ�"+pet.getHealth());
	}
}