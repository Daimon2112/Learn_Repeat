package playwriteTest;

import com.microsoft.playwright.Page;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import playwrite.pageObject.Browsers;
import playwrite.pageObject.LoginPage;
import playwrite.pageObject.MainPage;

import java.util.Properties;

public class BasePlaywriteTest {

    Browsers br;
    Page page;
    Properties prop;

    MainPage mainPage;
    LoginPage loginPage;


    @BeforeTest
    public void setup(){
        br = new Browsers();
        prop = br.init_prop();
        page = br.initBrowser(prop);
        mainPage = new MainPage(page);
        //loginPage = new LoginPage(page);

    }

    @AfterTest
    public void finish(){
        page.context().browser().close();
    }



}
