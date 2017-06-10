package com.cheer;

import java.io.*;

/*
 *格式模版保存在文本文件E:\IOTest\pettemplate.txt中，内容如下：
 *–您好！
 *–我的名字是{name}，我是一只{type}。
 *–我的主人是{master}。
 *其中{name}、{type}、{master}是需要替换的内容，
 *现在要求按照模板格式保存宠物数据到文本文件
 */
 
public class ChangePet
{
	public static final String SP = File.separator;		
	public static void main(String[] args)
	{
		String scrDir="E:"+SP+"pettemplate.txt";
		String destDir="E:"+SP+"pet.txt";
		Writer out=null;
		Reader in=null;
		
		try
		{
			out=new FileWriter(srcDir);
			
			in=new FileReader(destDir);
			//写入数据
			StringBuffer sb=new StringBuffer();
			sb.append("–您好！\r\n–我的名字是{name}，我是一只{type}。\r\n–我的主人是{master}。");
			String str=sb.toString();
			char[] chs=str.toCharArray();			
			out.write(chs);
			out.flush();
			//读取
			char[] buf=new char[8];
			int len=0;
			StringBuffer sq=new StringBuffer();
			while((len=in.read(buf))!=-1)
			{
				String sc=new String(buf,0,len);
				sq.append(sc);
			}
			
			String newStr = sq.toString().replace("{name}", "小明").replace("{type}", "萨摩").replace("{master}",
					"主人");
			System.out.println(newStr);
			out.write(newStr);
			
		}	
		catch (FileNotFoundException e)
		{
			System.out.println("找不到文件");
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if (null != out)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}

	}

}
