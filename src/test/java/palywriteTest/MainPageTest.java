package palywriteTest;


import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.*;
import playwrite.pageObject.Browsers;
import playwrite.pageObject.MainPage;

public class MainPageTest {

    Browsers br;
    Page page;

    MainPage mainPage;

    @BeforeTest
    public void setup(){
        br = new Browsers();
        page = br.initBrowser("chromium");
        mainPage = new MainPage(page);
    }

    @Test
    public void firstTest(){
        mainPage.getMainPageTitle();
    }

    @DataProvider
    public Object[][] getTestData(){
        return new Object[][]{
                {"macboor"},
                {"imac"},
                {"iphone"}
        };
    }

    @Test(dataProvider = "getTestData")
    public void secondTest(String productName){
        String actualSearch = mainPage.doSearch(productName);
        Assert.assertEquals(actualSearch, "Search - " +productName);
    }

    @AfterTest
    public void finish(){
        page.context().browser().close();
    }
}
