package staticdemo;

public class StaticClass 
{
  private static int myCounter = 0;

  public StaticClass() //constructutor header
  {       
     myCounter += 1;
     System.out.println("Constructing StaticClass object.");
  }
  
  public int getMyCounter() //code to read static varible value
  {
     return myCounter;
  }

}
