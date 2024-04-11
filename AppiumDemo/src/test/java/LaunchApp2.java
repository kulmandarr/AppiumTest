import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

public class LaunchApp2 {
    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        String cloudName = "nuance-public";
        String securityToken = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI0NWFjOWM4OC00YmZmLTQwNGQtYTM2Ny0zOWI2Y2I4OTFlYjEifQ.eyJqdGkiOiI5YzdhNTc3Ny04M2QxLTQ1OTQtOWY3OC04ZjU0NDBhYTNjZjAiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNjEyMjY2NTYwLCJpc3MiOiJodHRwczovL2F1dGgzLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9udWFuY2UtcHVibGljLXBlcmZlY3RvbW9iaWxlLWNvbSIsImF1ZCI6Imh0dHBzOi8vYXV0aDMucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL251YW5jZS1wdWJsaWMtcGVyZmVjdG9tb2JpbGUtY29tIiwic3ViIjoiYTM1OGMzNDEtYWNiYS00NThmLTgwYjgtOGY1ZjZlNzdmMGExIiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwibm9uY2UiOiJkMWNlNDg1Yi1mYmE0LTQxM2ItOTc1Zi1kOGU2NGQwZmY4OTgiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJkZmVlYTU1Yi1lZTIzLTRkNmYtODE4MC1hZmQ2ZjliMmNlNTQiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlcG9ydGl1bSI6eyJyb2xlcyI6WyJhZG1pbmlzdHJhdG9yIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG9mZmxpbmVfYWNjZXNzIn0.ejtAok0CdoEWf9svMEuoGh4Dyji0j9HWEJswSaw9tJU";
//        PerfectoLabUtils.uploadMediaTestingCloud(securityToken,"C:\\PoC\\apk\\app-debug.apk","PRIVATE:app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("location", "NA-US-BOS");
        capabilities.setCapability("resolution", "1080x1920");
        capabilities.setCapability("manufacturer", "Google");
        capabilities.setCapability("model", "Pixel");
        capabilities.setCapability("deviceName", "FA69W0304353");
        capabilities.setCapability("securityToken", securityToken);

        driver = new AndroidDriver(new URL("https://"+ cloudName +".perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            // driver.quit();
        }
    }

    @Test
    public void Addition() {
        driver.get("https://www.att.com/buy/phones/");
        WebDriverWait wait = new WebDriverWait(driver, 60);
        ExpectedCondition<WebElement> c2CReady = ExpectedConditions.visibilityOfElementLocated(By.id("//button[@id='tc-chat-bubble']"));
        try {
            wait.until(c2CReady).click();
//            WebElement element = driver.findElement(By.id("//button[@id='tc-chat-bubble']"));
//            element.click();
        }
        finally {
            driver.quit();
        }

    }
}
