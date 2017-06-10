import java.util.*;
import java.io.*;
public class TestFileWriterChar
{
	public static final String SP=File.separator;
	public static void main(String[] args)
	{
		String file="E:"+SP+"javaio"+SP+"昆山.txt";
		Reader in=null;
		Writer out=null;
		
		try
		{
			//首先要把读的放在上面，没读的进行不下去
			in= new FileReader(file);
			out =new FileWriter(file);
			
			
			//1.0
			/*
			//写入数据
			out.write('其');
			out.write('然');
			//把缓存区写入数据
			out.flush();
			//读取数据
			while(in.ready())
			{
				char ch=(char)in.read();
				System.out.println(ch);
			}*/
			
			
			//2.0
			//写入数据（输出）
			String str="其然软件培训服务有限公司.\r\n";
			char[] chs=str.toCharArray();
			out.write(chs);
			out.write("总经理：涂总。\r\n");
			out.write(97);
			
			out.flush();
			//读取数据（输入）
			char[] buf=new char[8];//水瓢，通过其进行添加
			int len=0;
			StringBuffer sb=new StringBuffer();//存放读取的内容
			while((len=in.read(buf))!=-1)
			{
				String con=new String(buf,0,len);
				sb.append(con);
			}
			System.out.println(sb.toString());
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
		//关闭输入流、输出流
		finally
		{
			if(null !=in)
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
			
			if(null !=out)
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
		}
	}
}
