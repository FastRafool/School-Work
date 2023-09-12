package payroll3;
import javax.swing.JOptionPane;
public class Payroll3 

{
// Program to calculate Payroll of full/part-time workers. It includes overtime payments too and claculates OT as time and a half.
    public static void main(String[] args) 
    {
      JOptionPane.showMessageDialog(null, "This program is used to calculate your gross pay as a employee of our organisation."
       + "\nPlease enter your information as decimal numbers, no commas, periods or any other\ncharacters except decimals will be accepted."
       + "Thank you again for being a valued \nemployee.");
       
       String myHourlypay = JOptionPane.showInputDialog("What is your hourly pay?");
       double a =Integer.parseInt(myHourlypay);
        
       String s = JOptionPane.showInputDialog("How many hours do you work this week?");
       int hoursWorked = Integer.parseInt(s);
       int regularHours;
       int overtimeHours;
       double otpayrate;
       double otpaid;
       double totalpay;
       double regularhrspaid;
       
       //String myGym = JOptionPane.showInputDialog("How much money do you get weekly from your gym stipend?");
       //double c =Integer.parseInt(myGym);
       
       //If block for any hours above max overtime hours
       if (hoursWorked>60)
       {
       System.out.println("You have violated the policy, you cannot work more than 60hours in a week.");
       System.exit(0);
       }
       else if (hoursWorked>40)
       {
       overtimeHours = hoursWorked-40;
       otpayrate= a +(a /2);
       otpaid = overtimeHours *otpayrate;
       regularhrspaid =a*hoursWorked; //"regularhrspaid" is the amount paid within the employee's regular hours
       totalpay = otpaid + regularhrspaid;
       }
       
       //If block to calculate any overtime hours
       if (hoursWorked<=40)
       {
       overtimeHours =0;
       otpayrate= 0;
       otpaid = overtimeHours *otpayrate;
       regularhrspaid =a*hoursWorked;
       totalpay = otpaid + regularhrspaid;
       }
       //If the hours go into Overtime then the else statement will calculate the OT payrate.
       else  
       { 
       otpayrate= a +(a /2);
       overtimeHours = hoursWorked-40;
       otpaid = overtimeHours *otpayrate;
       regularhrspaid =a*hoursWorked;
       totalpay = otpaid + regularhrspaid; 
       }
       
       //If block to calculate regular hours
       if (hoursWorked<=40)
       {
       regularHours=hoursWorked;
       regularhrspaid =a*hoursWorked;
       }
       else
       {
       otpaid= overtimeHours *otpayrate;
       regularHours=40;
       regularhrspaid = regularHours*a;
       totalpay = otpaid + regularhrspaid;
       }
       
       System.out.printf("Hours Worked this week: %d\n",hoursWorked);
       System.out.printf("Hourly pay rate: $%.2f%n",a);
       System.out.printf("Overtime pay rate: $%.2f%n",otpayrate);
       System.out.printf("Overtime hours worked: %d\n",overtimeHours);
       System.out.printf("Regular hours worked: %d\n",regularHours);
       //System.out.printf("\nGym stipend: $%.2f%n",c);
       System.out.printf("Total wages for the week: $%.2f%n",totalpay);
       
    }
    
}
