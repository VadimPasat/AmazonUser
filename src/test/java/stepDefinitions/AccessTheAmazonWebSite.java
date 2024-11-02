package stepDefinitions;


import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import page.AddCheapestSnickersToCart;

@Getter
@Log4j
public class AccessTheAmazonWebSite {

    private final ScenarioContext scenarioContext = ScenarioContext.getScenarioContext();
    WebDriver webDriver = (WebDriver) scenarioContext.getData("driver");
    private final AddCheapestSnickersToCart addCheapestSnickersToCart = new AddCheapestSnickersToCart(webDriver);

    @Given("Access Amazon website")
    public void accessAmazonWebSite() throws InterruptedException {
        webDriver.get("https://www.amazon.com/");
    }

    @Then("Amazon home page is displayed")
    public void homePageIsDisplayed() throws InterruptedException {
        addCheapestSnickersToCart.homePageIsDisplayed();
    }
}
