public class ShallowClone {  
  
    public static void main(String[] args) throws CloneNotSupportedException
	{  
  
        Swallow s1 = new Swallow("大雁a", new Wing(10), new Wing(10));  
        Swallow s2 = (Swallow) s1.clone();  
          
        System.out.println("(s1==s2)="+ (s1==s2));  
        System.out.println("s1.name="+ s1.getName()+", s2.name="+s2.getName());  
        System.out.println("(s1.leftWing==s2.leftWing) = "+(s1.getLeftWing()==s2.getLeftWing())); 
		
        System.out.println("(s1.rightWing==s2.rightWing) = "+(s1.getRightWing()==s2.getRightWing()));    

System.out.println(s1.getRightWing().hashCode());   
System.out.println(s2.getRightWing().hashCode());   		
    }  
} 