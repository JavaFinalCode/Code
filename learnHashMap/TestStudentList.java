/**
*对学生类分别用List、Map、Set存储，并进行遍历输出，迭代器方法和增强for循环方法
*
*/
import java.util.*;

public class TestStudentList
{
	public static void main(String[] args)
	{
		Student1 stu1 = new Student1("01", "李雷", 99);
		Student1 stu2 = new Student1("02", "韩梅梅", 88);
		Student1 stu3 = new Student1("03", "露西", 77);
		Student1 stu4 = new Student1("04", "波利", 66);

		List<Student1> stus = new ArrayList<Student1>();
		// 判断集合是否为空
		if (stus.isEmpty())
		{
			System.out.println("现在集合中有元素吗？没有请添加");
		}
		stus.add(stu1); // 按对象直接添加
		stus.add(1, stu2);
		stus.add(stu3);
		stus.add(3, stu4);

		if (stus.contains(stu4)) // 判断集合里是否包含某个对象
		{
			System.out.println("集合里包含stu2");
		}

		int i = stus.indexOf(stu4); // 找到某对象在集合里的位置
		System.out.println("stu4在集合里的下标是" + i);

		System.out.println("请打印此对象详细信息");
		System.out.println(stus.get(3)); // 以下标搜索集合元素

		System.out.println("请删除此非人类");
		stus.remove(stu4); // 以对象名删除集合内元素，也可以以下标删除

		System.out.println("现在集合里一共有几个元素：" + stus.size());// 集合容量

		// 普通for遍历输出
		for (int j = 0; j < stus.size(); j++)
		{
			System.out.println(stus.get(j));
		}
		System.out.println("****************************************************");

		// 增强for循环输出
		for (Student1 s : stus)
		{
			System.out.println(s);
		}
		System.out.println("****************************************************");

		// 迭代器输出
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
		return "学号：" + nub + "\t姓名" + name + "\t成绩" + score;
	}
}