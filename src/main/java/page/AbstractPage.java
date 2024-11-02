package page;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class AbstractPage {
    protected WebDriverWait wait;
    private final WebDriver driver;

    public AbstractPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 30);
    }
}

