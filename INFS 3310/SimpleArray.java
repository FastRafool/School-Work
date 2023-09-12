package simplearray;
import java.util.*;

public class SimpleArray 
{

    public static void main(String[] args) 
    {
        int[]myIntegerArray = {1, 4, 9, 16, 25, 36};
        double[]myDoubleArray = {1.2, 3.14, 2.687, 9.0, 13.25};
        String[]myStringArray = {"INFS","QUMT","MSBA"};
    
        System.out.println(Arrays.toString(myIntegerArray));
        System.out.println(Arrays.toString(myDoubleArray));
        System.out.println(Arrays.toString(myStringArray));
    
    //Array indexing - which element you want by number
        int fourthInteger = myIntegerArray[3]; //Computer Science counting
        System.out.println("Fourth integer: " + fourthInteger);
        double thirdDouble = myDoubleArray[2];
        System.out.println("Third double: " + thirdDouble);
        String secondString =myStringArray[1];
        System.out.println("Second String: " +secondString);
        //int wrongIndex = myIntegerArray[8];
        
        final int ARRAY_SIZE = 3;
        int[] limitedValues = new int[ARRAY_SIZE];
        System.out.println(Arrays.toString(limitedValues));
        limitedValues[0] =1;
        System.out.println(Arrays.toString(limitedValues));
        limitedValues[2] = 255;
        System.out.println(Arrays.toString(limitedValues));
        
        
        int i = 2; // counter
        int[] myFactorials = new int[11]; //Declare a fixed-size array to hold the numbers
        //You can address factorials directly through the index
        myFactorials[0] = 1; //0! is equal to 1
        myFactorials[1] = 1; //1! also equals 1
        System.out.println("Starter array has two elements: " + Arrays.toString(myFactorials)); 
        
        //Use a loop to read out the values of an Array
        for(int j=0; j<5; j++)
        {
            System.out.println("Next double: " + myDoubleArray[j]);
        }
        System.out.println("Run Complete.");
        
        int itemsSelected[] = {1,3,4};
        System.out.println(Arrays.toString(itemsSelected));
        for(int j=0; j<3; j++)
        {
        int nextINDEX = itemsSelected[j];
        double nextDouble = myDoubleArray[nextINDEX];
        System.out.println("Next selected double" + nextDouble);
        
            System.out.println("Selected double:" + myDoubleArray[itemsSelected[j]]);
        }   
        int stringsSelected[] = {1,2};
        System.out.println(Arrays.toString(stringsSelected));

    for(int j=0; j<2; j++)
    {
      int nextINDEX = stringsSelected[j];
      String nextString = myStringArray[nextINDEX];
      System.out.println("Next selected string: " + nextString);       
      System.out.println("Selected string:" + myStringArray[stringsSelected[j]]);
    }
    
    }
    
}
