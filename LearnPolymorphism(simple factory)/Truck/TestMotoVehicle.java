import java.util.Scanner;
public class TestMotoVehicle
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		MotoVehile[] motos = new MotoVehile[4]; 
		motos[0] = new Car("汽车","京NY28588");
		motos[1] = new Bus("货车","京NY28588");
		motos[2] = new Truck("卡车","京NY28588",4);
		System.out.println("请输入天数：");
		int days=sc.next();
		Rent r=new Rent();
		r.calcTotalRent(motos,days);
		System.out.println(r.calcTotalRent(motos,days));	
	}
}



public abstract class MotoVehicle
{
	private String name;
	private String brand;
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getBrand()
	{
		return this.brand;
	}
	
	public void setBrand(String brand)
	{
		this.brand=brand;
	}
	
	public abstract void calcRent(int days);
	
	
} 

class car extends MotoVehicle
{
	private final int dayRates=600;
	
	public Car(name,brand)
	{
		super(name,brand);
	}
	@Override
	public void calcRent(int days)
	{
		int total=days*dayRates;
		System.out.println(total);
	}
}

class Bus extends MotoVehicle
{
	private final int dayRates=800;
	
	public Bus(name,brand)
	{
		super(name,brand)
	}
	@Override
	public void calcRent(int days)
	{
		int total=days*dayRates;
		System.out.println(total);
	}
}

class Truck extends MotoVehicle
{	
	private int perTon;
	private final int dayRates=1000;
	
	public Truck(name,brand,perTon)
	{
		super.(name,brand);
		this.perTon=perTon;
	}
	@Override
	public void calcRent(int days)
	{
		int total=days*dayRates*perTon;
		System.out.println(total);
	}
}

class Rent
{	
	public int calcTotalRent(MotoVehile[] motos,int days)
	{   
		int totalRent = 0;  	 
      for(int i=0;i<motos.length;++i)
      {
     		totalRent += motos[i].calRent(days);     		
      } 
      return totalRent;				
	}
}