/**
*��ѧ����ֱ���List��Map��Set�洢�������б����������������������ǿforѭ������
*
*/
import java.util.*;

public class TestStudentList
{
	public static void main(String[] args)
	{
		Student1 stu1 = new Student1("01", "����", 99);
		Student1 stu2 = new Student1("02", "��÷÷", 88);
		Student1 stu3 = new Student1("03", "¶��", 77);
		Student1 stu4 = new Student1("04", "����", 66);

		List<Student1> stus = new ArrayList<Student1>();
		// �жϼ����Ƿ�Ϊ��
		if (stus.isEmpty())
		{
			System.out.println("���ڼ�������Ԫ����û�������");
		}
		stus.add(stu1); // ������ֱ�����
		stus.add(1, stu2);
		stus.add(stu3);
		stus.add(3, stu4);

		if (stus.contains(stu4)) // �жϼ������Ƿ����ĳ������
		{
			System.out.println("���������stu2");
		}

		int i = stus.indexOf(stu4); // �ҵ�ĳ�����ڼ������λ��
		System.out.println("stu4�ڼ�������±���" + i);

		System.out.println("���ӡ�˶�����ϸ��Ϣ");
		System.out.println(stus.get(3)); // ���±���������Ԫ��

		System.out.println("��ɾ���˷�����");
		stus.remove(stu4); // �Զ�����ɾ��������Ԫ�أ�Ҳ�������±�ɾ��

		System.out.println("���ڼ�����һ���м���Ԫ�أ�" + stus.size());// ��������

		// ��ͨfor�������
		for (int j = 0; j < stus.size(); j++)
		{
			System.out.println(stus.get(j));
		}
		System.out.println("****************************************************");

		// ��ǿforѭ�����
		for (Student1 s : stus)
		{
			System.out.println(s);
		}
		System.out.println("****************************************************");

		// ���������
		Iterator<Student1> it = stus.iterator();
		while (it.hasNext())
		{
			System.out.println(it.next());
		}

	}
}

class Student1
{
	private String nub;
	private String name;
	private int score;

	public Student1()
	{

	}

	public Student1(String nub, String name, int score)
	{
		this.nub = nub;
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString()
	{
		return "ѧ�ţ�" + nub + "\t����" + name + "\t�ɼ�" + score;
	}
}