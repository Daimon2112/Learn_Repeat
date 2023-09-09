package Classic;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClassicSelenium {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().create();//минуса в том что нужно следить за версией в пом так как бразуеры обновляются
        WebDriver driver = new ChromeDriver();
        driver.get("https://letcode.in/alert");
        driver.findElement(By.id("accept")).click();
        driver.wait(5000);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        System.out.println(alertText);
        System.out.println(driver.getTitle());


    }




}
