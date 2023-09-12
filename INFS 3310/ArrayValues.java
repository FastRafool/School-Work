package arrayvalues;
import java.util.*;

public class ArrayValues 
{

    public static void main(String[] args) 
    {
        int[]myIntegerArray = {1, 4, 9, 16, 25, 36};
        double[]myDoubleArray = {1.2, 3.14, 2.687, 9.0, 13.25};
        String[]myStringArray = {"INFS","QUMT","MGMT"};
    
        System.out.println(Arrays.toString(myIntegerArray));
        System.out.println(Arrays.toString(myDoubleArray));
        System.out.println(Arrays.toString(myStringArray));
        
        int thirdInteger = myIntegerArray[2]; //Computer Science counting
        System.out.println("The third Integer is " + thirdInteger);
        
        double fourthDouble = myDoubleArray[3];
        System.out.println("The fourth double is " + fourthDouble);
        
        String secondString = myStringArray[1];        
        System.out.println("The second String is " +secondString);
        
        
        System.out.println("The length of the integer array is " + myIntegerArray.length);
        System.out.println("The length of the double array is " + myDoubleArray.length);        
        System.out.println("The length of the String array is " + myStringArray.length);
        
    }
    
}
