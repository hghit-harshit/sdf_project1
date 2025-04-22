package arbritraryarithmetic;
import arbritraryarithmetic.AInteger;

public class AFloat 
{
    // we will treat floast as two integers before and 
    // after the decimal point
    String m_beforeDeci;
    String m_afterDeci;
    boolean m_sign; 
    int m_digitsBefore;
    int m_digitsAfter;
    int m_precision; // basically how many digits we want after decimal

    public AFloat()
    {
        m_beforeDeci = "0";
        m_afterDeci  = "0";
    }
    public AFloat(String l_string)
    {
        // l_string = 
        // int l_index = l_string.indexOf('.');
       

        // first we'll remove all the spaces from the string
        l_string = l_string.replaceAll("\\s+", "");
        //l_string = l_string.trim().replaceAll("[^\\d.-]", "");

        // then we check if the string is in valid form
        if(!l_string.matches("^-?\\d+(\\.\\d+)?$"))
        {
            System.out.print("integer me problem h\n");
            System.out.print("Invalid Entry");
        }

        if(l_string.charAt(0) == '-')
        {
            m_sign   = false;
            l_string = l_string.substring(1);
        }
        else m_sign = true;

        int l_index = l_string.indexOf('.');
        if(l_index != -1)
        {
            m_beforeDeci = l_string.substring(0,l_index);
            // now we remove any leading zeros
            m_beforeDeci = m_beforeDeci.replaceAll("^0+(?!$)","");

            m_afterDeci  = l_string.substring(l_index+1);
            // now we remove trailing zero
            m_afterDeci  = m_afterDeci.replaceAll("0+$","");
            if(m_afterDeci == "")m_afterDeci = "0";
            // this will happen when there nothing after decimal
            
        }
        else
        {
            // there is no decimal point
            m_beforeDeci = l_string;
            m_afterDeci  = "0";
        }
        m_digitsBefore = m_beforeDeci.length();
        m_digitsAfter  = m_afterDeci.length();
    }
    
    public AFloat(AFloat l_other)
    {
        m_afterDeci    = l_other.m_afterDeci;
        m_beforeDeci   = l_other.m_beforeDeci;
        m_digitsBefore = l_other.m_digitsBefore;
        m_digitsAfter  = l_other.m_digitsAfter;
        m_sign         = l_other.m_sign;
    }

    public AFloat Add(AFloat l_other)
    {
        // first we add after deci
        int l_carry = 0;
        // String l_shorterString = m_afterDeci;
        // String l_longerString  = l_other.m_afterDeci;
        // if(m_digitsAfter > l_other.m_digitsAfter)
        // {
        //     l_shorterString = l_other.m_afterDeci;
        //     l_longerString  = m_afterDeci; 
        // }
        AFloat l_answer = new AFloat();
        int index = Math.min(m_digitsAfter,l_other.m_digitsAfter) - 1;
        int i = index;
        StringBuilder l_result = new StringBuilder();
        // we what we are doing here is suppose we want to add say
        // xyz.39 and abc.3455
        // so well just add 39 and 34 and append 55 in the afterDeci part of the answer
        while(i >= 0)
        {
            int digit1 = m_afterDeci.charAt(i) - '0';
            int digit2 = l_other.m_afterDeci.charAt(i) - '0';
            int sum = digit1 + digit2 + l_carry;
            l_carry = sum / 10;
            l_result.append(sum % 10);
            i--;
        }
        // now we append remaining numbers into result
        l_result = l_result.reverse();
        if(m_digitsAfter < l_other.m_digitsAfter)
        { l_result.append(l_other.m_afterDeci.substring(index + 1)); }
        else 
        { l_result.append(m_afterDeci.substring(index + 1)); }

        AInteger copy = new AInteger(m_beforeDeci);
        AInteger otherCopy = new AInteger(l_other.m_beforeDeci);
        //System.out.print("this is causing problems\n");
        copy = copy.Add(new AInteger(String.valueOf(l_carry))); // we could use Parse here
        l_answer.m_beforeDeci = copy.Add(otherCopy).m_value; // change it to get value
        l_answer.m_afterDeci = l_result.toString();
        l_answer.m_sign = true;
        return l_answer;
        
    }

