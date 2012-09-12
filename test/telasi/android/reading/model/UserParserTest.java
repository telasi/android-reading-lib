package telasi.android.reading.model;

import java.io.FileInputStream;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import junit.framework.TestCase;

public class UserParserTest extends TestCase {

  public void testUserParser() throws Exception {
    InputStream in = new FileInputStream("data/user.xml");
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);
      User user = new UserParser().parse(xpp);
      assertNotNull(user);
      assertEquals(11795, user.getId());
      assertEquals("dimitri.kurashvili@telasi.ge", user.getEmail());
      assertEquals("dimitri", user.getLogin());
      assertEquals("დიმიტრი", user.getFirstName());
      assertEquals("ყურაშვილი", user.getLastName());
    } finally {
      in.close();
    }
  }

  public void testUserParserForError() throws Exception {
    InputStream in = new FileInputStream("data/error.xml");
    try {
      XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
      xpp.setInput(in, null);
      LoginException le = null;
      try {
        new UserParser().parse(xpp);
      } catch (LoginException ex) {
        le = ex;
      }
      assertNotNull(le);
      assertEquals("error message", le.getMessage());
    } finally {
      in.close();
    }
  }

}
