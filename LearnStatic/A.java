/**
*实例方法相对于静态方法（或者叫类方法）而言，它就是没有 static 前缀的一类一般方法，被对象拥有（这也是称之为“实例”方法的原因）。
*特点是定义的时候前面没有 static 前缀，本类中直接调用的时候必须也在实例方法内，否则调用前必须先实例出一个对象。
*
*/
class A
{
	public void instanceMethod () 
	{
		System.out.println("这是一个实例方法。");
   }
	public static void staticMethod ()	
	{
   	System.out.println("这是一个静态方法。");
      //instanceMethod(); 这样是错的      
      new A().instanceMethod();        //只有这样才对。
   }
}
class B 
{
	public void callInstanceMethod ()
	{
		new A().instanceMethod();        //调用一个 A 实例的实例方法。
		A.staticMethod();        //调用一个 A 的静态方法。
   }
}