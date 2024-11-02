@BeforeHook
@Run
@AfterHook
Feature: Add cheapest Snickers and Skittles to cart from Amazon

  Background:
    Given Access Amazon website
    Then Amazon home page is displayed

  Scenario: Snickers and Skittles to cart
    When Skittles are inserted in search bar
    And Result is displayed
    Then Sort the price by low to high
    And Add the cheapest available Skittles to the basket
#    Then Add the product to cart
