public class AboutThread
{
	//
	public static void main(String[] args)
	{
		//
		Thread up=new Upload();
		Thread down = new Download();
		
		//�߳�����,���ǵ���start()���������ǵ���run��������run�����������������̡߳�
		up.start();
		down.start();
		
	}
}
//(��һ����)�Զ����߳���
class Upload extends Thread
{
	//(�ڶ�����)��дrun������run�������߳�ʵ��ִ�еĴ���
	//run��������Ҫȥ���ã��߳��������Զ�����
	@Override
	public void run()
	{
		System.out.println("�����ϴ�������");
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
		System.out.println("�������ء�����");
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