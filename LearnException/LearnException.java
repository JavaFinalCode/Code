import java.util.*;
import java.util.Scanner;
public class LearnException
{
	public static void main(String[] args)throws OwnException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("������γ̱��1~3:");
		try
		{
			int a=sc.nextInt();
			if(a<1||a>3)
			{
				throw new OwnException("��������ֲ���1~3֮��");
			}
			else if(a>1&&a<3)
			{
				switch(a)
				{
					case 1:
					{
						System.out.println("java���");
						break;	
					}
					case 2:
					{
						System.out.println("CSharp���");
						break;	
					}
					case 3:
					{
						System.out.println("web���");
						break;	
					}
				}
			}
		}
		catch(InputMismatchException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.exit(1);���������һ�� ���᲻ִ��finally��!
		}
		finally
		{
			System.out.println("��ӭ������!");
		}
	}
}

class OwnException extends Exception
{
	 public OwnException(String msg)  
    {  
        super(msg);  
    }  
}