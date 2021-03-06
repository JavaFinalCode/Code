public class LearnEnum
{
	/*
	 * public static final int MONDAY=1;
	 * 
	 * public static final int TUESDAY=2;
	 * 
	 * public static final int WEDNESDAY=3;
	 * 
	 * public static final int THURDAY=4;
	 * 
	 * public static final int FRIDAY=5;
	 * 
	 * public static final int SATURDAY=6;
	 * 
	 * public static final int SUNDAY=7;
	 */

	public static void main(String[] args)
	{
		// System.out.println(Day.SUNDAY.getDesc());
		print(Day.SUNDAY);

		/*
		switch(day)
		{
			case 1 : {
						System.out.println("星期一");
						break;
					 }
			case 2 : {
						System.out.println("星期二");
						break;
					 }							 
			case 3 : {
						System.out.println("星期三");
						break;
					 }
			case 4 : {
						System.out.println("星期四");
						break;
					 }							 
			case 5 : {
						System.out.println("星期五");
						break;
					 }							 
			case 6 : {
						System.out.println("星期六");
						break;
					 }
			case 7 : {
						System.out.println("星期日");
						break;
					 }							 							 			
		}*/

	}

	public static void print(Day day)
	{
		System.out.println(day);
	}

}

enum Day
{
	MONDAY(1, "星期一"), TUESDAY(2, "星期二"), WEDNESDAY(3, "星期三"), THURDAY(4, "星期四"), FRIDAY(5, "星期五"), SATURDAY(6,
			"星期六"), SUNDAY(7, "星期日");

	private int val;
	private String desc;

	Day(int val)
	{
		this.val = val;
	}

	Day(int val, String desc)
	{
		this.val = val;
		this.desc = desc;
	}

	public int getVal()
	{
		return this.val;
	}

	public String getDesc()
	{
		return this.desc;
	}

	@Override
	public String toString()
	{
		return this.desc;
	}

}