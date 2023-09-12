package playwrite;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Paths;

public class Second {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        BrowserType.LaunchOptions headless = new BrowserType.LaunchOptions().setHeadless(false);
        Page page = playwright.chromium().launch(headless).newPage();

        page.navigate("https://letcode.in/dropdowns");

//1в
        Locator fruitsDD = page.locator("#fruits");
        fruitsDD.selectOption("3");
        String textContent = page.locator("p.subtitle").textContent();


        Locator heros = page.locator("id = superheros");
        heros.selectOption(new String[]{"ta","am"});//таким образом мы ыбрали несколько юзеров в списке

//2в
        //page.selectOption("#lang", "java");
//разница между 1в и 2в в том что первый позволяет обратится непосредственно в определенному локатору и производить с ним действия
     // а 2в находит первый попавшийся и делать с ними действия - если больше 1 дропдауна то не подходит
//        Locator langua = page.locator("#lang");
//        langua.selectOption("java");

        Locator langua = page.locator("#lang");
        Locator options = langua.locator("options");
        int count = options.count();
        langua.selectOption(new SelectOption().setIndex(count-1));//выбираем последний в списке




        //page.locator("username").type("Admin");// можно использовать данный тип поиска локаторов если не работает page.type// ВСЕГДА ИСПОЛЬЗОВАТЬ .fill
        //page.locator("username").fill("Admin");// оличие между type и fill в том что fill сначала очищает поля потом пишет текст а type сразу вставляет
        page.waitForTimeout(10000);
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("photo-1.png")));
        System.out.println("Browser opened");
        System.out.println(page.title());
        System.out.println(page.url());
        page.close();
        playwright.close();//заканчивает тест
    }
}
