import java.io.*;
/**
*实现细节：
*得先实现克隆、序列化接口，才能实现克隆
*浅克隆以及部分深克隆方法会随着对象增多变得越复杂！（浅克隆：直接调用克隆方法）
*最优《推荐》：采用对象序列化、反序列化的方式来实现深克隆
*克隆方法得抛出CloneNotSupportedException 异常!
*/
public class ShallowClone
{
	public static void main(String[] args)throws Exception
	{
		Master master=new Master("达文西");
		Cat cat=new Cat("阿黄","黄",12,master);
		
		//浅克隆
		//Cat copy=cat.clone();
	
		//自定义深克隆方法
		Cat copy=cat.deepClone();
		copy.setName("汤姆");
		copy.setColor("蓝色");
		copy.setAge(13);
		copy.getMaster().setName("9527");
		
		//System.out.println(cat==copy);
		//System.out.println(cat.euqals(copy));
		
		System.out.println(cat.getMaster()==copy.getMaster());
		System.out.println(copy);
		System.out.println(cat);
	}
}

//得先实现克隆、序列化接口
class Cat implements Cloneable ,Serializable
{
	private String name;
	private String color;
	private int age;
	//Master引用master
	private Master master;
	//Cat构造器给属性赋值
	public Cat(String name,String color,int age, Master master)
	{
		this.name=name;
		this.color = color;
		this.age=age;
		this.master=master;
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
	
	public Master getMaster()
	{
		return this.master;
	}
	
	public void setMaster(Master master)
	{
		this.master=master;
	}
		
	@Override
	public Cat clone()throws CloneNotSupportedException
	{
		//浅克隆
		Cat cat=(Cat)super.clone();
		
		//<不推荐>深克隆：方法1:随着引用对象越多，就越复杂
		//Master master=new Master();
		//cat.setMaster(master);
		
		//<不推荐>深克隆：方法2：随着引用对象越多，就越复杂；
		Master master=cat.getMaster().clone();
		cat.setMaster(master);
		return cat;		
	}
	
	@Override
	public String toString()
	{
		return "name:"+name+"color:"+color+"age:"+age;
	}
	
	//最优《推荐》：采用对象序列化、反序列化的方式来实现深克隆
	public Cat deepClone()throws Exception
	{
		ByteArrayOutputStream bos=new ByteArrayOutputStream ();
		ObjectOutputStream oos=new  ObjectOutputStream(bos);
		oos.writeObject(this);
		
		ByteArrayInputStream bis=new ByteArrayInputStream (bos.toByteArray());
		ObjectInputStream ois=new  ObjectInputStream(bis);
		return (Cat)ois.readObject();
		
	}
}


//实现克隆接口，重写克隆方法，克隆方法得抛出CloneNotSupportedException 异常!
class Master implements Cloneable ,Serializable
{
	private String name;
	
	public Master(){}
	
	public Master(String name)
	{
		this.name=name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	@Override
	public Master clone()throws CloneNotSupportedException 
	{
		return (Master)super.clone();
	}
	
	@Override
	public String toString()
	{
		return "master's name is"+name;
	}	
}
