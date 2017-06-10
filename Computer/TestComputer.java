package day18;
/**
 * 采用面向接口编程思想组装一台计算机。
 * 计算机的主要组成部分有：
 *CPU
 *硬盘
 *内存
 */
public class TestComputer 
{
	public static void main(String[] args)
	{
		Computer computer = new Computer();
		CPU C = new IntelCPU();
		EMS E = new Memory();
		HDD H = new HardDisk();
		System.out.println("计算机配置如下:");
		computer.print(C, H, E);
	}
}
//定义电脑类
class Computer 
{
	private CPU C; 
	
	void print(CPU C, HDD H, EMS E)
	{
		System.out.print("CPU的品牌是:" + C.getBrand() + "," + "主频是:" + C.getFreq() + "\n");
		System.out.print("硬盘容量:" + H.getCapacity() + "G" + "\n");
		System.out.print("内存容量:" + E.getCapacity() + "G");
	}
}
//定义CPU接口
interface CPU 
{
	public String getBrand();

	public String getFreq();
}
class IntelCPU implements CPU  
{
	public String getBrand()
	{
		return "core";
	}

	public String getFreq()
	{
		return "i5";
	}
}
 //定义内存接口
interface EMS
{
	public String getCapacity();
}

class Memory implements EMS
{
	public String getCapacity()
	{
		return "6";
	}
}
 //定义硬盘接口
interface HDD 
{
	public String getCapacity();
}

class HardDisk implements HDD
{
	public String getCapacity()
	{
		return "500";
	}
}

