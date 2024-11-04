package page;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Log4j
public class ChangeDeliveryToAddress extends AbstractPage {

    String filePath = "src/main/resources/zipCodesUSA.csv";

    @FindBy(xpath = "//*[@id='nav-global-location-popover-link']")
    private WebElement deliveryToButton;

    @FindBy(xpath = "//*[@id='GLUXZipUpdateInput']")
    private WebElement enterUSAZipCodeField;

    @FindBy(xpath = "//*[@id='GLUXZipUpdate']/span/input")
    private WebElement applyZipCode;

    @FindBy(xpath = "/html/body/div[5]/div/div/div[2]/span")
    private WebElement continueButton;

    public ChangeDeliveryToAddress(WebDriver driver) {
        super(driver);
    }

    public void changeDeliveryToAddress() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(deliveryToButton));
        deliveryToButton.click();

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            if (records.isEmpty()) {
                log.error("CSV file is empty.");
                return;
            }
        } catch (IOException e) {
            log.error("Failed to read CSV file: " + e.getMessage());
            return;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(records.size());
        List<String> randomIndex = records.get(randomNumber);
        enterUSAZipCodeField.sendKeys(randomIndex.get(0));
        log.info("Fill the USA ZIP code with the value:" + randomIndex.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(applyZipCode));
        applyZipCode.click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
        log.info("The ZIP code was changed");
    }

}
