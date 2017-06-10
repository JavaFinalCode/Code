public class TestPrinter
{
	public static void main(String[] args)
	{
	    InkBox inkBox = null;//墨盒引用
	    Paper paper = null;  //纸张引用
	    Printer printer = new Printer();
	 
	    //2、使用黑白墨盒在A4纸上打印
	    inkBox = new GrayInkBox();//墨盒引用指向黑白墨盒类对象
	    paper = new A4Paper();//纸张引用指向A4纸类对象
	    printer.setColor(inkBox);//对墨盒进行赋值
	    printer.setSize(paper);//对纸张进行赋值
	    printer.print();//调用打印方法
	 
	    //3、使用彩色墨盒在B5纸上打印
	    inkBox = new ColorInkBox();
	    paper = new B5Paper();
	    printer.setColor(inkBox);
	    printer.setSize(paper);
	    printer.print();
	 
	    //4、使用彩色墨盒在A4纸上打印
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
   	System.out.println("使用"+inkBox.getColor()+"墨盒在"+paper.getSize()+"纸上打印。");
   }
}
//墨盒接口
interface InkBox
{
    public String getColor();
}
 
//彩色墨盒类
class ColorInkBox implements InkBox
{
	public String getColor()
	{
		return "彩色";
	}
} 
 
//黑白墨盒类
class GrayInkBox implements InkBox
{
	public String getColor()
	{
		return "黑白";
	}
}

//纸张接口 
interface Paper
{
    public String size();
}
  
//A4打印纸类
class A4Paper implements Paper
{
	public String getSize()
	{
		return "A4";
	}
}
 
//B5打印纸类
class B5Paper implements Paper
{
	public String getSize()
	{
		return "B5";
	}
}
