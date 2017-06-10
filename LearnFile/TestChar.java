import java.io.*;
import java.util.*;
/**
*����ģ�������ļ�
*/
public class TestChar
{
	public static final String SP=File.separator;
	public static final String TEMPLATE_FILE="E:"+SP+"javaio"+SP+"";
	public static final String SAVE_FILE="E:"+SP+"javaio"+SP+"";
	
	//1:�ж��ļ��Ƿ����2:д����Ϣ ͬʱ��ȡ��Դ�ļ���ͨ���÷������س���
	public static String readTemplate()
	{
		Reader in=null;
		Writer out=null;
		StringBuffer sb=new StringBuffer();
		try
		{
			File template=new File(TEMPLATE_FILE);
			if(!TEMPLATE_FILE.exists())
			{
				System.out.println(TEMPLATE_FILE+"������");
				String templateContent="���ã�\r\n�ҵ�������{name}��\r\n����{type}��\r\n�ҵ�������{master}";
				out =new FileWriter(template);
				out.write(templateContent);
				out.flush();
				return templateContent;
			}
			
			in=new Filewriter(template);
			char[] buf=new char[8];
			int len=0;
			while((len=in.read())!=-1)
			{
				sb.append(buf,0,len);
			}
			return sb.toString();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return sb.toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return sb.toString();
		}
		finally
		{
			if(out!=null)
			{
				try
				{
					out.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
			if(in!=null)
			{
				try
				{
					in.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
		}
		
	}
	
	public static void readInfo(Pet pet,String templateContent)
	{
		String newContent=templateContent.replace("{name}",pet.getName()).replace("{type}",pet.getType()).replace("{master}",pet.getMaster());
		writer out=null;
		try
		{
			out=new FileWriter(SAVE_FILE);
			out.write(newContent);
			out.flush();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(out!=null)
			{
				try
				{
					out.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("�ɹ�д�룡");			
		}
	}
	//��ȡpet����ͬʱ��ø��丳ֵ
	public static Pet getPet()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("������������������͡�����");
		String name=sc.next();
		String type=sc.next();
		String master=sc.next();
		sc.close();
		return new Pet(name,type,master);
	}
	
	public static void main(String[] args)
	{
		readInfo(getPet(),readTemplate());
	}
	
}
class Pet
{
	private String name;
	private String type;
	private String master;	
	
	Pet(){}
	
	Pet(String name,String type,String master)
	{
		this.name=name;
		this.type=type;
		this.master=master;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getType()
	{
		return this.type;
	}	
	
	public String getMaster()
	{
		return this.master;
	}
}