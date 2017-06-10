import java.util.*;
import java.io.*;
public class ForeachD
{
//	public static final String SP=File.separator;
	
	public static void main(String[] args)
	{
		
		File file=new File("E:\\");
		if(file.isFile())
		{
			System.out.println("您给定的是一个文件");
		}
		else
		{
			File[] fileLists=file.listFiles();//返回一个该目录下的文件名数组
			for (int i = 0; i < fileLists.length; i++)
			{ 
         	System.out.println(fileLists[i].getName());    //输出元素名称
         	if (fileLists[i].isDirectory()) //判断元素是不是一个目录
         	{    
            	System.out.println(fileLists[i]+"\t");    //如果是目录，继续调用本方法来输出其子目录
           	}
         }
		}	
	}
}