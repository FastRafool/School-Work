package simpleforloops;
public class SimpleForLoops 
{
    //This program is used to give examples/applications of for loops.
    public static void main(String[] args) 
    {
       // For loop which calculates the perfect squares of the numbers 1 thru 5.
        System.out.println("\nPerfect squares:"); 
        for (int i = 1; i <= 5; i += 1)
        {
        int nextSquare = i * i;
            System.out.print(nextSquare + " ");
        }
        System.out.println("\n");
        
        
        // For loop which counts down from 15 down to 0 by 3's. 
        System.out.println("\nCount from 15 down to 0 by 3's:");
        for (int i = 15; i >=0; i -=3)  
        {
            System.out.print(i + " ");
        }
        
       
        // For loop which calculates total of all integers from 1 to 100. 
        System.out.println("\n\n\nTotal of all integers from 1 to 100:");
        int myTotal = 0;
     
        for (int i = 1; i<=100; i++)
        { 
            myTotal +=i;    
        }
        System.out.println(myTotal + "\n");
        
        
  
   // For loop which will reverse any string put into it. 
     String myString = "The world is yours to own";
     String myStringReversed = "";
     String myLetter;

     for(int i=0; i < myString.length(); i++) 
     {
         myLetter = myString.substring(i,i+1);
         // add the letter at index i to what's already reversed.
         myStringReversed = myLetter + myStringReversed;
     }
     System.out.println("\'" + myString+ "\'" + " reversed is: " +myStringReversed);
   
    }
    
}
