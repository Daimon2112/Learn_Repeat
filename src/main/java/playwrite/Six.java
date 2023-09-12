package playwrite;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Six {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//берем разшерение из монитора
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));//width and height

        Page page = context.newPage();
        //page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.navigate("https://letcode.in/frame");//https://davidwalsh.name/demo/multiple-file-upload.php - check upload



//        String getHeaderName = page.locator("//h5[text()='Login']").textContent(); //таким образом получаем текст
//        System.out.println(getHeaderName);


//Гуляние по фреймам - начинаем от главного и спускаемся ниже
//        Frame frame = page.frame("firstFr"); - главный фрейм
//        Frame innerFrame = page.frameByUrl("https://letcode.in/innerFrame"); - второстипенный фрейм
//        innerFrame.getByPlaceholder("Enter email").type("blabla@gmial.com");
//        page.waitForTimeout(10000);


//singl upload
        page.setInputFiles("textsomeSelectorOTheButton", Paths.get("Absolute File path"));//загрузка one upload file
        page.setInputFiles("textsomeSelectorOTheButton", new Path [0]);//remove file
//multiple upload
        page.setInputFiles("textsomeSelectorOTheButton", new Path[]{
                Paths.get("Absolute File path file1"),
                Paths.get("Absolute File path file2"),
                Paths.get("Absolute File path file3"),
                Paths.get("Absolute File path file4")});//multiple select file upload

//download
        Download download = page.waitForDownload(() -> {
            page.click("put locator here of the button/link");
        });
        Path path = download.path();
        System.out.println(download.path());
        // Save downloaded file somewhere
        System.out.println(download.url());
        System.out.println(download.page().title());
        download.saveAs(Paths.get("/path/to/save/download/or/at.txt"));
        System.out.println(download.suggestedFilename());//get file name







}}
