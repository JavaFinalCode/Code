public class CustomerService
{
	//存放姓名
	String[] name=new String[10];
	//存放数据的索引以及存放名字的个数了；
	int index=0;
	//添加姓名
	public void addName(String name)
	{
		name[index]=name;
		index++;
	}
	//列出所有名字
	public void listName()
	{
		for(int i=0;i<index;i++)
		{
			System.out.println(name[i]+"\t");
		}
	}
	
	//存放客户的名字
	public boolean modifyName(String oldName,String newName)
	{
		for(int i=0;i<index;i++)
		{
			if(oldName.equals(name[i]))
			{
				name[i]=newName;
				return true;
			}
		}
		return false;
	}
	//查看名字是否存在
	public boolean isExist(String name)
	{
		for(int i=0;i<index;i++)
		{
			if(oldName.equals(name[i]))
			{
				return true;
			}	
		}
		return false;	
	}
}