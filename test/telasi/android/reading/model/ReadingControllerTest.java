package telasi.android.reading.model;

import java.util.List;

import junit.framework.TestCase;

public class ReadingControllerTest extends TestCase {

  public void testSendReester() throws Exception {
    String username = "dimitri";
    String password = "dima123";
    int inspectorId = 11795;
    List<Reester> reester = ReadingController.getReestersOverHTTP(username, password, inspectorId, 1);
    assertNotNull(reester);
    // route name
    String rtname = reester.get(0).getRouteName();
    assertNotNull(rtname);
  }
}
