import java.util.Scanner;
public class TestMotoVehicle
{
	public static void main(String[] args)
	{
		System.out.println("欢迎光临神州租车！");
		System.out.println("************************************************************");
		System.out.println("\t\t轿车\t\t\t\t客车(金龙金杯)");
		System.out.println("车型\t别克GL8\t宝马550i\t别克林荫大道\t<=16座\t\t>16座");
		System.out.println("日租\t600\t500\t\t300\t\t800\t\t1500");
		System.out.println("************************************************************");
		System.out.println("请选择您需要的品牌（别克，宝马，金杯，金龙），租用时间：");
		Scanner sc=new Scanner(System.in);
		String brand=sc.next();
		int days=sc.nextInt();
		System.out.println("为您准备中，请稍等。。。");
		if("别克".equals(brand)||"宝马".equals(brand))
		{
			System.out.println("请选择您需要型号");
			String type=sc.next();
			System.out.println("请系统录入车辆信息，车牌号，颜色，里程数");
			String no=sc.next();
			String color=sc.next();
			String mileage=sc.next();
			Car c=new Car(no,brand,color,mileage,days,type);
			c.calcRent(days);
		}
		else if("金杯".equals(brand)||"金龙".equals(brand))
		{
			System.out.println("请选择您需要座位数");
			int seatCount=sc.nextInt();
			System.out.println("请系统录入车辆信息，车牌号，颜色，里程数");
			String no=sc.next();
			String color=sc.next();
			String mileage=sc.next();
			Bus b=new Bus(no,brand,color,mileage,days,seatCount);
			b.calcRent(days);
		}
		else
		{
			System.out.println("无此车，谢谢您的光临");
		}
		
	}
}