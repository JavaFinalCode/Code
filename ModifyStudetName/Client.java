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
			System.out.println("请输入名字：");
			String name=sc.next();
			service.addName(name);
			String chose=null;
			do
			{
				if(isNotValid)
				{
					System.out.println("输入有误，请重新输入");
				}
				else
				{
					System.out.println("是否继续添加？(y/n)");
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
		System.out.println("请输入要修改的名字：");
		String oldName=sc.next();
		System.out.println("请输入新的名字：");
		String newName=sc.next();
		if(service.mofifyName(oldName,newName))
		{
			System.out.println("修改成功！");
			service.listName();
		}
   }
}