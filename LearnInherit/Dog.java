public class Dog extends Pet 
{
	//���Լ�˽�е�Ʒ��
	private String strain;
	
	public String getStrain()
	{
		return this.strain;
	}
	
	public Dog(){}
	// ����������
	public Dog(String name,int health,int love,String strain)
	{
		//���ø��๹���������еĲ���
		super(name,health,love);
		this.strain=strain;
	}
	//��д���෽�������ø��෽��ͬʱ�����Լ�˽�е����
	@Override
	public void print()
	{
		super.print();
		System.out.print("\t���ࣺ"+strain);	
	}	
}
