/**
*double�����Զ�װ�䡢���仹�к�String���ͽ���ת����
*�ܽ᣺��Ŀ��������ɶ���͵���Ŀ���װ�����µķ���,����д����(����ͼƬ�ܽ�)��
*ת��String�����и�toString()������
*�׳�NumberForMatException(���ݸ�ʽת������)Stringת����������ʱ��
*/
public class PackageType
{
	public static void main(String[] args)
	{
		//double�����Զ�װ��(jdk5.0�Զ�װ��)
		double i=0;
		Double j=i;
		System.out.println("double�����Զ�װ���Ϊ"+j);
		
		//װ���Ϸ�ʽ
		double l=0;
		Double m=Double.valueOf(l);
		System.out.println("double�����Ϸ�ʽװ���Ϊ"+m);
		
		//�Զ�����
		Double s=new Double("8");
		double t=s;
		System.out.println("Double�����Զ������Ϊ"+t);
		
		//�����Ϸ�ʽ
		Double o=new Double("8");
		double p=o.doubleValue();
		System.out.println("Double�����Ϸ�ʽװ���Ϊ"+p);
	
		//��String����ת��
		//double--��String
		double w= 8 ;
		String v=String.valueOf(w);
		System.out.println("doubleת��String��Ϊ"+v);
		
		//Double-->String
		Double c=new Double("8");
		String d=String.valueOf(c);
		System.out.println("Doubleת��String��Ϊ"+d);
		
		//String--��double
		String x="1";
		double y=Double.valueOf(x);
		//double y=Double.paseInt(x);
		System.out.println("Stringת��double��Ϊ"+y);
				
		//String--��Double
		String e="1";
		Double f=Double.valueOf(e);
		System.out.println("Stringת��Double��Ϊ"+f);
	}
}