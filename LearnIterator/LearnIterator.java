import java.util.*;
public class LearnIterator
{
	public static void main(String[] args)
	{
		Map penguins=new HashMap();
		Penguin yaya=new Penguin("Q妹");
		Penguin ouou=new Penguin("Q妹");
		Penguin feifei=new Penguin("Q仔");
		Penguin meimei=new Penguin("Q妹");
		penguins.put("丫丫",yaya);
		penguins.put("欧欧",ouou);
		penguins.put("菲菲",feifei);
		penguins.put("美美",meimei);
		Set keys=penguins.keySet();//map有个keySet方法获取key的Set类型集合
		Iterator it=keys.iterator();//返回一个迭代对象
		//方法一遍历输出
		while(it.hasNext()) //当（存在下一个）                 
		{
			String key=(String)it.next();//获得下一个的key
			Penguin penguin=(Penguin)penguins.get(key);
			System.out.println(key+"--"+penguin);
		}
		System.out.println("***************************");//方法二遍历输出
		for(Object key:penguins.keySet())
		{
			System.out.println(key+"--" +penguins.get(key));
			}
	}
}
class Penguin
{
	private String sex;
	public Penguin(String sex)
	{
		this.sex=sex;
	}
	public String toString()
	{
		return this.sex;
	}
}