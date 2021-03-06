Feature: ChargeBack Complete flow

  Scenario Outline: Verify 'Payment Status' is Paid and 'Record Type' is Payment then 'Adjustment','Chargeback' and 'Refund' button is visible

    Given I login to Salesforce URL
    When I click on Contacts tag
    And I click on Recently Viewed dropdown Select List View
    And I select "All Contacts" from the list view
    And I type the contact "David Dash" in the Search box and press Enter to view the particular contact detail
    And I click on the searched contact name
    And I click the Opportunities button
    And I click on Opportunities name David Dash 123 Donation link
    And I click on "Related" Tab
    And I click on Payment
    And I click "Related" Tab under Payment
    And I click the required Payment under Payments (Related Payment Record) option
    And I click "Edit Chargeback Bank Action" button
    And I edit Chargeback Bank Action from Chargeback cancelled to Chargeback processed under Chagreback Information
    And I click on Save button
    Then I verify whether the correct error "Chargeback status cannot be modified from cancelled to processed." message displayed

    Examples:
      | Chargeback Bank Action | Chargeback Received On | Chargeback Reference Number |
      | Chargeback initiated | 2019-08-27 | CN100102 |