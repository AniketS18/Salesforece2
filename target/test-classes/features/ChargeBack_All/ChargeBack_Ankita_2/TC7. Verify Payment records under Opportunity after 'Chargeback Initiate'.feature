Feature: Verify Payment records under Opportunity chargeback initiated
  As a Salesforce user, login to Salesforce and Searching Payment Records with Chargeback Request

  Scenario Outline: Verify Payment records under Opportunity after 'Chargeback Initiate'
    Given I login to Salesforce URL
    When I click on Contacts tag
    And I click on Recently Viewed dropdown Select List View
    And I select "All Contacts" from the list view
    And I type the contact "Alannah Matheson" in the Search box and press Enter to view the particular contact detail
    And I click on the searched contact name
    And I click the Opportunities button
    And I click on Opportunities name Alannah Matheson RC-00001 Donation link
    And I click on "Related" Tab
    And I click on Payment
    And I click on Paid CheckBox with click on Edit Paid option
    And I select Payment Date from payment date 2019-08-27
    And I click on Save button
    And I click "Chargeback" radio button
    And I provide details Chargeback Bank Action,Chargeback Received On and Chargeback Reference Number
      | Chargeback Bank Action | Chargeback Received On | Chargeback Reference Number |
      | <Chargeback Bank Action> | <Chargeback Received On> | <Chargeback Reference Number> |
    And I select on Chargeback's "Save" button
    And I clicked Opportunity Name Alannah Matheson RC-00001 Donation under Details tab
    And I clicked Payments link
    And I click on Payment Number
    And I scroll down to page
    Then I verify Payment records successfully created under Chargeback Information which are Chargeback Bank Action,Chargeback Received On and Chargeback Reference Number
      | Chargeback Bank Action   | Chargeback Received On   | Chargeback Reference Number   |
      | <Chargeback Bank Action> | <Chargeback Received On> | <Chargeback Reference Number> |

    Examples:
      | Chargeback Bank Action | Chargeback Received On | Chargeback Reference Number |
      | Chargeback initiated   | 2019-08-30             | CN100102                    |

