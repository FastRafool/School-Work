package types;

public class Types { //Class header

    
    public static void main(String[] args) 
  {     //main method static means you don't have to create an object
        System.out.println("Backslash n moves \ndown one line.");
        System.out.println("\n\nI\nlove\nJohana!");
         System.out.println("\n\nBackslash backslash prints a backslash: \\");
          System.out.println("\n\nMark Twain famously said \'Man is the only animal that blushes...or needs to.\'");
          System.out.println("\n\'The man who goes to the gym regardless of how he feels, will always beat the guy who goes to the gym whenever he feels like.\'");
          
          //You need escapae quotes when you're assembling SQL statements
          
          
          String mySQL = "SELECT NAME FROM CUSTOMERS WHERE ZIP = \'77539\'";
          System.out.println(mySQL + "\n\n\n\n");
          
          
      //DATA TYPES
      byte myByte = -3; //bytes can be from -127 to 127
      //byte myByte2 = 129; 
    System.out.println("Variable type byte: " + myByte);
    
    short myShort = 4096; //shorts can be from -32768 to 32768
    //USE AN int FOR INTEGERS UNLESS YOU HAVE A SERIOUS REASON NOT TO
    int myInt = 1123456789; // ints from -2billion to 2 billion
    int myInt2 = 2147483647;
    
    //Java assumes a numeric value is an int unless you mark it with
   //long myLongTooLarge = 9323372036854449203;
   long myLongOkay = 93948320282042040L;
   System.out.println(Long. MAX_VALUE);
    
   float myFloat = 42.0F; //MARK a float literal with 'F'
   //USE A double for fractional numbers unless you have a serious reason not to
  double myDouble = 43.0;
    
           
    boolean myboolean =(6>5);
    //Bolean variables evaluate to true or false
    System.out.println(" Is 6 greater than 5? " + myboolean);
    
     boolean myBoolean2 =(6> 5);
     System.out.println("Is 6 less than 5? " + myBoolean2);
     
     boolean myBoolean3 =(5 != 5);
     System.out.println("Is 6 not equal to 5?" + myBoolean3);
     
     boolean myBoolean4 = true;
     
     
     char myChar = 'O';
     System.out.println("My name starts with a " +  myChar);
     
    //You can assign char values using the decimal ASCII code
    char myChar2 = 101;
    System.out.println("The second letter in my name is " + myChar2);
    
    //Watch out: Integer divsiion drops the fraction, IF ther is one 
    
    int myDivisionOkay = 10/2;
    System.out.println("10 divided by 2 is " + myDivisionOkay);
    
    int myDivisionWrong = 11/2;
    //int myDivsionWrong = 11/2; //Truncated, not rounded
    System.out.println("\n11 divided by 2 is NOT " + myDivisionWrong);
    
    double myDivisionCorrect = 11.0/2.0;
    System.out.println("Correct division: " +myDivisionCorrect);
    
    double myDivisionRight = (double)11/2; //cast
    System.out.println("Casting integer calculation to double: " + myDivisionRight);
    
    //Division by zero is Forbidden - By Mathematics, Not Java
    //double my zeroMistake = 37/0;
    
    double myZeroMistake2 = 37f/0;
    double myZeroMistake3 = 37.0/0.0;
    System.out.println("Division by zero: " + myZeroMistake2);
                
        //ORDER OF OPERATIONS: PEMDAS parentheses,exponents,multiply,divide,add,subtract
        System.out.println(1+3/2);
        System.out.println(3/2);
        System.out.println(3.0/2);
        System.out.println(1.0 + 3.0 / 2.0);
        //Compute mean average
        System.out.println((1+3)/2); //Parentheses help clarity (documentation)
        System.out.println((1.0 + 3.0 + 7.0 ) / 3.0);
        
        //MODULO - REMAINDERS
        System.out.println(10 % 3); //Modulo returns an integer
        System.out.println(10 % 2);
        System.out.println(10 % 7);
        System.out.println(17/12);//Modulo cycle is the denominator
        
        //EXPONENTS
        //int twoToEighth = 2**48;
        double twoToEighth = Math.pow(2.0,8.0);
        System.out.println(twoToEighth);
        //ClassName.MethodName(arguments)
        double twoToSixteenth = Math.pow(2.0,16.0);
        System.out.println(twoToSixteenth);
        double squareRoot49 = Math.sqrt(49.0);
        System.out.println(squareRoot49);
        
        //CONSTANTS
        final double myPi = 3.14;
        final double myPiBig = 3.14159;
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
    
    
}
