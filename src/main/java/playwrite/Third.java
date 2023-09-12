package playwrite;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;



public class Third {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
//        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
//        launchOptions.setHeadless(false);
//        launchOptions.setSlowMo(100);
//        Browser browser = playwright.chromium().launch(launchOptions);
//        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setJavaScriptEnabled(true));
//
//        Page page = context.newPage();

        page.navigate("https://letcode.in/alert");

        page.onDialog(dialog -> {//page.onDialog - говорит о том что будет алерт и что с ним нужно сделать(настраивать доп не нужно)
                    System.out.println(dialog.message());
                    page.waitForTimeout(10000);
                    dialog.accept();
                });

        page.locator("id=accept").click();

        //System.out.println(page.locator("#myName").textContent());



}
}
