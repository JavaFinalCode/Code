//����   ��ѭʱ�����еĹ���
//����  ͬʱִ��
public class LearnThread
{
	public static void main(String[] args)
	{
		call();
	}
	
	//Thread.currentThread().getName()��ȡ���֣�
	//Thread.currentThread().toString()��ȡ���ݣ�
	public static  void call()
	{
		//��ȡ��ǰ�ֳɵ����֣�Ҳ�������߳�main
		System.out.println("�������̣߳��ҽУ�"+Thread.currentThread().getName());		
		try
		{
			for(int i =0;i<5;i++)
			{
				//�����Ǻ���
				Thread.sleep(2000);
				System.out.println("��˯���ˡ�����");
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}