import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LaunchApp3 {
    private AndroidDriver driver;
    private String accessKey = "eyJ4cC51Ijo4NDEzNDk1LCJ4cC5wIjo4NDEzNDk0LCJ4cC5tIjoiTVRVNU1EVXhNVFF4T1RZeE1nIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE5MDU4NzMyNTYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.VbsAPpfeeaPW5o0ZKzDRy9sgTfM-0T0sjmpDfAx_Bmc";

    @BeforeTest
    public void setUp() throws Exception {
        String cloudName = "testingcloud";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
        capabilities.setCapability("accessKey", accessKey);


        driver = new AndroidDriver(new URL("https://nuance.experitest.com/wd/hub"), capabilities);

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            // driver.quit();
        }
    }

    @Test
    public void validateChat() {
        driver.get("https://www.att.com/buy/phones/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS) ;
        try {
            WebElement clickToChat = driver.findElement(By.cssSelector("button#tc-chat-bubble"));
            System.out.println(clickToChat.isDisplayed());
            Actions act = new Actions(driver);
            act.moveToElement(clickToChat).doubleClick().build().perform();
            //           clickToChat.click();
            WebElement ATTMsg = driver.findElement(By.cssSelector("span.agentMsg"));
            String AgentMsg = ATTMsg.getText();
            String transcript;
            Assert.assertEquals("Hi, I’m AT&T’s automated virtual assistant. How can I help you today?",AgentMsg);
            WebElement insertText = driver.findElement(By.cssSelector("textarea#tcChat_txtInput_input.tcChat"));
            insertText.sendKeys("Hi");
            WebElement sendButton = driver.findElement(By.cssSelector("button#tcChat_btnSend_img.tcChat"));
            sendButton.click();
            List<WebElement> transcripts= driver.findElements(By.id("//table[@role='presentation']//tr//td"));
            for(WebElement element: transcripts) {
                transcript = element.getText();
                System.out.println(transcript);
            }
            WebElement virtualAgentMessage = driver.findElement(By.cssSelector("div#automaton_vaLink_1590667261690"));
            String expectedMessage = virtualAgentMessage.getText();
            Assert.assertEquals("Hey there! I’m here for you. Tell me what you’d like help with or ask me a question.",AgentMsg);
        }
        finally {
            driver.quit();
        }

    }
}
