//解决乱码问题
public class TestCharSet
{
	public static void main(String[] args)
	{
		String str="我是中国人";
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
*给你一个String型的，编码是gb18030
*转换为utf-8
*gb18030--->utf-8
*String str="我是中国人";
*byte[] bytes=str.getBytes();
*String gb18030str=new String(bytes,"gb18030");
*byte[] utf8Bytes=gb18030str.getBytes("utf-8");
*/