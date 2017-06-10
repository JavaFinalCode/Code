public class Grade
{
	Stud[] stu=new Stud[10];          //创建1个Stud数组 stu
	int index=0;
	public boolean addStudents(Stud s)//传1个Stud的对象s
	{
			stu[index]=s;                //把每个对象存到stu这个数组里
			index++;
			return true;	
	}
	
	public void showStudents()
	{
		for(int i=0;i<index;i++)
		{
			stu[i].showStud();//每个对象打印
		}	
	}
}