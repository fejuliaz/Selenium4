package chromeDevToolProtocol;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChromeDevelopmentToolsTests {

    ChromeDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /**
     * Load errors from the console in Chrome to the console in Intelij
     */
    @Test
    public void  viewBrowserConsoleLogs(){
        //get the Dev tools
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        //enable console logs
        devTools.send(Log.enable());
        //add a listener
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("--------");
            System.out.println("Level " + logEntry.getLevel());
            System.out.println("Text  " + logEntry.getText());
            System.out.println("Broken URL  " + logEntry.getUrl());
        });

        //load the AUT

        driver.get("http://the-internet.herokuapp.com/broken_images");
    }

}
