import java.io.File;
import java.io.IOException;
public class TestFile
{
	public static void main(String[] args)throws IOException
	{
		//String���͵��ļ���
		final String pathName="E:"+File.separator+"javaio"+File.separator;
		final String fileName=pathName+"hello.java";
		//������ӵ�new File()��
		File path=new File(pathName);
		//������ļ�������
		if(!path.exists())
		{
			 //����mkdir()���������ļ���
			if(path.mkdirs())
			{
				System.out.println("creat path successfully");
			}
			else
			{
				System.out.println("Creatpath failed");
			}
			
			
			File file=new File(fileName);
			//������ļ�������
			if(!file.exists())
			{
				//�����ļ�
				file.createNewFile();
			}
			System.out.println(file);
			
			//����C��
			File diskC=new File("C:\\");
			for(String str:diskC.list())			
			{
				System.out.println(str);
			}
		}
	}
}