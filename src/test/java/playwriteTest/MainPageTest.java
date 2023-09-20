package playwriteTest;


import org.testng.Assert;
import org.testng.annotations.*;

public class MainPageTest extends BasePlaywriteTest {



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

}
