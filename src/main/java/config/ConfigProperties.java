package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static final ConfigProperties instance = new ConfigProperties();

    //private constructor to avoid client applications to use constructor
    private ConfigProperties(){}
    public static ConfigProperties getInstance(){
        return instance;
    }



    public  String getConfig(String key)  {
             String result="";
            InputStream inputStream = null;
            try {
                Properties prop = new Properties();
                String propFileName = "config.properties";

                 inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
                 result = prop.getProperty(key);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

