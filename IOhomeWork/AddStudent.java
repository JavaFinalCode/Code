package home0513;
/**
*�ۺϱ���⡣��Ŀ�ṩ�������ļ���20170512�ļ����µ���ҵ�ļ����У����ֱ���ѧ����Ϣ(4��)���γ���Ϣ��4�������ɼ���Ϣ��4������Ҫ��Ϊ��
*���������ļ�����Ϣ���ϵ�һ�����ļ��У���ʽΪ�� ѧ�� ���� �Ա� ����ϵ �γ� �ɼ�
*�ļ�����Ҫ���տγ̺Ž�������1-4��������ͬһ�γ̳ɼ����մӴ�С����
 */
import java.io.*;
import java.util.*;

public class AddStudent
{
	public static final String SP = File.separator;
	public static final String FILE1 = "E:" + SP + "IOTest" + SP + "�ɼ���Ϣ.txt";
	public static final String FILE2 = "E:" + SP + "IOTest" + SP + "�γ���Ϣ.txt";
	public static final String FILE3 = "E:" + SP + "IOTest" + SP + "ѧ����Ϣ.txt";
	public static final String FILE4 = "E:" + SP + "IOTest" + SP + "����Ϣ.txt";

	public static void main(String[] args)
	{
		getScore();
	}

	public static void getScore()
	{
		Reader in = null;
		Writer out = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try
		{
			out = new FileWriter(FILE4);
			in = new FileReader(FILE1);

			br = new BufferedReader(in);
			bw = new BufferedWriter(out);

			String line = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (null != (line = br.readLine()))
			{
				sb.append(line);
				sb.deleteCharAt((sb.length() - 2));
				System.out.println(sb.toString());
			}
		}

		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

		finally
		{
			if (null != bw)
			{
				try
				{
					bw.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (null != br)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}
}

class Student implements Comparable<Student>
{
	private String sNo;
	private String sName;
	private String sex;
	private String dept;
	private int score;

	public Student()
	{
	}

	public void setSNo(String sNo)
	{
		this.sNo = sNo;
	}

	public String getSNo()
	{
		return this.sNo;
	}

	public void setSName(String sName)
	{
		this.sName = sName;
	}

	public String getSName()
	{
		return this.sName;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getSex()
	{
		return this.sex;
	}

	public void setDept(String dept)
	{
		this.dept = dept;
	}

	public String getDept()
	{
		return this.dept;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getScore()
	{
		return this.score;
	}

	@Override
	public String toString()
	{
		return sNo + "\t" + sName + "\t" + sex + "\t" + dept + "\t" + score;
	}

	// �Աȣ���дcompareTo()������Comparable�ӿ��ṩ�ķ�����λ��java.lang
	@Override
	public int compareTo(Student stu)
	{
		int result = 0;
		if (null != stu)
		{
			result = this.getDept().compareTo(stu.getDept());
			if (result == 0)
			{
				if (0 != this.getScore() && 0 != stu.getScore())
				{
					result = -(this.getScore() - stu.getScore());
				}
			}
			return result;
		}
		return result;
	}

}