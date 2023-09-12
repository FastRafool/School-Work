package ifthen;
public class IfThen 
{

    public static void main(String[] args) 
  {
    int myIdentifier = 2;  //0 for Star Trek, 1 for Star Wars 
    
    if (myIdentifier == 0) //2 for Deckard
    {
      System.out.println("Hello Captain Kirk!");
    }
    else if (myIdentifier ==1)
    {
      System.out.println("Hello Luke");
    }
    else if (myIdentifier ==2)
    {
      System.out.println("Hello Deckard");
    }   
    else //otherwise anything else
    {
      System.out.println("You are not a SCI-FI character");
    }
    System.out.println("Tests Complete\n\n");
  
  
    //Multiple condition decision structures
    
    int myVar1 = 1;
    int myVar2 = 6;
    
    /*if ((myVar1 >= 0 )&& (myVar2 ==6) )
    {
    System.out.println("Hello Spock");
    }
    else //otherwise anything else
    {
      System.out.println("You are not a SCI-FI character");
    }*/
   
    if (myVar1 ==0)
    {
       if (myVar2 ==6) // Nested IF is an AND
    {
       System.out.println("Hello Spock");
    }
    
    }
    else
    {
    System.out.println("You are not a sci-fi character.");
    }
    
    if (myVar1 ==0)
    {
    System.out.println("Hello HAL 1");
    }
    else if (myVar2 ==6)
    {
    System.out.println("Hello HAL 2");
    }
    
    System.out.println("Tests Complete");
    
    // ----------------------------------------------------------------------------------------------------------------------
  //Week 8 Day 2 of class
    
    double myPrice = 1.0;
    boolean isOnSale = true;
    double discountPrice;
    boolean isWednesday = true;
    double discountWen;
    
    if(isOnSale)
    {
    discountPrice = myPrice - (myPrice *.25);
   
    }
    else{
    discountPrice = -1.0;
    }
  System.out.println("Discounted price = " + discountPrice);
  
  if ((isOnSale) && (isWednesday))
  {
  discountWen = myPrice - (myPrice *.35);
  }
  else
  {
  discountWen = -1.0;
  }
  System.out.println("Discounted Price = "+ discountWen);
  
  
  
  
  }
    
       
}
