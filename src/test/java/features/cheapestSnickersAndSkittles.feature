@BeforeHook
@Run
@AfterHook
Feature: Add cheapest Snickers and Skittles to cart from Amazon

  Background:
    Given Access Amazon website
    And Amazon home page is displayed
    And Choose one ZIP Code from the USA

  Scenario: Snickers and Skittles to cart
    When Skittles are inserted in search bar
    Then Result is displayed
    And Sort the price by low to high
    And Add the cheapest available Skittles to the basket
    When Snickers are inserted in search bar
    Then Result is displayed
    And Sort the price by low to high
    And Add the cheapest available Snickers to the basket
    Then Validate if the basket calculates the result correctly
    And Access the Amazon basket
    And Press to checkout button
    Then Validate if the user gets redirected to the registration page
