import java.util.*;
//����ѧ���ࣨѧ�ţ��������ɼ�����ϵ��ַ���绰���룩��ʹ��Map��ѧ��ѧ����Ϊkey��ѧ��������Ϣ��ҵvalue��
public class StudentMap
{
	public static void main(String[] args)
	{
		
		Student xiaomingNo=new Student("1");
		Student xiaoningNo=new Student("2");
		Student xiaoqiangNo=new Student("3");
		Student xiaobingNo=new Student("4");
		
		Map studentsMap = new HashMap();
		StudentInfo xiaoming=new StudentInfo("С��",66,"��ɽ",1550688);
		StudentInfo xiaoning=new StudentInfo("С��",99,"��ɽ��",1550615);
		StudentInfo xiaoqiang=new StudentInfo("Сǿ",90,"��ɽ",66666666);
		StudentInfo xiaobing=new StudentInfo("С��",88,"��ɽ",1552348);
		studentsMap.put(xiaomingNo,xiaoming);
		studentsMap.put(xiaoningNo,xiaoning);
		studentsMap.put(xiaoqiangNo,xiaoqiang);
		studentsMap.put(xiaobingNo,xiaobing);
		System.out.println("�ܼ���" + studentsMap.size() + "��ѧ���ɼ�");
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

