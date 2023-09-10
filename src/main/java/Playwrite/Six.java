package Playwrite;

import com.microsoft.playwright.*;

public class Six {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));



        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        //page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.navigate("https://letcode.in/frame");

//        String getHeaderName = page.locator("//h5[text()='Login']").textContent(); //таким образом получаем текст
//        System.out.println(getHeaderName);


//Гуляние по фреймам - начинаем от главного и спускаемся ниже
//        Frame frame = page.frame("firstFr"); - главный фрейм
//        Frame innerFrame = page.frameByUrl("https://letcode.in/innerFrame"); - второстипенный фрейм
//        innerFrame.getByPlaceholder("Enter email").type("blabla@gmial.com");
//        page.waitForTimeout(10000);



}}
