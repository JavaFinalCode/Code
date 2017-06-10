public final class Bus extends MotoVehicle
{
	private int seatCount;
	private final int dayRates1=800;
	private final int dayRates2=1500;

	public Bus(String no,String brand,String color,String mileage,int days,int seatCount)
	{
		super(no,brand,color, mileage,days);
		this.seatCount=seatCount;
	}
	
	@Override
  public void calcRent(int days)
  {
  	if(this.seatCount<=16)
  	{
  		System.out.println("品牌车型"+getBrand()+seatCount+"座");
  		System.out.println("车牌号"+getNo());
  		System.out.println("车身颜色"+getColor());
  		System.out.println("已开公里数"+getMileage());
  		System.out.println("租赁天数"+getDays());
  		System.out.println("您需付款："+dayRates1*days);
  	}
  	else if(this.seatCount>16)
  	{
  		System.out.println("品牌车型"+getBrand()+seatCount+"座");
  		System.out.println("车牌号"+getNo());
  		System.out.println("车身颜色"+getColor());
  		System.out.println("已开公里数"+getMileage());
  		System.out.println("租赁天数"+getDays());
  		System.out.println("您需付款："+dayRates2*days);
  	}
  	
  }
	
	
	
}