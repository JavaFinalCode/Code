//现在更多的是面向接口编程，List常用方法 得看他的实现类（实现接口类似于上转型，）
//使用ArrayList存储元素
//上转型对象：指向子类后，丢失自己特有的，如果重写了调用的话，就是调用自己的！！
import java.util.*;
public class LearnArrayListDog
{
	public static void main(String[] args)
	{
		//new Dog实例对象，用于存放到集合里面，构造器传参
		Dog ououDog = new Dog("欧欧");
		Dog feifeiDog = new Dog("飞飞");
		Dog meimeiDog = new Dog("美美");
		Dog honghongDog = new Dog("红红");
		//创建集合dogs集合 List接口   ArrayList其实现类
		List dogs = new ArrayList();
		//往dogs集合里进行添加对象dog   
		dogs.add( ououDog );
		dogs.add( meimeiDog );
		dogs.add( feifeiDog );
		dogs.add( 2,honghongDog );
		System.out.println("共计有"+dogs.size()+"条狗狗");	
		System.out.println("分别是：");
		//普通for循环遍历	(默认调用toString输出)
		//以往我们通过调show方法，现在我们使用重写toString方法输出！	·
		for(int i = 0;i<dogs.size();i++)
		{ 
			System.out.println(dogs.get(i));						
		}
		//增强for循环遍历		
		for (Object obj:dogs)
		{
			System.out.println(obj);
		}
		
	}
}
//创建狗类
class Dog
{
	private String name;
	
	Dog()
	{
		
	}
	
	//所有的类都继承于OBject类!
	//重写一下toString方法，这样就会调用自己的toString方法();不重写就会默认调用父类（Object）的toString()方法！
	
	Dog(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return "名字：" + name;
	}
}

//上转型对象：指向子类后，丢失自己特有的，如果重写了调用的话，就是调用自己的！！
//所有的类都继承于OBject类!
//重写一下toString方法，这样就会调用自己的toString方法();不重写就会默认调用父类（Object）的toString()方法！
/**
*
*Cat类-->（继承于）->Pet类(他们都继承与Object类)
*实例化后能使用的方法：
*
*
*
*/
//方法里要求添加的是E（泛型），如果添加的是对象的化，会显示不安全！则需要使用javac -encoding utf8 -Xlint:unchecked TestList.java进行编译！
