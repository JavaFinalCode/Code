//�����������
public class TestCharSet
{
	public static void main(String[] args)
	{
		String str="�����й���";
		byte[] bytes=str.getBytes();
		for(byte b:bytes)
		{
			System.out.println(b);
		}
		System.out.println("*****************************");
		bytes =str.getBytes("gb18030");
		
		for(byte b:bytes)
		{
			System.out.println(b);
		}
		System.out.println("*****************************");
		
		//gb18030--->utf-8
		String gb18030str=new String(bytes,"gb18030");
		byte[] utf8Bytes=gb18030str.getBytes("utf-8");
	}	
}

/*
*����һ��String�͵ģ�������gb18030
*ת��Ϊutf-8
*gb18030--->utf-8
*String str="�����й���";
*byte[] bytes=str.getBytes();
*String gb18030str=new String(bytes,"gb18030");
*byte[] utf8Bytes=gb18030str.getBytes("utf-8");
*/