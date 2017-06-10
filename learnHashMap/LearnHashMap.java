/**
*为企鹅添加一个编号，并作为键存储多个企鹅信息到应用泛型的HashMap集合
*使用Iterator、foreach语句进行遍历
*使用包装类Integer 标记Map的键类型
*Map<Integer,Penguin>
*/
import java.util.*;

public class TestHashMap
{
	public static void main(String[] args)
	{
		Penguin p = new Penguin("1001", "欧欧", "Q仔");
		Penguin p1 = new Penguin("1002", "亚亚", "Q妹");
		Penguin p2 = new Penguin("1003", "美美", "Q妹");
		Penguin p3 = new Penguin("1004", "菲菲", "Q妹");
		Map<Integer, Penguin> map = new HashMap<Integer, Penguin>();
		map.put(1, p);
		map.put(2, p1);
		map.put(3, p2);
		map.put(4, p3);
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		System.out.println("共有" + map.size() + "只企鹅");
		System.out.println("分别是:");
		System.out.println("使用foreach语句遍历,所有企鹅的昵称和品种是:");
		for (Integer key : map.keySet())
		{
			System.out.println(key + "\t" + map.get(key));
		}
		System.out.println("---------------------");
		
		System.out.println("使用Iterator遍历,所有企鹅的昵称和品种是:");
		while (it.hasNext())
		{
			Integer key = it.next();
			System.out.println(key + "\t" + map.get(key));
		}
	}
}


class Penguin
{
	private String num;
	private String name;
	private String sex;

	public Penguin()
	{

	}

	public Penguin(String num, String name, String sex)
	{
		this.num = num;
		this.name = name;
		this.sex = sex;
	}

	public String getNum()
	{
		return num;
	}

	@Override
	public String toString()
	{
		return this.num + "\t" + this.name + "\t" + this.sex;
	}
}