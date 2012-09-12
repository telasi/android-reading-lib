package telasi.android.reading.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class ReadingController {
  public static String VERSION = "0.0.1.rc2";

  public static String getVersion() {
    return VERSION;
  }

  /*------------------------------------------------------------------------------------------------------------------------------------------*/

  public static Reester getReesterOverIO(InputStream in) throws XmlPullParserException, IOException, ParseException {
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);
      return new ReesterParser().parse(xpp);
    } finally {
      in.close();
    }
  }

  public static Reester getReesterOverHTTP(String username, String password) throws IOException, XmlPullParserException, ParseException {
    return getReesterOverHTTP(username, password, (Date) null);
  }

  public static Reester getReesterOverHTTP(String username, String password, Date date) throws IOException, XmlPullParserException, ParseException {
    String query = "?username=" + username + "&password=" + password;
    if (date != null)
      query += "&date=" + Config.formatDate(date);
    URL url = new URL(Config.getReesterUrl() + query);
    InputStream in = url.openStream();
    return getReesterOverIO(in);
  }

  public static Reester getReesterOverHTTP(XmlPullParser xpp, String username, String password, String date) throws IOException, XmlPullParserException, ParseException {
    return getReesterOverHTTP(username, password, date == null ? null : Config.parseDate(date));
  }

  /*------------------------------------------------------------------------------------------------------------------------------------------*/

  private static String readInputStream(InputStream in) throws IOException {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      StringBuilder text = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        text.append(line);
        text.append("\n");
      }
      return text.toString().trim();
    } finally {
      in.close();
    }
  }

  public static String sendReesterOverHTTP(Reester reester, String username, String password) throws IOException, UploadException, XmlPullParserException {
    XmlSerializer xps = XmlPullParserFactory.newInstance().newSerializer();
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Config.getReesterUploadUrl());
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    nameValuePairs.add(new BasicNameValuePair("username", username));
    nameValuePairs.add(new BasicNameValuePair("password", password));
    nameValuePairs.add(new BasicNameValuePair("reester", new ReesterSerializer().reesterSerialization(xps, reester, false)));
    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    HttpResponse response = httpclient.execute(httppost);
    String text = readInputStream(response.getEntity().getContent());
    if ("OK".equalsIgnoreCase(text)) {
      return "რეესტრი გაგზავნიალია.";
    } else {
      throw new UploadException(text);
    }
  }

  public static void saveReesterOverIO(Reester reester, OutputStream out) throws IOException, XmlPullParserException {
    XmlSerializer xps = XmlPullParserFactory.newInstance().newSerializer();
    new ReesterSerializer().reesterSerialization(xps, out, reester, true);
  }

}
