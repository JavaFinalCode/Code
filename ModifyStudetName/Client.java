import java.util.Scanner;
public class Client
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		CustomerService service =new CustomerService();
		boolean isNotValid=false;
		do
		{
			System.out.println("���������֣�");
			String name=sc.next();
			service.addName(name);
			String chose=null;
			do
			{
				if(isNotValid)
				{
					System.out.println("������������������");
				}
				else
				{
					System.out.println("�Ƿ������ӣ�(y/n)");
				}
				chose=sc.next();
				isNotValid=!"y".equals(chose)&&!"n".equals(chose)	;				
			}while(isNotValid);
	    
		if("n".equals(chose))
		{
			break;
		}
		}while(true);
		
		service.listName("");
		System.out.println("������Ҫ�޸ĵ����֣�");
		String oldName=sc.next();
		System.out.println("�������µ����֣�");
		String newName=sc.next();
		if(service.mofifyName(oldName,newName))
		{
			System.out.println("�޸ĳɹ���");
			service.listName();
		}
   }
}