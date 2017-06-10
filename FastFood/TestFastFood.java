/**
 * 创建
 * 
 *
 */
public class TestFastFood
{
	public static void main(String[] args)
	{
/**
 * 实例化KFC
 * 把地址通过账单的构造器传过去
 *并存到账单里面
 */
		KFC kfc=new KFC();
		Billing billing= new Billing("肯德基","名仕大厦","2017年4月24日");	
		kfc.setBilling(billing);
/**
 * 通过UnitFood()构造器传参 点食物
 * 在把这堆东西往点餐方法里面一传。
 *
 */		
		UnitFood kfcHamburger=new UnitFood(15,"香辣汉堡",2);
		UnitFood kfcChicken=new UnitFood(10,"香辣鸡翅",4);
		kfc.order(kfcHamburger,kfcChicken);
/**
 * 把钱给到付款额里面
 */		
		kfc.pay(100);
/**
 * 打印账单
 */	
		kfc.printBilling();
/**
 * 打印俩句服务员的话并提供制作鸡排，制作汉堡的方法！！
 */		
		System.out.println("-----------------------------------");
		System.out.println("商品即将制作，请稍等！");
		kfc.cookingHamburger();
		kfc.cookingChicken();		
		System.out.println("商品制作完成，请享用！");		
	}	
}