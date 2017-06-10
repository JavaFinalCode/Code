public abstract class MotoVehicle
{
	private String no;
	private String brand;
	private String color;
	private String mileage;
	private int days;
	
	public MotoVehicle()
	{
		
	}
	
	public MotoVehicle(String no,String brand,String color,String mileage,int days)
	{
		this.no=no;
		this.brand=brand;
		this.color=color;
		this.mileage=mileage;
		this.days=days;
	}
	
	public  String getNo()
	{
		return no;
	}
	
	public  String getBrand()
	{
		return brand;
	}
	
	public  String getColor()
	{
		return color;
	}
	
	public  String getMileage()
	{
		return mileage;
	}
	
	public  int getDays()
	{
		return days;
	}
	
	public abstract void calcRent(int days);
	
}