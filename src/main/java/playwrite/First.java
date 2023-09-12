package playwrite;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class First {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        page.type("input[name='username']", "Admin");
        page.type("input[name=\"password\"]", "admin123");//если не кликает тогда поставить \"
        page.click("//button[@type='submit']");
        //page.locator("username").type("Admin");// можно использовать данный тип поиска локаторов если не работает page.type// ВСЕГДА ИСПОЛЬЗОВАТЬ .fill
        //page.locator("username").fill("Admin");// оличие между type и fill в том что fill сначала очищает поля потом пишет текст а type сразу вставляет
        Locator loc = page.locator("input[name='username']");
        loc.press("Tab");// таким образом мы инициализируем нажатие данной клавиатуры - можно любую кнопку прописать
        page.waitForTimeout(10000);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("photo-1.png")));
        System.out.println("Browser opened");
        System.out.println(page.title());
        System.out.println(page.url());
        browser.close();//просто закрывает броузер
        playwright.close();//заканчивает тест
    }
}
