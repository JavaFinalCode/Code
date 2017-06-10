import java.util.*;
//创建学生类（学号，姓名，成绩，联系地址、电话号码），使用Map，学生学号作为key，学生所有信息作业value。
public class StudentMap
{
	public static void main(String[] args)
	{
		
		Student xiaomingNo=new Student("1");
		Student xiaoningNo=new Student("2");
		Student xiaoqiangNo=new Student("3");
		Student xiaobingNo=new Student("4");
		
		Map studentsMap = new HashMap();
		StudentInfo xiaoming=new StudentInfo("小明",66,"昆山",1550688);
		StudentInfo xiaoning=new StudentInfo("小宁",99,"玉山镇",1550615);
		StudentInfo xiaoqiang=new StudentInfo("小强",90,"东山",66666666);
		StudentInfo xiaobing=new StudentInfo("小兵",88,"华山",1552348);
		studentsMap.put(xiaomingNo,xiaoming);
		studentsMap.put(xiaoningNo,xiaoning);
		studentsMap.put(xiaoqiangNo,xiaoqiang);
		studentsMap.put(xiaobingNo,xiaobing);
		System.out.println("总计有" + studentsMap.size() + "名学生成绩");
		System.out.println(studentsMap.keySet());
		System.out.println(studentsMap.values());
		System.out.println(studentsMap);
	}
}

class Student
{
	private String no;
	private StudentInfo[] students=new StudentInfo[10];
	
	Student(){}
	
	Student(String no)
	{
		this.no = no;
	}
	
	public String toString()
	{
		return no;
	}
}

class StudentInfo
{
	private String name; 
	private int score;
	private String address;
	private int telePhoneNo;
	
	StudentInfo(){}
	
	StudentInfo(String name,int score,String address,int telePhoneNo)
	{
		this.name = name;
		this.score =score;
		this.address = address;
		this.telePhoneNo = telePhoneNo;
	}
	
	public String toString()
	{
		return name+score+address+telePhoneNo;
	}
}

