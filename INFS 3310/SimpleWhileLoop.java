package simplewhileloop;

public class SimpleWhileLoop 
{
    public static void main(String[] args) 
   {
     int myCounter = 7; //Building WHILE loop for FINITE counting
     
     while(myCounter >= 0)
     {
         System.out.println(myCounter);
         //myCounter = myCounter -1;
         myCounter -= 1;
         //myCounter--;
     }
        System.out.println("Blastoff One\n");
        
        int myCounter2 = 10; //Building WHILE loop for FINITE counting
     
     while(myCounter2 <= 50)
     {
         System.out.println(myCounter2);
         //myCounter = myCounter -1;
         myCounter2 += 10;
         //myCounter--;
     }
     
     
        while(true)
        {
            System.out.println(myCounter);
            //myCounter -=1;
            if (myCounter <= 0)
            {
                System.out.println("SecondBlastoff...");
                break;
            }
        myCounter -=1;

    }
}
}  

