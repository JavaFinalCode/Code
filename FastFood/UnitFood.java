/**
 * ����һ��ʳ����
 *�������۸����ƣ����� 
 *������get�۸�get���ƣ�get����
 *�����������������θ�ֵ
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
 