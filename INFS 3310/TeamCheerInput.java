package teamcheerinput;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class TeamCheerInput 
{
    public static void main(String[] args) 
    
    {
             
      Scanner myInput = new Scanner(System.in);
      System.out.println("Please enter a sports cheer below");
      String myCheer = myInput.nextLine();
      
      System.out.println("Please enter your favorite team name");
      String myTeam= myInput.nextLine();
      
      System.out.print(myCheer + "," + myTeam + "!");
      
      JOptionPane.showMessageDialog(null, "Your cheer is " + myCheer + "!");
      String myTeam2 = JOptionPane.showInputDialog("What is your second team?");
      JOptionPane.showMessageDialog(null, myCheer + "," + myTeam2 + "!"); 
      
    }
    
}
