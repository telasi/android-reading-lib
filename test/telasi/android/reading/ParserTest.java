package telasi.android.reading;

import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

public class ParserTest extends TestCase {

	public void testParsing() throws Exception {
		InputStream in = new FileInputStream("data/reester.xml");
		Reester r = MainController.parseReester(in);
	}
	
}
