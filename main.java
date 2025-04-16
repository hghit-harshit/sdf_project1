import arbritraryarithmetic.AInteger;
import java.util.Scanner;
public class main {
    static public void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);
        String a = reader.nextLine();
        String b = reader.nextLine();

        AInteger some = new AInteger(a);
        AInteger thing = new AInteger(b);
        
        AInteger other = some.Mult(thing);
        other.Print();
    }
}
