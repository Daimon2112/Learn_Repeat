import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static classic.ConfigReader.*;

public class TestSome extends BaseTest {



    @DataProvider(name = "TestData")
    public Object[][] testData(){
        return new Object[][]{
                {"Admin", "admin123"},
                {"NoAdmin", "456123"},
                {"Whiskas", "Kitkat"}
        };
    }

    @Test
    public void firstTest(String username, String password) {
        loginPageOrange.openUrl();
        loginPageOrange.enterUserName(username);
        loginPageOrange.enterPassword(password);
        loginPageOrange.pressButtonLogin();
        System.out.println(username);
        System.out.println(password);
    }

    @Test(dataProvider ="TestData", priority = 1)//+ добавить анотация в basetest
    public void firstTestUseDataProvider(String username, String password) {
        loginPageOrange.openUrl();
        loginPageOrange.enterUserName(username);
        loginPageOrange.enterPassword(password);
        loginPageOrange.pressButtonLogin();
        System.out.println(username);
        System.out.println(password);
    }

    @Test
    public void firstTestUsePropertyData() throws IOException {
        String url = getUrl();
        String username = getUserName();
        String password = getUserPassword();
        loginPageOrange.openUrl();//tyt url vstavlyiaem
        loginPageOrange.enterUserName(username);
        loginPageOrange.enterPassword(password);
        loginPageOrange.pressButtonLogin();

    }
}
