package telasi.android.reading;

import junit.framework.TestCase;

public class ConfigTest extends TestCase {

	public void testBaseUrlTest() {
		String url = Config.getBaseUrl();
		assertNotNull(url);
		assertEquals("http://", url.substring(0, 7));
	}
	
}
