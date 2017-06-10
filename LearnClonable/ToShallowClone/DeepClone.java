public class DeepClone
{
	public static void main(String[] args)throws Exception
	{
		
		Cat cat=new Cat("阿黄","黄",12);		
		//浅克隆
		Cat copy=cat.clone();		
		System.out.println(cat==copy);		
		copy.setName("汤姆");
		copy.setColor("蓝色");
		copy.setAge(13);		
	}
}

class Cat implements Cloneable
{
	private String name;
	private String color;
	private int age;	
	public Cat(String name,String color,int age)
	{
		this.name=name;
		this.color = color;
		this.age=age;
	}
	@Override
	public Cat clone()throws CloneNotSupportedException
	{
		//浅克隆
		Cat cat=(Cat)super.clone();
		return cat;		
	}	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setColor(String color)
	{
		this.color=color;
	}
	
	public void setAge(int age)
	{
		this.age=age;
	}	
	@Override
	public String toString()
	{
		return "name:"+name+"color:"+color+"age:"+age;
	}
	
	
}
