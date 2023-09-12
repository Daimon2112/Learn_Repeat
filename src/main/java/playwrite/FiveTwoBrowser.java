package playwrite;

import com.microsoft.playwright.*;

/*
 * TODO:
Try to created in Page object two browser
 */

public class FiveTwoBrowser {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));


// вуаля у нас есть 2 браузера и можно с ними работать без проблем - открываются два отдельных независимых
        BrowserContext context = browser.newContext();
        Page page1 = context.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(page1.title());


        BrowserContext context2 = browser.newContext();
        Page page2 = context2.newPage();
        page2.navigate("https://letcode.in/test");
        System.out.println(page2.title());

        page2.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page1.navigate("https://letcode.in/test");

}
}
