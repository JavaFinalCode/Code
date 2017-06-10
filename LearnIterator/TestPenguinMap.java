import java.util.*;
public class TestPenguinMap
{
	public static void main(String[] args)
	{
		Map penguins=new HashMap();
		
		Penguin p1=new Penguin("QÃÃ");
		Penguin p2=new Penguin("QÃÃ");
		Penguin p3=new Penguin("Q×Ğ");
		Penguin p4=new Penguin("QÃÃ");
		
		
		penguins.put("ÑÇÑÇ",p1);
		penguins.put("·Æ·Æ",p2);
		penguins.put("Å·Å·",p3);
		penguins.put("ÃÀÃÀ",p4);
		
		Set keys=penguins.keySet();
		Iterator it=keys.iterator();
		while(it.hasNext())
		{
			String key=(String)it.next();
			Penguin pengu=(Penguin)penguins.get(key);
			System.out.println(key+".."+pengu);
		}
	}
}

class Penguin
{
	private String sex;
	
	Penguin(){}
	
	Penguin(String Sex)
	{
		this.sex=sex;
	}
	
	public String toString()
	{
		return "\t"+this.sex;
	}
	
}