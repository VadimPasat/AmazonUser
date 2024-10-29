AmazonUser Automation Framework
Overview
This project is an automated test suite for Amazon web testing. The primary test case focuses on simulating a new user searching for specific products (Snickers and Skittles), adding the cheapest items to their basket, and verifying that the basket calculates the total correctly. Additionally, the test checks that when attempting to proceed to checkout without an account, the user is redirected to the registration page.

The project uses Selenium WebDriver and Cucumber to ensure the test flow is written in descriptive language, allowing for easy export and automated reporting. It is set up with multi-OS support (Windows, Linux, macOS) for cross-platform compatibility.

Prerequisites
JDK 11+
Ensure you have JDK 11 or later installed. You can download it from OpenJDK.

Maven
Maven is used for dependency management and project build. You can install it from Maven’s official site.

Chrome Browser
The project currently uses Chrome for browser automation. Make sure you have the latest version of Chrome installed.

Cloning the Project
Clone this repository to your local machine:

bash
Copy code
git clone https://github.com/yourusername/AmazonUser.git
cd AmazonUser
Project Structure
src/test/resources/features
Contains Cucumber feature files. The test cases are written in a descriptive, human-readable format for easy export to automated test reports.

src/test/java/steps
Contains step definitions that map Cucumber feature steps to code.

src/test/java/drivers
Multi-platform support for drivers. Drivers are set up for Windows, Linux, and macOS.

src/test/java/utils
Contains helper functions to keep the test code DRY (Don't Repeat Yourself).

Test Case Description
The main test scenario is written in src/test/resources/features/AmazonUser.feature:

gherkin
Copy code
Feature: Amazon User Product Search and Checkout

  Scenario: Add Cheapest Snickers and Skittles to Basket and Proceed to Checkout
    Given I am on the Amazon homepage
    When I search for "Snickers"
    And I add the cheapest Snickers to my basket
    And I search for "Skittles"
    And I add the cheapest Skittles to my basket
    Then the basket total should be the sum of the added products' prices
    When I proceed to checkout
    Then I should be redirected to the registration page if I’m not logged in
    
Setup Instructions
Install Dependencies
Once you’ve cloned the repository, navigate to the project directory and install the dependencies using Maven:

bash
Copy code
mvn clean install
Setup WebDriverManager
This project uses WebDriverManager to automatically download the appropriate version of ChromeDriver based on the browser version and operating system. No manual driver setup is needed.

Cross-Platform Configuration
WebDriverManager will detect the OS (Windows, Linux, or macOS) and download the correct version of ChromeDriver automatically.

Test Configuration
You can modify test configurations (e.g., base URLs, timeout settings) in the config.properties file located in src/test/resources/config.

Running the Tests
To run all tests in the test suite, execute the following Maven command:

bash
Copy code
mvn test
The results, including step-by-step test execution, will be displayed in the console. The tests are written in a Cucumber format, allowing easy export to automated test reports.

Reporting
By default, Cucumber reports are generated in target/cucumber-reports. You can open the HTML report to view detailed results and screenshots (if any failures occur).

Future Enhancements
Expand Test Suite: Additional scenarios for Amazon’s user journey can be added in a modular format.
Multi-Browser Testing: Add configurations for Firefox, Safari, and Edge.
Headless Mode: For CI/CD integration, configure headless browser testing for faster execution.
