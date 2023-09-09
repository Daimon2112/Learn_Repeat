package Playwrite;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class Fourth {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        //Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
//        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
//        launchOptions.setHeadless(false);
//        launchOptions.setSlowMo(100);

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        BrowserContext context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions()//добавляем screen запись https://playwright.dev/java/docs/trace-viewer//тут начало записи
                .setScreenshots(true)
                .setSnapshots(true));


        Page page = context.newPage();


        page.navigate("https://letcode.in/alert");

        page.onDialog(dialog -> {//page.onDialog - говорит о том что будет алерт и что с ним нужно сделать(настраивать доп не нужно)
            System.out.println(dialog.message());
            page.waitForTimeout(10000);
            dialog.accept();
        });

        page.locator("id=accept").click();

        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("testScreen.zip")));// конец записи и помещаем все в зип и потом можем локально открыть или через веб плейрайт

        //System.out.println(page.locator("#myName").textContent());
    }
}