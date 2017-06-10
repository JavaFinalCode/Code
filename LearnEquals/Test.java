import java.util.*;
public class Test
{
	private List<Student> list=new ArrayList<Student>();
	private Set<Student> stus=new HashSet<Student>(10);
	public void init()
	{
		Student s0=new Student("1","小明",85);
		//
		Student s1=new Student("1","小明",85);
		Student s3=new Student("1","小明",85);
		
		Student s2=new Student("2","ls李四",92);
		Student s4=new Student("3","ww王五",61);		
		Student s5=new Student("4","ngls尼古拉斯赵四",63);
		Student s6=new Student("5","df杜甫",83);
		Student s7=new Student("6","ss苏轼",99);
		Student s8=new Student("7","wa王安石",99);
		Student s9=new Student("8","smg司马光宁",61);
		Student s10=new Student("9","smg司马光",20);
		
		
		stus.add(s0);
		stus.add(s1);
		stus.add(s2);
		stus.add(s3);
		stus.add(s4);
		stus.add(s5);
		stus.add(s6);
		stus.add(s7);
		stus.add(s8);
		stus.add(s9);
		stus.add(s10);
		
		list.addAll(stus);
	}
	
	public void sort(Comparator<Student> comparator)
	{
		for(Student stu:list)
		{
			System.out.println(stu);
		}
		System.out.println("*******************************************************");	
		collections.sort(list,comparator);
		for(Student stu:list)
		{
			System.out.println(stu);
		}
	}
	
	public void sort()
	{
		for(Student stu:list)
		{
			System.out.println(stu);
		}
		System.out.println("*******************************************************");	
		collections.sort(list);
		for(Student stu:list)
		{
			System.out.println(stu);
		}
	}	
	
	public void testEquals()
	{
		for(Student stu:stus)
		{
			System.out.println(stu);
		}
		
		String str1=new String("123");
		String str="123";
		System.out.println(str.hashCode());
		
		System.out.println(str1.hashCode());		
		System.out.println(str==str1);
		String str4=new String("123");
		String str2="123";
		String str3="123";
		
		Set<String> strs=new HashSet<String>();
		strs.add(str);
		strs.add(str1);
		strs.add(str2);
		strs.add(str3);
		strs.add(str4);
		
		for(String e: strs)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String[] args)
	{
		Test test=new Test();
		test.init();
		
		//自己具备比较能力
		test.sort();
		
		//通过比较器
		//Comparator<Student> comparator=null;
		//comparator=new ScoreComparator();
		//comparator=new NameComparator();
		//test.sort(comparator);
	}
}

class Student implements Comparable<Student>
{
	private String no;
	private String name;
	private int score;
	
	public Student(String no,String name,int score)
	{
		this.no=no;
		thos.name=name;
		this.score=score;
	}
	
	public String getNo()
	{
		return this.no;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getScore()
	{
		return this.score;
	}
	
	@Override
	public String toString()
	{
		return "学号："+this.no+"\t姓名:"+this.name+"\t成绩："+this.score;
	}
	
	@Override
	public int hashCode()
	{
		final int prime=31;
		int hash=1;
		
		hash=hash*prime+(null==this.no?0:this.no.hashCode());
		hash=hash*prime+(null==this.name?0:this.name.hashCode());
		hash=hash*prime+this.score;
		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
		{
			return true;
		}
		
		if(null=obj)
		{
			return false;
		}
		
		if(this.getClass()!=obj.getClass())
		{
			return false;
		}
		
		if(!(obj instanceof Student))
		{
			return false;
		}
		
		Student another=(Student)obj;
		
		if(null==this.no)
		{
			if(null!=another.getNo())
			{
				return false;
			}
		}
		
		if(!this.no.equals(another.getNo()))
		{
			return false;
		}
		
		/*
		if(null==this.name)
		{
			if(null!=another.getName())
			{
				return false;
			}
		}
		
		if(!this.name.equals(another.getName()))
		{
			return false;
		}
		*/
		return true;
	}
	
	@Override
	public int comparaTo(Student stu)
	{
		int result= 0;
		if(null!=stu)
		{
			result=-(this.getScore()-stu.getScore());
			
			if(result ==0)
			{
				if(null!=this.getNo()&&null !=stu.getNo())
				{
					result=this.getNo().compareTo(stu.getNO());
				}		
			}
			return result;
		}
		return result;
	}
}

class ScoreComparator implements Comparator<Student>
{
	@Override 
	public int compare(Student s0,Student s1)
	{
		int result=0;
		
		if(null !=s0&&null!=s1)
		{
			result=-(s0.geScore()-s1.getScore());
			
			if(result==0)
			{
				if(null!=s0.getNo()&&null!=s1.getNo())
				{
					result=s0.getNo().compareTo(s1.getNo());
				}
			}
			
			return result;
		}
		return result;
	}
}

class NameComparator implements Comparator<Student>
{
	@Override
	public int compare(Student s0,Student s1)
	{
		int result=0;
		if(null !=s0&&null!=s1)
		{
			if(null!=s0.getName()&&null!=s1.getName())
			{
				result=s0.getName().compareTo(s1.getName());
			}
			
			if(result==0)
			{
				if(null!=s0.getNo()&&null!=s1.getNo())				
				{
					result =s0.getNo().compareTo(s1.getNo());
				}
			}
			
			return result;
		}
		return result;
	}
}