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
    String selenoidUserLogin = System.getProperty("SELENOID_USER_LOGIN");
    String selenoidUserPassword = System.getProperty("SELENOID_USER_PASSWORD");
    String remoteServerUrl = System.getProperty("SELENOID_REMOTE_SERVER");

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = System.getProperty("BROWSER");
        Configuration.browserVersion = System.getProperty("BROWSER_VERSION");
        Configuration.browserSize = System.getProperty("WINDOW_SIZE");
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
                selenoidUserLogin + ":" + selenoidUserPassword +"@" + remoteServerUrl + "/wd/hub";
    }
}