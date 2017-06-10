public class TestPrinter
{
	public static void main(String[] args)
	{
	    InkBox inkBox = null;//ī������
	    Paper paper = null;  //ֽ������
	    Printer printer = new Printer();
	 
	    //2��ʹ�úڰ�ī����A4ֽ�ϴ�ӡ
	    inkBox = new GrayInkBox();//ī������ָ��ڰ�ī�������
	    paper = new A4Paper();//ֽ������ָ��A4ֽ�����
	    printer.setColor(inkBox);//��ī�н��и�ֵ
	    printer.setSize(paper);//��ֽ�Ž��и�ֵ
	    printer.print();//���ô�ӡ����
	 
	    //3��ʹ�ò�ɫī����B5ֽ�ϴ�ӡ
	    inkBox = new ColorInkBox();
	    paper = new B5Paper();
	    printer.setColor(inkBox);
	    printer.setSize(paper);
	    printer.print();
	 
	    //4��ʹ�ò�ɫī����A4ֽ�ϴ�ӡ
	    paper = new A4Paper();
	    printer.setSize(paper);
	    printer.print();
	}
}
class Printer
{
	InkBox inkBox; 
	Paper paper; 
	
	public void setInkBox(InkBox inkBox)
	{
   	this.InkBox = inkBox;
   }

	public void setPaper(Paper paper){
        this.paper = paper;
    }
 
	public void print()
	{
   	System.out.println("ʹ��"+inkBox.getColor()+"ī����"+paper.getSize()+"ֽ�ϴ�ӡ��");
   }
}
//ī�нӿ�
interface InkBox
{
    public String getColor();
}
 
//��ɫī����
class ColorInkBox implements InkBox
{
	public String getColor()
	{
		return "��ɫ";
	}
} 
 
//�ڰ�ī����
class GrayInkBox implements InkBox
{
	public String getColor()
	{
		return "�ڰ�";
	}
}

//ֽ�Žӿ� 
interface Paper
{
    public String size();
}
  
//A4��ӡֽ��
class A4Paper implements Paper
{
	public String getSize()
	{
		return "A4";
	}
}
 
//B5��ӡֽ��
class B5Paper implements Paper
{
	public String getSize()
	{
		return "B5";
	}
}
