/**
 * ����һ���˵�billing��
 *������Ʒ�֣���ַ�����ѣ��������
 *������������٣����Ѷ��٣����յ��ʳ���ӡ�˵�
 *���ʳ����Ϊ��֪һ�����������ݷŵ��������棨����Ϊ10��
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
 * �˵����������������������Σ����࣬��ַ�����ڣ�
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
		System.out.println("********�����嵥********");
		System.out.println(this.brand+"\t"+this.address);
		System.out.println("����ʱ�䣺"+this.date);
		System.out.println("---------------------------");
		
		System.out.println("����\t\t����\t����");
		for(UnitFood food:foods)
		{
			actualMoney=food.getPrice()*food.getQuantity();
			System.out.println(food.getName()+"\t"+food.getPrice()+"\t"+food.getQuantity());
		}
		System.out.println("-----------------------");
		System.out.println("��Ʒ�ܼƣ�"+actualMoney+"Ԫ");
		System.out.println("��ȡ�ֽ�"+payMoney+"Ԫ");
		System.out.println("���㣺"+(payMoney-actualMoney));
	}
}