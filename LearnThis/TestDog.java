public class TestDog
{
	public static void main(String[] args)
	{
		Dog dog=new Dog();
		dog.print();
		System.out.println("dog:"+dog.toString());
		
		System.out.println("************************************************");
		
		Dog dog1=new Dog("Сǿ",9);
		dog1.print();
		System.out.println("dog:"+dog1.toString());
	}
}

