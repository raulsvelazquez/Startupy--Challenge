package pom;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;

    static {
        String browserName = System.getenv("browser");

        switch (browserName)
        {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions());
                wait = new WebDriverWait(driver, 10);
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions());
                wait = new WebDriverWait(driver, 10);
                break;
            case "OPERA":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver(operaOptions());
                wait = new WebDriverWait(driver, 10);
                break;
            default:
                throw new IllegalArgumentException(browserName);
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        return chromeOptions;
    }

    private static FirefoxOptions firefoxOptions(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        return firefoxOptions;
    }

    private static OperaOptions operaOptions(){
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.addArguments("--headless");
        return operaOptions;
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, 10);
        action = new Actions(driver);
    }

    public static void navigateTo(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void closeBrowser() {
        driver.quit();
    }

    protected WebElement find(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    private List<WebElement> finds(String locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
    }

    public String title() {
        return driver.getTitle();
    }

    public void clickElement(String locator) {
        find(locator).click();
    }

    public void write(String locator, String textToWrite) {
        find(locator).clear();
        find(locator).sendKeys(textToWrite);
    }

    public String textFromElement(String locator) {
        return find(locator).getText();
    }

    public boolean elementIsDisplayed(String locator) {
        return find(locator).isDisplayed();
    }
}
