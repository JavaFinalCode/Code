//并发   遵循时间运行的过程
//并行  同时执行
public class LearnThread
{
	public static void main(String[] args)
	{
		call();
	}
	
	//Thread.currentThread().getName()获取名字！
	//Thread.currentThread().toString()获取内容！
	public static  void call()
	{
		//获取当前现成的名字，也就是主线程main
		System.out.println("我是主线程：我叫："+Thread.currentThread().getName());		
		try
		{
			for(int i =0;i<5;i++)
			{
				//参数是毫秒
				Thread.sleep(2000);
				System.out.println("我睡好了。。。");
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}