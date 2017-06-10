/**
 * 抽象出 快餐类
 *这边是引用了Billing一个对象billing
 * 方法：账单，点餐，付款，打印账单，制作汉堡，制作鸡排
 *
 */
public class FastFood
{
/**
 * Billing类型引用 
 */
	private Billing billing;
	public void setBilling(Billing billing)
	{
		this.billing=billing;
	}
/**
 * 点餐方法   (UnitFood... foods)这样写是可以打包过来输出 
 */	
	public void order(UnitFood... foods)
	{
		this.billing.setFoods(foods);
	}
	
	public void pay(double cash)
	{
		this.billing.setPayMoney(cash);
	}
	
	public void printBilling()
	{
		this.billing.print();
	}
	
	public void cookingHamburger()
	{
		System.out.println("制作汉堡");
	}
	
	public void cookingChicken()
	{
		System.out.println("制作鸡排");
	}
}
