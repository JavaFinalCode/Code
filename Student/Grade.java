public class Grade
{
	Stud[] stu=new Stud[10];          //����1��Stud���� stu
	int index=0;
	public boolean addStudents(Stud s)//��1��Stud�Ķ���s
	{
			stu[index]=s;                //��ÿ������浽stu���������
			index++;
			return true;	
	}
	
	public void showStudents()
	{
		for(int i=0;i<index;i++)
		{
			stu[i].showStud();//ÿ�������ӡ
		}	
	}
}