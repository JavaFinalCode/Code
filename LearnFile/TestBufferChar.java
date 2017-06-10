import java.io.*;
import java.util.*;
/**
*根据模板生成文件
*/
public class TestBufferChar
{
	public static final String SP=File.separator;
	public static final String TEMPLATE_FILE="E:"+SP+"javaio"+SP+"TEMPLATE.txt";
	public static final String SAVE_FILE="E:"+SP+"javaio"+SP+"pet.txt";
	BufferedWriter bw=null;
	BufferedReader br=null;
	br=new BufferedReader(in);//读的文件
	
	//1:判断文件是否存在2:写入信息 同时读取到源文件并通过该方法返回出来
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
				System.out.println(TEMPLATE_FILE+"不存在");
				String templateContent="您好！\r\n我的名字是{name}，\r\n我是{type}，\r\n我的主任是{master}";
				out =new FileWriter(template);
				bw=new BufferedWriter(out);//写的文件地
				bw.write(templateContent,0,templateContent.length());
				out.flush();
				return templateContent;
			}
			
			in=new FileReader(template);
			br=new BufferedReader(in);//读的文件			
			String line=null;
			while(null!=(line=br.readLine()))
			{
				sb.append(line).append("\r\n");
			}
			sb.deleteCharAt(sb.length()-2);
			return sb.toString();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				try
				{
					bw.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
			if(br!=null)
			{
				try
				{
					br.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
		}
		
	}
	//主方法里面调这方法时，调其他方法作为形参！
	public static void readInfo(Pet pet,String templateContent)
	{
		String newContent=templateContent.replace("{name}",pet.getName()).replace("{type}",pet.getType()).replace("{master}",pet.getMaster());
		Writer out=null;
		try
		{
			out=new FileWriter(SAVE_FILE);
			bw=new BufferedWriter(out);//读的文件
			bw.write(newContent,0,newContent.length());
			bw.flush();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				try
				{
					bw.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("成功写入！");			
		}
	}
	//获取pet对象同时获得给其赋值
	public static Pet getPet()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入宠物姓名、类型、主人");
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