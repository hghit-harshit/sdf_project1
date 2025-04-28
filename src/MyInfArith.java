import arbritraryarithmetic.AInteger;
import arbritraryarithmetic.AFloat;
import java.util.Scanner;


public class MyInfArith {
    static public void main(String[] args)
    {
        if(args.length != 4)
        {
            System.out.print("Usage : <int/float> <add/sub/mult/div> <operand1> <operand2>");
            return;
        }

        String type = args[0];
        String operation = args[1];
        String operand1 = args[2];
        String operand2 = args[3];

        if(type.equals("int"))
        {
            AInteger a = AInteger.Parse(operand1);
            AInteger b = AInteger.Parse(operand2);
            AInteger c = AInteger.Parse("0");
            if(operation.equals("add")){c = a.Add(b);}
            else if(operation.equals("sub")){c = a.Subtract(b);}
            else if(operation.equals("div")){c = a.Divide(b);}
            else if(operation.equals("mult")){c = a.Mult(b);}

            c.Print();
        }
        else if(type.equals("float"))
        {
            AFloat a = AFloat.Parse(operand1);
            AFloat b = AFloat.Parse(operand2);
            AFloat c = AFloat.Parse("0");
            if(operation.equals("add")){c = a.Add(b);}
            else if(operation.equals("sub")){c = a.Subtract(b);}
            else if(operation.equals("div")){c = a.Divide(b);}
            else if(operation.equals("mult")){c = a.Mult(b);}

            c.Print();
        }
        
    }
}
