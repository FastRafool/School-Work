/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
// The tipdocs package is used sort of like a folder to hold multiple related class types.
package tipdocs;
import java.text.NumberFormat;
/**
 *
 * @author Odudu Otu
 * This program is used to calculate the grosspay of a purchase plus any tips or tax associated with the transaction.
 * The currency used is United States dollars. 
 */

public class TipDocs {
//This is the class used for the project. I would need to state this. 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int  cost = 45;
        // This is the original cost of the purchase.
        double grossPay, tax = 0.08*cost;
        // This is the gross pay and the value of tax.
        double tip = 0.10*cost;
        // Im giving the tip a value.
        grossPay = (cost + tax + tip);
        // Total claculation of the bill.
        
          /*"cost" is basic cost or original price of meal. grossPay is just the total cost of the meal inlcuding the tip and taxes.
        the point of this code is for it to be used to calculate meal costs and make a printed receipt to the customer*/
        
        System.out.println ("basic cost: $" + cost);
        //Printing the value of cost to the screen
        System.out.println ("tax computed: $" + tax);
        // Printing the value of tax to screen
        System.out.println ("tip added: $" + tip);
        // Printing the vlaue of tip to screen
        System.out.println ("final amount is: $" + grossPay);
        // Printing the final bill to the customer
    }
    
}
