import org.junit.Test;

import arbritraryarithmetic.AInteger;

import static org.junit.Assert.*;

public class MyTest 
{
    @Test
    public void testAdd()
    {
        AInteger a = new AInteger("123456789");
        AInteger b = new AInteger("987654321");
        AInteger result = a.Add(b);
        assertEquals("1111111110",result.GetValue());
    }
}
