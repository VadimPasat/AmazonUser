package page.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.AbstractPage;
import page.CustomWebElement;

public class Product extends AbstractPage {

    @Getter
    @FindBy(css = ".title-recipe")
    private WebElement productName;

    @Getter
    @FindBy(css = ".s-image")
    private WebElement productImage;

    @Getter
    @FindBy(css = "[class='a-price']")
    private WebElement productPrice;

    @Getter
    @FindBy(css = "[data-action='puis-atcb-add-action-retail']")
    private WebElement addToCartButton;

    public Product(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CustomWebElement getAddToCartButtonCustomElement() {
        return new CustomWebElement(getDriver(), addToCartButton);
    }
}
