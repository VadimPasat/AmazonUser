package stepDefinitions;

import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import page.AddCheapestSnickersToCart;

import static org.junit.Assert.assertEquals;

@Getter
@Log4j
public class SearchForCheapestSnickers {

    private final ScenarioContext scenarioContext = ScenarioContext.getScenarioContext();
    WebDriver webDriver = (WebDriver) scenarioContext.getData("driver");
    private final AddCheapestSnickersToCart addCheapestSnickersToCart = new AddCheapestSnickersToCart(webDriver);


    @When("{} are inserted in search bar")
    public void snickersAreInsertedInSearchBar(String item) throws InterruptedException {
        addCheapestSnickersToCart.searchForTheCheapestItems(item);
    }

    @Then("Result is displayed")
    public void ResultIsDisplayed() throws InterruptedException {
        assertEquals("Results", addCheapestSnickersToCart.getSearchResultsTitle().getText().substring(0, 7));
        log.info("Results are displayed");
    }

    @And("Sort the price by low to high")
    public void sortThePriceLowToHigh() throws InterruptedException {
        addCheapestSnickersToCart.sortTheProductByLowToHigh();
    }

    @And("Add the cheapest available {} to the basket")
    public void iAddTheCheapestAvailableItemToTheBasket(String item) throws InterruptedException {
        addCheapestSnickersToCart.addTheCheapestAvailableItemToTheBasket();
    }

    @And("Validate if the basket calculates the result correctly")
    public void checkIfBasketCalculatesTheResultsCorrectly() throws InterruptedException {
        addCheapestSnickersToCart.checkIfBasketCalculatesTheResultsCorrectly();
    }

    @And("Access the Amazon basket")
    public void accessTheAmazonBasket() throws InterruptedException {
        addCheapestSnickersToCart.clickOnTheBasket();
    }

    @And("Press to checkout button")
    public void pressOnCheckoutButton() throws InterruptedException {
        addCheapestSnickersToCart.clickOnCheckoutButton();
    }


    @And("Validate if the user gets redirected to the registration page")
    public void validateIfUserGetsRedirectedToTheRegistrationPage() throws InterruptedException {
        addCheapestSnickersToCart.singInForm();
    }
}
