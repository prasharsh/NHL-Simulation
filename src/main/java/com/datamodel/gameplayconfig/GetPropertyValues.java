package com.datamodel.gameplayconfig;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

    String result = "";
    InputStream inputStream;

    public String loadProperties(){

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream == null) {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            } else {
                prop.load(inputStream);
            }

            result = prop.getProperty("importType");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
