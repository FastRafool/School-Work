package dowhileloops;
import java.util.Scanner;
public class DoWhileLoops 
{

    public static void main(String[] args) 
    {
      
       //DO while loop used to calculate perfect squares of the numbers 1-5. 
       int i =1;
       int nextSquare;
        System.out.println("Perfect Squares:");
        do
        {
            //i +=1;
            nextSquare = i * i;
            System.out.print(nextSquare + " ");
            i ++;
        }
        while (i <= 5);
        System.out.println();
        System.out.println();
        
        //DO while loop used to count from 15 down to 0 by 3's.
        i = 15;
        System.out.println("Count from 15 down to 0 by 3's: ");
        do
        {
            System.out.print(i + " ");
            i -= 3;
        }
        while (i >= 0);
        System.out.println("\nLast value: " + i);
        System.out.println();
        
        // DO while loop used to calculate the total of all integers from 1 to 100.
        i = 1;
        int myTotal = 0;
        System.out.println("Total of all integers from 1 to 100:");
        do
        {
        myTotal += i; //accumulator
        i += 1;             
        }
        while (i <= 100);
        System.out.println(myTotal);
        System.out.println();
        
    
    

        
        //Do while loop used to ask the user a question. The loop will continue till a correct answer is given.
        Scanner scanner = new Scanner(System.in);
        String guess;
       do 
      {
          System.out.print("Who invented the lighbulb? (First and last name):  "); //Step 1: ask the user the question.
          guess = scanner.nextLine();
        if ("Thomas Edison".equals(guess))
        {       
      
        }
        else 
        {
          System.out.print("Hint: their first name rhymes with promised. \n"); //Step 2: gives the user a hint if they are not able to answer the question.                        
        }        
                       
      }
       while (!"Thomas Edison".equals(guess)); //Once the user answers the question correctly then the loop stops and we congradulate them.
      System.out.println("Congratulations, you are correct!");    
        
    }
    
}
