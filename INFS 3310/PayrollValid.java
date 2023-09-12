package payrollvalid;
import javax.swing.JOptionPane;

public class PayrollValid 
{

    public static void main(String[] args) 
    {
     JOptionPane.showMessageDialog(null, "This program is used to calculate your gross pay as a employee of our organisation."
       + "\nPlease enter your information as decimal numbers, no commas, periods or any other\ncharacters except decimals will be accepted."
       + "Thank you again for being a valued \nemployee.");
       
       String myHourlypay = JOptionPane.showInputDialog("What is your hourly pay?");
       double a =Integer.parseInt(myHourlypay);
        
       String myHoursworked = JOptionPane.showInputDialog("How many hours do you work in one week?");
       int b = Integer.parseInt(myHoursworked);
       
       boolean hrs = true;
       while(b<0 || b>19)
       {
           if(hrs)
           {
           JOptionPane.showMessageDialog(null,"Invalid # hours reported. Valid hours are integers from 1 to 19 inclusive. Please re-enter.");
           myHoursworked = JOptionPane.showInputDialog("How many hours do you work in one week?");
           b = Integer.parseInt(myHoursworked);
           }
           if(b>=0 && b<=19)
           {
           hrs = false;
           }
       
       }
        
       String myGym = JOptionPane.showInputDialog("How much money do you get weekly from your gym stipend?");
       double c =Integer.parseInt(myGym);
        
       double grosspay = (a*b+c);
        
       System.out.printf("Hourly pay: $%.2f%n",a);
       System.out.printf("Weekly hours: %d",b);
       System.out.printf("\nGym stipend: $%.2f%n",c);
       System.out.printf("Total wages for the week: $%.2f%n",grosspay);   
    }
    
}
