package telasi.android.reading;

import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

  public void testParsing() throws Exception {
    InputStream in = new FileInputStream("data/reester.xml");
    Reester r = new ReesterParser().parse(in);
    assertNotNull(r);
    // assertNotSame(0, r.getId());
  }

}
