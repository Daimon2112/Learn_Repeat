import Classic.Browser;
import Classic.ConfigReader;
import Classic.LoginPageOrange;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.lang.module.Configuration;

public class BaseTest {
    protected LoginPageOrange loginPageOrange = new LoginPageOrange();
    protected ConfigReader configReader = new ConfigReader();


    @BeforeTest
    public void init(){
        Browser.getInstance();
    }


    @AfterTest
    //@AfterMethod//работает в связке с датапровайдером в данном случаи метод означает как прогон 1 теста с одними данными
    public void tearDown(){
        Browser.kill();
    }
}
