package Classic;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClassicSelenium {
    public static void main(String[] args) {
        WebDriver driver = WebDriverManager.chromedriver().create();//минуса в том что нужно следить за версией в пом так как бразуеры обновляются
        driver.get("http://www.google.com");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());


    }




}
