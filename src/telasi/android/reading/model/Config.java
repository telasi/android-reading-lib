package telasi.android.reading.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ეს კლასი გამოიყენება კონფიგურაციის მონაცემების მისაღებად.
 */
class Config {

  static String getBaseUrl() {
    return "http://192.168.1.11:3000/";
  }

  static String getReesterUrl() {
    return getBaseUrl() + "android/readings/reester.xml";
  }

  static String getReesterUploadUrl() {
    return getBaseUrl() + "android/readings/upload.xml";
  }

  static String getLoginUrl() {
    return getBaseUrl() + "android/login.xml";
  }
  
  static String formatDate(Date date) {
    return new SimpleDateFormat("d-MMM-yyyy").format(date);
  }

  static Date parseDate(String dateString) throws ParseException {
    return new SimpleDateFormat("d-MMM-yyyy").parse(dateString);
  }

}
