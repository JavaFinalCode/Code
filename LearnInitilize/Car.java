public class Car
{
	private int site=4;
	
//	Car(){}
	Car()
	{
		System.out.println("载客量是"+site+"人。");
	}
	
	public void setSite(int site)
	{
		this.site=site;
	}
	
	void print()
	{
		System.out.println("载客量是"+site+"人。");
	}
}