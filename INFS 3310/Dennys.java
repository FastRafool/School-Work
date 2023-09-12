/* IF customerAge >=55 half price
* IF customerAge < 12 half price
* IF today is Saturday NO DISCOUNTS for antone
* IF today is not Saturday, then the age discounts 
 */
package dennys;
import java.util.Scanner;

public class Dennys {

    public static void main(String[] args) 
    {
        //data needed - make some variables
        double fullPrice = 10.0;
        double finalPrice = -1;
        //String myToday = "Wednesday";
        String todayTF;
        Scanner myInputScanner = new Scanner(System.in);//Create object
        System.out.println("Enter your age: ");
        String myAge = myInputScanner.nextLine();
        int customerAge = Integer.parseInt(myAge);
        System.out.println("You are " + myAge + "years old.");
        //IF decision structure to get right price
        //if (customerAge >= 55)
        /*
        if ((customerAge >=55) || (customerAge <12))
        {
        finalPrice =fullPrice/2.0; 
        }
        else 
        {
        finalPrice = fullPrice; 
        }
        //Report the price for this customer
        System.out.println("Your final cost is " +finalPrice);
        
        todayTF = myToday.equals("Wednesday");
        if (todayTF)
        {
            System.out.println("WHEN TRUE: " + todayTF);
        }
        else
        {
            System.out.println("WHEN FALSE: " + todayTF);
        }
        */
        //boolean flippedBoolean = !(true);
        //System.out.println("NOT operator: " + flippedBoolean);
        System.out.println("What is today? ");
        
        String myDay = myInputScanner.nextLine();
        System.out.println("You said today is " + myDay);
        
        boolean myDayTF = myDay.equals("Saturday");
        
        if(myDayTF)
        {
        finalPrice = fullPrice;
        System.out.println("Entered the Saturday condition");
        }
        
        else
        {
        //Check for the age discounts
        if ((customerAge >=55) || (customerAge <12 ))
        {
        finalPrice = fullPrice/2.0;
        }
        else 
        {
        finalPrice = fullPrice;
        }
        }
        
        
        if (!(myDayTF) && ((customerAge >=55) || (customerAge < 12)))
        {
           finalPrice = fullPrice/2.0;
        }
        else
        {
            finalPrice = fullPrice;
        }
        System.out.println("Your final price is: " + finalPrice);
    }
    
}

