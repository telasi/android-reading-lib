package telasi.android.reading.model;

import java.io.FileInputStream;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import telasi.android.reading.model.Account;
import telasi.android.reading.model.Meter;
import telasi.android.reading.model.Reading;
import telasi.android.reading.model.Reester;
import telasi.android.reading.model.ReesterItem;
import telasi.android.reading.model.ReesterParser;

import junit.framework.TestCase;

public class ReesterParserTest extends TestCase {

  public void testParsing() throws Exception {
    InputStream in = new FileInputStream("data/reester.xml");
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);

      Reester r = new ReesterParser().parse(xpp);
      assertNotNull(r);
      assertEquals(1, r.getId());
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
      assertEquals("0177, ზაქარიაძის ქ. №05, სად. 1, ბ. 001", acc.getAddress());
      
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
    } finally {
      in.close();
    }
  }

  public void testParsingWithException() throws Exception {
    InputStream in = new FileInputStream("data/error.xml");
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);

      DownloadException de = null;
      try {
        new ReesterParser().parse(xpp);
      } catch (DownloadException ex) {
        de = ex;
      }
      assertNotNull(de);
      assertEquals("error message", de.getMessage());

    } finally {
      in.close();
    }
  }

}
