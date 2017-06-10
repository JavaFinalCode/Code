import java.io.*;
import java.util.*;
public class TestInputStream
{	
	//ȫ�ֳ���
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
			
			//v1.0д������
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
			//д������
			String str="abcdefghijklmnopqrstuvwxyz";
			byte[] bytes=str.getBytes();//ת�����ֽ�,�����ֽ��������
			os.write(bytes);  //���ֽڣ�д�룩�ļ�   
			
			//��ȡ����
			byte[] buf=new byte[8]; //����һ������,������������˴�ӡ����
			int len=0;
			StringBuffer sb=new StringBuffer();
			
			while(!((len=is.read(buf))==-1))
			{
				//����ת��ΪString
				String content=new String(buf,0,len);//��ת����String��ӡ������
				//���
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
			//������벻Ϊ�գ��͹ر�д�룬��Ȼ��յĹص����ᱨ��ָ����󣡣�
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
*os.write(bytes);  //���ֽڣ�д�룩�ļ�
*buf���������������
*(len=is.read(buf))==-1)//-->��������ֵ��=-1����˵�����ж�����is.read()���ص�����len���ֽ�
*os.write(buf);
*srcԴ  dest Ŀ��
*/