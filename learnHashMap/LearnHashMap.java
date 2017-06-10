/**
*Ϊ������һ����ţ�����Ϊ���洢��������Ϣ��Ӧ�÷��͵�HashMap����
*ʹ��Iterator��foreach�����б���
*ʹ�ð�װ��Integer ���Map�ļ�����
*Map<Integer,Penguin>
*/
import java.util.*;

public class TestHashMap
{
	public static void main(String[] args)
	{
		Penguin p = new Penguin("1001", "ŷŷ", "Q��");
		Penguin p1 = new Penguin("1002", "����", "Q��");
		Penguin p2 = new Penguin("1003", "����", "Q��");
		Penguin p3 = new Penguin("1004", "�Ʒ�", "Q��");
		Map<Integer, Penguin> map = new HashMap<Integer, Penguin>();
		map.put(1, p);
		map.put(2, p1);
		map.put(3, p2);
		map.put(4, p3);
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		System.out.println("����" + map.size() + "ֻ���");
		System.out.println("�ֱ���:");
		System.out.println("ʹ��foreach������,���������ǳƺ�Ʒ����:");
		for (Integer key : map.keySet())
		{
			System.out.println(key + "\t" + map.get(key));
		}
		System.out.println("---------------------");
		
		System.out.println("ʹ��Iterator����,���������ǳƺ�Ʒ����:");
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