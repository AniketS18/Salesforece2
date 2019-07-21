
package SalesforceSystemTest.stepdefinitions;

import SalesforceSystemTest.navigation.NavigateTo;
import SalesforceSystemTest.navigation.SalesforceHomePage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;


//import static org.assertj.core.api.Assertions.assertThat;

public class SalesforceStepDefinitions {

    @Steps
    NavigateTo navigateTo;
    SalesforceHomePage homePage;
    String firstName, lastName, Salutation, Phone, tagName;
    String expectedString;
    String enteredSupporterLevel, expectedSupporterLevel, expectedLevelOfService, expectedSelectService, expectedchoosedSupporter, enteredSelectService;
    String opportunityName,primaryCampaignSource, closeDate, stage;

    public WebDriver initialize() {
        String saleforceNotifications = getConfigurationDetails("webdriver.base.disable_notifications");
        String driverName = getConfigurationDetails("webdriver.name.chrome");
        String driverPath = getConfigurationDetails("webdriver.chrome.driver");
        ChromeOptions option = new ChromeOptions();
//        option.addExtensions(new File("C:\\Users\\Aniket\\Downloads\\Block-image_v1.0.crx"));
        option.addArguments(saleforceNotifications);
        System.setProperty(driverName, driverPath);
        WebDriver driver = new ChromeDriver(option);
        return driver;
    }

    public String getConfigurationDetails(String nameURL) {
        EnvironmentVariables props = Injectors.getInjector().getInstance(EnvironmentVariables.class);
        String configDetails = EnvironmentSpecificConfiguration.from(props).getProperty(nameURL);
        return configDetails;
    }

    WebDriver driver = initialize();

