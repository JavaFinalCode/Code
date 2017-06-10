import java.io.*;

public class TestSerializable
{
	public static final String SP=File.separator;
		
	public static void main(String[] args)
	{
		String file="E:"+SP+"ʲô��.ser";
		Student stu=new Student("���ǳ�",9527,"����","946");
//		writeObject(stu,file);
		Student stu1=(Student)readObject(file);
		System.out.println(stu1);
	}
	public static void writeObject(Student stu,String file)
	{	
		OutputStream os=null;
		ObjectOutputStream oos=null;	
		try
		{
			os=new FileOutputStream(file);			
			oos=new ObjectOutputStream(os); 
			oos.writeObject(stu);
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
			if(oos!=null)
			{
				try
				{					
					oos.close();				
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Object readObject(String file)
	{
		InputStream is=null;
		ObjectInputStream ois=null;
		try
		{		
			is =new FileInputStream(file);			
			ois=new ObjectInputStream(is);
			return ois.readObject();
		}
		catch(InvalidClassException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(ois!=null)
			{
				try
				{					
					ois.close();				
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
*transient:�Ǿ�̬���ݲ��뱻���л�����ʹ������ؼ������Ρ� 
*/

class Student implements Serializable
{
	private String name;
	private String score;
	private int no;
	private transient String password;
	
	Student(){}
	
	Student(String name,int no,String score,String password)
	{
		this.name=name;
		this.no=no;
		this.score=score;
		this.password=password;
	}
	
	public String getName()
	{
		return  this.name;
	}
	public  int getNo()
	{
		return this.no;	
	}
	
	public String getScore()
	{
		return this.score;
	}
	
	public String getPassword()
	{
		return this.password;	
	}
	
	@Override
	public String toString()
	{
		return "name:"+name+"\t"+"no:"+no+"\t"+"score:"+score+"\t"+"password:"+password;
	}
}