import java.io.*;
import java.util.*;
public class CopyBinaryFile
{
	public static final String SP=File.separator;
	public static void main(string[] args)
	{
		String fileName="Centre.txt";
		String srcDir="E:"+SP+"javaio"+SP;
		String destDir="E:"+SP+"javaiox"+SP;
		System.out.println(new Date());
		copy(fileName,srcDir,destDir);
		System.out.println(new Date());
	}
	
	public static void copy(String fileName,String srcDir,String destDir)
	{
		InputStream is=null;
		OutputStream os=null;
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		try
		{
			//V3.0极速版 拷贝
			//涉及模式中的装饰模式
			is=new InputStream(srcDir+fileName);
			bis=new BufferedInputStream(is);
			os=new OutputStream(destDir+fileName);
			bos=new BufferedOutputStream(os);
			
			byte[] bytes=new byte[3072];
			int len=0;
			while((len=bis.read(bytes,o.buf.length))!=-1)
			{
				bos.write(bytes,0,len);
			}
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
			if(bos!=null)
			{
				try
				{
					bos.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
			if(bis!=null)
			{
				try
				{
					bis.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
		}
	}
}