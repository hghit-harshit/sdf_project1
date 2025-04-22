import arbritraryarithmetic.AInteger;
import arbritraryarithmetic.AFloat;
import java.util.Scanner;
public class main {
    static public void main(String[] args)
    {
        // Scanner reader = new Scanner(System.in);
        // String a = reader.nextLine();
        // String b = reader.nextLine();

        // AInteger some = new AInteger(a);
        // AInteger thing = new AInteger(b);
        
        // AInteger other1 = some.Add(thing);
        // other1.Print();
        // //AInteger other2 = some.Subtract(thing);
        // AInteger other3 = some.Mult(thing);
        // other3.Print();
        // AInteger other4 = some.Divide(thing);
        // other4.Print();
        // AFloat some = new AFloat(a);
        // some.Print();
        // AFloat thing = new AFloat(b);
        // thing.Print();

        // AFloat other = some.Add(thing);
        // other.Print();
        //other2.Print();
        
        AFloat a = AFloat.Parse("8792726365283060579833950521677211.0");
        AFloat b = AFloat.Parse("493835253617089647454998358.0");
        AFloat result = a.Divide(b);
        //result.Print();
        String answer = "17804979.091469989302961159520087878533"+
        "68052180603671167924683984130815398609236273785506830355"+
        "402448624808416473436067720971642607709808710597044219662"+
        "7540469031041948493652479229112208111436434654956";
        
        result.Print();
        //assertEquals(answer,result.GetValue());

        // AFloat a  = new AFloat("2.0");
        // AFloat b = new AFloat("246917626808544823727499179.0");

        // AFloat result = a.Subtract(b);
        // result.Print();

    }
}
