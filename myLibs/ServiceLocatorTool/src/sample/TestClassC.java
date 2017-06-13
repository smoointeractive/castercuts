package sample;

/**
 * Created by sachamoo on 6/12/17.
 */
public class TestClassC {
    private int a = 20;
    private int b = 2;
    private int c = 34;


    public int AddAbc()
    {
        return a + b + getC();
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
