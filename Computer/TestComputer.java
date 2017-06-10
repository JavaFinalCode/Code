package day18;
/**
 * ��������ӿڱ��˼����װһ̨�������
 * ���������Ҫ��ɲ����У�
 *CPU
 *Ӳ��
 *�ڴ�
 */
public class TestComputer 
{
	public static void main(String[] args)
	{
		Computer computer = new Computer();
		CPU C = new IntelCPU();
		EMS E = new Memory();
		HDD H = new HardDisk();
		System.out.println("�������������:");
		computer.print(C, H, E);
	}
}
//���������
class Computer 
{
	private CPU C; 
	
	void print(CPU C, HDD H, EMS E)
	{
		System.out.print("CPU��Ʒ����:" + C.getBrand() + "," + "��Ƶ��:" + C.getFreq() + "\n");
		System.out.print("Ӳ������:" + H.getCapacity() + "G" + "\n");
		System.out.print("�ڴ�����:" + E.getCapacity() + "G");
	}
}
//����CPU�ӿ�
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
 //�����ڴ�ӿ�
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
 //����Ӳ�̽ӿ�
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

