package telasi.android.reading.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ReesterParserTest extends TestCase {

  public void testParsing() throws Exception {
    InputStream in = new FileInputStream("data/reester.xml");
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);

      Reester r = new ReesterParser().parse(xpp);
      assertNotNull(r);
      assertEquals(80, r.getId());
      assertEquals(Config.parseDate("01-Jun-2012"), r.getCycleDate());
      assertEquals(11795, r.getInspectorId());
      assertEquals(99, r.getDownloads());
      assertEquals(52, r.getUploads());
      assertEquals(2, r.getStatus());
      assertEquals(349201, r.getSchedule());
      assertEquals(11724, r.getRoute());
      assertEquals("დიდუბე ბლ.20 მარშ. 2", r.getRouteName());
      assertEquals(9, r.getRegionId());
      assertEquals("დელისი", r.getRegionName());
      assertEquals(523, r.getBlockId());
      assertEquals("დელისი ბლ.1", r.getBlockName());
      assertEquals(265, r.getCount());

      ReesterItem[] items = r.getItems();
      assertEquals(265, items.length);

      ReesterItem item = items[0];
      assertEquals(66973867, item.getId());

      assertEquals(1, item.getSequence());

      Account acc = item.getAccount();
      assertNotNull(acc);
      assertEquals(0, acc.getStatus());
      assertFalse(acc.isCut());
      assertEquals(269179, acc.getCustkey());
      assertEquals(269239, acc.getAcckey());
      assertEquals("3769675", acc.getAccountNumber());
      assertEquals("3769675-1", acc.getAccountID());
      assertEquals("ადამაშვილი გ ბ", acc.getCustomerName());

      Address address = acc.getAddress();
      assertNotNull(address);
      assertEquals("0177, ნუცუბიძის ქ. კორპ. 1 №42, სად. 1, ბ. 001", address.getFullAddress());
      assertEquals(361, address.getStreetId());
      assertEquals("ნუცუბიძის ქ.", address.getStreetName());
      assertEquals("42", address.getHouse());
      assertEquals("1", address.getBuilding());
      assertEquals("1", address.getPorch());
      assertEquals("001", address.getFlate());

      assertEquals(274, acc.getInstalledCapacity(), 0.001);
      assertEquals(0, acc.getMinCharge(), 0.001);
      assertEquals(1500, acc.getMaxCharge(), 0.001);

      Meter meter = item.getMeter();
      assertNotNull(meter);
      assertEquals("172088", meter.getNumber());
      assertTrue(meter.isActive());
      assertEquals(null, meter.getSealNumber());
      assertTrue(meter.isSealActive());
      assertEquals(5, meter.getDigits());
      assertEquals(1, meter.getCoeff());
      assertEquals("M2X 10/60", meter.getType());
      assertFalse(meter.isWithout());

      Reading reading = item.getReading();
      assertNotNull(reading);
      assertTrue(reading.isReadingConfirmed());
      assertEquals(432123, reading.getReading(), 0.001);
      assertEquals(15031, reading.getPreviousReading(), 0.001);
      assertEquals(Config.parseDate("01-Jun-2012"), reading.getPreviousReadingDate());
      assertEquals(14483, reading.getPreviousRealReading(), 0.001);
      assertEquals(Config.parseDate("01-Mar-2011"), reading.getPreviousRealReadingDate());

      HashMap<Integer, String> streets = r.getStreets();
      assertNotNull(streets);
      assertFalse(streets.isEmpty());
      assertEquals(1, streets.size());
      assertEquals("ნუცუბიძის ქ.", streets.get(361));

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

  public void testParsingWithMultipleRoutes() throws Exception {
    InputStream in = new FileInputStream("data/reesters.xml");
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);
      List<Reester> reesters = new ReesterParser().parseReesters(xpp);
      assertFalse(reesters.isEmpty());
      assertEquals(10, reesters.size());

      Reester reester = reesters.get(0);

      assertEquals(96, reester.getId());
      assertEquals(12024, reester.getRoute());
      assertEquals(200, reester.getBlockId());
      assertEquals("დიდუბე ბლ.20 მარშ. 2", reester.getRouteName());

    } finally {
      in.close();
    }
  }
}
