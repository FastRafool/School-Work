/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fancyprint_function;
public class FancyPrint_function 
{
    static void myPrint(String nextDecorator, String nextPhrase, String nextEnding)
        {
           System.out.println(nextDecorator + nextPhrase + nextDecorator + nextEnding); 
        }
    public static void main(String[] args) 
    {
        String myDecorator = "^^^^^";
        String myPhrase = "Picture yourself in a boat on a river";
        String myEnding = "!!!";
        myPrint(myDecorator,myPhrase, myEnding);
        
    }
}