    private int Compare(AFloat a , AFloat b)
    {
        // This will return 1 if a>b
        // 0 if a == b
        // -1 if a<b
        // Cuz if a == b then we dont want to go througth whole computation
        if(a.m_digitsBefore > b.m_digitsBefore)return 1;
        else if(b.m_digitsBefore > a.m_digitsBefore)return -1;
        else
        {
            // first we compare the part before integer
            for(int i = 0; i < a.m_digitsBefore; ++i)
            {
                if(a.m_beforeDeci.charAt(i) > b.m_beforeDeci.charAt(i))return 1;
                else if(b.m_beforeDeci.charAt(i) > a.m_beforeDeci.charAt(i))return -1;
            }

            // now we compare part after decimal point
            int  j = Math.min(a.m_digitsAfter,b.m_digitsAfter);
            // basically we are checking till the least significant digits
            // of the number with less digits after the decimal
            for(int i = 0; i < j; ++i)
            {
                if(a.m_afterDeci.charAt(i) >b.m_afterDeci.charAt(i))return 1;
                else if (a.m_afterDeci.charAt(i) < b.m_afterDeci.charAt(i)) return -1;
            }
            // now we check if all the digits of the number with more digits after 
            // are decimal or not
            int k = Math.max(a.m_digitsAfter,b.m_digitsAfter);
            
            for(int i = j; i < k; ++i)
            {
                char t = (a.m_digitsAfter > b.m_digitsAfter) ? a.m_afterDeci.charAt(i) : b.m_afterDeci.charAt(i);
                if(t != '0') return ((a.m_digitsAfter > b.m_digitsAfter) ? 1 : -1);

            }

            return 0;
        }

    }

    public AFloat Subtract(AFloat l_other)
    {
        AFloat l_answer = new AFloat();

        // again we always subtract smaller number from smaller
        // first we subtract digits after decimal
        int index = Math.max(m_digitsAfter, l_other.m_digitsAfter)-1;
        int i = index;
        StringBuilder l_result = new StringBuilder();
        int l_borrow = 0;
        while(i >= 0)
        {
            int digit1 = (i < m_digitsAfter) ? (m_afterDeci.charAt(i) - '0') : 0;
            int digit2 = (i < l_other.m_digitsAfter) ? (l_other.m_afterDeci.charAt(i) - '0') : 0;
            // int sum = digit1 + digit2 + l_carry;
            // l_carry = sum / 10;
            // l_result.append(sum % 10);
            // i--;
            int diff = 0;
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
            --i;
        }
        
        
        // now we subtract the digits before decimal
        // now first subtract the borrow and and then subtract them
        AInteger copy = new AInteger(m_beforeDeci);
        AInteger otherCopy = new AInteger(l_other.m_beforeDeci);

        copy = copy.Subtract(new AInteger(String.valueOf(l_borrow)));
        l_answer.m_beforeDeci = copy.Subtract(otherCopy).m_value;
        l_answer.m_afterDeci = l_result.reverse().toString();
        l_answer.m_sign = (Compare(this,l_other) == -1) ? false : true; // we decide the sign
        l_answer.m_digitsBefore = l_answer.m_beforeDeci.length();
        l_answer.m_digitsAfter = l_answer.m_afterDeci.length();
        return l_answer;
    }


