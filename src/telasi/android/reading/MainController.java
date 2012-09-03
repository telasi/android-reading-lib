package telasi.android.reading;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MainController {

	public static void getReesterOverHttp(Date d, int inspectorId) {
		// String query = "?date=" + Config.
	}

	static Reester parseReester(InputStream in) throws XmlPullParserException, IOException {
		XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser(); // Xml.newPullParser();
		parser.setInput(in, null);
		parser.nextTag();

		// XXX: XmlPullParserFactory.newInstance().newPullParser() gives stub implementation
		
		return null;
	}

}
