package simpledowhileloops;

public class SimpleDoWhileLoops 
{
    public static void main(String[] args) 
    {
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
        
      /*  
        i = 1;
        double myTotal = 0;
        System.out.println("Adds all the fractions from 1/1 to 1/100");
        do
        {
        myTotal += 1.0/i; //accumulator
        i += 1.0;             
        }
        while (i <= 100);
        System.out.println(myTotal);
        System.out.println(); 
     */
        
    }
    
}
