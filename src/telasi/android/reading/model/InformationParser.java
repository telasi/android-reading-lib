package telasi.android.reading.model;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class InformationParser implements MessageTags {
  private XmlPullParser xpp;
  private String currentTag;
  private Information information;
  private Error error;

  Information parse(XmlPullParser xpp) throws XmlPullParserException, IOException, ParseException, InformationException {
    this.xpp = xpp;
    processDocument();
    if (error != null) {
      throw new InformationException(error.getMessage());
    } else {
      return information;
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
    if (INFO.equals(currentTag)) {
      this.information = new Information();
    } else if (ERROR.equals(currentTag)) {
      this.error = new Error();
    }
  }

  private void onText() {
    if (MESSAGE.equals(currentTag)) {
      if (error != null)
        error.setMessage(xpp.getText());
      else
        information.setMessage(xpp.getText());
    }
  }

}
