package g4dhl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

public class PropertyLoader {
	InputStream inputStream;

	public HashMap<String, String> getPropValues() throws IOException {

		HashMap<String, String> propsMap = new HashMap<String, String>();
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			//s = "/users/grad/prashantk/CSCI5308builds/target";

			s= s.substring(0, s.lastIndexOf("target"));
			System.out.println("Current relative path is: " + s);
			Properties property = new Properties();
			String fileName = "application.properties";
			//inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			inputStream = new FileInputStream(s+fileName);
			if (inputStream != null) {
				property.load(inputStream);
			} else {
				throw new FileNotFoundException("file '" + fileName + "' not found in the classpath");
			}
			propsMap.put("driver", property.getProperty("datasource.driver-class-name"));
			propsMap.put("url", property.getProperty("datasource.url"));
			propsMap.put("username", property.getProperty("datasource.username"));
			propsMap.put("password", property.getProperty("datasource.password"));
			System.out.println(propsMap.get("url"));
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return propsMap;
	}
}