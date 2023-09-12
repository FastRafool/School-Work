package staticdemo;

public class StaticDemo 
{

    public static void main(String[] args) 
    {
      int ObjectReport;
      
      //Call the constructor
      StaticClass firstObject = new StaticClass();
      StaticClass secondObject = new StaticClass();
      StaticClass thirdObject = new StaticClass();
      //Read the static variable
      ObjectReport = thirdObject.getMyCounter();
      System.out.println("You have " + ObjectReport + " objects.");
      
      
      
    }
    
}
