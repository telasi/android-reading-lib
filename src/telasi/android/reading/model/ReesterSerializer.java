package telasi.android.reading.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.xmlpull.v1.XmlSerializer;

class ReesterSerializer implements ReesterTags {

  private void addTag(XmlSerializer xps, String tag, String value) throws IOException {
    xps.startTag("", tag);
    if (value != null)
      xps.text(String.valueOf(value));
    xps.endTag("", tag);
  }

  private void addTag(XmlSerializer xps, String tag, int value) throws IOException {
    addTag(xps, tag, String.valueOf(value));
  }

  private void addTag(XmlSerializer xps, String tag, double value) throws IOException {
    addTag(xps, tag, String.valueOf(value));
  }

  private void addTag(XmlSerializer xps, String tag, Date value) throws IOException {
    addTag(xps, tag, Config.formatDate(value));
  }

  private void reesterSerialization_internalization(XmlSerializer xps, Writer writter, Reester reester, boolean full) throws IOException {
    xps.setOutput(writter);
    xps.startDocument("UTF-8", true);
    xps.startTag("", REESTER);
    addTag(xps, REESTER_ID, reester.getId());
    if (full) {
      addTag(xps, CYCLE_DATE, reester.getCycleDate());
      addTag(xps, INSPECTOR, reester.getInspectorId());
      addTag(xps, DOWNLOADS, reester.getDownloads());
      addTag(xps, UPLOADS, reester.getUploads());
      addTag(xps, REESTER_STATUS, reester.getStatus());
    }
    // items
    xps.startTag("", "items");
    for (ReesterItem item : reester.getItems()) {
      xps.startTag("", "item");

      addTag(xps, ITEM_ID, item.getId());
      if (full) {
        addTag(xps, ROUTE, item.getRoute());
        addTag(xps, SEQUENCE, item.getSequence());
        addTag(xps, SCHEDULE, item.getSchedule());
      }

      if (full) {
        xps.startTag("", ACCOUNT);
        addTag(xps, ACCOUNT_STATUS, item.getAccount().getStatus());
        addTag(xps, CUT, item.getAccount().isCut() ? 1 : 0);
        addTag(xps, CUSTKEY, item.getAccount().getCustkey());
        addTag(xps, ACCKEY, item.getAccount().getAcckey());
        addTag(xps, ACCNUMB, item.getAccount().getAccountNumber());
        addTag(xps, ACCID, item.getAccount().getAccountID());
        addTag(xps, CUSTNAME, item.getAccount().getCustomerName());
        xps.endTag("", ACCOUNT);
      }

      if (full) {
        xps.startTag("", METER);
        addTag(xps, METER_NUMBER, item.getMeter().getNumber());
        addTag(xps, METER_STATUS, item.getMeter().isActive() ? 0 : 1);
        addTag(xps, SEAL_NUMBER, item.getMeter().getSealNumber());
        addTag(xps, SEAL_STATUS, item.getMeter().isSealActive() ? 0 : 1);
        addTag(xps, DIGITS, item.getMeter().getDigits());
        addTag(xps, COEFF, item.getMeter().getCoeff());
        addTag(xps, METER_TYPE, item.getMeter().getType());
        addTag(xps, METER_WITHOUT, String.valueOf(item.getMeter().isWithout()));
        xps.endTag("", METER);
      }

      xps.startTag("", READING);
      addTag(xps, READING_READING, item.getReading().getReading());
      if (full) {
        addTag(xps, PREVIOUS_READING, item.getReading().getPreviousReading());
        addTag(xps, PREVIOUS_READING_DATE, item.getReading().getPreviousReadingDate());
        addTag(xps, PREVIOUS_REAL_READING, item.getReading().getPreviousRealReading());
        addTag(xps, PREVIOUS_REAL_READING_DATE, item.getReading().getPreviousRealReadingDate());
      }
      xps.endTag("", READING);

      if (full) {
        xps.startTag("", OTHER);
        addTag(xps, INSTALLED_CAPACITY, item.getAccount().getInstalledCapacity());
        addTag(xps, MIN_CHARGE, item.getAccount().getMinCharge());
        addTag(xps, MAX_CHARGE, item.getAccount().getMaxCharge());
        xps.endTag("", OTHER);
      }

      xps.endTag("", "item");
    }
    xps.endTag("", "items");
    xps.endTag("", REESTER);
    xps.endDocument();
  }

  void reesterSerialization(XmlSerializer xps, Writer writter, Reester reester, boolean full) throws IOException {
    reesterSerialization_internalization(xps, writter, reester, full);
  }

  void reesterSerialization(XmlSerializer xps, OutputStream out, Reester reester, boolean full) throws IOException {
    try {
      reesterSerialization(xps, new OutputStreamWriter(out), reester, full);
    } finally {
      out.close();
    }
  }

  String reesterSerialization(XmlSerializer xps, Reester reester, boolean full) throws IOException {
    StringWriter w = new StringWriter();
    reesterSerialization(xps, w, reester, full);
    return w.toString();
  }

}
