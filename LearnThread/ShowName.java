class  ShowName implements Runnable
{
	//自定义线程，实现Runnable接口
	public void run()
   {
   	String strName=Thread.currentThread().getName();
   	for(int i=0;i<50;i++)
   	{
   		System.out.println(strName+":"+i);
   	}	
   }
}

 class ThreadPriority
{
	public static void main(String[] args)
	{
		//三个线程
		Thread t1=new Thread(new ShowName());//第一个自定义线程
		Thread t2=new Thread(new ShowName());//第二个自定义线程
		Thread tm=Thread.currentThread();//获得当前线程，也就是主线程
		
		//分别设置线程名称
		t1.setName("t1");
		t2.setName("t2");
		tm.setName("tm");
		
		//分别设置线程的优先级
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		
		System.out.println(t1.getName()+"的优先级"+t1.getPriority());
		System.out.println(t2.getName()+"的优先级"+t2.getPriority());
		System.out.println(tm.getName()+"的优先级"+tm.getPriority());
		
		t1.start();
		t2.start();
		
		String strName=tm.getName();
		for(int i=0;i<50;i++)
		{
				System.out.println(strName+":"+i);
		}
	}	
}