public class TestPhone
{
	OldPhone op=new OldPhone("ŵ����","52cc");
	op.show();
	op.chat();
	op.player();
	
	NewPhone np=new NewPhone("��Ϊ","P108");
	np.show();
	np.chat();
	np.player();
	np.video();
	np.surf();
	np.takePhoto();
	np.watch("small movie");
	
	
}
//�����ֻ���
abstract class Phone
{
	private String brand;
	private String type;
	
	public Phone(){}
	
	public Phone(String brand,String type)
	{
		this.brand=brand;
		this.type=type;
	}
	
	public void show()
	{
		System.out.println("����һ���ͺ�Ϊ"+type+"��"+brand+"�ֻ�");
	}
	public abstract void call();
	public abstract void info();
}
//���ֻ���
class NewPhone extends Phone implements Online,TakePhoto,sVideoChat,Player,Chat,WatchFilm
{
	public NewPhone(String brand,String type)
	{
		super(brand,type);
	}
	@Override
	public void player()
	{
		System.out.println("����������Ϣ����������");
	}
	
	@Override
	public void chat()
	{
		System.out.println("������������");
	}
	
	@Override
	public void surf()
	{
		System.out.println("�Ѿ������ƶ�����......");
	}
	
	@Override
	public void takePhoto()
	{
		System.out.println("���ꡣ�����ճɹ�����������");
	}
	
	@Override
	public void video()
	{
		System.out.println("������Ƶ����");
	}
	
	@Override
	public void watch(String film)
	{
		System.out.println("�ڿ�:"+film);
	}
}
//���ֻ����������ܽӿ�
interface Online
{
	void surf();
}

interface TakePhoto
{
	void takePhoto();
}

interface VideoChat
{
	void video();
}

interface WatchFilm
{
	void watch(String movie);
}
//���ֻ���
class  OldPhone extends Phone implements Player,Chat
{
	public OldPhone(String brand,String type)
	{
		super(brand,type);
	}
	@Override
	public void player()
	{
		System.out.println("����������Ϣ����������");
	}
	
	@Override
	public void chat()
	{
		System.out.println("������������");
	}
}
//���ֻ���2�����ܽӿ�
interface Player
{
	void play();
}

interface Chat
{
	void chat();
}
 