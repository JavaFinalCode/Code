import java.util.*;
/**
*把多个企鹅的信息添加到集合中
*查看企鹅的数量及所有企鹅的信息
*删除集合中部分企鹅的元素
*判断集合中是否包含指定企鹅
*/
public class LearnArrayListPenguin
{
	public static void main(String[] args)
	{
		Penguin ououDog = new Penguin( "欧欧  ","Q仔" );
		Penguin feifeiDog = new Penguin( "飞飞","Q妹" );
		Penguin meimeiDog = new Penguin( "美美","Q妹" );
		Penguin honghongDog = new Penguin( "红红","Q妹" );

		List Penguins = new ArrayList();
		
		Penguins.add( ououDog );
		Penguins.add( meimeiDog );
		Penguins.add( feifeiDog );
		Penguins.add( 2 , honghongDog );
		System.out.println( "共计有" + Penguins.size() + "条狗狗" );	
		System.out.println( "分别是：" );
		
		for (Object obj:Penguins)
		{
			System.out.println( obj );
		}
		Penguins.remove( ououDog );
		Penguins.remove( feifeiDog );
		System.out.println("*************************************************");
		System.out.println("删除后还有"+Penguins.size()+"条狗狗");
		System.out.println("分别是：");
		
		for (Object obj : Penguins)
		{
			System.out.println(obj);
		}
		System.out.println("*************************************************");
		if(Penguins.contains( meimeiDog ))
		{
			System.out.println( "集合中包含美美的信息" );
		}
		else
		{
			System.out.println( "集合中不包含美美的信息" );
		}
	}
}
//创建狗类
class Penguin
{
	private String name;
	private String sex;
	
	Penguin()
	{
		
	}
		
	Penguin(String name,String sex)
	{
		this.name = name;
		this.sex = sex;
	}
	
	public String toString()
	{
		return  name+"\t"+sex;
	}
}