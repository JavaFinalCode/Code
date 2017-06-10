import java.util.Scanner;
public class Account
{
	double balance=0;
	Scanner sc=new Scanner(System.in);
	//取款功能
	public void withdraw()
	{
		System.out.print("请输入取款金额：");
  		int drawMoney = sc.nextInt();
  		if(balance<drawMoney)
  		{
   		System.out.println("余额不足!");
  		}
  		else
  		{
   		System.out.println("取款成功!");
   	}
   	balance=balance-drawMoney;
   	System.out.println("\n***当前余额为："+balance+"元***");
	}
	//存款功能
	public void deposit()
	{
		 System.out.print("请输入存款金额：");
  		 balance= balance+sc.nextDouble();
  		 System.out.println("存款成功!");
  		 System.out.println("\n***当前余额为："+balance+"元***");
	}
}
