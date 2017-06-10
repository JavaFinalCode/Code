class Student implements Cloneable 
{
	private int age;
	private String name ;
	private Teacher teacher;
	
	public Student(int age,String name,Teacher teacher)
	{
		this.age=age;
		this.name=name;
		this.teacher=teacher;
	}
	
	public int getAge()
	{
		reture age;
	}	
	
	public void setAge(int age)
	{
		this.age=age;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Teacher getTeacher()
	{
		return teacher;
	}
	
	public Object Clone()
	{
		try
		{
			Student s=(Student)super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return s;
	}
	public String toString()
	{
		return "student's age:"+age+"\t"+"Student'name:"+name+"\t "+"student'teacher :"+
	}
}

class Teacher 
{
	private String name;
	
	public Teacher(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public Teacher Clone()
	{
		try
		{
			Teacher t=(Teacher)super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return t;
	}
	public String toString()
	{
		return "teacher's name"+name;
	}
	
}

public class TestStudent
{
	public static void  main(String[] args)throws CloneNotSupportedException
	{
		Student s1=new Student(18,"曹明","余德泉");
		Student s2=s1.clone();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1==s2);
	}
}
