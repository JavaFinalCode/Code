public class ProtectDoor extends Door implements Lock,DoorBell
{
	public void lockUp()
	{
		System.out.println("上锁");
	}
	public void openLock()
	{
		System.out.println("开锁");
	}
	public void openDoor()
	{
		System.out.println("开门");
	}
	public void closeDoor()
	{
		System.out.println("关门");
	}
	public void ringDoor()
	{
		System.out.println("按门铃，拍照！");
	}
}