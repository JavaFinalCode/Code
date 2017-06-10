import static Contants.*;

public abstract class MotoVehile
{
	private int rent;
	private String brand;
	public MotoVehile(){}
	
	public MotoVeile (String brand)
	{
		this.brand =brand ;
	}
	
	public setRent(int rent)
	{
		this.rent=rent;
	}
	public int getRent()
	{
		return this.rent;	
	}
	public abstract int calcRent(int days);
}


public class Car extends MotoVehile
{
	private int type;
	
	public Car(){}
	
	public Car(int type,String brand)
	{
		super.(brand);	
		this.type=type;
	}	
	
	@Override
	public int calcRent(int  days)
	{
		switch(this.type)
		{
			case TYPE_BUOCK_GLE:this.setRent(PRICE_BUICK_GLE*days);
										break;
			case TYPE_BMW_550I:this.setRent(PRICE_BMW_550I*days);
										break;
			case TYPE_BUICK_PARK_AVENUE:this.setRent(TYPE_BUICK_PARK_AVENUE*days);
										break;
			default:this.setRent(-1);
			
		}
		return this.getRent;
	}
}


class Bus extends MotoVehile
{
	private int seat;
	
	public Bus (){}
	
	public Bus(int seat,String brand)
	{
		super(brand);
		this.seat=seat;	
	}	
	
	@Override
	public int calcRent(int days)
	{
		if(this.seat>SEAT_VALUE)
		{
			this.seatRent(PRICE_GT_16*days);
			return this.getRent();
		}
		else
		{
			this.setRent(PRICE_LT_16*days);
			return this.getRent();	
		}
	}
}

public class Truck extends MotoVehile
{
	private int ton;
	public Truck(){}
	
	public Truck(int ton,String brand)
	{
		super(brand);
		this.ton=ton;			
	}	
	
	@Override
	public int calcRent(int days)
	{
		this.setRent(ton*days*TRUCK_PRICE);
		return this.getRent();	
	}
}

public class RentHelper
{
	public static void calcTotalRent(int days,MotoVehile...motos)
	{
		int totalRent=0;
		for(MotoVehile moto:motos)
		{
			totalRent+=moto.calcRent(days);
		}
		
		System.out.println("总租金："+toatlRent);
	}	
}

public class TestRent
{
	public static void main(String args)
	{
		MotoVehile moto0=new Car(TYPE_BMW_550I,CAR_BRAND_BMW);
		MotoVehile moto1=new Car(TYPE_BUICK_PARK_AVENUE,CAR_BRAND_BUICK);	
		MotoVehile moto2=new Bus(30,BUS_BRAND_JINLONG);
		MotoVehile moto3=new Bus(10,BUS_BRAND_JINBEI);
		MotoVehile moto4=new Truck(20,TRUCK_BRAND_DONGFENG);
		MotoVehile moto5=new Truck(40,TRUCK_BRAND_JIEFANG);
		RentHelper.calcTotalRent(7,moto0,moto1,moto2,moto3,moto4,moto5);
	}	
}

public final class constants
{
	public static final int TYPE_BUICK_GLE=1;
	public static final int TYPE_BMW_550I=2;
	public static final int TYPE_BUICK_PARK_AVENUE=3;
	public static final int PRICE_BUICK_GLE=600;
	public static final int PRICE_BMW_550I=500;
	public static final int PRICE_BUICK_PARK_AVENUE=300;
	public static final String CAR_BRAND_BUICK="别克";
	public static final String CAR_BRAND_BMW="宝马";
	public static final int PRICE_GT_16=1800;
	public static final int PRICE_LT_16=1500;
	public static final int SEAT_VALUE=16;
	public static final String BUS_BRAND_JINLONG="金龙";
	public static final String BUS_BRAND_JINBEI="金杯";
	public static final String TRUCK_BRAND_DONGFENG="东风";
	public static final String TrUCK_BRAND_JIEFANG="解放";
}
