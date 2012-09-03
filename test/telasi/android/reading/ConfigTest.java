package telasi.android.reading;

import junit.framework.TestCase;

public class ConfigTest extends TestCase {

	public void testBaseUrl() {
		String base_url = Config.getBaseUrl();
		assertNotNull(base_url);
		assertEquals("http://", base_url.substring(0, 7));
	}

	public void testReesterUrl() {
		String reester_url = Config.getReesterUrl();
		assertNotNull(reester_url);
		assertTrue(reester_url.startsWith(Config.getBaseUrl()));
		assertTrue(reester_url.endsWith("reester.xml"));
	}

}
