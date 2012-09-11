package telasi.android.reading.model;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import junit.framework.TestCase;

public class SerializerTest extends TestCase {

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
    String xmlText = new ReesterSerializer().shortReesterSerialization(xps, reester);
    assertTrue(xmlText.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
    assertTrue(xmlText.contains("<id>100</id>"));
    assertTrue(xmlText.contains("<id>1</id>"));
    assertTrue(xmlText.contains("<reading>1300.0</reading>"));
    assertTrue(xmlText.contains("<id>2</id>"));
    assertTrue(xmlText.contains("<reading>1500.0</reading>"));
  }

}
