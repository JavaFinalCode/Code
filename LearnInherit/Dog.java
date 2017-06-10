public class Dog extends Pet 
{
	//狗自己私有的品种
	private String strain;
	
	public String getStrain()
	{
		return this.strain;
	}
	
	public Dog(){}
	// 构造器传参
	public Dog(String name,int health,int love,String strain)
	{
		//调用父类构造器传公有的参数
		super(name,health,love);
		this.strain=strain;
	}
	//重写父类方法，调用父类方法同时加上自己私有的输出
	@Override
	public void print()
	{
		super.print();
		System.out.print("\t种类："+strain);	
	}	
}
