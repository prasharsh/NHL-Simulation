package g4dhl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;


public class PropertyLoader {
	InputStream inputStream;

	public HashMap<String, String> getPropValues() throws IOException {

		HashMap<String, String> propsMap = new HashMap<String, String>();
		try {
			Properties property = new Properties();
			String fileName = "application.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (inputStream != null) {
				property.load(inputStream);
			} else {
				throw new FileNotFoundException("file '" + fileName + "' not found in the classpath");
			}
			propsMap.put("driver", property.getProperty("datasource.driver-class-name"));
			propsMap.put("url", property.getProperty("datasource.url"));
			propsMap.put("username", property.getProperty("datasource.username"));
			propsMap.put("password", property.getProperty("datasource.password"));
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return propsMap;
	}
}