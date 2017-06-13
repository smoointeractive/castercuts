package sample;

/**
 * Created by sachamoo on 6/12/17.
 */
public class SimpleClassWithDependency {

    private TestClassC essentialTest;
    private TestClass wowTest;

    public SimpleClassWithDependency( TestClassC test, TestClass test2)
    {
        essentialTest = test;
        wowTest = test2;
    }

    public int SomeIntValue()
    {
        if(null == essentialTest && null == wowTest) return -1;

        essentialTest.setC(2487632);
        return essentialTest.AddAbc();
    }

    public int SomeOtherIntValue()
    {
        if(null == essentialTest && null == wowTest) return -1;

        return wowTest.AddAbc();
    }
}
