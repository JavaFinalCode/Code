import java.util.*;

public class TestStudentSet
{
	public static void main(String[] args)
	{
		Student3 stu1 = new Student3("01", "����", 99);
		Student3 stu2 = new Student3("02", "��÷÷", 88);
		Student3 stu3 = new Student3("03", "¶��", 77);
		Student3 stu4 = new Student3("04", "����", 66);

		Set<Student3> stus = new HashSet<Student3>();

		if (stus.isEmpty()) // �жϼ����Ƿ�Ϊ��
		{
			System.out.println("���ڼ�������Ԫ����û�������");
		}
		stus.add(stu1); // ������ֱ�����
		stus.add(stu2);
		stus.add(stu3);
		stus.add(stu4);

		if (stus.contains(stu4)) // �жϼ������Ƿ����ĳ������
		{
			System.out.println("���������stu2");
		}

		stus.remove(stu4); // ����Ԫ��ɾ����ӦԪ�أ�����removeAll();����

		// ��ǿforѭ�����
		for (Student3 s : stus)
		{
			System.out.println(s);
		}
		System.out.println("****************************************************");

		// ���������
		Iterator<Student3> it = stus.iterator();
		while (it.hasNext())
		{
			System.out.println(it.next());
		}

	}
}

class Student3
{
	private String nub;
	private String name;
	private int score;

	public Student3()
	{
	}

	public Student3(String nub, String name, int score)
	{
		this.name = name;
		this.nub = nub;
		this.score = score;
	}

	public String getNub()
	{
		return this.nub;
	}

	@Override
	public String toString()
	{
		return "ѧ�ţ�" + nub + "\t����" + name + "\t�ɼ�" + score;
	}

}