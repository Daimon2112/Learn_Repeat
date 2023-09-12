package classic;

import org.openqa.selenium.By;

public class LoginPageOrange extends AbstractPage {

    private static final String URL_ORANGE = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    public void openUrl(){
        browser.open(URL_ORANGE);

    }

    public void enterUserName(String userName) {
        browser.click(By.name("username"));
        browser.sendText(By.name("username"), userName);

    }

    public void enterPassword(String userPassword){
        browser.click(By.name("password"));
        browser.sendText(By.name("password"), userPassword);

    }

    public void pressButtonLogin(){
        browser.click(By.xpath("//button[@type='submit']"));


    }



//Так берутся данные из файла проперти напрямую
//    public void openUrl() throws IOException {
//        browser.open(getUrl());
//
//    }
//
//    public void enterUserName() throws IOException {
//        browser.click(By.name("username"));
//        browser.sendText(By.name("username"), getUserName());
//
//    }
//
//    public void enterPassword() throws IOException {
//        browser.click(By.name("password"));
//        browser.sendText(By.name("password"), getUserPassword());
//
//    }
}
