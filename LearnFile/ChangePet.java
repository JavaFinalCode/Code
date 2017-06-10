package com.cheer;

import java.io.*;

/*
 *��ʽģ�汣�����ı��ļ�E:\IOTest\pettemplate.txt�У��������£�
 *�C���ã�
 *�C�ҵ�������{name}������һֻ{type}��
 *�C�ҵ�������{master}��
 *����{name}��{type}��{master}����Ҫ�滻�����ݣ�
 *����Ҫ����ģ���ʽ����������ݵ��ı��ļ�
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
			//д������
			StringBuffer sb=new StringBuffer();
			sb.append("�C���ã�\r\n�C�ҵ�������{name}������һֻ{type}��\r\n�C�ҵ�������{master}��");
			String str=sb.toString();
			char[] chs=str.toCharArray();			
			out.write(chs);
			out.flush();
			//��ȡ
			char[] buf=new char[8];
			int len=0;
			StringBuffer sq=new StringBuffer();
			while((len=in.read(buf))!=-1)
			{
				String sc=new String(buf,0,len);
				sq.append(sc);
			}
			
			String newStr = sq.toString().replace("{name}", "С��").replace("{type}", "��Ħ").replace("{master}",
					"����");
			System.out.println(newStr);
			out.write(newStr);
			
		}	
		catch (FileNotFoundException e)
		{
			System.out.println("�Ҳ����ļ�");
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
