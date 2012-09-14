package telasi.android.reading.model;

import junit.framework.TestCase;

public class ReadingControllerTest extends TestCase {

  // public void testLogin() throws Exception {
  // User user = ReadingController.login("dimitri", "dima123");
  // assertNotNull(user);
  // }

  public void testSendReester() throws Exception {
    String username = "dimitri";
    String password = "dima123";
    Reester reester = ReadingController.getReesterOverHTTP(username, password, "1-Jun-2012");
    assertNotNull(reester);
    Information info = ReadingController.sendReesterOverHTTP(reester, username, password);
    assertNotNull(info);
    assertEquals("რეესტრი ატვირთულია.", info.getMessage());
  }
  
}
