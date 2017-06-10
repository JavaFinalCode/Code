public class AboutThread
{
	//
	public static void main(String[] args)
	{
		//
		Thread up=new Upload();
		Thread down = new Download();
		
		//线程启动,而是调用start()方法，不是调用run方法，调run方法哪来是启动的线程。
		up.start();
		down.start();
		
	}
}
//(第一步：)自定义线程类
class Upload extends Thread
{
	//(第二步：)重写run方法，run方法是线程实际执行的代码
	//run方法不需要去调用，线程启动会自动调用
	@Override
	public void run()
	{
		System.out.println("正在上传。。。");
		try
		{
			for(int i=0;i<10;i++)
			{
				Thread.sleep(2000);
				System.out.print("==");
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}		
	}	
}

class Download extends Thread
{
	@Override
	public void run()
	{
		System.out.println("正在下载。。。");
		try
		{
			for(int i=0;i<10;i++)
			{
				Thread.sleep(3000);
				System.out.print("##");
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}	
	
}