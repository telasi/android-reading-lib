package telasi.android.reading.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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
  public static String VERSION = "0.0.1.rc4";

  public static String getVersion() {
    return VERSION;
  }

  /*----------------------------------------------------------------------------------------------------*/

  public static Reester getReesterOverIO(InputStream in) throws XmlPullParserException, IOException, ParseException, DownloadException {
    try {
      XmlPullParser xpp = createParser();
      xpp.setInput(in, null);
      return new ReesterParser().parse(xpp);
    } finally {
      in.close();
    }
  }

  public static Reester getReesterOverHTTP(String username, String password) throws IOException, XmlPullParserException, ParseException, DownloadException {
    return getReesterOverHTTP(username, password, (Date) null);
  }

  public static Reester getReesterOverHTTP(String username, String password, Date date) throws IOException, XmlPullParserException, ParseException, DownloadException {
    String query = "?username=" + username + "&password=" + password;
    if (date != null)
      query += "&date=" + Config.formatDate(date);
    URL url = new URL(Config.getReesterUrl() + query);
    InputStream in = url.openStream();
    return getReesterOverIO(in);
  }

  public static Reester getReesterOverHTTP(String username, String password, String date) throws IOException, XmlPullParserException, ParseException, DownloadException {
    return getReesterOverHTTP(username, password, date == null ? null : Config.parseDate(date));
  }

  /*----------------------------------------------------------------------------------------------------*/

  public static Information sendReesterOverHTTP(Reester reester, String username, String password) throws IOException, InformationException, XmlPullParserException, ParseException {
    XmlSerializer xps = getSerializer();
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Config.getReesterUploadUrl());
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    nameValuePairs.add(new BasicNameValuePair("username", username));
    nameValuePairs.add(new BasicNameValuePair("password", password));
    nameValuePairs.add(new BasicNameValuePair("reester", new ReesterSerializer().reesterSerialization(xps, reester, false)));
    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    HttpResponse response = httpclient.execute(httppost);
    InputStream in = response.getEntity().getContent();
    ByteArrayInputStream in2 = null;
    try {
      StringBuilder text = new StringBuilder();
      String line;
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      while ((line = reader.readLine()) != null) {
        text.append(line);
        text.append("\n");
      }
      in2 = new ByteArrayInputStream(text.toString().getBytes("UTF-8"));
      XmlPullParser xpp = createParser();
      xpp.setInput(in2, null);
      Information info = new InformationParser().parse(xpp);
      if (info == null) {
        throw new InformationException(text.toString());
      }
      return info;
    } finally {
      in.close();
      if (in2 != null) {
        in2.close();
      }
    }
  }

  public static void saveReesterOverIO(Reester reester, OutputStream out) throws IOException, XmlPullParserException {
    try {
      XmlSerializer xps = getSerializer();
      new ReesterSerializer().reesterSerialization(xps, out, reester, true);
    } finally {
      out.close();
    }
  }

  /*----------------------------------------------------------------------------------------------------*/

  public static User login(String username, String password) throws IOException, XmlPullParserException, ParseException, LoginException {
    String query = "?username=" + username + "&password=" + password;
    URL url = new URL(Config.getLoginUrl() + query);
    InputStream in = url.openStream();
    XmlPullParser xpp = createParser();
    xpp.setInput(in, null);
    return new UserParser().parse(xpp);
  }

  /*----------------------------------------------------------------------------------------------------*/

  private static XmlPullParser createParser() throws XmlPullParserException {
    return XmlPullParserFactory.newInstance().newPullParser();
  }

  private static XmlSerializer getSerializer() throws XmlPullParserException {
    return XmlPullParserFactory.newInstance().newSerializer();
  }

}
