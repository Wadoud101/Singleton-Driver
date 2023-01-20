import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SingletonDriver {
    private static SingletonDriver singletonDriver;
    private static WebDriver driver;

    private SingletonDriver(String browser) {
        if (driver == null)
        {
            browser = browser == null ? "chrome" : browser;

            switch (browser) {

                case "chrome":
                    ChromeOptions option1=new ChromeOptions();
                    option1.addArguments("--start-maximized","--disable-notifications");
                    option1.setExperimentalOption("excludeSwitches", new String[]{"disable-popup-blocking","enable-automation"});
                    driver= WebDriverManager.chromedriver().avoidShutdownHook().capabilities(option1).create();
                    break;

                case "firefox":
                    driver= new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;

                case "edge":
                    EdgeOptions option3=new EdgeOptions();
                    option3.addArguments("--start-maximized");
                    driver= new EdgeDriver(option3);
                    break;

                case "safari":
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver=new SafariDriver();
                    break;

            }
        }

    }

    public static SingletonDriver getSingletonDriver() {
        if (singletonDriver == null) {
            singletonDriver = new SingletonDriver("chrome");
        }
        return singletonDriver;
    }
}

