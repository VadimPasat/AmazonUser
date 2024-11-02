package utils;

import constant.DriverType;
import constant.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static DriverType driverType;
    private WebDriver driver;
    private static OperatingSystem operatingSystem;
    ChromeOptions windowsChromeOptions = new ChromeOptions();
    FirefoxOptions windowsFirefoxOptions = new FirefoxOptions();


    public DriverFactory() {
        driverType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        operatingSystem = FileReaderManager.getInstance().getConfigFileReader().getOperatingSystem();
    }

    public WebDriver getDriver() {
        if (driver == null)
            driver = createLocalDriver();
        return driver;
    }

    private WebDriver createLocalDriver() {
        switch (operatingSystem) {
            case WINDOWS:
                switch (driverType) {
                    case CHROME:
                        System.out.println("Setting up ChromeDriver for Windows");
                        windowsChromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(windowsChromeOptions);
                        break;
                    case FIREFOX:
                        System.out.println("Setting up FirefoxDriver for Windows");
                        windowsFirefoxOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver(windowsFirefoxOptions);
                        break;
                    default:
                        throw new RuntimeException("Unsupported browser for Windows: " + driverType);
                }
                break;
            case MAC:
                switch (driverType) {
                    case CHROME:
                        System.out.println("Setting up ChromeDriver for Mac");
                        windowsChromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
                        WebDriverManager.chromedriver().mac().setup();
                        driver = new ChromeDriver(windowsChromeOptions);
                        break;
                    case FIREFOX:
                        System.out.println("Setting up FirefoxDriver for Mac");
                        windowsFirefoxOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
//                        WebDriverManager.firefoxdriver().driverVersion("0.32.0").mac().setup();
//                        FirefoxOptions options = new FirefoxOptions();
//                        options.addArguments("-safe-mode");
                        WebDriverManager.firefoxdriver().mac().setup();
                        driver = new FirefoxDriver(windowsFirefoxOptions);
                        break;
                    default:
                        throw new RuntimeException("Unsupported browser for Mac: " + driverType);
                }
                break;
            case LINUX:
                switch (driverType) {
                    case CHROME:
                        System.out.println("Setting up ChromeDriver for Linux");
                        windowsChromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
                        WebDriverManager.chromedriver().linux().setup();
                        driver = new ChromeDriver(windowsChromeOptions);
                        break;
                    case FIREFOX:
                        System.out.println("Setting up FirefoxDriver for Linux");
                        windowsFirefoxOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
                        WebDriverManager.firefoxdriver().linux().setup();
                        driver = new FirefoxDriver(windowsFirefoxOptions);
                        break;
                    default:
                        throw new RuntimeException("Unsupported browser for Linux: " + driverType);
                }
                break;

            default:
                throw new RuntimeException("Unsupported operating system: " + operatingSystem);
        }

        if (FileReaderManager.getInstance().getConfigFileReader().getBrowserWindowSize())
            driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().
                getConfigFileReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }
}
