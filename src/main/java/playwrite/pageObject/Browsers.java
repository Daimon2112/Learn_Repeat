package playwrite.pageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Browsers {
    /**
     * try to add constructor to abstract page playwrite
     */

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    Properties prop;
//    private static Browser instance1 = null;
//
//    public static Browser getInstance() {
//        if (instance1 != null) {
//            return instance1;
//        }
//        return instance1 = initBrowser();
//    }

//    public Page initBrowser(String browserName){

    public Page initBrowser(Properties prop){

        String browserName = prop.getProperty("browser").trim();
//        System.out.println("Browser name is: " + browserName);

        System.out.println("Browser name is: " + browserName);
        playwright = Playwright.create();

//        switch (browserName.toLowerCase()){

        switch (browserName.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new LaunchOptions().setHeadless(false));
                break;
                case "chrome":
                browser = playwright.webkit().launch(new LaunchOptions().setChannel("chrome"));
                break;
            default:
                System.out.println("Please select the browser");
                break;
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        //page.navigate("blabla.com")
        page.navigate(prop.getProperty("url").trim());
        return page;
    }

    /**
     * this method is used to initialize the properties from config
     */
    public Properties init_prop(){
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }



}
