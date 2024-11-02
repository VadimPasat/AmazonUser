package stepDefinitions;

import context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.AddCheapestSnickersToCart;;import java.util.List;

@Getter
@Log4j
public class SearchForCheapestSnickers {

    private final ScenarioContext scenarioContext = ScenarioContext.getScenarioContext();
    WebDriver webDriver = (WebDriver) scenarioContext.getData("driver");
    private final AddCheapestSnickersToCart addCheapestSnickersToCart = new AddCheapestSnickersToCart(webDriver);


    @When("{} are inserted in search bar")
    public void snickersAreInsertedInSearchBar(String item) throws InterruptedException {
        addCheapestSnickersToCart.searchForTheCheapestItems(item);
        Thread.sleep(1000);
    }

    @When("Result is displayed")
    public void ResultIsDisplayed() throws InterruptedException {
        // assertEquals(addCheapestSnickersToCart.getAssertPostSearchAction().getAttribute("value"), "Snickers");
        System.out.println("All good");
    }

    @Then("Sort the price by low to high")
    public void sortThePriceLowToHigh() throws InterruptedException {
        addCheapestSnickersToCart.sortTheProductByLowToHigh();
    }


    @Then("Add the cheapest available {} to the basket")
    public void iAddTheCheapestAvailableItemToTheBasket(String item) {
        addCheapestSnickersToCart.addTheCheapestAvailableItemToTheBasket(item);
    }
}
