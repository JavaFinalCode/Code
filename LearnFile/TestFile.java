import java.io.File;
import java.io.IOException;
public class TestFile
{
	public static void main(String[] args)throws IOException
	{
		//String类型的文件名
		final String pathName="E:"+File.separator+"javaio"+File.separator;
		final String fileName=pathName+"hello.java";
		//将其添加到new File()中
		File path=new File(pathName);
		//如果此文件不存在
		if(!path.exists())
		{
			 //调用mkdir()方法创建文件夹
			if(path.mkdirs())
			{
				System.out.println("creat path successfully");
			}
			else
			{
				System.out.println("Creatpath failed");
			}
			
			
			File file=new File(fileName);
			//如果此文件不存在
			if(!file.exists())
			{
				//创建文件
				file.createNewFile();
			}
			System.out.println(file);
			
			//遍历C盘
			File diskC=new File("C:\\");
			for(String str:diskC.list())			
			{
				System.out.println(str);
			}
		}
	}
}