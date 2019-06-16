package com.leeroy.album.steps;

import com.leeroy.album.utils.ConfigProperties;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AlbumSteps {

    private Properties properties;
    private DesiredCapabilities capabilities;
    private IOSDriver<IOSElement> driver;


    @Before
    public void beforeTest() {
        properties = ConfigProperties.loadProperties();
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPad Simulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        capabilities.setCapability(MobileCapabilityType.APP, properties.getProperty("app.path"));
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "AlbumTest");
        capabilities.setCapability("useNewWDA", false);
        capabilities.setCapability("bundleId", "com.anoop.QATest");
    }

    @Given("^User launch the Leeroy mobile app$")
    public void userVisitsTheLeeroyMobileApp() throws MalformedURLException {
        URL url = new URL(properties.getProperty("appium.url"));
        driver = new IOSDriver<>(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        assertLoginText();
    }

    private void assertLoginText() {
        String loginText = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Login\"]")).getText();
        Assert.assertEquals(loginText, "Login");
    }

    @When("^User enters the credentials$")
    public void userEntersTheCredentials() {
        driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"QATest\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[1]"))
                .sendKeys(properties.getProperty("valid.user"));
        driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"QATest\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[2]"))
                .sendKeys(properties.getProperty("valid.password"));
    }

    @And("^User clicks the Login$")
    public void userClicksTheLoginButton() {
        driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Login\"]")).click();
    }

    @Then("^User Successfully Logged in to the Leeroy music album$")
    public void userSuccessfullyLoggedInToTheLeeroyMusicAlbum() {
        String homeText = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"David Albums\"]")).getText();
        Assert.assertEquals(homeText, "David Albums");
    }

    @And("^User clicks album to see the title and singer$")
    public void userClicksAlbumToSeeTheTitleAndSinger() {
        driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"QATest\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther"))
                .click();
        String title = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Album Details\"]")).getText();
        Assert.assertEquals(title, "Album Details");
        Assert.assertEquals(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"1989\"]")).getText(), "1989");
        Assert.assertEquals(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Taylor Swift\"]")).getText(), "Taylor Swift");
    }

    @Then("^User click back to albums page$")
    public void userClickBackToAlbumsPage() {
        driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"David Albums\"]")).click();
    }

    @And("^User clicks logout$")
    public void userClicksLogout() {
        driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Logout\"]"));
    }

    @When("^User enters the wrong credentials$")
    public void userEntersTheWrongCredentials() {
        driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"QATest\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[1]"))
                .sendKeys("test");
        driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"QATest\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField[2]"))
                .sendKeys("test");
    }

    @Then("^User stays in login page$")
    public void userStaysInLoginPage() {
        assertLoginText();
    }

    @After
    public void AfterTest() {
        driver.removeApp("com.anoop.QATest");
        driver.closeApp();
        driver.quit();
    }
}
