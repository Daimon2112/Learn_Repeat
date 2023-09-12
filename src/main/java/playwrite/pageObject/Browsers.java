package playwrite.pageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class Browsers {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
//    private static Browser instance1 = null;
//
//    public static Browser getInstance() {
//        if (instance1 != null) {
//            return instance1;
//        }
//        return instance1 = initBrowser();
//    }

    public Page initBrowser(String browserName){
        System.out.println("Browser name is: " + browserName);
        playwright = Playwright.create();
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
        return page;
    }



}
