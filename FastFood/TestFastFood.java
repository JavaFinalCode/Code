/**
 * ����
 * 
 *
 */
public class TestFastFood
{
	public static void main(String[] args)
	{
/**
 * ʵ����KFC
 * �ѵ�ַͨ���˵��Ĺ���������ȥ
 *���浽�˵�����
 */
		KFC kfc=new KFC();
		Billing billing= new Billing("�ϵ»�","���˴���","2017��4��24��");	
		kfc.setBilling(billing);
/**
 * ͨ��UnitFood()���������� ��ʳ��
 * �ڰ���Ѷ�������ͷ�������һ����
 *
 */		
		UnitFood kfcHamburger=new UnitFood(15,"��������",2);
		UnitFood kfcChicken=new UnitFood(10,"��������",4);
		kfc.order(kfcHamburger,kfcChicken);
/**
 * ��Ǯ�������������
 */		
		kfc.pay(100);
/**
 * ��ӡ�˵�
 */	
		kfc.printBilling();
/**
 * ��ӡ�������Ա�Ļ����ṩ�������ţ����������ķ�������
 */		
		System.out.println("-----------------------------------");
		System.out.println("��Ʒ�������������Եȣ�");
		kfc.cookingHamburger();
		kfc.cookingChicken();		
		System.out.println("��Ʒ������ɣ������ã�");		
	}	
}