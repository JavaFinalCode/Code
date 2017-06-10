public class Dog
{
	private String name;
	private int age;
	
	public Dog()
	{
		//调用其他构造方法
		this("旺财",10);
		System.out.println("DOg().......");
		System.out.println("this:"+this);
		//this.name="旺财";
		//this.age=10;   等效于   age=10;
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
		System.out.println("名字："+this.name+"年龄："+this.age);
	}
}