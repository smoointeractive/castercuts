package pl.czak.gwtsample;

import pl.czak.gwtsample.client.SampleAppTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SampleAppSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for SampleApp");
    suite.addTestSuite(SampleAppTest.class);
    return suite;
  }
}
