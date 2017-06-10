import java.io.*;
import java.util.*;
public class TestInputStream
{	
	//全局常量
	public static final String SP=File.separator;
	
	public static void main(String[] args)
	{
		String file="E:"+SP+"javaio"+SP+"chaoxian.txt";
		InputStream is=null;
		OutputStream os=null;
		try
		{
			os=new FileOutputStream(file);
			is= new FileInputStream(file);
			
			//v1.0写入数据
			/*
			os.write(97);
			os.write(97);
			os.write(97);
			int ava=0;
			while((ava=is.available())>0)
			{
				char ch=(char)is.read();
				System.out.println(ch);
			}
			*/
			
			//2.0
			//写入数据
			String str="abcdefghijklmnopqrstuvwxyz";
			byte[] bytes=str.getBytes();//转换成字节,返回字节数组存入
			os.write(bytes);  //将字节（写入）文件   
			
			//读取数据
			byte[] buf=new byte[8]; //定义一个容器,将其再虚拟机端打印出来
			int len=0;
			StringBuffer sb=new StringBuffer();
			
			while(!((len=is.read(buf))==-1))
			{
				//将其转化为String
				String content=new String(buf,0,len);//（转换成String打印出来）
				//添加
				sb.append(content);
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
		finally
		{
			//如果输入不为空，就关闭写入，不然你空的关掉，会报空指针错误！！
			if(null !=is)
			{
				try
				{
					is.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if(null !=os)
			{
				try
				{
					os.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}	
	}
}
/**
*os.write(bytes);  //将字节（写入）文件
*buf数组用来存放文字
*(len=is.read(buf))==-1)//-->当（返回值！=-1，则说明还有东西）is.read()返回的是有len个字节
*os.write(buf);
*src源  dest 目标
*/