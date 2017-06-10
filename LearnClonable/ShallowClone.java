import java.io.*;
/**
*ʵ��ϸ�ڣ�
*����ʵ�ֿ�¡�����л��ӿڣ�����ʵ�ֿ�¡
*ǳ��¡�Լ��������¡���������Ŷ���������Խ���ӣ���ǳ��¡��ֱ�ӵ��ÿ�¡������
*���š��Ƽ��������ö������л��������л��ķ�ʽ��ʵ�����¡
*��¡�������׳�CloneNotSupportedException �쳣!
*/
public class ShallowClone
{
	public static void main(String[] args)throws Exception
	{
		Master master=new Master("������");
		Cat cat=new Cat("����","��",12,master);
		
		//ǳ��¡
		//Cat copy=cat.clone();
	
		//�Զ������¡����
		Cat copy=cat.deepClone();
		copy.setName("��ķ");
		copy.setColor("��ɫ");
		copy.setAge(13);
		copy.getMaster().setName("9527");
		
		//System.out.println(cat==copy);
		//System.out.println(cat.euqals(copy));
		
		System.out.println(cat.getMaster()==copy.getMaster());
		System.out.println(copy);
		System.out.println(cat);
	}
}

//����ʵ�ֿ�¡�����л��ӿ�
class Cat implements Cloneable ,Serializable
{
	private String name;
	private String color;
	private int age;
	//Master����master
	private Master master;
	//Cat�����������Ը�ֵ
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
		//ǳ��¡
		Cat cat=(Cat)super.clone();
		
		//<���Ƽ�>���¡������1:�������ö���Խ�࣬��Խ����
		//Master master=new Master();
		//cat.setMaster(master);
		
		//<���Ƽ�>���¡������2���������ö���Խ�࣬��Խ���ӣ�
		Master master=cat.getMaster().clone();
		cat.setMaster(master);
		return cat;		
	}
	
	@Override
	public String toString()
	{
		return "name:"+name+"color:"+color+"age:"+age;
	}
	
	//���š��Ƽ��������ö������л��������л��ķ�ʽ��ʵ�����¡
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


//ʵ�ֿ�¡�ӿڣ���д��¡��������¡�������׳�CloneNotSupportedException �쳣!
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
