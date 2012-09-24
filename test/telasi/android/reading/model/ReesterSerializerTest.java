package telasi.android.reading.model;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class ReesterSerializerTest extends TestCase {

  private ReesterItem newItem(int id, double reading) {
    ReesterItem item = new ReesterItem();
    item.setId(id);
    item.setReading(newReading(reading));
    return item;
  }

  private Reading newReading(double reading) {
    Reading r = new Reading();
    r.setReading(reading);
    return r;
  }

  public void testShortReesterSerializer() throws Exception {
    Reester reester = new Reester();
    reester.setId(100);
    reester.addItem(newItem(1, 1300));
    reester.addItem(newItem(2, 1500));

    XmlSerializer xps = XmlPullParserFactory.newInstance().newSerializer();
    String xmlText = new ReesterSerializer().reesterSerialization(xps, reester, false);
    assertTrue(xmlText.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
    assertTrue(xmlText.contains("<id>100</id>"));
    assertTrue(xmlText.contains("<id>1</id>"));
    assertTrue(xmlText.contains("<reading>1300.0</reading>"));
    assertTrue(xmlText.contains("<id>2</id>"));
    assertTrue(xmlText.contains("<reading>1500.0</reading>"));
  }

  public void testFullReesterSerializer() throws Exception {
    // getting reester from xml
    InputStream in = new FileInputStream("data/reester.xml");
    Reester reester1 = null;
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);
      reester1 = new ReesterParser().parse(xpp);
    } finally {
      in.close();
    }
    // serialize full reester
    XmlSerializer xps = XmlPullParserFactory.newInstance().newSerializer();
    String reesterXML = new ReesterSerializer().reesterSerialization(xps, reester1, true);
    // getting second reester from serialized one
    InputStream in2 = new ByteArrayInputStream(reesterXML.getBytes("UTF-8"));
    Reester reester2 = null;
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in2, null);
      reester2 = new ReesterParser().parse(xpp);
    } finally {
      in.close();
    }
    // compare reesters
    assertEquals(reester1.getId(), reester2.getId());
    assertEquals(reester1.getCycleDate(), reester2.getCycleDate());
    assertEquals(reester1.getInspectorId(), reester2.getInspectorId());
    assertEquals(reester1.getDownloads(), reester2.getDownloads());
    assertEquals(reester1.getUploads(), reester2.getUploads());
    assertEquals(reester1.getStatus(), reester2.getStatus());
    assertEquals(reester1.getSchedule(), reester2.getSchedule());
    assertEquals(reester1.getBlockId(), reester2.getBlockId());
    assertEquals(reester1.getBlockName(), reester2.getBlockName());
    assertEquals(reester1.getRegionId(), reester2.getRegionId());
    assertEquals(reester1.getRegionName(), reester2.getRegionName());
    assertEquals(reester1.getItems().length, reester2.getItems().length);
    assertEquals(reester1.getRoute(), reester2.getRoute());
    assertEquals(reester1.getCount(), reester2.getCount());
    for (int i = 0; i < reester1.getItems().length; i++) {
      ReesterItem item1 = reester1.getItems()[i];
      ReesterItem item2 = reester2.getItems()[i];
      assertEquals(item1.getId(), item2.getId());
      assertEquals(item1.getSequence(), item2.getSequence());
      Account acc1 = item1.getAccount();
      Account acc2 = item2.getAccount();
      assertEquals(acc1.getAcckey(), acc2.getAcckey());
      assertEquals(acc1.getCustkey(), acc2.getCustkey());
      assertEquals(acc1.getStatus(), acc2.getStatus());
      assertEquals(acc1.isCut(), acc2.isCut());
      assertEquals(acc1.getAccountNumber(), acc2.getAccountNumber());
      assertEquals(acc1.getAccountID(), acc2.getAccountID());
      assertEquals(acc1.getCustomerName(), acc2.getCustomerName());
      assertEquals(acc1.getInstalledCapacity(), acc2.getInstalledCapacity());
      assertEquals(acc1.getMinCharge(), acc2.getMinCharge());
      assertEquals(acc1.getMaxCharge(), acc2.getMaxCharge());
      Address adr1 = acc1.getAddress();
      Address adr2 = acc2.getAddress();
      assertEquals(adr1.getFullAddress(), adr2.getFullAddress());
      assertEquals(adr1.getStreetId(), adr2.getStreetId());
      assertEquals(adr1.getStreetName(), adr2.getStreetName());
      assertEquals(adr1.getHouse(), adr2.getHouse());
      assertEquals(adr1.getBuilding(), adr2.getBuilding());
      assertEquals(adr1.getPorch(), adr2.getPorch());
      assertEquals(adr1.getFlate(), adr2.getFlate());
      Meter mtr1 = item1.getMeter();
      Meter mtr2 = item2.getMeter();
      assertEquals(mtr1.isActive(), mtr2.isActive());
      assertEquals(mtr1.getNumber(), mtr2.getNumber());
      assertEquals(mtr1.getSealNumber(), mtr2.getSealNumber());
      assertEquals(mtr1.isSealActive(), mtr2.isSealActive());
      assertEquals(mtr1.getDigits(), mtr2.getDigits());
      assertEquals(mtr1.getCoeff(), mtr2.getCoeff());
      assertEquals(mtr1.getType(), mtr2.getType());
      assertEquals(mtr1.isWithout(), mtr2.isWithout());
      Reading rdn1 = item1.getReading();
      Reading rdn2 = item2.getReading();
      assertEquals(rdn1.getReading(), rdn2.getReading());
      assertEquals(rdn1.isReadingConfirmed(), rdn2.isReadingConfirmed());
      assertEquals(rdn1.getPreviousReading(), rdn2.getPreviousReading());
      assertEquals(rdn1.getPreviousReadingDate(), rdn2.getPreviousReadingDate());
      assertEquals(rdn1.getPreviousRealReading(), rdn2.getPreviousRealReading());
      assertEquals(rdn1.getPreviousRealReadingDate(), rdn2.getPreviousRealReadingDate());
    }
  }

}
