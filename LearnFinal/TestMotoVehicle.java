import java.util.Scanner;
public class TestMotoVehicle
{
	public static void main(String[] args)
	{
		System.out.println("��ӭ���������⳵��");
		System.out.println("************************************************************");
		System.out.println("\t\t�γ�\t\t\t\t�ͳ�(������)");
		System.out.println("����\t���GL8\t����550i\t���������\t<=16��\t\t>16��");
		System.out.println("����\t600\t500\t\t300\t\t800\t\t1500");
		System.out.println("************************************************************");
		System.out.println("��ѡ������Ҫ��Ʒ�ƣ���ˣ������𱭣�������������ʱ�䣺");
		Scanner sc=new Scanner(System.in);
		String brand=sc.next();
		int days=sc.nextInt();
		System.out.println("Ϊ��׼���У����Եȡ�����");
		if("���".equals(brand)||"����".equals(brand))
		{
			System.out.println("��ѡ������Ҫ�ͺ�");
			String type=sc.next();
			System.out.println("��ϵͳ¼�복����Ϣ�����ƺţ���ɫ�������");
			String no=sc.next();
			String color=sc.next();
			String mileage=sc.next();
			Car c=new Car(no,brand,color,mileage,days,type);
			c.calcRent(days);
		}
		else if("��".equals(brand)||"����".equals(brand))
		{
			System.out.println("��ѡ������Ҫ��λ��");
			int seatCount=sc.nextInt();
			System.out.println("��ϵͳ¼�복����Ϣ�����ƺţ���ɫ�������");
			String no=sc.next();
			String color=sc.next();
			String mileage=sc.next();
			Bus b=new Bus(no,brand,color,mileage,days,seatCount);
			b.calcRent(days);
		}
		else
		{
			System.out.println("�޴˳���лл���Ĺ���");
		}
		
	}
}