public class Dog
{
	private String name;
	private int age;
	
	public Dog()
	{
		//�����������췽��
		this("����",10);
		System.out.println("DOg().......");
		System.out.println("this:"+this);
		//this.name="����";
		//this.age=10;   ��Ч��   age=10;
	}
	
	public Dog(String name,int age)
	{
		System.out.println("Dog(String name,int age)......");
		this.name=name;
		this.age=age;
		System.out.println("this:"+this);
		//this.print();
		//print();
	}
	
	public void print()
	{
		System.out.println("print().........");
		System.out.println("this:"+this);
		System.out.println("���֣�"+this.name+"���䣺"+this.age);
	}
}