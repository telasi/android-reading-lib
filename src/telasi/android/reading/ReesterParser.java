package telasi.android.reading;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class ReesterParser {
  private Reester reester;
  private ReesterItem item;
  private XmlPullParser xpp;
  private String address;
  private List<String> path = new ArrayList<String>();

  // reester
  static String PATH_REESTER = "reester";
  static String PATH_REESTER_ID = PATH_REESTER + "/id";
  static String PATH_REESTER_CYCLEDATE = PATH_REESTER + "/cycledate";
  static String PATH_REESTER_INSPECTOR = PATH_REESTER + "/inspector";
  static String PATH_REESTER_DOWNLOADS = PATH_REESTER + "/downloads";
  static String PATH_REESTER_UPLOADS = PATH_REESTER + "/uploads";
  static String PATH_REESTER_STATUS = PATH_REESTER + "/status";
  // items/item
  static String PATH_ITEM = PATH_REESTER + "/items/item";
  static String PATH_ITEM_ID = PATH_ITEM + "/id";
  static String PATH_ITEM_ROUTE = PATH_ITEM + "/route";
  static String PATH_ITEM_SEQUENCE = PATH_ITEM + "/sequence";
  static String PATH_ITEM_SCHEDULE = PATH_ITEM + "/schedule";
  // account
  static String PATH_ITEM_ACCOUNT = PATH_ITEM + "/account";
  static String PATH_ITEM_ACCOUNT_STATUS = PATH_ITEM_ACCOUNT + "/status";
  static String PATH_ITEM_ACCOUNT_CUT = PATH_ITEM_ACCOUNT + "/cut";
  static String PATH_ITEM_ACCOUNT_CUSTKEY = PATH_ITEM_ACCOUNT + "/custkey";
  static String PATH_ITEM_ACCOUNT_ACCKEY = PATH_ITEM_ACCOUNT + "/acckey";
  static String PATH_ITEM_ACCOUNT_ACCNUMB = PATH_ITEM_ACCOUNT + "/accnumb";
  static String PATH_ITEM_ACCOUNT_ACCID = PATH_ITEM_ACCOUNT + "/accid";
  static String PATH_ITEM_ACCOUNT_CUSTNAME = PATH_ITEM_ACCOUNT + "/custname";
  // meter
  static String PATH_ITEM_METER = PATH_ITEM + "/meter";
  static String PATH_ITEM_METER_NUMBER = PATH_ITEM_METER + "/number";
  static String PATH_ITEM_METER_STATUS = PATH_ITEM_METER + "/status";
  static String PATH_ITEM_METER_SEAL = PATH_ITEM_METER + "/seal_number";
  static String PATH_ITEM_METER_SEAL_STATUS = PATH_ITEM_METER + "/seal_status";
  static String PATH_ITEM_METER_DIGITS = PATH_ITEM_METER + "/digits";
  static String PATH_ITEM_METER_COEFF = PATH_ITEM_METER + "/coeff";
  // reading
  static String PATH_ITEM_READING = PATH_ITEM + "/reading";
  static String PATH_ITEM_READING_CURR = PATH_ITEM_READING + "/reading";
  static String PATH_ITEM_READING_PREV = PATH_ITEM_READING + "/previous_reading";
  static String PATH_ITEM_READING_PREV_DATE = PATH_ITEM_READING + "/previous_reading_date";
  static String PATH_ITEM_READING_PREV_REAL = PATH_ITEM_READING + "/previous_real_reading";
  static String PATH_ITEM_READING_PREV_REAL_DATE = PATH_ITEM_READING + "/previous_real_reading_date";
  // other parameters
  static String PATH_ITEM_OTHER = PATH_ITEM + "/other";
  static String PATH_ITEM_OTHER_INSCP = PATH_ITEM_OTHER + "/installed_capacity";
  static String PATH_ITEM_OTHER_MINCHARGE = PATH_ITEM_OTHER + "/min_charge";
  static String PATH_ITEM_OTHER_MAXCHARGE = PATH_ITEM_OTHER + "/max_charge";

  Reester parse(InputStream in) throws XmlPullParserException, IOException, ParseException {
    try {
      this.xpp = XmlPullParserFactory.newInstance().newPullParser();
      this.xpp.setInput(in, null);
      processDocument();
      return this.reester;
    } finally {
      in.close();
    }
  }

  private void processDocument() throws XmlPullParserException, IOException, ParseException {
    int eventType; // xpp.getEventType();
    do {
      eventType = xpp.next();
      if (eventType == XmlPullParser.START_TAG) {
        path.add(xpp.getName());
        changeAddress();
        onTagStart();
      } else if (eventType == XmlPullParser.END_TAG) {
        path.remove(path.size() - 1);
        changeAddress();
      } else if (eventType == XmlPullParser.TEXT && !xpp.isWhitespace()) {
//        System.out.println(this.address + ": " + xpp.getText());
        onText();
      }
    } while (eventType != XmlPullParser.END_DOCUMENT);
  }

  private void onTagStart() {
    if (this.address.equals(PATH_REESTER)) {
      this.reester = new Reester();
    } else if (this.address.equals(PATH_ITEM)) {
      this.item = new ReesterItem();
      this.reester.addItem(item);
    } else if (this.address.equals(PATH_ITEM_ACCOUNT)) {
      this.item.setAccount(new Account());
    } else if (this.address.equals(PATH_ITEM_METER)) {
      this.item.setMeter(new Meter());
    } else if (this.address.equals(PATH_ITEM_READING)) {
      this.item.setReading(new Reading());
    }
  }

  private void onText() throws ParseException {
    // reester
    if (this.address.equals(PATH_REESTER_ID)) {
      this.reester.setId(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_REESTER_CYCLEDATE)) {
      this.reester.setCycleDate(Config.parseDate(xpp.getText()));
    } else if (this.address.equals(PATH_REESTER_INSPECTOR)) {
      this.reester.setInspectorId(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_REESTER_DOWNLOADS)) {
      this.reester.setDownloads(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_REESTER_UPLOADS)) {
      this.reester.setUploads(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_REESTER_STATUS)) {
      this.reester.setStatus(Integer.parseInt(xpp.getText()));
    }
    // item
    else if (this.address.equals(PATH_ITEM_ID)) {
      this.item.setId(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_ROUTE)) {
      this.item.setRoute(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_SEQUENCE)) {
      this.item.setSequence(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_SCHEDULE)) {
      this.item.setSchedule(Integer.parseInt(xpp.getText()));
    }
    // item -> account
    else if (this.address.equals(PATH_ITEM_ACCOUNT_STATUS)) {
      this.item.getAccount().setStatus(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_ACCOUNT_CUT)) {
      this.item.getAccount().setCut(Integer.parseInt(xpp.getText()) == 1);
    } else if (this.address.equals(PATH_ITEM_ACCOUNT_CUSTKEY)) {
      this.item.getAccount().setCustkey(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_ACCOUNT_ACCKEY)) {
      this.item.getAccount().setAcckey(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_ACCOUNT_ACCNUMB)) {
      this.item.getAccount().setAccountNumber(xpp.getText());
    } else if (this.address.equals(PATH_ITEM_ACCOUNT_ACCID)) {
      this.item.getAccount().setAccountID(xpp.getText());
    } else if (this.address.equals(PATH_ITEM_ACCOUNT_CUSTNAME)) {
      this.item.getAccount().setCustomerName(xpp.getText());
    } else if (this.address.equals(PATH_ITEM_OTHER_INSCP)) {
      this.item.getAccount().setInstalledCapacity(Double.parseDouble(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_OTHER_MINCHARGE)) {
      this.item.getAccount().setMinCharge(Double.parseDouble(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_OTHER_MAXCHARGE)) {
      this.item.getAccount().setMaxCharge(Double.parseDouble(xpp.getText()));
    }
    // item -> meter
    else if (this.address.equals(PATH_ITEM_METER_NUMBER)) {
      this.item.getMeter().setNumber(xpp.getText());
    } else if (this.address.equals(PATH_ITEM_METER_STATUS)) {
      this.item.getMeter().setActive(Integer.parseInt(xpp.getText()) == 0);
    } else if (this.address.equals(PATH_ITEM_METER_SEAL)) {
      this.item.getMeter().setSealNumber(xpp.getText());
    } else if (this.address.equals(PATH_ITEM_METER_SEAL_STATUS)) {
      this.item.getMeter().setSealActive(Integer.parseInt(xpp.getText()) == 0);
    } else if (this.address.equals(PATH_ITEM_METER_DIGITS)) {
      this.item.getMeter().setDigits(Integer.parseInt(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_METER_COEFF)) {
      this.item.getMeter().setCoeff(Integer.parseInt(xpp.getText()));
    }
    // item -> reading
    else if (this.address.equals(PATH_ITEM_READING_CURR)) {
      this.item.getReading().setReading(Double.parseDouble(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_READING_PREV)) {
      this.item.getReading().setPreviousReading(Double.parseDouble(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_READING_PREV_DATE)) {
      this.item.getReading().setPreviousReadingDate(Config.parseDate(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_READING_PREV_REAL)) {
      this.item.getReading().setPreviousRealReading(Double.parseDouble(xpp.getText()));
    } else if (this.address.equals(PATH_ITEM_READING_PREV_REAL_DATE)) {
      this.item.getReading().setPreviousRealReadingDate(Config.parseDate(xpp.getText()));
    } 
  }

  private void changeAddress() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < path.size(); i++) {
      if (i > 0)
        b.append("/");
      b.append(path.get(i));
    }
    this.address = b.toString().toLowerCase();
  }
}
