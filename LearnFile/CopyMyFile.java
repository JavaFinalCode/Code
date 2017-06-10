import java.util.*;
import java.io.*;
/**
* 创建文件“D:\我的青春谁做主.txt”并自行输入内容
* 创建C:\myFile的目录。
* 创建输入流FileInputStream对象，负责对D:\我的青春谁做主.txt文件的读取。
* 创建输出流FileOutputStream对象，负责将文件内容写入到C:\myFile\myPrime.txt中。
* 创建中转站数组words，存放每次读取的内容。
* 通过循环实现文件读写。
* 关闭输入流、输出流。
*/
public class CopyMyFile
{
	public static final String SP=File.separator;
	public static void main(String[] args)throws IOException
	{
		final String pathName="C:"+SP+"myFile"+SP;
		final String fileName="D:"+SP+"我的青春我做主.txt";
		final String fileName1=pathName+"myPrime.txt";
		File path=new File(pathName);
		File file=new File(fileName);
		File file1=new File(fileName1);
		InputStream is=null;
		OutputStream os=null;
		Scanner sc=null;
		if(!path.exists()||!file.exists()||!file1.exists())
		{
			//创建文件“D:\我的青春谁做主.txt”并自行输入内容
			path.mkdirs();
			// 创建C:\myFile的文件。
			file.createNewFile();
			//创建C:\myFile\myPrime.txt的文件夹。
			file1.createNewFile();		
		}
		try
		{			
			os=new FileOutputStream(file);//我的青春我做主.txt
			is=new FileInputStream(file1);//myPrime.txt
			System.out.println("请输入你要写入的内容：");
			sc=new Scanner(System.in);
			String str=sc.next();
			byte[] bytes=str.getBytes();
			os.write(bytes);//(此时已经将要挟的东西写好了)
			
			
			byte[] words=new byte[8]; 
			int len=0;
			StringBuffer sb=new StringBuffer();
	
			while(!((len=is.read(words))==-1))
			{
				String content=new String(words,0,len);
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
		//关闭输入流、输出流
		finally
		{
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
