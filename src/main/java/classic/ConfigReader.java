package classic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;
    private static final String URL = "urlOrange";
    private static final String USERNAME = "orangeUser";
    private static final String USERPASSWORD = "orangePassword";


    public static Properties getPropertyObject() throws IOException {
        FileInputStream readfile = new FileInputStream("src/main/resources/somedata.properties");
        prop = new Properties();
        prop.load(readfile);
        return prop;
    }

    public static String getUrl() throws IOException {
        return getPropertyObject().getProperty(URL);
    }

    public static String getUserName() throws IOException {
        return getPropertyObject().getProperty(USERNAME) ;
    }

    public static String getUserPassword() throws IOException {
        return getPropertyObject().getProperty(USERPASSWORD);
    }
}
