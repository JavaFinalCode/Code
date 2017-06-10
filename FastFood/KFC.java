/**
 * 创建 肯德基类 继承 快餐类
 * 重写 制作鸡排，制作汉堡的方法，因为每家实现不一样
 *
 */
public class KFC extends FastFood
{
	@Override
	public void cookingHamburger()
	{
		super.cookingHamburger();
		System.out.println("肯德基香辣鸡腿堡");
	}
	@Override
	public void cookingChicken()
	{
		super.cookingChicken();
		System.out.println("肯德基香辣鸡排");
	}
}