package stepDefinitions;


import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import page.AddCheapestSnickersToCart;
import page.ChangeDeliveryToAddress;

@Getter
@Log4j
public class AccessTheAmazonWebSite {

    private final ScenarioContext scenarioContext = ScenarioContext.getScenarioContext();
    WebDriver webDriver = (WebDriver) scenarioContext.getData("driver");
    private final AddCheapestSnickersToCart addCheapestSnickersToCart = new AddCheapestSnickersToCart(webDriver);
    private final ChangeDeliveryToAddress changeDeliveryToAddress = new ChangeDeliveryToAddress(webDriver);

    @Given("Access Amazon website")
    public void accessAmazonWebSite() throws InterruptedException {
        webDriver.get("https://www.amazon.com/");
        log.info("Access the amazon website");
    }

    @And("Amazon home page is displayed")
    public void homePageIsDisplayed() throws InterruptedException {
        addCheapestSnickersToCart.homePageIsDisplayed();
    }

    @And("Choose one ZIP Code from the USA")
    public void changeLocation() throws InterruptedException {
        changeDeliveryToAddress.changeDeliveryToAddress();

    }
}
