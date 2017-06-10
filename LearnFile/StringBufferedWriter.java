import java.util.*;
import java.io.*;
public class StringBufferedWriter
{
	public static final String SP=File.separator;
	String src="E:"+SP+"javaio"+"学习超级写.txt";

	Writer wt=null;
	Reader rd=null;
	BufferedWriter bw=null;
	BufferedReader br=null;	
	public static void main(String[] args)
	{
		try
		{			
			wt=new FileWriter(src);//写
			rd=new FileReader(src);//读
			//装饰
			bw=new BufferedWriter(wt);
			br=new BufferedReader(rd);
			
			//V1.0每次处理一个字符
			/*
			wt.write('其');
			wt.write('然');
			//把缓存区数据写到磁盘
			wt.flush();
			
			while(in.ready())
			{
				char[] ch=(char)in.read();
				System.out.println(ch);
			}
			*/
			
			//V2.0每次处理一个字符数组
			//写入数据
			/*
			String str=""其然软件有限公司。\r\n;
			char[] chs=str.toCharArray();
			wt.write(chs);
			wt.write("总经理：涂总。\r\n");
			wt.write(97);
			wt.flush();
			
			//读取数据
			char[] buf=new char[8];
			int len=0;
			StringBuffer sb=new StringBuffer();
			while((len=in.read(buf))!=-1)
			{
				String content=new String(buf,0,len);
				sb.append(content);
			}
			*/
				
			//3.0版本 装饰模式 增强父类对象功能,不用改变父类结构(继承)
			String str="我是一只小小鸟";
			bw.write(str,0,str.length());
			bw.newLine();
			String str2="怎么飞也飞不高";
			char[] chs=str2.toCharArray();
			bw.write(chs,o,chs.length);
			bw.flush();			
			StringBuffer lines=new StringBuffer();
			String line=null;
			while(null!=(line=br.readLine()))
			{
				lines.append(line).append("\r\n");
			}
			lines.deleteCharAt(lines.length()-2);
			System.out.println(lines.toString());
			
			//stream方法
			/*java.util.stream.Stream<String> stream=br.lines();
			Iterator<String> it=stream.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}*/
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
}