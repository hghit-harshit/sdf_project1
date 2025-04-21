import org.junit.Test;

import arbritraryarithmetic.AFloat;
import arbritraryarithmetic.AInteger;
import static org.junit.Assert.*;


public class MyTest 
{
    @Test
    public void testIntAdd()
    {
        AInteger a = new AInteger("123456789");
        AInteger b = new AInteger("987654321");
        AInteger result = a.Add(b);
        assertEquals("1111111110",result.GetValue());

        a = AInteger.Parse("23650078224912949497310933240250");
        b = AInteger.Parse("42939783262467113798386384401498");
        result = a.Add(b);
        assertEquals("66589861487380063295697317641748",result.GetValue());


    }

    @Test
    public void testIntSub()
    {
        AInteger a = AInteger.Parse("3116511674006599806495512758577");
        AInteger b = AInteger.Parse("57745242300346381144446453884008");
        AInteger result = a.Subtract(b);
        assertEquals("-54628730626339781337950941125431",result.GetValue());
    }

    @Test
    public void testIntMult()
    {
        AInteger a = new AInteger("14344163160445929942680697312322 ");
        AInteger b = new AInteger("23017167694823904478474013730519");
        AInteger result = a.Mult(b);
        assertEquals("330162008905899217578310782382075660760972861550182008086155118",result.GetValue());
    }

    @Test
    public void testIntDiv()
    {
        AInteger a = new AInteger("8792726365283060579833950521677211");
        AInteger b = new AInteger("493835253617089647454998358");
        AInteger result = a.Divide(b);
        assertEquals("17804979",result.GetValue());
    }

    @Test 
    public void testFloatAdd()
    {
        AFloat a = AFloat.Parse("84486723.420039");
        AFloat b = AFloat.Parse("70974199.843732");
        AFloat result = a.Add(b);
        assertEquals("155460923.263771",result.GetValue());
    }

    @Test 
    public void testFloatSub()
    {
        AFloat a = AFloat.Parse("840196454.51725");
        AFloat b = AFloat.Parse("712586963.70283");
        AFloat result = a.Subtract(b);
        assertEquals("127609490.81442",result.GetValue());
    }

    @Test 
    public void testFloatMult()
    {
        AFloat a = AFloat.Parse("6400251.9377695");
        AFloat b = AFloat.Parse("2326541.6827934");
        AFloat result = a.Mult(b);
        assertEquals("14890452913599.9717457253213",result.GetValue());
    }
}
