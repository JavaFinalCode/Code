public class TestPhone
{
	OldPhone op=new OldPhone("诺基亚","52cc");
	op.show();
	op.chat();
	op.player();
	
	NewPhone np=new NewPhone("华为","P108");
	np.show();
	np.chat();
	np.player();
	np.video();
	np.surf();
	np.takePhoto();
	np.watch("small movie");
	
	
}
//抽象手机类
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
		System.out.println("这是一部型号为"+type+"的"+brand+"手机");
	}
	public abstract void call();
	public abstract void info();
}
//新手机类
class NewPhone extends Phone implements Online,TakePhoto,sVideoChat,Player,Chat,WatchFilm
{
	public NewPhone(String brand,String type)
	{
		super(brand,type);
	}
	@Override
	public void player()
	{
		System.out.println("发送文字信息。。。。。");
	}
	
	@Override
	public void chat()
	{
		System.out.println("开启语音聊天");
	}
	
	@Override
	public void surf()
	{
		System.out.println("已经启动移动网络......");
	}
	
	@Override
	public void takePhoto()
	{
		System.out.println("咔嚓。。拍照成功。。。。。");
	}
	
	@Override
	public void video()
	{
		System.out.println("开启视频聊天");
	}
	
	@Override
	public void watch(String film)
	{
		System.out.println("在看:"+film);
	}
}
//新手机的三个功能接口
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
//老手机类
class  OldPhone extends Phone implements Player,Chat
{
	public OldPhone(String brand,String type)
	{
		super(brand,type);
	}
	@Override
	public void player()
	{
		System.out.println("发送文字信息。。。。。");
	}
	
	@Override
	public void chat()
	{
		System.out.println("开启语音聊天");
	}
}
//老手机的2个功能接口
interface Player
{
	void play();
}

interface Chat
{
	void chat();
}
 