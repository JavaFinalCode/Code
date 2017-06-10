//�����ڲ���
public class InnerClass
{
	public static void main(String[] args)
	{
		Thread t1 = new Thread(new Runnable()
		                       {
		                       		@Override
											public void run()
											{
												while(true)
												{
													System.out.println("��........");	
												}	
											}			                       	
		                       });
		t1.start();
		                       	
		Thread t2 = new Thread(new Write());
		t2.start();
		
		//lambda���ʽ�����ֵ���һ�ֺ������˼��
		Thread t3 = new Thread(()->
									 {		                      	
									 	while(true)
										{
											System.out.println("ɾ........");	
										}						
									 });
		t3.start();	                       
		                       	
	}
}

class Write implements Runnable
{
	@Override
	public void run()
	{
		while(true)
		{
			System.out.println("д........");	
		}	
	}	
}