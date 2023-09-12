package exceptions;

public class Exceptions 
{

    public static void main(String[] args) 
    {
     /*
        int[] myArray = {8,13,21};
        System.out.println(myArray[2]);
    
    
    try
    {
        int[] myArray = {8,13,21};
        System.out.println(myArray[6]);        
    }
    catch (ArrayIndexOutOfBoundsException e1)
    {
        System.out.println("You have an Array index error");
        System.out.println(e1);
    }
        System.out.println("Execution continues"); 
    }
    */
        
        
     try
     {
         int[] myArray = {8,13,21};
         System.out.println(myArray[6]);
     }
     catch (Exception e1)
     {
         System.out.println("You have an error");
         System.out.println(e1);
     }
         System.out.println("End");
    }
}
