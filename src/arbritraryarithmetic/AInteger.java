package arbritraryarithmetic;
import java.util.concurrent.Flow.Subscriber;

public class AInteger
{
    protected String m_value; // the value of the integer
    protected int m_digits; // number of digits in the number
    private boolean m_sign; // if ture then postive and false for negative
    // made them protected as i wanted AFloat to be able to access them

    public AInteger() // default constructor
    {
        m_value = "0";
        m_digits = 1;
        m_sign = true;
    }

    public AInteger(String l_string)
    {
        l_string = l_string.replaceAll("\\s", "");// removing whitespaces
        if(!l_string.matches("^-?\\d+$")) // checking it the string is valid 
        {   
            System.out.print("Invalid entry!\n");
            throw new IllegalArgumentException("Invalid Input\n");
        }
        if(l_string.charAt(0) == '-')// if the first character is '-' then the number is 
        {
            l_string = l_string.substring(1);
            m_sign = false;
        }
        else// dont really need this but it makes things clear i guess
        {
            m_sign = true;
        }
        
        l_string = l_string.replaceAll("^0+(?!$)",""); // remove all leading zeros          
        m_value = l_string;
        m_digits = m_value.length();
        // if it is a negatie number we exclude the megative sign from
        // number of digits 
    }

    public AInteger(AInteger l_other)
    {
        m_value  = l_other.m_value;
        m_digits = l_other.m_digits;
        m_sign   = l_other.m_sign;
    }

    
    public AInteger Add(AInteger l_other)
    {
        // if one of them is zeor we just return the other number
        if(m_value.equals("0") )return new AInteger(l_other);
        else if(l_other.m_value.equals("0")) return new AInteger(this);
        if((m_sign != l_other.m_sign))// if one one of them is negative then we have to subtract
        {
            // first we make copies because we dont want to change 
            // original values
            AInteger copy      = new AInteger(this); 
            AInteger otherCopy = new AInteger(l_other);
            // but we want the signs to be positive
            copy.m_sign = true;
            otherCopy.m_sign = true;
   
            if(m_sign == false)
            {
                // this is -a + b == b - a
                return otherCopy.Subtract(copy);
            }
            else if(l_other.m_sign == false)
            {
                // this is a + (-b) = a - b;
                return copy.Subtract(otherCopy);
            }
        }
    
        AInteger l_answer = new AInteger(); // Initialize with 0
        int l_carry = 0;

        // Ensure m_digits is the larger number
        String l_longerValue  = m_value;
        String l_shorterValue = l_other.m_value;
        if (m_digits < l_other.m_digits) 
        {
            l_longerValue = l_other.m_value;
            l_shorterValue = m_value;
        }

        // Perform addition
        StringBuilder l_result = new StringBuilder();

        int i = l_longerValue.length() - 1;
        int j = l_shorterValue.length() - 1;

        // Add digit by digit from right to left
        while (i >= 0 || j >= 0 || l_carry != 0) 
        {
            int digit1 = (i >= 0) ? (l_longerValue.charAt(i) - '0') : 0;
            int digit2 = (j >= 0) ? (l_shorterValue.charAt(j) - '0') : 0;
            int sum = digit1 + digit2 + l_carry;
            l_carry = sum / 10;
            l_result.append(sum % 10);
            i--;
            j--;
        }

        // Reverse the result string
        l_answer.m_value  = l_result.reverse().toString();
        l_answer.m_digits = l_answer.m_value.length();
        l_answer.m_sign   = m_sign;
        // here we are making the sign of answer same as m_sing
        //this is because the control will only ever reach here
        // if m_sign and l_other.m_sign are same
        //then if both are posi answer will be posi
        // and same for negative
        return l_answer;

    }

    protected static int Compare(AInteger a, AInteger b)
    {
        // This will return 1 if a>b
        // 0 if a == b
        // -1 if a<b
        // Cuz if a == b then we dont want to go througth whole computation

        if(a.m_digits > b.m_digits)return 1;
        else if (b.m_digits > a.m_digits) return -1;
        else
        {
            // Now we know a and b have same number of digits
            // so we start comparing
            for(int i = 0; i < a.m_digits; ++i)
            {
                if(a.m_value.charAt(i) > b.m_value.charAt(i))return 1;
                else if(b.m_value.charAt(i) > a.m_value.charAt(i))return -1;
            }
            // we have check all the digits and never trigerre any of the 
            // situation that means that they both are equal
            return 0;
        }
    }
 
