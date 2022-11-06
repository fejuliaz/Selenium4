package relativeLocators;

import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

public class RelativeLocatorsTests extends BaseTests {


    @Test
    public void relativeLocatorTests(){
        WebElement loginPane = driver.findElement(By.xpath("//h5"));
        WebElement credentials = driver.findElement(RelativeLocator.with(
                By.xpath("//div[@class='orangehrm-login-error']"))
                .below(loginPane));

        System.out.println(credentials.getText());

    }
}
