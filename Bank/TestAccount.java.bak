import java.util.Scanner;
public class TestAccount
{
	public static void main(String[] args)
	{
		Account ac=new Account();
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入您的个人信息：");
		BasicInfo b1=new BasicInfo();
		System.out.println("请输入您的名字：");
		b1.name=sc.next();
		System.out.println("请输入您的年龄：");
		b1.age=sc.nextInt();
		System.out.println("请输入您的账号：");
		b1.accountId=sc.nextLong();
		System.out.println("请输入您的开户日期：");
		b1.openDate=sc.next();
		b1.showInfo();
		boolean isLogIn=true;	
		while(isLogIn)
		{			
			System.out.println("请选择1:存款\t2：取款\t0：退出\n\r请选择你要办理的业务");
			int input=sc.nextInt();
			switch(input)
			{
				case 1:
				{
					ac.deposit();
					break;	
				}
				case 2:
				{
					ac.withdraw();
					break;	
				}
				case 0:
				{
					isLogIn=false;
					System.out.println("退出成功");
					break;	
				}				
			}
		}
	}
}