public class CustomerService
{
	//�������
	String[] name=new String[10];
	//������ݵ������Լ�������ֵĸ����ˣ�
	int index=0;
	//�������
	public void addName(String name)
	{
		name[index]=name;
		index++;
	}
	//�г���������
	public void listName()
	{
		for(int i=0;i<index;i++)
		{
			System.out.println(name[i]+"\t");
		}
	}
	
	//��ſͻ�������
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
	//�鿴�����Ƿ����
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