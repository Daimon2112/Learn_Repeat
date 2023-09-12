import classic.Browser;
import classic.ConfigReader;
import classic.LoginPageOrange;
import org.testng.annotations.*;

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
