package telasi.android.reading.model;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class UserParser implements ErrorTags {
  static final String USER = "user";
  static final String EMAIL = "email";
  static final String LOGIN = "login";
  static final String FIRST_NAME = "first_name";
  static final String LAST_NAME = "last_name";
  static final String PERSKEY = "perskey";

  private XmlPullParser xpp;
  private String currentTag;
  private User user;
  private Error error;

  User parse(XmlPullParser xpp) throws XmlPullParserException, IOException, ParseException, LoginException {
    this.xpp = xpp;
    processDocument();
    if (error != null) {
      throw new LoginException(error.getMessage());
    } else {
      return user;
    }
  }

  private void processDocument() throws XmlPullParserException, IOException, ParseException {
    int eventType;
    do {
      eventType = xpp.next();
      if (eventType == XmlPullParser.START_TAG) {
        onTagStart();
      } else if (eventType == XmlPullParser.TEXT && !xpp.isWhitespace()) {
        onText();
      }
    } while (eventType != XmlPullParser.END_DOCUMENT);
  }

  private void onTagStart() {
    this.currentTag = xpp.getName();
    if (USER.equals(currentTag)) {
      this.user = new User();
    } else if (ERROR.equals(currentTag)) {
      this.error = new Error();
    }
  }

  private void onText() {
    if (ERROR_MESSAGE.equals(currentTag)) {
      this.error.setMessage(xpp.getText());
    } else if (EMAIL.equals(currentTag)) {
      this.user.setEmail(xpp.getText());
    } else if (LOGIN.equals(currentTag)) {
      this.user.setLogin(xpp.getText());
    } else if (FIRST_NAME.equals(currentTag)) {
      this.user.setFirstName(xpp.getText());
    } else if (LAST_NAME.equals(currentTag)) {
      this.user.setLastName(xpp.getText());
    } else if (PERSKEY.equals(currentTag)) {
      this.user.setId(Integer.parseInt(xpp.getText()));
    }
  }
}
