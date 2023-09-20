package playwriteTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePlaywriteTest {

    @Test(priority = 1)
    public void testLogin(){
        System.out.println("Login to Page");
        loginPage = mainPage.navigateToLoginPage();
        loginPage.getLoginPageTitle();
    }

    @Test(priority = 2)
    public void testLogin1(){
        System.out.println("Login to Page 1");
        loginPage = mainPage.navigateToLoginPage();
        loginPage.getLoginPageTitle();
    }

    @Test(priority = 3)
    public void testLogin2(){
        loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());//data from property file
    }

}
