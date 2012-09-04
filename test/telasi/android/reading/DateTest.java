package telasi.android.reading;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class DateTest extends TestCase {
  
  Date getDate(int year, int month, int day) {
    Calendar c = Calendar.getInstance();
    c.set(year, month, day, 0, 0, 0);
    c.set(Calendar.MILLISECOND, 0);
    return c.getTime();
  }

  public void testDateFormat01() {
    assertEquals("4-Jan-2012", Config.formatDate(getDate(2012, 0, 4)));
  }

  public void testDateFormat02() {
    Calendar c = Calendar.getInstance();
    c.set(2012, 3, 14);
    assertEquals("14-Apr-2012", Config.formatDate(getDate(2012, 3, 14)));
  }

  public void testDateParsing01() throws ParseException {
    Date d1 = Config.parseDate("01-Jan-2012");
    Date d2 = getDate(2012, 0, 1);
    assertEquals(d2, d1);
  }

}
