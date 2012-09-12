package telasi.android.reading.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class ReesterParser implements ReesterTags, ErrorTags {
  // reester
  static final String PATH_REESTER = REESTER;
  static final String PATH_REESTER_ID = PATH_REESTER + "/" + REESTER_ID;
  static final String PATH_REESTER_CYCLEDATE = PATH_REESTER + "/" + CYCLE_DATE;
  static final String PATH_REESTER_INSPECTOR = PATH_REESTER + "/" + INSPECTOR;
  static final String PATH_REESTER_DOWNLOADS = PATH_REESTER + "/" + DOWNLOADS;
  static final String PATH_REESTER_UPLOADS = PATH_REESTER + "/" + UPLOADS;
  static final String PATH_REESTER_STATUS = PATH_REESTER + "/" + REESTER_STATUS;
  // items/item
  static final String PATH_ITEM = PATH_REESTER + "/" + ITEMS + "/" + ITEM;
  static final String PATH_ITEM_ID = PATH_ITEM + "/" + ITEM_ID;
  static final String PATH_ITEM_ROUTE = PATH_ITEM + "/" + ROUTE;
  static final String PATH_ITEM_SEQUENCE = PATH_ITEM + "/" + SEQUENCE;
  static final String PATH_ITEM_SCHEDULE = PATH_ITEM + "/" + SCHEDULE;
  // account
  static final String PATH_ITEM_ACCOUNT = PATH_ITEM + "/" + ACCOUNT;
  static final String PATH_ITEM_ACCOUNT_STATUS = PATH_ITEM_ACCOUNT + "/" + ACCOUNT_STATUS;
  static final String PATH_ITEM_ACCOUNT_CUT = PATH_ITEM_ACCOUNT + "/" + CUT;
  static final String PATH_ITEM_ACCOUNT_CUSTKEY = PATH_ITEM_ACCOUNT + "/" + CUSTKEY;
  static final String PATH_ITEM_ACCOUNT_ACCKEY = PATH_ITEM_ACCOUNT + "/" + ACCKEY;
  static final String PATH_ITEM_ACCOUNT_ACCNUMB = PATH_ITEM_ACCOUNT + "/" + ACCNUMB;
  static final String PATH_ITEM_ACCOUNT_ACCID = PATH_ITEM_ACCOUNT + "/" + ACCID;
  static final String PATH_ITEM_ACCOUNT_CUSTNAME = PATH_ITEM_ACCOUNT + "/" + CUSTNAME;
  // meter
  static final String PATH_ITEM_METER = PATH_ITEM + "/" + METER;
  static final String PATH_ITEM_METER_NUMBER = PATH_ITEM_METER + "/" + METER_NUMBER;
  static final String PATH_ITEM_METER_STATUS = PATH_ITEM_METER + "/" + METER_STATUS;
  static final String PATH_ITEM_METER_SEAL = PATH_ITEM_METER + "/" + SEAL_NUMBER;
  static final String PATH_ITEM_METER_SEAL_STATUS = PATH_ITEM_METER + "/" + SEAL_STATUS;
  static final String PATH_ITEM_METER_DIGITS = PATH_ITEM_METER + "/" + DIGITS;
  static final String PATH_ITEM_METER_COEFF = PATH_ITEM_METER + "/" + COEFF;
  // reading
  static final String PATH_ITEM_READING = PATH_ITEM + "/" + READING;
  static final String PATH_ITEM_READING_CURR = PATH_ITEM_READING + "/" + READING_READING;
  static final String PATH_ITEM_READING_PREV = PATH_ITEM_READING + "/" + PREVIOUS_READING;
  static final String PATH_ITEM_READING_PREV_DATE = PATH_ITEM_READING + "/" + PREVIOUS_READING_DATE;
  static final String PATH_ITEM_READING_PREV_REAL = PATH_ITEM_READING + "/" + PREVIOUS_REAL_READING;
  static final String PATH_ITEM_READING_PREV_REAL_DATE = PATH_ITEM_READING + "/" + PREVIOUS_REAL_READING_DATE;
  // other parameters
  static final String PATH_ITEM_OTHER = PATH_ITEM + "/" + OTHER;
  static final String PATH_ITEM_OTHER_INSCP = PATH_ITEM_OTHER + "/" + INSTALLED_CAPACITY;
  static final String PATH_ITEM_OTHER_MINCHARGE = PATH_ITEM_OTHER + "/" + MIN_CHARGE;
  static final String PATH_ITEM_OTHER_MAXCHARGE = PATH_ITEM_OTHER + "/" + MAX_CHARGE;

  // error
  static final String PATH_ERROR = ERROR;
  static final String PATH_ERROR_MESSAGE = PATH_ERROR + "/" + ERROR_MESSAGE;

  private Reester reester;
  private ReesterItem item;
  private XmlPullParser xpp;
  private String address;
  private Error error;
  private List<String> path = new ArrayList<String>();

  Reester parse(XmlPullParser xpp) throws XmlPullParserException, IOException, ParseException, DownloadException {
    this.xpp = xpp;
    processDocument();
    if (error != null) {
      throw new DownloadException(error.getMessage());
    } else {
      return this.reester;
    }
  }

  private void processDocument() throws XmlPullParserException, IOException, ParseException, DownloadException {
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
        onText();
      }
    } while (eventType != XmlPullParser.END_DOCUMENT);
  }

  private void onTagStart() {
    if (this.address.equals(PATH_ERROR)) {
      this.error = new Error();
    } else if (this.address.equals(PATH_REESTER)) {
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
    // error
    if (this.address.equals(PATH_ERROR_MESSAGE)) {
      this.error.setMessage(xpp.getText());
    }
    // reester
    else if (this.address.equals(PATH_REESTER_ID)) {
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
