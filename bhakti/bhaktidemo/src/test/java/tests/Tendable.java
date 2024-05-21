package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tendable {

    WebDriver driver; 
    Parser parser;
    WebDriverWait wait;

    @BeforeTest
    public void openDriver() throws IOException, InterruptedException {
        driver = new ChromeDriver();
        parser = new Parser("src/main/resources/ObjectRepository.properties");
        driver.get("https://www.tendable.com");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    /* Confirm accessibility of the top menus */
    @Test
    public void confirmAccessibility() throws InterruptedException {
        //Home
        Boolean home = driver.findElement(parser.getObjectLocator("Home")).isDisplayed();
        Assert.assertEquals(home, true);

        //Our Story
        Boolean ourStory = driver.findElement(parser.getObjectLocator("OurStory")).isDisplayed();
        Assert.assertEquals(ourStory, true);

        //Our Solution
        Boolean ourSolution = driver.findElement(parser.getObjectLocator("OurSolution")).isDisplayed();
        Assert.assertEquals(ourSolution, true);

        //Why Tendable
        Boolean whyTendable = driver.findElement(parser.getObjectLocator("WhyTendable")).isDisplayed();
        Assert.assertEquals(whyTendable, true);
    }

    /* Verify "Request A Demo" button is present and active */
    @Test
    public void demoButton() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Home page
        Boolean homeDemoButtonPresent = driver.findElement(parser.getObjectLocator("RequestADemo")).isDisplayed();
        Assert.assertEquals(homeDemoButtonPresent, true);
        Boolean homeDemoButtonActive = driver.findElement(parser.getObjectLocator("RequestADemo")).isEnabled();
        Assert.assertEquals(homeDemoButtonActive, true);

        //Navigate to Our Story page
        driver.findElement(parser.getObjectLocator("OurStory")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parser.getObjectLocator("TendableMeaning")));
        Boolean ourStoryDemoButtonPresent = driver.findElement(parser.getObjectLocator("RequestADemo")).isDisplayed();
        Assert.assertEquals(ourStoryDemoButtonPresent, true);
        Boolean ourStoryDemoButtonActive = driver.findElement(parser.getObjectLocator("RequestADemo")).isEnabled();
        Assert.assertEquals(ourStoryDemoButtonActive, true);

        //Navigate to Why Tendable? page
        driver.findElement(parser.getObjectLocator("WhyTendable")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parser.getObjectLocator("ChooseTendable")));
        Boolean whyTendableDemoButtonPresent = driver.findElement(parser.getObjectLocator("RequestADemo")).isDisplayed();
        Assert.assertEquals(whyTendableDemoButtonPresent, true);
        Boolean whyTendableDemoButtonActive = driver.findElement(parser.getObjectLocator("RequestADemo")).isEnabled();
        Assert.assertEquals(whyTendableDemoButtonActive, true);
    }

    /* Error Message validation */
    @Test
    public void errorMessageValidation() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Navigate to Contact Us page
        driver.findElement(parser.getObjectLocator("ContactUs")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parser.getObjectLocator("MarketingContact")));
        driver.findElement(parser.getObjectLocator("MarketingContact")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parser.getObjectLocator("FullName")));
        
        //Enter details into the form
        driver.findElement(parser.getObjectLocator("FullName")).sendKeys("Bhakti Purohit");
        driver.findElement(parser.getObjectLocator("OrganisationName")).sendKeys("Tendable");
        driver.findElement(parser.getObjectLocator("CellPhone")).sendKeys("0123456789");
        driver.findElement(parser.getObjectLocator("Email")).sendKeys("abc@gmail.com");
        Select dropdown = new Select(driver.findElement(parser.getObjectLocator("JobRole")));
        dropdown.selectByValue("Management"); 
        driver.findElement(parser.getObjectLocator("Agreed")).click();
        driver.findElement(parser.getObjectLocator("Submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parser.getObjectLocator("ErrorMessage")));
        if(driver.findElement(parser.getObjectLocator("ErrorMessage")).isDisplayed()){
            System.out.println("PASS");
        } 
        else{
            System.out.println("FAIL");
        }
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}
