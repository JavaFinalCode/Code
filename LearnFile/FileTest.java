public class FileTest
{
	public static void main(String[] args)
	{
		
		final String pathName="E:"+File.separator+"javaio"+File.separator;
		final String filName=pathName+"hello world";
	
		File path =new File(pathName);
		if(!path.exists())
		{
			if(path.mkdirs())
			{
				System.out.println("Creat path Successfully!");
			}
			else
			{
				System.out.println("Creat Failed!");
			}
		}
		
		File file =new File(fileName);
		
		if(!file.exists())
		{
			file.creatNewFile();
		}
		System.out.println(file);
		
		file diskc= new file("c:\\");
		
		for(String )
		{
			System.out.println();
		}
	}
}