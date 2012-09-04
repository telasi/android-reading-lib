package telasi.android.reading;

import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

  public void testParsing() throws Exception {
    InputStream in = new FileInputStream("data/reester.xml");

    Reester r = new ReesterParser().parse(in);
    assertNotNull(r);
    assertEquals(8978, r.getId());
    assertEquals(Config.parseDate("01-Jun-2012"), r.getCycleDate());
    assertEquals(11795, r.getInspectorId());
    assertEquals(2, r.getDownloads());
    assertEquals(1, r.getUploads());
    assertEquals(1, r.getStatus());

    ReesterItem[] items = r.getItems();
    assertEquals(365, items.length);
    ReesterItem item = items[0];
    assertEquals(66953206, item.getId());
    assertEquals(4267, item.getRoute());
    assertEquals(1, item.getSequence());
    assertEquals(349201, item.getSchedule());

    Account acc = item.getAccount();
    assertNotNull(acc);
    assertEquals(2, acc.getStatus());
    assertTrue(acc.isCut());
    assertEquals(268997, acc.getCustkey());
    assertEquals(269057, acc.getAcckey());
    assertEquals("3735159", acc.getAccountNumber());
    assertEquals("3735159-1", acc.getAccountID());
    assertEquals("ბუბუტეიშვილი ლ ლ", acc.getCustomerName());
    assertEquals(274, acc.getInstalledCapacity(), 0.001);
    assertEquals(0, acc.getMinCharge(), 0.001);
    assertEquals(1500, acc.getMaxCharge(), 0.001);

    Meter meter = item.getMeter();
    assertNotNull(meter);
    assertEquals("173151", meter.getNumber());
    assertTrue(meter.isActive());
    assertEquals("0144391", meter.getSealNumber());
    assertTrue(meter.isSealActive());
    assertEquals(5, meter.getDigits());
    assertEquals(1, meter.getCoeff());

    Reading reading = item.getReading();
    assertNotNull(reading);
    assertEquals(14440, reading.getReading(), 0.001);
    assertEquals(14239, reading.getPreviousReading(), 0.001);
    assertEquals(Config.parseDate("01-Mar-2011"), reading.getPreviousReadingDate());
    assertEquals(14239, reading.getPreviousRealReading(), 0.001);
    assertEquals(Config.parseDate("01-Mar-2011"), reading.getPreviousRealReadingDate());
  }

}
