/**
*double类型自动装箱、拆箱还有和String类型进行转化！
*总结：看目标类型是啥？就调用目标包装类型下的方法,大方向写出来(根据图片总结)！
*转换String下面有个toString()方法！
*抛出NumberForMatException(数据格式转换错误)String转到其他类型时！
*/
public class PackageType
{
	public static void main(String[] args)
	{
		//double类型自动装箱(jdk5.0自动装箱)
		double i=0;
		Double j=i;
		System.out.println("double类型自动装箱后为"+j);
		
		//装箱老方式
		double l=0;
		Double m=Double.valueOf(l);
		System.out.println("double类型老方式装箱后为"+m);
		
		//自动拆箱
		Double s=new Double("8");
		double t=s;
		System.out.println("Double类型自动拆箱后为"+t);
		
		//拆箱老方式
		Double o=new Double("8");
		double p=o.doubleValue();
		System.out.println("Double类型老方式装箱后为"+p);
	
		//与String类型转换
		//double--》String
		double w= 8 ;
		String v=String.valueOf(w);
		System.out.println("double转换String后为"+v);
		
		//Double-->String
		Double c=new Double("8");
		String d=String.valueOf(c);
		System.out.println("Double转换String后为"+d);
		
		//String--》double
		String x="1";
		double y=Double.valueOf(x);
		//double y=Double.paseInt(x);
		System.out.println("String转换double后为"+y);
				
		//String--》Double
		String e="1";
		Double f=Double.valueOf(e);
		System.out.println("String转换Double后为"+f);
	}
}