import java.util.*;
import java.io.*;
/**
* �����ļ���D:\�ҵ��ഺ˭����.txt����������������
* ����C:\myFile��Ŀ¼��
* ����������FileInputStream���󣬸����D:\�ҵ��ഺ˭����.txt�ļ��Ķ�ȡ��
* ���������FileOutputStream���󣬸����ļ�����д�뵽C:\myFile\myPrime.txt�С�
* ������תվ����words�����ÿ�ζ�ȡ�����ݡ�
* ͨ��ѭ��ʵ���ļ���д��
* �ر����������������
*/
public class CopyMyFile
{
	public static final String SP=File.separator;
	public static void main(String[] args)throws IOException
	{
		final String pathName="C:"+SP+"myFile"+SP;
		final String fileName="D:"+SP+"�ҵ��ഺ������.txt";
		final String fileName1=pathName+"myPrime.txt";
		File path=new File(pathName);
		File file=new File(fileName);
		File file1=new File(fileName1);
		InputStream is=null;
		OutputStream os=null;
		Scanner sc=null;
		if(!path.exists()||!file.exists()||!file1.exists())
		{
			//�����ļ���D:\�ҵ��ഺ˭����.txt����������������
			path.mkdirs();
			// ����C:\myFile���ļ���
			file.createNewFile();
			//����C:\myFile\myPrime.txt���ļ��С�
			file1.createNewFile();		
		}
		try
		{			
			os=new FileOutputStream(file);//�ҵ��ഺ������.txt
			is=new FileInputStream(file1);//myPrime.txt
			System.out.println("��������Ҫд������ݣ�");
			sc=new Scanner(System.in);
			String str=sc.next();
			byte[] bytes=str.getBytes();
			os.write(bytes);//(��ʱ�Ѿ���ҪЮ�Ķ���д����)
			
			
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
		//�ر��������������
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
