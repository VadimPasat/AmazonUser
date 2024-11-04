package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWebElement {
    private WebDriver driver;
    private WebElement element;

    public CustomWebElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public void jsClick() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until the element is clickable

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void jsScroll() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
                "var element = arguments[0];" +
                        "var elementRect = element.getBoundingClientRect();" +
                        "var offset = window.innerHeight / 2 - elementRect.height / 2;" +
                        "window.scrollTo(0, elementRect.top + window.pageYOffset + offset);",
                element
        );
    }
}
