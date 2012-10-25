package telasi.android.reading.model;

import junit.framework.TestCase;

public class ReadingControllerTest extends TestCase {

  public void testSendReester() throws Exception {
    String username = "dimitri";
    String password = "dima123";
    // int inspectorId = 11795;
    // getting reester
    Reester reester = ReadingController.getReesterOverHTTP(username, password, 96);
    // update reester & send it back
    ReesterItem item = reester.getItems()[0];
    item.getMeter().setNewCoeff(100);
    item.getMeter().setNewNumber("666666");
    item.getMeter().setNewSealNumber("7777777");
    ReadingController.sendReesterOverHTTP(reester, username, password);
    // getting reester again
    reester = ReadingController.getReesterOverHTTP(username, password, 96);
    item = reester.getItems()[0];
    assertEquals(100, item.getMeter().getNewCoeff().intValue());
    assertEquals("666666", item.getMeter().getNewNumber());
    assertEquals("7777777", item.getMeter().getNewSealNumber());
  }

}
