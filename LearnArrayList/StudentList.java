import java.util.*;
/**
*����ѧ���ࣨѧ�ţ��������ɼ�������ѧ���ֱ����ArrayList��LinkedList����ȡ�༶������ɾ��ѧԱ�Ȳ�����
*
*/
public class StudentList
{
	public static void main(String[] args)
	{
		Student xiaoming = new Student("С��",1,90);
		Student xiaohong = new Student("С��",2,85);
		Student xiaoning = new Student("С��",3,95);
		Student xiaobing = new Student("С��",4,100);
		Student xiaoqiang = new Student("Сǿ",5,60);
		
		List students = new ArrayList();
		students.add(xiaoming);
		students.add(xiaohong);
		
		LinkedList studentsList = new LinkedList();
		studentsList.add(xiaoning);
		studentsList.addFirst(xiaobing);
		studentsList.addLast(xiaoqiang);
		int a= students.size() + studentsList.size();
		System.out.println("�༶һ���У�" + a + "λͬѧ");
		
		students.remove(xiaoming);
		studentsList.removeFirst();
		System.out.println("�༶ʣ��ͬѧ�ֱ��ǣ�");
		
		//����students����
		for (Object obj : students )
		{
			System.out.println(obj);
		}
		
		//����studentsList����
		for (Object obj : studentsList)
		{
			System.out.println(obj);
		}
	}
}
 
class Student
{
	private String name;
	private int no;
	private int score;
	
	Student(){}
	
	Student(String name,int no,int score)
	{
		this.name = name;
		this.no = no;
		this.score = score;
	}
	
	public String toString()
	{
		return name + "\t" + no + "\t" + score;
	}
}