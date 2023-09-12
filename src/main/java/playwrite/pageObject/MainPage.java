package playwrite.pageObject;

import com.microsoft.playwright.Page;

public class MainPage  {

    private Page page;

    private String search = "locator";
    private String searchIcon = "locator";
    private String searchPageHeader = "locator";
    private static final String GOTOURL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";

    public MainPage(Page page){
        this.page = page;
    }

    public void openSite(){
        page.navigate(GOTOURL);
    }

    public String getMainPageTitle() {
        return page.title();
    }

    public String getMainPageUrl(){
        return page.url();
    }

    public String doSearch(String productName){
        page.fill(search, productName);
        page.click(searchIcon);
        return page.textContent(searchPageHeader);
    }


}
