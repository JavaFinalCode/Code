public class Swallow implements Cloneable{  
  
  
    private String name;  
    private Wing leftWing;  
    private Wing rightWing;  
      
    public Swallow(String name, Wing leftWing, Wing rightWing){  
        this.name = name;  
        this.leftWing = leftWing;  
        this.rightWing = rightWing;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public Wing getLeftWing() {  
        return leftWing;  
    }  
  
    public void setLeftWing(Wing leftWing) {  
        this.leftWing = leftWing;  
    }  
  
    public Wing getRightWing() {  
        return rightWing;  
    }  
  
    public void setRightWing(Wing rightWing) {  
        this.rightWing = rightWing;  
    }  
      
    @Override  
    protected Object clone() throws CloneNotSupportedException {  
   
        return super.clone();  
    }  
}  