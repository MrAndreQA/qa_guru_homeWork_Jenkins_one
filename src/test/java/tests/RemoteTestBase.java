package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class RemoteTestBase {
    String selenoidUserLogin = System.getProperty("selenoidUserLogin", "user1");
    String selenoidUserPassword = System.getProperty("selenoidUserPassword", "1234");
    String selenoidRemoteServerUrl = System.getProperty(
            "selenoidRemoteServerUrl", "selenoid.autotests.cloud");
    static String browser = System.getProperty("browser", "chrome");
    static String browserVersion = System.getProperty("browserVersion", "128.0");
    static String browserSize = System.getProperty("browserResolution", "1920x1080");

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
//      Configuration.holdBrowserOpen = true;
        Configuration.holdBrowserOpen = false;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Скриншот результата теста");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    public void setupRemote() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.remote = "https://" +
                selenoidUserLogin + ":" + selenoidUserPassword +"@" + selenoidRemoteServerUrl + "/wd/hub";
    }
}