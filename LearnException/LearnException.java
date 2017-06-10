import java.util.*;
import java.util.Scanner;
public class LearnException
{
	public static void main(String[] args)throws OwnException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入课程编号1~3:");
		try
		{
			int a=sc.nextInt();
			if(a<1||a>3)
			{
				throw new OwnException("输入的数字不在1~3之间");
			}
			else if(a>1&&a<3)
			{
				switch(a)
				{
					case 1:
					{
						System.out.println("java编程");
						break;	
					}
					case 2:
					{
						System.out.println("CSharp编程");
						break;	
					}
					case 3:
					{
						System.out.println("web编程");
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
			//System.exit(1);如果加上这一句 将会不执行finally块!
		}
		finally
		{
			System.out.println("欢迎提出意见!");
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