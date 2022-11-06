package multiplewindows;

import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class MultipleWindowsTests extends BaseTests {

    @Test
    public void testNewWindowTab() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
        System.out.println("Title : " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothWindows() {

        //Automatically open and switch to new window
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());

        //Work in the new window
        driver.findElement(By.id("email_create")).sendKeys("Selenium4@tna.com");
        driver.findElement(By.id("SubmitCreate")).click();

        //Get the window ID Handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow  = iterate.next();

        //Switch and work in the main window

        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.id("search_query_top")).sendKeys("shirt");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title "+ driver.getTitle());
    }

}
