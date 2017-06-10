//匿名内部类
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
													System.out.println("读........");	
												}	
											}			                       	
		                       });
		t1.start();
		                       	
		Thread t2 = new Thread(new Write());
		t2.start();
		
		//lambda表达式，体现的是一种函数编程思想
		Thread t3 = new Thread(()->
									 {		                      	
									 	while(true)
										{
											System.out.println("删........");	
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
			System.out.println("写........");	
		}	
	}	
}