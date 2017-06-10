public final class Car extends MotoVehicle
{
	private String type;
	private final int dayRates1=600;
	private final int dayRates2=500;
	private final int dayRates3=300;
	
	
	public Car(String no,String brand,String color,String mileage,int days,String type)
	{
		super(no,brand,color, mileage,days);
		this.type=type;
	}
	
	@Override
  public void calcRent(int days)
  {
  	if("GL8".equals(this.type))
  	{
  		System.out.println("品牌车型"+getBrand()+type);
  		System.out.println("车牌号"+getNo());
  		System.out.println("车身颜色"+getColor());
  		System.out.println("已开公里数"+getMileage());
  		System.out.println("租赁天数"+getDays());
  		System.out.println("您需付款："+dayRates1*days);
  	}
  	else if("550i".equals(this.type))
  	{
  		System.out.println("品牌车型"+getBrand()+type);
  		System.out.println("车牌号"+getNo());
  		System.out.println("车身颜色"+getColor());
  		System.out.println("已开公里数"+getMileage());
  		System.out.println("租赁天数"+getDays());
  		System.out.println("您需付款："+dayRates2*days);
  	}
  	else if("林荫大道".equals(this.type))
  	{
  		System.out.println("品牌车型"+getBrand()+type);
  		System.out.println("车牌号"+getNo());
  		System.out.println("车身颜色"+getColor());
  		System.out.println("已开公里数"+getMileage());
  		System.out.println("租赁天数"+getDays());
  		System.out.println("您需付款："+dayRates3*days);
  	}
  }
}