    public AFloat Mult(AFloat l_other)
    {
        AFloat l_answer = new AFloat();
        // just multiply them as integer and replace 
        // the decimal's place
        AInteger copy = new AInteger(m_beforeDeci + m_afterDeci);
        AInteger otherCopy = new AInteger(l_other.m_beforeDeci + l_other.m_afterDeci);
        
        // copy.Print();
        // otherCopy.Print();

        AInteger l_result = copy.Mult(otherCopy);
        // now we caculate the place of the decimal point
        int deci = m_digitsAfter + l_other.m_digitsAfter;
        if(deci > l_result.m_digits)
        {
            //this is cases like 0.000005
            // we pad afterDeci with more zeroes
            StringBuilder paddedValue = new StringBuilder(l_result.m_value);
            for(int i = 0; i < deci - l_result.m_digits; ++i)
            {
                paddedValue.insert(0,"0");
            }
            l_result.m_value = paddedValue.toString();
            l_result.m_digits = l_result.m_value.length();
        }
        // trimming trailing zeroes
        l_answer.m_afterDeci = (l_result.m_value.substring(l_result.m_digits - deci)).replaceAll("0+$","");
        if(l_answer.m_afterDeci == "")l_answer.m_afterDeci = "0";
        
        l_answer.m_beforeDeci = l_result.m_value.substring(0,l_result.m_digits - deci);
        if(l_answer.m_beforeDeci == "")l_answer.m_beforeDeci = "0";
       
        l_answer.m_sign = (m_sign == l_other.m_sign);
        
        l_answer.m_digitsBefore = l_answer.m_beforeDeci.length();
        l_answer.m_digitsAfter = l_answer.m_afterDeci.length();
         
        return l_answer;
    }

   
    // we would try to implement the divison usig newtow raphson method
    public AFloat Divide(AFloat l_other)
    {
        
        // we are going to implement newton raphson method
        // which basically approximate value of 1/l_other
        // here we iterate with xi+1 = xi(2 - xi*l_other) untill we converge
        // to a satisfactory answer
        // and presision almost doubles with every iteration 
        // so for precision upto 1000 digits 10 iteration ought to be enough
        // but we will do upto 12 iterations
        StringBuilder l_initialGuess = new StringBuilder("1");
        for(int i = 0; i< l_other.m_digitsBefore; ++i)
        { l_initialGuess.insert(0,"0"); }
        l_initialGuess.insert(0,"0.");
        AFloat l_answer = new AFloat(l_initialGuess.toString());
        // initial guess
        // based on number of digits in l_other
        AFloat l_two = new AFloat("2.0");
        int i = 0;
        System.out.print("is it coming here\n");
        while(i < 20)
        {
            // l_answer = l_answer.Mult(l_two.Subtract(l_answer.Mult(l_other)));
            
            // l_answer.Print();
            // i++;

            AFloat mult = l_other.Mult(l_answer); // b * xₙ
            //mult.Print();
            AFloat sub = l_two.Subtract(mult);    // (2 - b * xₙ)
            //sub.Print();
            l_answer = l_answer.Mult(sub);    // xₙ₊₁ = xₙ * (2 - b * xₙ)
            //l_answer.Print();
            i++;
            
            // we are trimmin upto 1500 to get accurary of 1000 on final result 
            // if we only trim till 1000 its not accurate upto 1000
            if (l_answer.m_afterDeci.length() > 1500) 
            {
                l_answer.m_afterDeci = l_answer.m_afterDeci.substring(0, 1500);
                l_answer.m_digitsAfter = 1500;
            }
        }

        l_answer =  this.Mult(l_answer);

        if (l_answer.m_afterDeci.length() > 1000)// trimming upto 1000 digits after decimal
        {
            l_answer.m_afterDeci = l_answer.m_afterDeci.substring(0, 1000);
            l_answer.m_digitsAfter = 1000;
        }

        return l_answer;
    }
    public void Print()
    {
        if(!m_sign)System.out.print('-');
        System.out.print(m_beforeDeci);
        System.out.print('.');
        System.out.print(m_afterDeci);
        System.out.print("\n");
    }

    static public AFloat Parse(String l_string)
    {
        return new AFloat(l_string);
    }

    public String GetValue()
    {
        return (m_beforeDeci + '.' + m_afterDeci);
    }
}
