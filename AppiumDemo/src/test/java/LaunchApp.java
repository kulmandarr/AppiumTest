import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.serverevents.ServerEvents;
import io.appium.java_client.serverevents.TimedEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LaunchApp {
    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability("automationName","uiautomator2");
        capabilities.setCapability("disableSuppressAccessibilityService",true);
        capabilities.setCapability("unicodeKeyboard",true);
        capabilities.setCapability("resetKeyboard",true);
/*        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("location", "NA-US-BOS");
        capabilities.setCapability("resolution", "1080x1920");
        capabilities.setCapability("manufacturer", "Google");
        capabilities.setCapability("model", "Pixel");
        capabilities.setCapability("deviceName", "FA69W0304353");*/

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
           // driver.quit();
        }
    }

    @Test
    public void Addition() throws InterruptedException {
        driver.get("https://TouchCommerce:c0nvers10n@demo.touchcommerce.com/m");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS) ;
        int i;
        String[] source=new String[20];
        try {
            for (i = 0; i < 20; i++) {
                source[i] = driver.getPageSource();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for(i=0;i<20; i++)
        {
            System.out.println(source[i]);
        }

           WebDriverWait wait = new WebDriverWait(driver, 30);
//        List<String> lsArgs = Arrays.asList("help");
//        Map<String, Object> lsCmd =  new HashMap<String, Object>();
//        lsCmd.put("command","uiautomator");
//        lsCmd.put("lsCmd",lsArgs);
//        lsCmd.put("includeStderr",true);
//        String lsOutput = (String) driver.executeScript("mobile: shell", lsCmd);
//        System.out.println(lsOutput);
//            ServerEvents events = driver.getEvents();
//            List<TimedEvent> timedEvents = events.getEvents();
//            for(TimedEvent event:timedEvents)
//            {
//                System.out.println("EventName  " +event.getName());
//            }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Best Brands']")));
        String toastMessage = driver.findElement(By.xpath("//*[@text='Best Brands']")).getText();
        System.out.println(toastMessage);
//        try {
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"inqC2CImgContainer\"]/input")));
//            WebElement clickToChat = driver.findElement(By.xpath("//*[@id=\"inqC2CImgContainer\"]/input"));
//            Actions act = new Actions(driver);
//            act.moveToElement(clickToChat).doubleClick().build().perform();
////            clickToChat.click();
//            WebElement ATTMsg = driver.findElement(By.cssSelector("span.agentMsg"));
//            String AgentMsg = ATTMsg.getText();
//            String transcript;
//            Assert.assertEquals("Hi! Welcome to BestBrands. How may I help you?",AgentMsg);
//            WebElement insertText = driver.findElement(By.cssSelector("textarea#tcChat_txtInput_input.tcChat"));
//            insertText.sendKeys("Hi");
//            WebElement sendButton = driver.findElement(By.cssSelector("button#tcChat_btnSend_img.tcChat"));
//            sendButton.click();
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tcChat_chatWindow_span']//table[@role='presentation']//tr//td//div[@id='msg1']")));
//            List<WebElement> transcripts= driver.findElements(By.xpath("//div[@id='tcChat_chatWindow_span']//table[@role='presentation']//tr//td//span[@class='agentMsg']"));
//            String message1= transcripts.get(1).getText();
//            Assert.assertEquals("Hey there! I’m here for you. Tell me what you’d like help with or ask me a question.",message1);
//        }
//        finally {
            driver.quit();
//        }

    }

}