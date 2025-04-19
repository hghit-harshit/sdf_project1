import arbritraryarithmetic.AInteger;
import arbritraryarithmetic.AFloat;
import java.util.Scanner;
public class main {
    static public void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);
        String a = reader.nextLine();
        String b = reader.nextLine();

        // AInteger some = new AInteger(a);
        // AInteger thing = new AInteger(b);
        
        // AInteger other1 = some.Add(thing);
        // other1.Print();
        // //AInteger other2 = some.Subtract(thing);
        // AInteger other3 = some.Mult(thing);
        // other3.Print();
        // AInteger other4 = some.Divide(thing);
        // other4.Print();
        AFloat some = new AFloat(a);
        some.Print();
        AFloat thing = new AFloat(b);
        thing.Print();

        AFloat other = some.Add(thing);
        other.Print();
        //other2.Print();
        
        

    }
}
