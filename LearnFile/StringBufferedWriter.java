import java.util.*;
import java.io.*;
public class StringBufferedWriter
{
	public static final String SP=File.separator;
	String src="E:"+SP+"javaio"+"ѧϰ����д.txt";

	Writer wt=null;
	Reader rd=null;
	BufferedWriter bw=null;
	BufferedReader br=null;	
	public static void main(String[] args)
	{
		try
		{			
			wt=new FileWriter(src);//д
			rd=new FileReader(src);//��
			//װ��
			bw=new BufferedWriter(wt);
			br=new BufferedReader(rd);
			
			//V1.0ÿ�δ���һ���ַ�
			/*
			wt.write('��');
			wt.write('Ȼ');
			//�ѻ���������д������
			wt.flush();
			
			while(in.ready())
			{
				char[] ch=(char)in.read();
				System.out.println(ch);
			}
			*/
			
			//V2.0ÿ�δ���һ���ַ�����
			//д������
			/*
			String str=""��Ȼ������޹�˾��\r\n;
			char[] chs=str.toCharArray();
			wt.write(chs);
			wt.write("�ܾ���Ϳ�ܡ�\r\n");
			wt.write(97);
			wt.flush();
			
			//��ȡ����
			char[] buf=new char[8];
			int len=0;
			StringBuffer sb=new StringBuffer();
			while((len=in.read(buf))!=-1)
			{
				String content=new String(buf,0,len);
				sb.append(content);
			}
			*/
				
			//3.0�汾 װ��ģʽ ��ǿ���������,���øı丸��ṹ(�̳�)
			String str="����һֻСС��";
			bw.write(str,0,str.length());
			bw.newLine();
			String str2="��ô��Ҳ�ɲ���";
			char[] chs=str2.toCharArray();
			bw.write(chs,o,chs.length);
			bw.flush();			
			StringBuffer lines=new StringBuffer();
			String line=null;
			while(null!=(line=br.readLine()))
			{
				lines.append(line).append("\r\n");
			}
			lines.deleteCharAt(lines.length()-2);
			System.out.println(lines.toString());
			
			//stream����
			/*java.util.stream.Stream<String> stream=br.lines();
			Iterator<String> it=stream.iterator();
			while(it.hasNext())
			{
				System.out.println(it.next());
			}*/
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
			if(bw!=null)
			{
				try
				{
					bw.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
			if(br!=null)
			{
				try
				{
					br.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}	
		}
	}	
}