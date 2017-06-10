/**
 * 定义一个账单billing类
 *变量：品种，地址，消费，付款，日期
 *方法：付款多少，消费多少，接收点的食物，打印账单
 *点的食物因为不知一样，所以内容放到数组里面（长度为10）
 * 
 *
 */
public class Billing
{
	private String brand;
	private String address;
	private double payMoney;
	private double actualMoney;
	private String date;	
	private UnitFood[] foods=new UnitFood[10];
/**
 * 账单构造器（从外类往这边入参：种类，地址，日期）
 * 
 *
 */
	public Billing(String brand,String address,String date)
	{
		this.brand=brand;
		this.address=address;
		this.date=date;
	}
	
	public void setPayMoney(double payMoney)
	{
		this.payMoney=payMoney;
	}
	
	public void setActualMoney(double actualmoney)
	{
		this.actualMoney=actualMoney;
	}
	
	public void setFoods(UnitFood...foods)
	{
		this.foods=foods;
	}
	
	public void print()
	{
		System.out.println("********消费清单********");
		System.out.println(this.brand+"\t"+this.address);
		System.out.println("消费时间："+this.date);
		System.out.println("---------------------------");
		
		System.out.println("名称\t\t单价\t数量");
		for(UnitFood food:foods)
		{
			actualMoney=food.getPrice()*food.getQuantity();
			System.out.println(food.getName()+"\t"+food.getPrice()+"\t"+food.getQuantity());
		}
		System.out.println("-----------------------");
		System.out.println("商品总计："+actualMoney+"元");
		System.out.println("收取现金："+payMoney+"元");
		System.out.println("找零："+(payMoney-actualMoney));
	}
}