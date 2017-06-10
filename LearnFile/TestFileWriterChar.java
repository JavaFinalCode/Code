import java.util.*;
import java.io.*;
public class TestFileWriterChar
{
	public static final String SP=File.separator;
	public static void main(String[] args)
	{
		String file="E:"+SP+"javaio"+SP+"��ɽ.txt";
		Reader in=null;
		Writer out=null;
		
		try
		{
			//����Ҫ�Ѷ��ķ������棬û���Ľ��в���ȥ
			in= new FileReader(file);
			out =new FileWriter(file);
			
			
			//1.0
			/*
			//д������
			out.write('��');
			out.write('Ȼ');
			//�ѻ�����д������
			out.flush();
			//��ȡ����
			while(in.ready())
			{
				char ch=(char)in.read();
				System.out.println(ch);
			}*/
			
			
			//2.0
			//д�����ݣ������
			String str="��Ȼ�����ѵ�������޹�˾.\r\n";
			char[] chs=str.toCharArray();
			out.write(chs);
			out.write("�ܾ���Ϳ�ܡ�\r\n");
			out.write(97);
			
			out.flush();
			//��ȡ���ݣ����룩
			char[] buf=new char[8];//ˮư��ͨ����������
			int len=0;
			StringBuffer sb=new StringBuffer();//��Ŷ�ȡ������
			while((len=in.read(buf))!=-1)
			{
				String con=new String(buf,0,len);
				sb.append(con);
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
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//�ر��������������
		finally
		{
			if(null !=in)
			{
				try
				{
					in.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if(null !=out)
			{
				try
				{
					out.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
