//���ڸ����������ӿڱ�̣�List���÷��� �ÿ�����ʵ���ࣨʵ�ֽӿ���������ת�ͣ���
//ʹ��ArrayList�洢Ԫ��
//��ת�Ͷ���ָ������󣬶�ʧ�Լ����еģ������д�˵��õĻ������ǵ����Լ��ģ���
import java.util.*;
public class LearnArrayListDog
{
	public static void main(String[] args)
	{
		//new Dogʵ���������ڴ�ŵ��������棬����������
		Dog ououDog = new Dog("ŷŷ");
		Dog feifeiDog = new Dog("�ɷ�");
		Dog meimeiDog = new Dog("����");
		Dog honghongDog = new Dog("���");
		//��������dogs���� List�ӿ�   ArrayList��ʵ����
		List dogs = new ArrayList();
		//��dogs�����������Ӷ���dog   
		dogs.add( ououDog );
		dogs.add( meimeiDog );
		dogs.add( feifeiDog );
		dogs.add( 2,honghongDog );
		System.out.println("������"+dogs.size()+"������");	
		System.out.println("�ֱ��ǣ�");
		//��ͨforѭ������	(Ĭ�ϵ���toString���)
		//��������ͨ����show��������������ʹ����дtoString���������	��
		for(int i = 0;i<dogs.size();i++)
		{ 
			System.out.println(dogs.get(i));						
		}
		//��ǿforѭ������		
		for (Object obj:dogs)
		{
			System.out.println(obj);
		}
		
	}
}
//��������
class Dog
{
	private String name;
	
	Dog()
	{
		
	}
	
	//���е��඼�̳���OBject��!
	//��дһ��toString�����������ͻ�����Լ���toString����();����д�ͻ�Ĭ�ϵ��ø��ࣨObject����toString()������
	
	Dog(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return "���֣�" + name;
	}
}

//��ת�Ͷ���ָ������󣬶�ʧ�Լ����еģ������д�˵��õĻ������ǵ����Լ��ģ���
//���е��඼�̳���OBject��!
//��дһ��toString�����������ͻ�����Լ���toString����();����д�ͻ�Ĭ�ϵ��ø��ࣨObject����toString()������
/**
*
*Cat��-->���̳��ڣ�->Pet��(���Ƕ��̳���Object��)
*ʵ��������ʹ�õķ�����
*
*
*
*/
//������Ҫ����ӵ���E�����ͣ��������ӵ��Ƕ���Ļ�������ʾ����ȫ������Ҫʹ��javac -encoding utf8 -Xlint:unchecked TestList.java���б��룡
