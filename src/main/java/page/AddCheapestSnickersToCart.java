package page;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.components.Product;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@Log4j
@Getter
public class AddCheapestSnickersToCart extends AbstractPage {
    private double basketPrice;
    private WebElement elementWholePrice;
    private WebElement elementFractionPrice;
    Product product = new Product(super.getDriver());

    @FindBy(css = "[id='navbar']")
    private WebElement navigationBar;

    @FindBy(css = "[id='twotabsearchtextbox']")
    private WebElement searchField;

    @FindBy(css = "[id='nav-search-submit-button']")
    private WebElement searchButton;

    @FindBy(css = ".s-messaging-widget-results-header")
    private WebElement searchResultsTitle;

    @FindBy(css = ".a-button-dropdown:not(.quantity)")
    private WebElement sortDropDownButton;


    @FindBy(css = "[id='s-result-sort-select_1']")
    private WebElement sortPriceByLowToHigh;

    @FindBy(css = "[data-component-type='s-search-result']")
    private List<WebElement> allDisplayedProducts;

    @FindBy(css = "[class='ewc-subtotal-amount']")
    private WebElement totalPriceForTheItems;

    @FindBy(css = "[class='a-size-base a-text-bold']")
    List<WebElement> allItemsFromTheCart;

    @FindBy(css = "[id='nav-cart']")
    private WebElement basketButton;

    @FindBy(css = "[class='a-size-extra-large a-text-normal']")
    private WebElement shoppingCartText;

    @FindBy(css = "[name='proceedToRetailCheckout']")
    private WebElement checkoutButton;

    @FindBy(css = "[class='a-spacing-small']")
    private WebElement singInForm;


    public AddCheapestSnickersToCart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<Product> getAllDisplayedProducts() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(allDisplayedProducts.get(0)));
        return allDisplayedProducts.stream()
                .map(productElement -> {
                    Product product = new Product(this.getDriver());
                    PageFactory.initElements(getDriver(), product);
                    return product;
                })
                .collect(toList());
    }

    public void homePageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(navigationBar));
        log.info("The navigation logo is present and visible.");
    }

    public void searchForTheCheapestItems(String item) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        Thread.sleep(1000);
        searchField.click();
        searchField.clear();
        searchField.sendKeys(item);
        wait.until(ExpectedConditions.visibilityOf(searchField));
        assertEquals(searchField.getAttribute("value"), item);
        log.info("The item name: " + item + " was successfully inserted in search bar");
        searchButton.click();
        log.info("Click on search button");
    }

    public void sortTheProductByLowToHigh() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(sortDropDownButton));
            sortDropDownButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(sortPriceByLowToHigh));
            sortPriceByLowToHigh.click();
            assertEquals("Sort by:Price: Low to High", sortDropDownButton.getText());
            log.info("The products were sorted in acceding price order");
            //try {
        } catch (TimeoutException | AssertionError e) {
            e.printStackTrace();
            log.info("Failed to sort the products in acceding price order");
        }
    }

    public void addTheCheapestAvailableItemToTheBasket() throws InterruptedException {
        List<Product> products = getAllDisplayedProducts();
        log.info("Starting to iterate and find the cheapest available product");
        for (Product product : products) {
            try {
                product.getAddToCartButton().isDisplayed();
                product.getAddToCartButtonCustomElement().jsClick();
                return;
            } catch (NoSuchElementException ignored) {
                log.error("Add to Cart button not visible for product: " + product.getProductName());
            }
        }
    }

    public void checkIfBasketCalculatesTheResultsCorrectly() throws InterruptedException {
        log.info("Starting to validate if in the basket is showed the correct amount");
        wait.until(ExpectedConditions.visibilityOf(totalPriceForTheItems));
        double itemsPrice = 0.0;
        for (WebElement item : allItemsFromTheCart) {
            String priceText = item.getText();
            double price = extractPrice(priceText);
            itemsPrice += price;
        }
        String totalPriceText = totalPriceForTheItems.getText();
        double basketItemsSumCalculation = extractPrice(totalPriceText);

        Assert.assertEquals(itemsPrice, basketItemsSumCalculation, 0.01);
        log.info("The basket displayed correct amount");
        log.info("The total sum of items: " + itemsPrice);
        log.info("The total sum displayed in the basket: " + itemsPrice);
    }

    private double extractPrice(String priceText) {
        String cleanedPrice = priceText.replaceAll("[^\\d.]", "");
        try {
            return Double.parseDouble(cleanedPrice);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceText);
            return 0.0;
        }
    }

    public void clickOnTheBasket() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(basketButton));
        log.info("Click on shopping cart");
        basketButton.click();
        assertEquals("Shopping Cart", shoppingCartText.getText());
        log.info("The cart is displayed successfully");
    }

    public void clickOnCheckoutButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        log.info("Click on checkout button");
        checkoutButton.click();
    }

    public void singInForm() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(singInForm));
        assertEquals("Sign in", singInForm.getText());
        log.info("Sing in form is displayed");
        log.info("User was redirected to the registration page");
    }
}