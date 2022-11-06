package chromeDevToolProtocol;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v105.network.Network;
import org.openqa.selenium.devtools.v105.network.model.ConnectionType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class EmulateNetworkConditions {
    ChromeDriver driver;
    DevTools devTools;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        devTools = driver.getDevTools();
    }

    @Test
    public void enableSlowConnection(){
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));
        devTools.send(Network.emulateNetworkConditions(
                false,
                150,
                2500,
                2000,
                Optional.of(ConnectionType.CELLULAR3G)
        ));

        driver.get("https://www.linkedin.com/");
        System.out.println("Enable slow Network : " + driver.getTitle());
    }

    @Test
    public void doNotEnableSlowConnection(){
        driver.get("https://www.rexjones2.com/");
        System.out.println("Enable slow Network : " + driver.getTitle());
    }
}
