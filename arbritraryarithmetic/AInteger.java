package arbritraryarithmetic;

class AInteger
{
    private String m_value; // the value of the integer
    private int m_digits; // number of digits in the number

    public AInteger() // default constructor
    {
        m_value = "0";
    }

    public AInteger(String l_string)
    {
        m_value = l_string;
        if(l_string.charAt(0) != '-')m_digits = l_string.length();
        else m_digits = l_string.length() - 1;
        // if it is a negatie number we exclude the megative sign from
        // number of digits 
    }

    public AInteger(AInteger l_other)
    {
        m_value = l_other.m_value;
    }

    public AInteger Add(AInteger l_other)
    {
        // AInterger l_answer = Parse("");
        // int l_carry = 0;
        // if(m_digits >= l_other.digits)
        // {
        //     for(int i = l_other.m_digits-1; i >= 0; --i)
        //     {
        //         int l_total = (m_value[i] - '0') + (l_other.m_value[i] - '0') + l_carry;
        //         l_carry = l_total%10;
        //         l_answer = String.valueOf((l_total/10)) + l_answer;
        //     }
            
        //     int i = m_digits - l_other.m_digits - 1;
        //     while(i >= 0)
        //     {
        //         int l_total = (m_value[i] - '0') + l_carry;
        //         l_carry = l_total%10;
        //         l_answer = String.valueOf(l_total/10) + l_answer;
        //     }

        //     if(l_carry != 0)
        //     {
        //         l_answer = String.valueof(l_carry) + l_answer;
        //     }
            
        // }
        // else if(l_other.m_digits > m_digits)
        // {
        //     for(int i = m_digits-1; i >= 0; --i)
        //     {
        //         int l_total = (m_value[i] - '0') + (l_other.m_value[i] - '0') + l_carry;
        //         l_carry = l_total%10;
        //         l_answer = String.valueof((l_total/10)) + l_answer;
        //     }

        //     int i = l_other.m_digits - m_digits - 1;
        //     while(i >= 0)
        //     {
        //         int l_total = (l_other.m_value[i] - '0') + l_carry;
        //         l_carry = l_total%10;
        //         l_answer = String.valueOf(l_total/10) + l_answer;
        //     }

        //     if(l_carry != 0)
        //     {
        //         l_answer = String.valueof(l_carry) + l_answer;
        //     }
        // }

        AInteger l_answer = new AInteger(); // Initialize with 0
        int l_carry = 0;

        // Ensure m_digits is the larger number
        String l_longerValue = m_value;
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
        l_answer.m_value = l_result.reverse().toString();
        l_answer.m_digits = l_answer.m_value.length();

        return l_answer;

    }

    private int Compare(AInteger a, AInteger b)
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
            for(int i = 0; i < a.size(); ++i)
            {
                if(a.charAt(i) > b.charAt(i))return 1;
                else if(b.charAt(i) > a.charAt(i))return -1;
            }
            // we have check all the digits and never trigerre any of the 
            // situation that means that they both are equal
            return 0;
        }
    }
 
    public AInteger Subtract(AInteger l_other)
    {
        AInteger l_answer = new AInteger(); // Initialize with 0
        int l_borrow = 0;
        // this tells us if the answer of subtraction will be negative
        boolean l_isNegative = false;
        //We always want to subtract smaller value from larger value 
        // and then add the negative dign accordingly
        String l_biggerValue = m_value;
        String l_smallerValue = l_other.m_value;

        if (Compare(m_value,l_other.m_value) == 0) 
        { return new AInteger("0"); }
        else if(Comapare(m_value,l_other.m_value) == -1)
        {
            l_biggerValue = l_other.m_value;
            l_smallerValue = m_value;
            l_isNegative = true;
        }

        // Perform subtraction
        StringBuilder l_result = new StringBuilder();
        int i = l_longerValue.length() - 1;
        int j = l_shorterValue.length() - 1;

        // Subtract digit by digit from right to left
        while (i >= 0 || j >= 0 || l_carry != 0) 
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
                diff = 10*(digit1-l_carry) - digit2;
                l_carry = 1;
            }
            else
            {
                diff = (digit1-l_borrow) - digit2;
                l_carry = 0;
            }
            l_result.append(diff);
            i--;
            j--;
        }

        // Reverse the result 
        if(l_isNegative)l_result.append('-');
        l_answer.m_value = l_result.reverse().toString();
        l_answer.m_digits = l_answer.m_value.length() - (l_isNegative? 1 : 0);

        return l_answer;
    }

    public AInteger Mult(AInteger l_other)
    {
        AInteger l_answer = new AInteger();
        int l_carry = 0;
        boolean l_isNegative = false;
        if(m_value.charAt(0) == '-' ^ l_other.m_value.charAt(0) == '-')l_isNegative = true;
        
        // we will do somethign very similar to what we did in addition
        
    }
    static public AInteger Parse(String l_string) // returns an instance of AInterger
    {
        return new AInteger(l_string);
    }
}

