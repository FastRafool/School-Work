package compound.interest;
import java.util.Scanner; 

public class CompoundInterest 
{

    public static void main(String[] args) 
    {
    
	/* Summary: Program to calculate Compound Interest
   
--->  Suppose you want to deposit some money into a
savings account, and let the account earn compound interest
for a certain number of years. The formula for calculating the
balance of the account after a specified number of years is what we will be using
       */   
    
       System.out.println("Input all data using DECIMAL numbers only. Not \"a\" , \"$\" or any other symbol besides DECIMAL numbers.\n");
       //Directions for the user
       
       
       // The formula for the compound interest is:  A = P (1 + r/n)^n*t
       
       Scanner myInput = new Scanner(System.in);     
       System.out.println("Please input the principal amount of $, originally deposited into your account.");
       int principal = myInput.nextInt();
       // "principal" is P in the formula
       
       System.out.println("What is your annual interest rate? (Please input the amount without a % sign)");
       int interestRate = myInput.nextInt();
       // interest rate is the interest value the customer entered, but it has not been divided by 100 yet.
       
       double InterestP = (double)interestRate/100;
       // "InterestP" is r in the formula. (This amount will be divided by 100)
       
       System.out.println("How many times per year will your interest be compounded?"); 
       int interestPerYear = myInput.nextInt();     
       //"interestPerYear" is n in the formula
       
       System.out.println("Please input the number of years your account will be left to earn interest.");
       int totalYears =myInput.nextInt();
       // "totalYears" is t in the formula
       
       // Forumula: A = P (1 + r/n)^n*t
       double solution = (1+InterestP/interestPerYear); 
       double secondPartofSolution = Math.pow(solution, totalYears*interestPerYear);
       double finalanswer = secondPartofSolution*principal; //In order for the formula to work, I had to save the multiplication of "P" till last. According to PEMDAS the formula is still correct.
       
       System.out.println("\nYou will have a total of $" + finalanswer + " after your interest has been accrued.");
       
    }
    
}