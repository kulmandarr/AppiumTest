import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.remote.MobileCapabilityType;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LaunchBrowser{
    public WebDriver driver;
    public boolean flag=false;
    public String startDate="05/11/2021";
    public String endDate="05/12/2021";

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

/*        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");*/
/*        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("location", "NA-US-BOS");
        capabilities.setCapability("resolution", "1080x1920");
        capabilities.setCapability("manufacturer", "Google");
        capabilities.setCapability("model", "Pixel");
        capabilities.setCapability("deviceName", "FA69W0304353");*/
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_latest\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-extensions");
//        driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_latest\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://daxom.nuance-dax.com/ola");
        driver.findElement(By.xpath("//input[@name='TxtEmpId']")).sendKeys("123269");
        driver.findElement(By.xpath("//input[@name='TxtPassword']")).sendKeys("S@vit@0221");
        driver.findElement(By.xpath("//input[@id='BtnSignIn1']")).click();
    }

    @AfterTest
    public void tearDown() {
        driver.switchTo().defaultContent();
        if (!flag) {
            driver.findElement(By.xpath("//span[text()='Log Out']")).click();
        }
        driver.quit();
    }

    @Test
    public void approveAttendance() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//a[@id='ButtonGo']")).click();
        driver.findElement(By.xpath("//ul[@class='sidebar-menu']//li[@class='treeview']//span[contains(text(),'Attendance')]")).click();
        List<WebElement> subMenu = driver.findElements(By.xpath("//ul[@class='sidebar-menu']//li[@class='treeview active']//span[contains(text(),'Attendance')]//..//..//ul//a"));
        for (WebElement webElement:subMenu)
        {
            if(webElement.getText().contains("Approvals")){
                webElement.click();
                break;
            }
        }
        driver.switchTo().frame("ContentFrame");
        Select month = new Select(driver.findElement(By.id("MYFrom1_ddlMonth")));
        month.selectByValue("01");
        Select year = new Select(driver.findElement(By.id("MYFrom1_ddlYear")));
        year.selectByValue("2021");
        driver.findElement(By.id("buttonSearch")).click();

    }
    @Test
    public void loginHours(){
        driver.findElement(By.xpath("//input[@id='RadioIn']//..//ins")).click();
        driver.findElement(By.xpath("//input[@id='Button2']")).click();
        flag=true;
    }
    @Test
    public void logoutHours(){
        driver.findElement(By.xpath("//input[@id='RadioOut']//..//ins")).click();
        driver.findElement(By.xpath("//input[@id='Button2']")).click();
        flag=true;
    }
    @Test
    public void updateAttendance() throws InterruptedException {
//        SimpleDateFormat  formatter = new SimpleDateFormat("mm/dd/yyyy");
//        Date startDate = new Date();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//a[@id='ButtonGo']")).click();
        driver.findElement(By.xpath("//ul[@class='sidebar-menu']//li[@class='treeview']//span[contains(text(),'Attendance')]")).click();
        List<WebElement> subMenu = driver.findElements(By.xpath("//ul[@class='sidebar-menu']//li[@class='treeview active']//span[contains(text(),'Attendance')]//..//..//ul//a"));
        for (WebElement webElement:subMenu)
        {
            if(webElement.getText().contains("Update Attendance")){
                webElement.click();
                break;
            }
        }
        driver.switchTo().frame("ContentFrame");
        driver.findElement(By.xpath("//label[contains(text(),'Attendance Date')]//..//i")).click();
        driver.findElement(By.xpath("//input[@name='daterangepicker_start']")).clear();
        driver.findElement(By.xpath("//input[@name='daterangepicker_start']")).sendKeys(startDate);
        driver.findElement(By.xpath("//input[@name='daterangepicker_end']")).clear();
        driver.findElement(By.xpath("//input[@name='daterangepicker_end']")).sendKeys(endDate);
        driver.findElement(By.xpath("//label[contains(text(),'Attendance Date')]")).click();
        Select startTimeSelectHH = new Select(driver.findElement(By.xpath("//select[@id='lstInHH']")));
        startTimeSelectHH.selectByValue("10");
        Select startTimeSelectMM = new Select(driver.findElement(By.xpath("//select[@id='lstInMM']")));
        startTimeSelectMM.selectByValue("00");
        Select endTimeSelectHH = new Select(driver.findElement(By.xpath("//select[@id='lstOutHH']")));
        endTimeSelectHH.selectByValue("19");
        Select endTimeSelectMM = new Select(driver.findElement(By.xpath("//select[@id='lstOutMM']")));
        endTimeSelectMM.selectByValue("00");
        Select reason = new Select(driver.findElement(By.id("ddlAll")));
        reason.selectByIndex(4);
//        reason.selectByValue("OBE Worked From Home - (25)");
        driver.findElement(By.id("txtComments")).sendKeys("Work From Home");
        driver.findElement(By.id("BtnUpdate")).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("LblMessage")));
        Assert.assertEquals(driver.findElement(By.id("LblMessage")).getText(),"Attendance Requested Successfully");

        flag=false;
    }
}