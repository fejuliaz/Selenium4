package elementPosition;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ElementPositionTests {

    WebDriver driver;

  @BeforeClass
    public void setUp(){
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.get("https://testautomationu.applitools.com/learningpaths.html");
  }

  @Test
    public void testElementPosition(){
      WebElement logoTAU = driver.findElement(By.xpath("//div[@id='app']//header/a/img"));
      Rectangle rectTAUlogo = logoTAU.getRect();

      System.out.println("x:" + rectTAUlogo.getX());
      System.out.println("y:" + rectTAUlogo.getY());
      System.out.println("Width:" + rectTAUlogo.getWidth());
      System.out.println("Height:" + rectTAUlogo.getHeight());
  }
}
