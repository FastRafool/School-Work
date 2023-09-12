package discount;
import java.text.NumberFormat;

public class Discount 
{

    public static void main(String[] args) {
        // In summary this program is designed to give the Customer their gross payment in another countries currency.
        
        
        int shoes = 60;
        //"shoes" is the price of the product
        double diss = .10;
        //"diss" is the discount perentage 
        double discount = (.10*shoes);
        //Dollar amount of the discount. 
        double grosspay = (shoes - discount);
        //In order to find the gross payment we subract the discount from the original price. This will give us our answer.
        double naira = (grosspay*430); 
        // I used the nigerian currency which is $1 = 430 Nigerian naira. that is where i got the 430 from because it is the exchange rate between the two currencies
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        NumberFormat format = NumberFormat.getPercentInstance();
        String myshoe = formatter.format(shoes);
        String mydiscount = formatter.format(discount);
        String mydiss = format.format(diss);
        // Number formatter used to get me the Dollar and percentage values
        
        
        System.out.println("Original price: " + myshoe);
        System.out.println("Discount " + mydiss);
        System.out.println("Dollar amount of the discount: " + mydiscount);
        System.out.println("Final amount due: NGN " + naira ); 
        
    }
    
}
