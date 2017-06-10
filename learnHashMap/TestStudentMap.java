import java.util.*;

public class TestStudentSet
{
	public static void main(String[] args)
	{
		Student3 stu1 = new Student3("01", "李雷", 99);
		Student3 stu2 = new Student3("02", "韩梅梅", 88);
		Student3 stu3 = new Student3("03", "露西", 77);
		Student3 stu4 = new Student3("04", "波利", 66);

		Set<Student3> stus = new HashSet<Student3>();

		if (stus.isEmpty()) // 判断集合是否为空
		{
			System.out.println("现在集合中有元素吗？没有请添加");
		}
		stus.add(stu1); // 按对象直接添加
		stus.add(stu2);
		stus.add(stu3);
		stus.add(stu4);

		if (stus.contains(stu4)) // 判断集合里是否包含某个对象
		{
			System.out.println("集合里包含stu2");
		}

		stus.remove(stu4); // 根据元素删除对应元素，还有removeAll();方法

		// 增强for循环输出
		for (Student3 s : stus)
		{
			System.out.println(s);
		}
		System.out.println("****************************************************");

		// 迭代器输出
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
		return "学号：" + nub + "\t姓名" + name + "\t成绩" + score;
	}

}