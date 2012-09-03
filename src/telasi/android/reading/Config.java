package telasi.android.reading;

import java.io.FileInputStream;
import java.io.IOException;
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

}
