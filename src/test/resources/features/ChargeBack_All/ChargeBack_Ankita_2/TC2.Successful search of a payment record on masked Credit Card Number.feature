Feature: Refunds & Adjustments - Searching Payment Records with Chargeback Request
  As a Salesforce user, login to Salesforce and Searching Payment Records with Chargeback Request


  Scenario Outline: Successful search of a payment record on masked Credit Card Number
    Given I login to Salesforce URL
    And I click on the searched salesforce textbox
    And I search for "424242" of an Chargeback record
    And I clicked Payment Number on the searched record
    And I scroll down to page
    Then I verify the Related Payment Record Chargeback
      | Related Record Type |
      | <Related Record Type> |

    Examples:
      |Related Record Type |
      | Chargeback  |