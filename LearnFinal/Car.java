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
  		System.out.println("Ʒ�Ƴ���"+getBrand()+type);
  		System.out.println("���ƺ�"+getNo());
  		System.out.println("������ɫ"+getColor());
  		System.out.println("�ѿ�������"+getMileage());
  		System.out.println("��������"+getDays());
  		System.out.println("���踶�"+dayRates1*days);
  	}
  	else if("550i".equals(this.type))
  	{
  		System.out.println("Ʒ�Ƴ���"+getBrand()+type);
  		System.out.println("���ƺ�"+getNo());
  		System.out.println("������ɫ"+getColor());
  		System.out.println("�ѿ�������"+getMileage());
  		System.out.println("��������"+getDays());
  		System.out.println("���踶�"+dayRates2*days);
  	}
  	else if("������".equals(this.type))
  	{
  		System.out.println("Ʒ�Ƴ���"+getBrand()+type);
  		System.out.println("���ƺ�"+getNo());
  		System.out.println("������ɫ"+getColor());
  		System.out.println("�ѿ�������"+getMileage());
  		System.out.println("��������"+getDays());
  		System.out.println("���踶�"+dayRates3*days);
  	}
  }
}