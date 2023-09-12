package flavorswitch;
import java.util.Scanner;

public class FlavorSwitch {
    
    public static void main(String[] args) 
    {
      Scanner myInputScanner = new Scanner(System.in);
      System.out.println("Enter 'A' for Grape, 'B' for Orange, 'C' for Apple, 'D' for Lemon or 'E' for Mango flavor.");
      String mySelection = myInputScanner.nextLine();
      System.out.println("You selected: " + mySelection); //debug
      //Using a switch in order to determine which flavor the user is looking for.
      switch (mySelection) //The condition being tested for your Switch
      {
          case ("A"):
              System.out.println("Your flavor is Grape");
              break;
          case ("B"):
              System.out.println("Your flavor is Orange");
              break;
          case ("C"):
              System.out.println("Your flavor is Apple");
              break;
          case ("D"):
              System.out.println("Your flavor is Lemon");
              break;
          case ("E"):
              System.out.println("Your flavor is Mango");
              break;
          default:
              System.out.println("Invalid Input");
              break;
      }
        System.out.println("Completed this case.");
    }
    
}
