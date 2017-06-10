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
  		System.out.println("Ʒ�Ƴ���"+getBrand()+seatCount+"��");
  		System.out.println("���ƺ�"+getNo());
  		System.out.println("������ɫ"+getColor());
  		System.out.println("�ѿ�������"+getMileage());
  		System.out.println("��������"+getDays());
  		System.out.println("���踶�"+dayRates1*days);
  	}
  	else if(this.seatCount>16)
  	{
  		System.out.println("Ʒ�Ƴ���"+getBrand()+seatCount+"��");
  		System.out.println("���ƺ�"+getNo());
  		System.out.println("������ɫ"+getColor());
  		System.out.println("�ѿ�������"+getMileage());
  		System.out.println("��������"+getDays());
  		System.out.println("���踶�"+dayRates2*days);
  	}
  	
  }
	
	
	
}