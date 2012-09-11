package telasi.android.reading.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * ეს კლასი გამოიყენება კონფიგურაციის მონაცემების მისაღებად.
 */
class Config {
  private static Properties urls = new Properties();

  static {
    try {
      urls.load(new FileInputStream("config/url.properties"));
    } catch (IOException ioe) {
    }
  }

  static String getBaseUrl() {
    return urls.getProperty("base_url");
  }

  static String getReesterUrl() {
    return getBaseUrl() + urls.getProperty("reester_url");
  }

  static String formatDate(Date date) {
    return new SimpleDateFormat("d-MMM-yyyy").format(date);
  }

  static Date parseDate(String dateString) throws ParseException {
    return new SimpleDateFormat("d-MMM-yyyy").parse(dateString);
  }
  
}