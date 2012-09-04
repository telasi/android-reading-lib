package telasi.android.reading;

import java.util.Calendar;

import junit.framework.TestCase;

public class DateTest extends TestCase {

  public void testDateFormat01() {
    Calendar c = Calendar.getInstance();
    c.set(2012, 0, 4);
    assertEquals("4-Jan-2012", Config.formatDate(c.getTime()));
  }

  public void testDateFormat02() {
    Calendar c = Calendar.getInstance();
    c.set(2012, 3, 14);
    assertEquals("14-Apr-2012", Config.formatDate(c.getTime()));
  }
}
