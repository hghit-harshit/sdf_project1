import org.junit.Test;
import org.junit.Ignore;
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

    @Test
    public void testFloatDiv()
    {
        AFloat a = AFloat.Parse("8792726365283060579833950521677211.0");
        AFloat b = AFloat.Parse("493835253617089647454998358");
        AFloat result = a.Divide(b);
        result.Print();
        // String answer = "17804979.091469989302961159520087878533"+
        // "68052180603671167924683984130815398609236273785506830355"+
        // "402448624808416473436067720971642607709808710597044219662"+
        // "7540469031041948493652479229112208111436434654956";

        String answer = "17804979.09146998930296115952008787853368052"+
        "180603671167924683984130815398609236273785506830355402448624808"+
        "41647343606772097164260770980871059704421966275404690310419484936524"+
        "792291122081114364346549556545882660781207813774888532811754824839332062"+
        "8873212038521831796175097178393756991630649289371952080945699657311013647"+
        "3326038307712047961602766980010231085579717450527800616443092684228952"+
        "820563172846254925623320881862554037703915598346908546961836144564605"+
        "85618618253863295895863377395676717501551270102383278048566044232789204"+
        "11428531576952905223712556782595753214567885264076839274276581458783725"+
        "007997049339590564001773039514569197146273396379287326180178199234879446759"+
        "263821354818486190313631988717580298294302474895692839891956269022715404212219"+
        "1637462495808077226223555728699080604566203747759011424763643982500049461707165"+
        "22349708021931347742079338576194807827235770219027976360871689846375375514447732"+
        "15797726261550659156742560376120793663727977331823741302704956482022264257450617"+
        "7375868596076077";
        assertEquals(answer,result.GetValue());

        
    }
}
