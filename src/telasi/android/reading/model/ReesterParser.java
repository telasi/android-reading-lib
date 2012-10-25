package telasi.android.reading.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class ReesterParser implements ReesterTags, MessageTags {
  // reesters
  static final String PATH_REESTERS = REESTERS;
  // reester
  static final String PATH_REESTER = REESTER;
  static final String PATH_REESTER_ID = PATH_REESTER + "/" + REESTER_ID;
  static final String PATH_REESTER_CYCLEDATE = PATH_REESTER + "/" + CYCLE_DATE;
  static final String PATH_REESTER_INSPECTOR = PATH_REESTER + "/" + INSPECTOR;
  static final String PATH_REESTER_INSPECTOR_NAME = PATH_REESTER + "/" + INSPECTOR_NAME;
  static final String PATH_REESTER_SCHEDULE = PATH_REESTER + "/" + SCHEDULE;
  static final String PATH_REESTER_BLOCK_ID = PATH_REESTER + "/" + BLOCK_ID;
  static final String PATH_REESTER_BLOCK_NAME = PATH_REESTER + "/" + BLOCK_NAME;
  static final String PATH_REESTER_REGION_ID = PATH_REESTER + "/" + REGION_ID;
  static final String PATH_REESTER_REGION_NAME = PATH_REESTER + "/" + REGION_NAME;
  static final String PATH_REESTER_DOWNLOADS = PATH_REESTER + "/" + DOWNLOADS;
  static final String PATH_REESTER_UPLOADS = PATH_REESTER + "/" + UPLOADS;
  static final String PATH_REESTER_STATUS = PATH_REESTER + "/" + REESTER_STATUS;
  static final String PATH_REESTER_ROUTE = PATH_REESTER + "/" + ROUTE;
  static final String PATH_REESTER_ROUTE_NAME = PATH_REESTER + "/" + ROUTE_NAME;
  static final String PATH_REESTER_ITEM_COUNT = PATH_REESTER + "/" + ITEM_COUNT;
  // items/item
  static final String PATH_ITEM = PATH_REESTER + "/" + ITEMS + "/" + ITEM;
  static final String PATH_ITEM_ID = PATH_ITEM + "/" + ITEM_ID;
  static final String PATH_ITEM_SEQUENCE = PATH_ITEM + "/" + SEQUENCE;
  // account
  static final String PATH_ITEM_ACCOUNT = PATH_ITEM + "/" + ACCOUNT;
  static final String PATH_ITEM_ACCOUNT_STATUS = PATH_ITEM_ACCOUNT + "/" + ACCOUNT_STATUS;
  static final String PATH_ITEM_ACCOUNT_CUT = PATH_ITEM_ACCOUNT + "/" + CUT;
  static final String PATH_ITEM_ACCOUNT_CUSTKEY = PATH_ITEM_ACCOUNT + "/" + CUSTKEY;
  static final String PATH_ITEM_ACCOUNT_ACCKEY = PATH_ITEM_ACCOUNT + "/" + ACCKEY;
  static final String PATH_ITEM_ACCOUNT_ACCNUMB = PATH_ITEM_ACCOUNT + "/" + ACCNUMB;
  static final String PATH_ITEM_ACCOUNT_ACCID = PATH_ITEM_ACCOUNT + "/" + ACCID;
  static final String PATH_ITEM_ACCOUNT_CUSTNAME = PATH_ITEM_ACCOUNT + "/" + CUSTNAME;
  // addrss
  static final String PATH_ITEM_ACCOUNT_ADDRESS = PATH_ITEM_ACCOUNT + "/" + ADDRESS;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_FULL = PATH_ITEM_ACCOUNT_ADDRESS + "/" + FULL_ADDRESS;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_STREET_ID = PATH_ITEM_ACCOUNT_ADDRESS + "/" + STREET_ID;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_STREET_NAME = PATH_ITEM_ACCOUNT_ADDRESS + "/" + STREET_NAME;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_HOUSE = PATH_ITEM_ACCOUNT_ADDRESS + "/" + HOUSE;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_BUILDING = PATH_ITEM_ACCOUNT_ADDRESS + "/" + BUILDING;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_PORCH = PATH_ITEM_ACCOUNT_ADDRESS + "/" + PORCH;
  static final String PATH_ITEM_ACCOUNT_ADDRESS_FLATE = PATH_ITEM_ACCOUNT_ADDRESS + "/" + FLATE;
  // meter
  static final String PATH_ITEM_METER = PATH_ITEM + "/" + METER;
  static final String PATH_ITEM_METER_NUMBER = PATH_ITEM_METER + "/" + METER_NUMBER;
  static final String PATH_ITEM_METER_STATUS = PATH_ITEM_METER + "/" + METER_STATUS;
  static final String PATH_ITEM_METER_SEAL = PATH_ITEM_METER + "/" + SEAL_NUMBER;
  static final String PATH_ITEM_METER_SEAL_STATUS = PATH_ITEM_METER + "/" + SEAL_STATUS;
  static final String PATH_ITEM_METER_DIGITS = PATH_ITEM_METER + "/" + DIGITS;
  static final String PATH_ITEM_METER_COEFF = PATH_ITEM_METER + "/" + COEFF;
  static final String PATH_ITEM_METER_TYPE = PATH_ITEM_METER + "/" + METER_TYPE;
  static final String PATH_ITEM_METER_WITHOUT = PATH_ITEM_METER + "/" + METER_WITHOUT;
  static final String PATH_ITEM_METER_NEW_NUMBER = PATH_ITEM_METER + "/" + NEW_METER_NUMBER;
  static final String PATH_ITEM_METER_NEW_COEFF = PATH_ITEM_METER + "/" + NEW_COEFF;
  static final String PATH_ITEM_METER_NEW_SEAL_NUMBER = PATH_ITEM_METER + "/" + NEW_SEAL_NUMBER;
  // reading
  static final String PATH_ITEM_READING = PATH_ITEM + "/" + READING;
  static final String PATH_ITEM_READING_CURR = PATH_ITEM_READING + "/" + READING_READING;
  static final String PATH_ITEM_READING_CONFIRMED = PATH_ITEM_READING + "/" + READING_CONFIRMED;
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
  static final String PATH_ERROR_MESSAGE = PATH_ERROR + "/" + MESSAGE;

  private Reester reester;
  private ReesterItem item;
  private XmlPullParser xpp;
  private String address;
  private Error error;
  private List<String> path = new ArrayList<String>();
  private List<Reester> reesters;

  Reester parse(XmlPullParser xpp) throws XmlPullParserException, IOException, ParseException, DownloadException {
    this.xpp = xpp;
    processDocument();
    if (error != null) {
      throw new DownloadException(error.getMessage());
    } else {
      return this.reester;
    }
  }

  List<Reester> parseReesters(XmlPullParser xpp) throws XmlPullParserException, IOException, ParseException, DownloadException {
    this.xpp = xpp;
    processDocument();
    if (error != null) {
      throw new DownloadException(error.getMessage());
    } else {
      return this.reesters;
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
        onTagEnd();
        path.remove(path.size() - 1);
        changeAddress();
      } else if (eventType == XmlPullParser.TEXT && !xpp.isWhitespace()) {
        onText();
      }
    } while (eventType != XmlPullParser.END_DOCUMENT);
  }

  private void onTagStart() {
    if (this.address.endsWith(PATH_ERROR)) {
      this.error = new Error();
    } else if (this.address.endsWith(PATH_REESTERS)) {
      this.reesters = new ArrayList<Reester>();
    } else if (this.address.endsWith(PATH_REESTER)) {
      this.reester = new Reester();
    } else if (this.address.endsWith(PATH_ITEM)) {
      this.item = new ReesterItem();
      this.reester.addItem(item);
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT)) {
      this.item.setAccount(new Account());
    } else if (this.address.endsWith(PATH_ITEM_METER)) {
      this.item.setMeter(new Meter());
    } else if (this.address.endsWith(PATH_ITEM_READING)) {
      this.item.setReading(new Reading());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS)) {
      this.item.getAccount().setAddress(new Address());
    }
  }

  private void onTagEnd() {
    if (this.address.endsWith(PATH_REESTER)) {
      if (this.reesters != null)
        this.reesters.add(reester);
    }
  }

  private void onText() throws ParseException {
    // error
    if (this.address.endsWith(PATH_ERROR_MESSAGE)) {
      this.error.setMessage(xpp.getText());
    }
    // reester
    else if (this.address.endsWith(PATH_REESTER_ID)) {
      this.reester.setId(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_CYCLEDATE)) {
      this.reester.setCycleDate(Config.parseDate(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_INSPECTOR)) {
      this.reester.setInspectorId(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_INSPECTOR_NAME)) {
      this.reester.setInspectorName(xpp.getText());
    } else if (this.address.endsWith(PATH_REESTER_DOWNLOADS)) {
      this.reester.setDownloads(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_UPLOADS)) {
      this.reester.setUploads(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_STATUS)) {
      this.reester.setStatus(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_SCHEDULE)) {
      this.reester.setSchedule(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_BLOCK_ID)) {
      this.reester.setBlockId(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_REGION_ID)) {
      this.reester.setRegionId(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_BLOCK_NAME)) {
      this.reester.setBlockName(xpp.getText());
    } else if (this.address.endsWith(PATH_REESTER_REGION_NAME)) {
      this.reester.setRegionName(xpp.getText());
    } else if (this.address.endsWith(PATH_REESTER_ROUTE)) {
      this.reester.setRoute(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_REESTER_ROUTE_NAME)) {
      this.reester.setRouteName(xpp.getText());
    } else if (this.address.endsWith(PATH_REESTER_ITEM_COUNT)) {
      this.reester.setCount(Integer.parseInt(xpp.getText()));
    }
    // item
    else if (this.address.endsWith(PATH_ITEM_ID)) {
      this.item.setId(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_SEQUENCE)) {
      this.item.setSequence(Integer.parseInt(xpp.getText()));
    }
    // item -> account
    else if (this.address.endsWith(PATH_ITEM_ACCOUNT_STATUS)) {
      this.item.getAccount().setStatus(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_CUT)) {
      this.item.getAccount().setCut(Integer.parseInt(xpp.getText()) == 1);
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_CUSTKEY)) {
      this.item.getAccount().setCustkey(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ACCKEY)) {
      this.item.getAccount().setAcckey(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ACCNUMB)) {
      this.item.getAccount().setAccountNumber(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ACCID)) {
      this.item.getAccount().setAccountID(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_CUSTNAME)) {
      this.item.getAccount().setCustomerName(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_OTHER_INSCP)) {
      this.item.getAccount().setInstalledCapacity(Double.parseDouble(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_OTHER_MINCHARGE)) {
      this.item.getAccount().setMinCharge(Double.parseDouble(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_OTHER_MAXCHARGE)) {
      this.item.getAccount().setMaxCharge(Double.parseDouble(xpp.getText()));
    }
    // item -> account > address
    else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_FULL)) {
      this.item.getAccount().getAddress().setFullAddress(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_STREET_ID)) {
      this.item.getAccount().getAddress().setStreetId(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_STREET_NAME)) {
      this.item.getAccount().getAddress().setStreetName(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_HOUSE)) {
      this.item.getAccount().getAddress().setHouse(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_BUILDING)) {
      this.item.getAccount().getAddress().setBuilding(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_PORCH)) {
      this.item.getAccount().getAddress().setPorch(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_ACCOUNT_ADDRESS_FLATE)) {
      this.item.getAccount().getAddress().setFlate(xpp.getText());
    }
    // item -> meter
    else if (this.address.endsWith(PATH_ITEM_METER_NUMBER)) {
      this.item.getMeter().setNumber(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_METER_STATUS)) {
      this.item.getMeter().setActive(Integer.parseInt(xpp.getText()) == 0);
    } else if (this.address.endsWith(PATH_ITEM_METER_SEAL)) {
      this.item.getMeter().setSealNumber(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_METER_SEAL_STATUS)) {
      this.item.getMeter().setSealActive(Integer.parseInt(xpp.getText()) == 0);
    } else if (this.address.endsWith(PATH_ITEM_METER_DIGITS)) {
      this.item.getMeter().setDigits(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_METER_COEFF)) {
      this.item.getMeter().setCoeff(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_METER_TYPE)) {
      this.item.getMeter().setType(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_METER_WITHOUT)) {
      this.item.getMeter().setWithout(Boolean.parseBoolean(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_METER_NEW_NUMBER)) {
      this.item.getMeter().setNewNumber(xpp.getText());
    } else if (this.address.endsWith(PATH_ITEM_METER_NEW_COEFF)) {
      this.item.getMeter().setNewCoeff(Integer.parseInt(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_METER_NEW_SEAL_NUMBER)) {
      this.item.getMeter().setNewSealNumber(xpp.getText());
    }
    // item -> reading
    else if (this.address.endsWith(PATH_ITEM_READING_CURR)) {
      this.item.getReading().setReading(Double.parseDouble(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_READING_CONFIRMED)) {
      this.item.getReading().setReadingConfirmed(Boolean.parseBoolean(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_READING_PREV)) {
      this.item.getReading().setPreviousReading(Double.parseDouble(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_READING_PREV_DATE)) {
      this.item.getReading().setPreviousReadingDate(Config.parseDate(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_READING_PREV_REAL)) {
      this.item.getReading().setPreviousRealReading(Double.parseDouble(xpp.getText()));
    } else if (this.address.endsWith(PATH_ITEM_READING_PREV_REAL_DATE)) {
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
