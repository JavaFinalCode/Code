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
			System.out.println("����������һ���ļ�");
		}
		else
		{
			File[] fileLists=file.listFiles();//����һ����Ŀ¼�µ��ļ�������
			for (int i = 0; i < fileLists.length; i++)
			{ 
         	System.out.println(fileLists[i].getName());    //���Ԫ������
         	if (fileLists[i].isDirectory()) //�ж�Ԫ���ǲ���һ��Ŀ¼
         	{    
            	System.out.println(fileLists[i]+"\t");    //�����Ŀ¼���������ñ��������������Ŀ¼
           	}
         }
		}	
	}
}