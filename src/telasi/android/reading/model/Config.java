package telasi.android.reading.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ეს კლასი გამოიყენება კონფიგურაციის მონაცემების მისაღებად.
 */
public class Config {

  private static String connection_host = "1.1.7.160";
  private static String connection_port = "3000";

  public static void setHost(String host) {
    connection_host = host;
  }

  public static void setPort(String port) {
    connection_port = port;
  }

  public static String getHost() {
    return connection_host;
  }

  public static String getPort() {
    return connection_port;
  }

  static String getBaseUrl() {
    return "http://" + connection_host + ":" + connection_port + "/";
  }

  static String getReesterUrl() {
    return getBaseUrl() + "android/readings/reester.xml";
  }

  static String getReestersUrl() {
    return getBaseUrl() + "android/readings/reesters.xml";
  }

  static String getReesterUploadUrl() {
    return getBaseUrl() + "android/readings/upload.xml";
  }

  static String getLoginUrl() {
    return getBaseUrl() + "android/login.xml";
  }

  static String formatDate(Date date) {
    return new SimpleDateFormat("d-MMM-yyyy", Locale.US).format(date);
  }

  static Date parseDate(String dateString) throws ParseException {
    return new SimpleDateFormat("d-MMM-yyyy", Locale.US).parse(dateString);
  }

}
