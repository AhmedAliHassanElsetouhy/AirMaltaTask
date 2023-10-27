package utils;

import java.util.Properties;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils configUtils;

    private ConfigUtils() {
        String env = System.getProperty("env", "PRODUCTION");
        switch (env) {
            case "PRODUCTION":
                properties =
                        PropertiesUtils.loadProperties("src/test/java/config/production.properties");
                break;
            case "LOCAL":
                properties =
                        PropertiesUtils.loadProperties("src/test/java/config/local.properties");
                break;
            default:
                throw new RuntimeException("The Environment is not supported");
        }
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Could not find the base url in the property file");
    }

    public String homePageTitle(){
        String prop = properties.getProperty("homePageTitle");
        if(prop!=null){
            return prop;
        }
        throw new RuntimeException("Could not find the homePageTitle in the property file");
    }

    public String getDepartingFrom() {
        String prop = properties.getProperty("departingFrom");
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Could not find the departingFrom in the property file");
    }

    public String getFlyingTo(){
        String prop = properties.getProperty("flyingTo");
        if(prop!=null){
            return prop;
        }
        throw new RuntimeException("Could not find the flyingTo in the property file");
    }
}