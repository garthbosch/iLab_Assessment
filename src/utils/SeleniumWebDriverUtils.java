package utils;


import gfb.logging.Logging;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SeleniumWebDriverUtils {

    public WebDriver driver;
    public String url;
    public String browserType;
    public Integer waitTimeOut;
    public Boolean isDriverRunning;
    protected static Logger log = Logging.getLogger(true);

    File fileChromeDriver;
    File fileIEDriver;
    File fileFirefoxDriver;

    public SeleniumWebDriverUtils(String url, String browserType, Integer waitTimeOut) {
        this.url = url;
        this.browserType = browserType;
        this.waitTimeOut = waitTimeOut;
        isDriverRunning = false;
        fileChromeDriver = new File("lib\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        fileIEDriver = new File("lib\\IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", fileIEDriver.getAbsolutePath());
        fileFirefoxDriver = new File("lib\\geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", fileFirefoxDriver.getAbsolutePath());
    }

    public Boolean getDriverRunning() {
        return isDriverRunning;
    }

    public void startDriver() throws Exception {
        switch (browserType.toLowerCase()) {
            case BrowserType.CHROME:
                driver = new ChromeDriver(setCapabilities(BrowserType.CHROME));
                isDriverRunning = true;
                setURL();
                break;

            case BrowserType.FIREFOX:
                driver = new FirefoxDriver(setCapabilities(BrowserType.FIREFOX));
                isDriverRunning = true;
                break;

            case BrowserType.IE:
                driver = new InternetExplorerDriver(setCapabilities(BrowserType.IE));
                isDriverRunning = true;
                break;

            default:
                driver = new ChromeDriver(setCapabilities(BrowserType.CHROME));
                isDriverRunning = true;
                setURL();
                break;
        }
    }

    private DesiredCapabilities setCapabilities(String browserTypes) {
        DesiredCapabilities dc = null;
        if (browserTypes.equalsIgnoreCase(BrowserType.CHROME)) {
            dc = DesiredCapabilities.chrome();
            dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
            dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            dc.setJavascriptEnabled(true);
            ChromeOptions options = new ChromeOptions();
            /* added this code to remove the error message chrome was throwing "You are using an unsupported command-line flag:
             * --ignore-certificate-errors. Stability and security will suffer" */
            options.addArguments("test-type");
            /* added this code to remove the message chrome was throwing "Chrome is being controlled by automated test software" */
            options.addArguments("disable-infobars");
            dc.setCapability(ChromeOptions.CAPABILITY, options);
            return dc;
        }
        if (browserTypes.equalsIgnoreCase(BrowserType.IE)) {
            dc = DesiredCapabilities.internetExplorer();
            dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
            dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            dc.setJavascriptEnabled(true);
            return dc;
        }
        if (browserTypes.equalsIgnoreCase(BrowserType.FIREFOX)) {
            dc = DesiredCapabilities.firefox();
            dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
            dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            dc.setCapability("marionette", true);
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
            dc.setJavascriptEnabled(true);
            return dc;
        }
        return dc;
    }

    private void setURL() throws Exception {
        if (url != null && !url.isEmpty()) {
            driver.manage().timeouts().implicitlyWait(waitTimeOut, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(waitTimeOut, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(url);
        } else {
            throw new Exception("====================NO URL SPECIFIED======================");
        }
    }

    public void shutdown() {
        try {
            driver.quit();
            log.info("Driver shutting down");
        } catch (Exception ex) {
            log.error("Error found while shutting down driver - " + ex.getMessage());
        }
        isDriverRunning = false;
    }

    public Boolean waitForElementClickable(String xpath) {
        try {
            WebElement element = (new WebDriverWait(driver, waitTimeOut)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            log.info("Element " + xpath + " found on page");
            return true;
        } catch (Exception ex) {
            log.error("Unable to find element " + xpath + " - " + ex.getMessage());
            return false;
        }
    }

    public void clickByXpath(String element) {
        if (waitForElementClickable(element)) {
            driver.findElement(By.xpath(element)).click();
            log.info("Element " + element + " successfully clicked.");
        }
    }

    public void clickByLinkText(String linkText) {
        if (waitForElementClickable(linkText)) {
            driver.findElement(By.linkText(linkText)).click();
            log.info("Link Text " + linkText + " successfully clicked.");
        }
    }

    public String getTextByXpath(String element) {
        if (waitForElementClickable(element)) {
            return driver.findElement(By.xpath(element)).getText();
        }
        return null;
    }

    public void enterTextByXpath(String element, String text) {
        if (waitForElementClickable(element)) {
            driver.findElement(By.xpath(element)).sendKeys(text);
            log.info("Text " + text + " entered in element " + element + " successfully");
        }
    }

    public String getPageTitle() throws Exception {
        log.info("The title of the page: " + driver.getTitle());
        return driver.getTitle();
    }

    public boolean getIsElementDisplayed(String element) {
        if (waitForElementClickable(element)) {
            driver.findElement(By.xpath(element)).isDisplayed();
            log.info("Element " + element + " successfully displayed");
            return true;
        } else {
            return false;
        }
    }
}