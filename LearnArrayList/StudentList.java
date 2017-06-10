import java.util.*;
/**
*创建学生类（学号，姓名，成绩），讲学生分别放入ArrayList和LinkedList，获取班级人数，删除学员等操作。
*
*/
public class StudentList
{
	public static void main(String[] args)
	{
		Student xiaoming = new Student("小明",1,90);
		Student xiaohong = new Student("小红",2,85);
		Student xiaoning = new Student("小宁",3,95);
		Student xiaobing = new Student("小兵",4,100);
		Student xiaoqiang = new Student("小强",5,60);
		
		List students = new ArrayList();
		students.add(xiaoming);
		students.add(xiaohong);
		
		LinkedList studentsList = new LinkedList();
		studentsList.add(xiaoning);
		studentsList.addFirst(xiaobing);
		studentsList.addLast(xiaoqiang);
		int a= students.size() + studentsList.size();
		System.out.println("班级一共有：" + a + "位同学");
		
		students.remove(xiaoming);
		studentsList.removeFirst();
		System.out.println("班级剩余同学分别是：");
		
		//遍历students集合
		for (Object obj : students )
		{
			System.out.println(obj);
		}
		
		//遍历studentsList集合
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