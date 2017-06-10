/**
*自定义类：实现Runnable接口，Runnable里有个抽象run方法,重写run方法，完成创建自定义线程！
* 主方法:(1)Runnable calc=new Calc();//(也是一种多态)
*        (2) Thread cal=new Thread(calc);//Thread()构造器中放的runnable接口对象		
*/
public class LearnRunnable
{
	public static void main(String[] args)
	{
		Runnable calc=new Calc();//(也是一种多态)
		Thread cal=new Thread(calc);//Thread()构造器中放的runnable接口对象
		cal.start();
	}
}

//实现runnable接口（推荐）
class Calc implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("计算");
		try
		{
			for(int i =0;i<5;i++)
			{
				//参数是毫秒
				Thread.sleep(2000);
				System.out.println("^");
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}