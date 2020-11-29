package com.inputoutputmodel;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyLoader implements IPropertyLoader {
	
	final static Logger logger = Logger.getLogger(PropertyLoader.class);

	private String propertyValue;

	private static Properties loadProperties() {
		InputStream inputStream = null;
		boolean isFileNotOnServer = true;
		Properties property = new Properties();
		try {
			String fileName = "gameProperties.properties";
			inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(fileName);
			if (inputStream != null) {
				property.load(inputStream);
			} else {
				throw new FileNotFoundException("File '" + fileName + "' not found in the classpath");
			}

			property.getProperty("");

		} catch (Exception e) {
			logger.error("Exception: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					logger.warn("Exception when closing property loader input stream");
				}
			}
		}
		return property;
	}

	@Override
	public String getPropertyValue(String key) {
		return loadProperties().getProperty(key);
	}
}