package playwrite.pageObject;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;
    private String emailId = "//input[@id='input-email']";
    private String password = "//input[@id='input-password']";
    private String loginBtn = "//input[@value='Login']";

    public LoginPage(Page page){
        this.page = page;
    }

    public String getLoginPageTitle(){
        return page.title();
    }

    public boolean isForgotPasswordLink(){
        return page.isVisible("selector");
    }

    public boolean doLogin(String userName, String userPassword){
        System.out.println(userName + ":" + userPassword);
        page.fill(emailId, userName);
        page.fill(password, userPassword);
        page.click(loginBtn);
        if (page.isVisible("selector")){
            System.out.println("user is logged in succesfully.....");
            return true;
        }
        return false;
    }

}
