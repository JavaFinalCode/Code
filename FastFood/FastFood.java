/**
 * ����� �����
 *�����������Billingһ������billing
 * �������˵�����ͣ������ӡ�˵���������������������
 *
 */
public class FastFood
{
/**
 * Billing�������� 
 */
	private Billing billing;
	public void setBilling(Billing billing)
	{
		this.billing=billing;
	}
/**
 * ��ͷ���   (UnitFood... foods)����д�ǿ��Դ��������� 
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
		System.out.println("��������");
	}
	
	public void cookingChicken()
	{
		System.out.println("��������");
	}
}
