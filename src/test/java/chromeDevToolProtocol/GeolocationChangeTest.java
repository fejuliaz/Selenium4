package chromeDevToolProtocol;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GeolocationChangeTest {

    ChromeDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    /**
     * Mock location for the webpage to run.
     * Find latitude / longitude on latlong.net
     */
    @Test
    public void mockGEOLocation(){
        Map coordinates = new HashMap()
        {{
            put("latitude", 32.746941 );
            put("longitude", -97.092400);
            put("accuracy", 1);
        }};
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("https://where-am-i.org/");
    }

    @Test
    public void mockGEOLocation_Devtools(){
        DevTools devtools = driver.getDevTools();
        devtools.createSession();
        devtools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));
        driver.get("https://where-am-i.org/");
    }


}