    @Given("^I login to Salesforce URL$")
    public void i_login_to_Salesforce_URL() throws Throwable {
        navigateTo.LogintoSalesforce(driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @When("^I click on (.*) tag")
    public void i_click_on_contacts_page(String filedTagname) throws Throwable {
        navigateTo.clickContacts(driver,filedTagname);
        //   restapi.testRestApi();

    }

    @And("I click on the New Contact under Contact Page")
    public void i_click_on_new_contact() throws Throwable{
        navigateTo.clickNewContact(driver);

    }
    @When("I click on Contact dropdown")
    public void i_click_on_dropdown()throws Throwable{
        navigateTo.clickOnDropdown(driver);
    }


    @When("^I enter following details in (.*), (.*), (.*) and (.*) fields under New Contact Page:$")
    public void i_enter_details(String fieldSalutation, String fieldFirstName, String fieldLastName, String fieldPhone, DataTable dataTable) throws Throwable {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        firstName = data.get(0).get("First Name");
        lastName = data.get(0).get("Last Name");
        Salutation = data.get(0).get("Salutation");
        Phone = data.get(0).get("Phone");
        navigateTo.insertRecords(driver, fieldSalutation, fieldFirstName, fieldLastName, fieldPhone, firstName, lastName, Salutation, Phone);
    }

    @When("^I select (.*) under (.*)$")
    public void i_select(String supporterLevel, String supporterfieldName) throws Throwable {
        // navigateTo.selectFromDropDown(driver, supporterLevel, supporterfieldName);
        enteredSupporterLevel = supporterLevel;
    }

    @When("^I click on (.*) button")
    public void i_click_on_Save_button(String saveButton) {
        navigateTo.clickOnSave(driver, saveButton);
    }

    @And("^I click on Recently Viewed dropdown (.*)$")
    public void recently_viewed_dropdown(String filedDropdown) {
        navigateTo.recentlyViewedDropdown(driver,filedDropdown);
    }
    @And("^I click on the (.*) button$")
    public void i_click_on_URM_button(String URMButton) {
        navigateTo.clickOnURM(driver, URMButton);
    }

    @And("^I select \"(.*)\" from the list view$")
    public void select_all_contacts(String allContact) {
        navigateTo.allContacts(driver,allContact);
    }

    @And("^I type the contact \"(.*)\" in the Search box and press Enter to view the particular contact detail$")
    public void search_enter(String arg1) {
        navigateTo.contactNameInSearch(driver, arg1);
    }

//    @And("I click on (-?\\d) (-?\\d) ")
//    public void search_enter_name(String n) {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+n);
//        navigateTo.contactNameInSearch(driver);
//    }

    @And("I click on the searched contact name")
    public void searched_contact_name() {
        navigateTo.searchContactName(driver);
    }

//    @And("^I click on (.*) Tab$")
//    public void click_required_tab(String TagName) {
//        navigateTo.clickTabName(driver,TagName);


    @And("^I click on \"(.*)\" Tab$")
    public void click_required_tab(String fieldTagName) {
        navigateTo.clickTabName(driver, fieldTagName);
    }

    @And("I click \"(.*)\" button in the Engagement Plans section")
    public void Engagement_Plans_new(String newButton) {
        navigateTo.clickNewEngagementPlans(driver, newButton);
    }

    @And("I click on Engagement Plan Template textbox and select a plan under New Engagement Plan page")
    public void select_Engagement_Plan_Template() {
        navigateTo.clickTextboxOfEngagementPlan(driver);
    }

    @When("^I verify the an Engagement Plan Template and Contact is created$")
    public void i_enter_details(DataTable dataTable) throws Throwable {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String ept = data.get(0).get("Engagement Plan Template");
        String contactName = data.get(0).get("Contact Name");

        navigateTo.verifyDetails(driver, ept, contactName);
    }

    @And("I scroll down to page")
    public void scroll_Down() {
        navigateTo.scrollDown(driver);
    }

    @And("I scroll up to page")
    public void  scroll_up(){
        navigateTo.scrollUP(driver);
    }

    @And("^I click on (.*) new (.*)$")
    public void click_EPN(String EPN,String Number) {
        navigateTo.clickOnEpn(driver,EPN,Number);
    }

    @And("^I click \"(.*)\" button$")
    public void click_ERM(String editButton) {
        navigateTo.clickOnERM(driver, editButton);
    }
//    @And("^I enter \"(.*)\" and \"(.*)\" in Relationship Manager filed$")
//    public  void enter_Relationship_Manager(String fieldFirstName,String filedLastName,DataTable dataTable){
//        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
//        firstName = data.get(0).get("First Name");
//        lastName = data.get(0).get("Last Name");
//        navigateTo.enterRelationshipManager(driver,firstName,lastName);
//        }

    @And("^I enter \"(.*)\" in Relationship Manager filed$")
    public  void  click_Relationship_Manager(String fieldName){
//        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
//        firstName = data.get(0).get("First Name");
//        lastName = data.get(0).get("Last Name");
        navigateTo.clickOnRelationshipManager(driver,fieldName);
    }
    @Then("^I verify the (.*) name is populated with previous value$")
    public void  verify_Previous_Relationship_Manager(String filedPRManager,DataTable dataTable){
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String PreviousManager = data.get(0).get(filedPRManager);
        System.out.println("Previous Manager "+PreviousManager);
        navigateTo.verifyPRM(driver,PreviousManager);
    }
    @Then("^I verify the (.*) is now updated with new manager name$")
    public void verify_Updated_Relationship_Manager(String filedManager,DataTable dataTable) throws Throwable {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String NewManager = data.get(0).get("Manager Name");
        navigateTo.verifyURM(driver,NewManager,filedManager);

    }
    @And("^I click on the cross mark against the (.*) name and remove it$")
    public void cross_Mark_Relationship_Manager(String filedManager){
        navigateTo.crossMark(driver,filedManager);
    }
    @And("^I \"(.*)\" the PopUp$")
    public void  PopUp_Close(String filedClose){
        navigateTo.ClosePopUp(driver,filedClose);
    }
    @Then("I verify following details under Contacts page")
    public  void i_verify_details_under_contacts_page(DataTable dataTable) throws Throwable {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String filedName = data.get(0).get("Name");
        String filedPhone = data.get(0).get("Phone");
        navigateTo.verifyDetailsUnderContact(driver,filedName,filedPhone);
    }
    @Then("I verify whether correct error \"(.*)\" message displayed or not")
    public void i_verify_correct_error_message_displayed(String filedMessage) {
        navigateTo.verifyCorrectErrorMessage(driver,filedMessage);
    }
    @Then("I verify whether the correct error \"(.*)\" message displayed")
    public void i_verify_correct_error_message(String filedMessage){
        navigateTo.verifyErrorMessage(driver,filedMessage);
    }
    @And("I click on \"(.*)\" check box")
    public void i_click_check_box(String filedCheckBox){
        navigateTo.clickCheckBox(driver,filedCheckBox);
    }
//    @When("I click on Opportunity tab")
//    public void i_click_opportunities() {
//        navigateTo.clickOpportunity(driver);
//    }
    @When("I click on Opportunities dropdown")
    public void i_click_opportunities_dropdown() {
        navigateTo.clickOpportunitydropdown(driver);
    }
    @When("I click New Opportunity button")
    public void i_click_new_opportunity(){
        navigateTo.clickNewOpportunity(driver);
    }
    @And("I select Donation button")
    public void i_select_donation(){
        navigateTo.selectDonation(driver);
    }
    @And("I click the Next button")
    public void i_click_next(){
        navigateTo.clickNext(driver);
    }
    @And("^I enter following details in (.*), (.*), (.*) and (.*) fields under New Opportunity: Donation$")
    public void i_enter_opportunity_details(String fieldOpportunityName, String fieldPrimaryCampaignSource, String filedCloseDate, String filedStage, DataTable dataTable) throws Throwable {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        opportunityName = data.get(0).get("Opportunity Name");
        primaryCampaignSource = data.get(0).get("Last Name");
        closeDate = data.get(0).get("Salutation");
        stage = data.get(0).get("Phone");
        navigateTo.insertdetails(driver, fieldOpportunityName, fieldPrimaryCampaignSource, filedCloseDate, filedStage, opportunityName, primaryCampaignSource, closeDate, stage);
    }
}


