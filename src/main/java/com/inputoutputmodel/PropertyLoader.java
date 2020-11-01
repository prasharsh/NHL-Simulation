package com.inputoutputmodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

public class PropertyLoader implements IPropertyLoader {
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
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					System.out.println("Exception when closing property loader input stream");
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
