/**
*ʵ����������ھ�̬���������߽��෽�������ԣ�������û�� static ǰ׺��һ��һ�㷽����������ӵ�У���Ҳ�ǳ�֮Ϊ��ʵ����������ԭ�򣩡�
*�ص��Ƕ����ʱ��ǰ��û�� static ǰ׺��������ֱ�ӵ��õ�ʱ�����Ҳ��ʵ�������ڣ��������ǰ������ʵ����һ������
*
*/
class A
{
	public void instanceMethod () 
	{
		System.out.println("����һ��ʵ��������");
   }
	public static void staticMethod ()	
	{
   	System.out.println("����һ����̬������");
      //instanceMethod(); �����Ǵ��      
      new A().instanceMethod();        //ֻ�������Ŷԡ�
   }
}
class B 
{
	public void callInstanceMethod ()
	{
		new A().instanceMethod();        //����һ�� A ʵ����ʵ��������
		A.staticMethod();        //����һ�� A �ľ�̬������
   }
}