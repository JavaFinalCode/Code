/**
*�Զ����ࣺʵ��Runnable�ӿڣ�Runnable���и�����run����,��дrun��������ɴ����Զ����̣߳�
* ������:(1)Runnable calc=new Calc();//(Ҳ��һ�ֶ�̬)
*        (2) Thread cal=new Thread(calc);//Thread()�������зŵ�runnable�ӿڶ���		
*/
public class LearnRunnable
{
	public static void main(String[] args)
	{
		Runnable calc=new Calc();//(Ҳ��һ�ֶ�̬)
		Thread cal=new Thread(calc);//Thread()�������зŵ�runnable�ӿڶ���
		cal.start();
	}
}

//ʵ��runnable�ӿڣ��Ƽ���
class Calc implements Runnable
{
	@Override
	public void run()
	{
		System.out.println("����");
		try
		{
			for(int i =0;i<5;i++)
			{
				//�����Ǻ���
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