import java.util.InputMismatchException;
import java.util.Scanner;
/**
*模拟数组越界异常。按照课堂上的例子处理异常。并且自定义一个异常。
*/
public class ArrayException
{
	public static void main(String[] args)throws OwnException
	{
		int[] arr={1,2,3};
		System.out.println("请输入你所要的查询的下标：");
		Scanner  sc=null;
		sc=new Scanner(System.in);
		int inPut=sc.nextInt();
		try
		{
			if(inPut<0||inPut>2)
			{
				throw new OwnException("输入的数字不在0~2之间");
			}
			else if(inPut>=0&&inPut<3)
			{
				switch(inPut)
				{
					case 0:
					{
						System.out.println(arr[0]);
						break;
					}
					case 1:
					{
						System.out.println(arr[1]);
						break;
					}
					case 2:
					{
						System.out.println(arr[2]);
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
		}
		finally
		{
			if(null!=sc)
			{
				sc.close();
				System.out.println("欢迎提出意见!");
			}
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