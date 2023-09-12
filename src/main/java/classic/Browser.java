package classic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Browser {

    private static final int WAIT_ELEMENT_TIMEOUT_SECOND = 30;
    private WebDriver driver;
    private static Browser instance = null;

    private Browser(WebDriver driver){
        this.driver = driver;
    }

    public static Browser getInstance(){
        if(instance != null){
            return instance;
        }
        return instance = init();
    }

    private static Browser init(){
        //WebDriver driver = WebDriverManager.chromedriver().create(); first variant

        WebDriverManager.chromedriver().setup();//second variant
        WebDriver driver = new ChromeDriver();
        ChromeOptions co = new ChromeOptions();
        co.setHeadless(true);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);
//        options.setExperimentalOption("w3c", false);
//        options.setExperimentalOption("useAutomationExtension", false);
//        options.addArguments("start-maximized");
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        WebDriver driver = new ChromeDriver(options);
        return new Browser(driver);
    }

    public void open(String url) {
        //Logger.debug("Go to URL: " + url);
        driver.get(url);
    }

    public void click(By locator) {
        waitForVisible(locator);
        highlightElement(locator);
        unHighlightElement(locator);
        //Logger.debug("Click element '" + driver.findElement(locator).getText() + "' (" + locator + ")");
        try {
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            //Logger.error("Click intercepted: " + locator);
            takeScreenshot("Click-intercepted");
            throw e;
        }
    }

    public void sendText(By locator, String text) {
        waitForVisible(locator);
        //element.clear();
        highlightElement(locator);
        //Logger.debug("Type text '" + text + "' (" + locator + ")");
        driver.findElement(locator).sendKeys(text);
        unHighlightElement(locator);
    }

    public String getText(By locator) {
        waitForVisible(locator);
        highlightElement(locator);
        unHighlightElement(locator);
        String text = driver.findElement(locator).getText();
        //Logger.debug("Get text of element (" + locator + "): " + text);
        return text;
    }

    public boolean isElementPresent(By locator) {
        boolean isPresent = driver.findElements(locator).size() > 0;
        //Logger.debug("Is element present (" + locator + "): " + isPresent);
        return isPresent;
    }

    private void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background-image: radial-gradient(lime, transparent 75%)');", driver.findElement(locator));
    }

    private void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background-image: none');", driver.findElement(locator));
    }

    public WebElement waitForPresent(By locator) {
        return new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT_SECOND).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForVisible(By elementBy) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(elementBy));//visibilityOfAllElementsLocatedBy
    }

    public void waitForElementDisappear(int timeout, By locator) {
        //Logger.debug("Wait until element disappear (" + locator + ")");
        ExpectedCondition<Boolean> elementIsAbsent = driver -> driver.findElements(locator).size() == 0;
        new WebDriverWait(driver, timeout).until(elementIsAbsent);
    }

    public void selectDropdownByName(By locator, String text) {
        //Logger.debug("Select dropdown '" + text + "' (" + locator + ")");
        Select dropdownlist = new Select(driver.findElement(locator));
        dropdownlist.selectByVisibleText(text);
    }

    public void scrollToElement(By locator) {
        waitForPresent(locator);
        do {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
            sleep(50);
        }
        while (!driver.findElement(locator).isDisplayed());
    }

    public String getAttribute(By locator, String attribute) {
        waitForVisible(locator);
        highlightElement(locator);
        waitForVisible(locator);
        //Logger.debug("Get attribute: " + driver.findElement(locator).getAttribute(attribute));
        return driver.findElement(locator).getAttribute(attribute);

    }

    public int countElements(By locator) {
        int size = driver.findElements(locator).size();
        //Logger.debug("Count elements (" + locator + "): " + size);
        return size;
    }

    public void setCheckBox(By locator, boolean value) {
        //Logger.debug("Set checkbox value (" + locator + "): " + value);
        waitForVisible(locator);
        if (driver.findElement(locator).isSelected() != value) {
            driver.findElement(locator).click();
        }
    }

    public String getTextOfNthElement(By locator, int n) {
        List<WebElement> elements = driver.findElements(locator);
        WebElement nthElement = elements.get(n);
        String text = nthElement.getText();
        //Logger.debug("Get text of " + n + "-th element (" + locator + "): " + text);
        return text;
    }

    public void uploadFile(By locator, String absolutePathToFile) {
        waitForPresent(locator);
        //Logger.debug("Uploading file " + absolutePathToFile);
        driver.findElement(locator).sendKeys(absolutePathToFile);
    }

    public void waitForFileDownload(int timeout, String filepath) {
        //Logger.debug("Wait until file downloaded (" + filepath + ")");
        ExpectedCondition<Boolean> ifFileExists = driver -> new File(filepath).exists();
        new WebDriverWait(driver, timeout).until(ifFileExists);
    }

    public void clearLine(By element) {
        int cnt = 0;
        WebElement path = driver.findElement(element);
        while (cnt++ < 30) {
            path.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void switchToFrame(By locator) {
        WebElement iframe = waitForPresent(locator);
        driver.switchTo().frame(iframe);
    }

    public int getNumberOfOpenedTabs() {
        int size = driver.getWindowHandles().size();
        //Logger.debug("Count opened tabs: " + size);
        return size;
    }

    public void waitForTabsNumber(int expectedTabsNumber) {
        //Logger.debug("Wait until windows number is " + expectedTabsNumber);
        new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfWindowsToBe(expectedTabsNumber));
    }

    public void switchToTab(int tabNumber) {
        //Logger.debug("Switch to tab " + tabNumber);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber - 1));
    }

    public void findTabAndSwitch(By expectedElement) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (isElementPresent(expectedElement)) return;
        }
        throw new RuntimeException("Tab with element " + expectedElement + " not found");
    }

    /**
     * Opens a new browser tab and switches focus to it.
     */
    public void openNewTabAndSwitchToIt() {
        //Logger.debug("Open a new tab and switch to it");
        ((JavascriptExecutor) driver).executeScript(("window.open('','_blank');"));
        String current = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        handles.remove(current);
        String newTab = handles.iterator().next();
        driver.switchTo().window(newTab);
    }

    /**
     * Condition: Two browser tabs are opened.
     * Closes the first tab and switches focus to the second tab.
     */
    public void closeFirstTabAndSwitchToSecondTab() {
        String current = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        driver.close();
        handles.remove(current);
        String newTab = handles.iterator().next();
        driver.switchTo().window(newTab);
    }

    public void windowTabSwitchUsingJS(String linkinnewtab) {//open new tab
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(linkinnewtab);
        System.out.println(driver.getWindowHandles());//возвращает id 2 вкладок
    }

    public void switchAndControlTab(int numbertab, String link) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(numbertab));
        driver.get(link);
    }

    /**
     * Switches to a new already opened tab.
     */
    public void switchToNewTab() {
        Set<String> handles = driver.getWindowHandles();
        String current = driver.getWindowHandle();
        handles.remove(current);
        String newTab = handles.iterator().next();
        driver.switchTo().window(newTab);
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        //Logger.debug("Get current URL: " + url);
        return url;
    }

    public void openNewTabAndCloseCurrentTab() {
        String current = driver.getWindowHandle();
        openNewTabAndSwitchToIt();
        driver.switchTo().window(current);
        System.out.println("Window with title: " + driver.getTitle() + " closed at: " + new Date().toString());
        driver.close();
    }

    /**
     * Closes an active tab and switches to the original tab.
     */
    public void closeCurrentTabAndSwitchToOriginalTab() {
        Set<String> handles = driver.getWindowHandles();
        System.out.println("Window with title: " + driver.getTitle() + " closed at: " + new Date().toString());
        driver.close();
        // wait while progress is sent after course exit
        sleep(2);
        driver.switchTo().window(handles.iterator().next());
    }

    /**
     * Opens a new clean browser tab, switches to it and closes the previous browser tab.
     */
    public void openNewTabSwitchToItAndClosePreviousTab() {
        ((JavascriptExecutor) driver).executeScript(("window.open('','_blank');"));
        System.out.println("Number of tabs after opening a new tab: " + getNumberOfOpenedTabs());
        closeFirstTabAndSwitchToSecondTab();
    }

    public String takeScreenshot(String name) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String scrPath = "screenshots/" + name + ".jpg";
            File copy = new File(scrPath);
            FileUtils.copyFile(screenshot, copy);
            return scrPath;
        } catch (IOException e) {
            System.out.println();
            //Logger.error("Failed to make screenshot");
            return null;
        }
    }

    public static void sleep(long ms) {
        try {
//            Logger.debug("Wait " + ms + " ms");
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<String> getTextOfElementsAsList(By locator) {
        List<String> results = new ArrayList<>();
        driver.findElements(locator).forEach(e -> results.add(e.getText()));
        return results;
    }

    public void closeOtherTabs() {
        String mainTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(mainTab)) {
                driver.switchTo().window(tab);
                driver.close();
            }
        }
        driver.switchTo().window(mainTab);
    }

    public static void kill() {
        if (instance != null) {
            try {
                instance.driver.quit();
            } finally {
                instance = null;
            }
        }
    }
}
