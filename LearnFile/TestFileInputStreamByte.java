import java.util.*;
import java.io.*;
public class TestFileInputStreamByte
{
	public static final String SP=File.separator;
	public static boolean check(String fileName,String srcDir,String destDir)throws IOException
	{
		File src=new File(srcDir+fileName);
		File dest=new File(destDir);
		if(!src.exists())
		{
			System.out.println("源文件不存在,赋值失败!");
			return false;
		}
		if(!src.isFile())
		{
			System.out.println("源文件不合法,赋值失败!");
			return false;
		}
		if(!dest.exists())
		{
			System.out.println("目标路径不存在,赋值失败!");
			return false;
		}
		return true;
	}
	
	public static void copyOf(String fileName,String srcDir,String destDir)
	{
		InputStream is=null;
		OutputStream os=null;
		try
		{	
			if(check(fileName,srcDir,destDir))
			{		
				is=new FileWriter(srcDir+fileName);
				os=new FileReader(destDir+fileName);
				
				
				//复制
				byte[] words=new byte[8]; 
				int len=0;			
				while(!((len=in.read(words))==-1))
				{
					out.write(words,0,len);
				} 
				String str="我爱你中国";
				os.write(str.getBytes("UTF-8"));
				
			}				
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
	
	public static void main(String[] args)
	{
		String fileName="我的青春我做主.txt";
		String srcDir="E:"+SP+"javaio"+SP;
		String destDir="E:"+SP+"javaio"+SP+"File"+SP;
		copyOf(fileName,srcDir,destDir);
	}
}