    public AInteger Subtract(AInteger l_other)
    {
        AInteger l_answer = new AInteger(); // Initialize with 0
        // if first argument is zero then we return 
        // the negative of the other value
        if(m_value.equals("0")) 
        {
            l_answer = new AInteger(l_other);
            l_answer.m_sign = !(l_other.m_sign);
            return l_answer;
        }
        else if(l_other.m_value.equals("0")) return new AInteger(this); 
        // second arg is 0 then just return the first argument
        
        else if(m_sign== false || l_other.m_sign == false)
        {
            // if the signs are not same 
            // then we handle those cases differently
            AInteger copy      = new AInteger(this);
            AInteger otherCopy = new AInteger(l_other);
            copy.m_sign = true;
            otherCopy.m_sign = true;

            if(m_sign == false && l_other.m_sign == false)
            {
                // this is -a - (-b)
                // which is b - a
                return otherCopy.Subtract(copy);
            }
            else if(m_sign == false)
            {
                // this is basically -a -b
                // so we can do ans = -(a+b)
                l_answer = copy.Add(otherCopy);
                // and now flip the sign of the answer`
                l_answer.m_sign = false;
                return l_answer;
            }
            else if(l_other.m_sign == false)
            {
                // this is  a - (-b)
                // which is a + b
                return copy.Add(otherCopy);
            }
        }
        
        int l_borrow = 0;
        // this tells us if the answer of subtraction will be negative
        boolean l_sign = true;
        //We always want to subtract smaller value from larger value 
        // and then add the negative dign accordingly
        String l_biggerValue = m_value;
        String l_smallerValue = l_other.m_value;

        if (Compare(this,l_other) == 0) 
        { return new AInteger("0"); }
        else if(Compare(this,l_other) == -1)
        {
            l_biggerValue = l_other.m_value;
            l_smallerValue = m_value;
            l_sign = false;
        }

        // Perform subtraction
        StringBuilder l_result = new StringBuilder();
        int i = l_biggerValue.length() - 1;
        int j = l_smallerValue.length() - 1;

        // Subtract digit by digit from right to left
        while (i >= 0 || j >= 0) 
        {
            int digit1 = (i >= 0) ? (l_biggerValue.charAt(i) - '0') : 0;
            // here digit1 will never be zero as we are making sure that
            // we are subtrating smaller value from bigger value
            int digit2 = (j >= 0) ? (l_smallerValue.charAt(j) - '0') : 0;
            int diff = 0;
            // what we are doing here is trying to imitate the very basic
            // subtraction algorithm
            // if digit1 is smaller than digits2 it borrows 10 from its 
            // left neighbour
            if((digit1-l_borrow) < digit2)
            {
                diff = 10+(digit1-l_borrow) - digit2;
                l_borrow = 1;
            }
            else
            {
                diff = (digit1-l_borrow) - digit2;
                l_borrow = 0;
            }
            l_result.append(diff);
            i--;
            j--;
        }
        // Reverse the result 

        l_result = l_result.reverse();

        while(l_result.length() > 1 && l_result.charAt(0) == '0')
        {
            l_result.deleteCharAt(0);
        }

        l_answer.m_value  = l_result.toString();
        l_answer.m_digits = l_answer.m_value.length();
        l_answer.m_sign   = l_sign;

        return l_answer;
    }

    public AInteger Mult(AInteger l_other)
    {
        if(m_value == "0" || l_other.m_value == "0")return new AInteger("0");
        // if either one is zero we return zero
        AInteger l_answer = new AInteger();
       
        int i = m_digits - 1;
        while(i >= 0)
        {
            int l_carry = 0;
            AInteger temp = new AInteger();
            StringBuilder result = new StringBuilder();

            for(int k = i; k < m_digits-1; ++k)result.append(0);

            int digit1 = (m_value.charAt(i) - '0');

            for(int j = l_other.m_digits - 1; j >= 0; --j)
            {
                int digit2 = (l_other.m_value.charAt(j) - '0');
                int prod = (digit1*digit2) + l_carry;
                l_carry = prod/10;
                result.append(prod%10);
            }

            if(l_carry != 0)result.append(l_carry);

            temp.m_value = result.reverse().toString();
            temp.m_digits = temp.m_value.length();
            temp.m_sign = true;
            
            l_answer = l_answer.Add(temp);
            --i;
        }

        l_answer.m_sign = (m_sign == l_other.m_sign);
        // if they have different signs then only the answer will be negative
        return l_answer;
    }

    public AInteger Divide(AInteger l_other)
    {
        l_other.Print();
        if(m_value.equals("0"))return new AInteger("0");
        else if(l_other.m_value.equals("1")) return new AInteger(this);
        else if(l_other.m_value.equals("0"))
        {
            System.out.print("Division by zero!\n");
            throw new IllegalArgumentException("Division by Zero!");
        }
        System.out.print("its getting here\n");
    
        // We could use the newton raphson method that is 
        // implemented in float and just output the part before decimal

        AFloat a = AFloat.Parse(m_value);
        AFloat b = AFloat.Parse(l_other.m_value);
        AFloat c = a.Divide(b);
    
        AInteger l_answer = new AInteger(c.m_beforeDeci);
        return l_answer;
    }

    static public AInteger Parse(String l_string) // returns an instance of AInterger
    {
        return new AInteger(l_string);
    }

    public void Print()
    {
        if(!m_sign)System.out.print('-');
        System.out.print(m_value);
        System.out.print("\n");
    }

    public String GetValue()
    {
        return (m_sign ?m_value : '-'+m_value);
    }
}

