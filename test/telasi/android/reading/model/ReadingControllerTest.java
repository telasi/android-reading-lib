package telasi.android.reading.model;

import junit.framework.TestCase;

public class ReadingControllerTest extends TestCase {

  public void testLogin() throws Exception {
    User user = ReadingController.login("dimitri", "dima123");
    assertNotNull(user);
  }
  
}
