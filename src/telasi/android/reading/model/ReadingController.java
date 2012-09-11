package telasi.android.reading.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ReadingController {
  public static String VERSION = "0.0.1.rc1";

  public static String getVersion() {
    return VERSION;
  }

  public static Reester getReesterOverIO(XmlPullParser xpp, InputStream in) throws XmlPullParserException, IOException, ParseException {
    try {
      xpp.setInput(in, null);
      return new ReesterParser().parse(xpp);
    } finally {
      in.close();
    }
  }

  public static Reester getReesterOverHTTP(XmlPullParser xpp, String username, String password, Date date) throws IOException, XmlPullParserException, ParseException {
    String query = "?date=" + Config.formatDate(date) + "&username=" + username + "&password=" + password;
    URL url = new URL(Config.getBaseUrl() + query);
    InputStream in = url.openStream();
    return getReesterOverIO(xpp, in);
  }

  public static Reester getReesterOverHTTP(XmlPullParser xpp, String username, String password, String date) throws IOException, XmlPullParserException, ParseException {
    return getReesterOverHTTP(xpp, username, password, Config.parseDate(date));
  }

}
