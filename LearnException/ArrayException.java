import java.util.InputMismatchException;
import java.util.Scanner;
/**
*ģ������Խ���쳣�����տ����ϵ����Ӵ����쳣�������Զ���һ���쳣��
*/
public class ArrayException
{
	public static void main(String[] args)throws OwnException
	{
		int[] arr={1,2,3};
		System.out.println("����������Ҫ�Ĳ�ѯ���±꣺");
		Scanner  sc=null;
		sc=new Scanner(System.in);
		int inPut=sc.nextInt();
		try
		{
			if(inPut<0||inPut>2)
			{
				throw new OwnException("��������ֲ���0~2֮��");
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
				System.out.println("��ӭ������!");
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