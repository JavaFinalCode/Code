import java.util.*;
/**
*�Ѷ��������Ϣ��ӵ�������
*�鿴��������������������Ϣ
*ɾ�������в�������Ԫ��
*�жϼ������Ƿ����ָ�����
*/
public class LearnArrayListPenguin
{
	public static void main(String[] args)
	{
		Penguin ououDog = new Penguin( "ŷŷ  ","Q��" );
		Penguin feifeiDog = new Penguin( "�ɷ�","Q��" );
		Penguin meimeiDog = new Penguin( "����","Q��" );
		Penguin honghongDog = new Penguin( "���","Q��" );

		List Penguins = new ArrayList();
		
		Penguins.add( ououDog );
		Penguins.add( meimeiDog );
		Penguins.add( feifeiDog );
		Penguins.add( 2 , honghongDog );
		System.out.println( "������" + Penguins.size() + "������" );	
		System.out.println( "�ֱ��ǣ�" );
		
		for (Object obj:Penguins)
		{
			System.out.println( obj );
		}
		Penguins.remove( ououDog );
		Penguins.remove( feifeiDog );
		System.out.println("*************************************************");
		System.out.println("ɾ������"+Penguins.size()+"������");
		System.out.println("�ֱ��ǣ�");
		
		for (Object obj : Penguins)
		{
			System.out.println(obj);
		}
		System.out.println("*************************************************");
		if(Penguins.contains( meimeiDog ))
		{
			System.out.println( "�����а�����������Ϣ" );
		}
		else
		{
			System.out.println( "�����в�������������Ϣ" );
		}
	}
}
//��������
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