/**
 * 定义一个食物类
 *变量：价格，名称，单价 
 *方法：get价格，get名称，get单价
 *构造器：构造器传参赋值
 */
public class UnitFood
{
	private double price;
	private String name;
	private int quantity;
	
	public UnitFood(double price,String name,int quantity)
	{
		this.price= price;
		this.name=name;
		this.quantity=quantity;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
}
 