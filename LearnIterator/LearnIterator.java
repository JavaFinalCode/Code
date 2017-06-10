import java.util.*;
public class LearnIterator
{
	public static void main(String[] args)
	{
		Map penguins=new HashMap();
		Penguin yaya=new Penguin("Q��");
		Penguin ouou=new Penguin("Q��");
		Penguin feifei=new Penguin("Q��");
		Penguin meimei=new Penguin("Q��");
		penguins.put("ѾѾ",yaya);
		penguins.put("ŷŷ",ouou);
		penguins.put("�Ʒ�",feifei);
		penguins.put("����",meimei);
		Set keys=penguins.keySet();//map�и�keySet������ȡkey��Set���ͼ���
		Iterator it=keys.iterator();//����һ����������
		//����һ�������
		while(it.hasNext()) //����������һ����                 
		{
			String key=(String)it.next();//�����һ����key
			Penguin penguin=(Penguin)penguins.get(key);
			System.out.println(key+"--"+penguin);
		}
		System.out.println("***************************");//�������������
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