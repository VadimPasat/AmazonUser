package page;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Log4j
@Getter
public class AddCheapestSnickersToCart extends AbstractPage {

    WebDriver driver;

    public AddCheapestSnickersToCart addCheapestSnickersToCart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        return null;
    }

    @FindBy(xpath = "//div[@role='heading' and text()='Amazon Payment Products']")
    private WebElement asserHomePage;

    @FindBy(xpath = "//input[@type='text' or @placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement assertSearchField;

    @FindBy(xpath = "//input[contains(@id, 'nav-search-submit')]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='search']//h2[contains(@class, 'a-size-medium-plus') and contains(@class, 'a-color-base') and text()='Results']")
    private WebElement assertPostSearchAction;

    @FindBy(xpath = "//span[@data-action='a-dropdown-button']")
    private WebElement clickOnDropDownButtonSortBy;

    @FindBy(xpath = "//*[@id=\"s-result-sort-select_1\"]")
    private WebElement sortPriceByLowToHigh;

    @FindBy(xpath = "//span[@class=\"a-dropdown-prompt\" and text()='Price: Low to High']")
    private WebElement assertSortByAction;

    @FindBy(css = "div[data-component-type='s-search-result']")
    private List<WebElement> findAllElements;

    @FindBy(xpath = "//button[text()='Add to Cart']")
    private By availableTextLocator;

    @FindBy(css = "input[type='submit']")
    private By addToBasketButtonLocator;

    @FindBy(xpath = "//span[@class='a-offscreen']")
    private By priceLocator;


    public AddCheapestSnickersToCart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void homePageIsDisplayed() {
        wait.until(ExpectedConditions.elementToBeClickable(asserHomePage));
        assertEquals("The page does not seem to be Amazon's homepage.", "Amazon Payment Products", getAsserHomePage().getText().trim());
        log.info("The navigation logo is present and visible.");
    }

    public void searchForTheCheapestItems(String item) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(item);
        wait.until(ExpectedConditions.visibilityOf(assertSearchField));
        assertEquals(assertSearchField.getAttribute("value"), item);
        searchButton.click();
        try {
            assertEquals(assertPostSearchAction.getText(), "Results");
        } catch (TimeoutException | AssertionError e) {
            // Handle the timeout or assertion error
            e.printStackTrace();
            log.info("Login failed");
        }
    }

    public void sortTheProductByLowToHigh() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(clickOnDropDownButtonSortBy));
        clickOnDropDownButtonSortBy.click();
        sortPriceByLowToHigh.click();
        wait.until(ExpectedConditions.visibilityOf(assertSortByAction));
        assertEquals("Price: Low to High", assertSortByAction.getText());
        try {
        } catch (TimeoutException | AssertionError e) {
            e.printStackTrace();
            log.info("Failed to sort the products in acceding price order");
        }
    }

    public void addTheCheapestAvailableItemToTheBasket(String item) {
        WebElement cheapestProduct = null;
        double lowestPrice = Double.MAX_VALUE;

        for (WebElement product : findAllElements) {
            // Check if the product has the "Available" text
            List<WebElement> availability = product.findElements(availableTextLocator);
            if (availability.isEmpty()) {
                continue; // Skip this product if it isn't available
            }
            // Get the price of the product
            WebElement priceElement = product.findElement(priceLocator); // Adjust `priceLocator` as needed
            double price = Double.parseDouble(priceElement.getText().replace("$", "")); // Parse price as double

            // Check if this is the cheapest available product
            if (price < lowestPrice) {
                lowestPrice = price;
                cheapestProduct = product;
            }
        }
        // If a cheapest available product was found, add it to the basket
        if (cheapestProduct != null) {
            WebElement addButton = cheapestProduct.findElement(addToBasketButtonLocator);
            addButton.click();
        } else {
            throw new AssertionError("No " + item + " products available at the moment");
        }
    }
}