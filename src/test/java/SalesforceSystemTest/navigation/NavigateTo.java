
package SalesforceSystemTest.navigation;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class NavigateTo {

    @Step("Open the Salesforce home page")
    public void LogintoSalesforce(WebDriver driver) {
        String salesforceURL = getConfigurationDetails("webdriver.base.url");
        String userName = getConfigurationDetails("Salesforce.username");
        String passWord = getConfigurationDetails("Salesforce.password");
        driver.get(salesforceURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='Login']")).click();
    }

    public String getConfigurationDetails(String nameURL) {
        EnvironmentVariables props = Injectors.getInjector().getInstance(EnvironmentVariables.class);
        String configDetails = EnvironmentSpecificConfiguration.from(props).getProperty(nameURL);
        return configDetails;
    }


    public void waitfortheelement() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickContacts(WebDriver driver, String tagname) {
        waitfortheelement();
        // String e=driver.findElement(By.xpath("//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[@class='slds-truncate'][text()='"+ tagname +"']]]")).getText();


        driver.findElement(By.xpath(".//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[@class='slds-truncate'][text()='" + tagname + "']]]")).click();
//       // driver.findElement(By.xpath(".//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[@title='Once Off']]//span")).click();
//        driver.findElement(By.xpath("//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none']//a[@class='slds-context-bar__label-action dndItem']//span[text()='Once Off']")).click();
        // driver.findElement(By.xpath(".//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none']//a//span[@class='slds-truncate'][text()='"+ tagname +"']")).click();
        waitfortheelement();
    }

    public void clickNewContact(WebDriver driver) {
        waitfortheelement();
        WebElement element1 = driver.findElement(By.xpath("//div[contains(@class,'menuItemsWrapper')]//span//span[contains(text(),'New Contact')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element1);


    }

    public void clickOnDropdown(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-chevrondown slds-icon_container'])[2]")).click();
        waitfortheelement();
    }

    public void clickNewOpportunity(WebDriver driver) {
        waitfortheelement();
        WebElement element1 = driver.findElement(By.xpath("//div[contains(@class,'menuItemsWrapper')]//span//span[contains(text(),'New Opportunity')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element1);
        waitfortheelement();
//        Actions act1 = new Actions(driver);
//        act1.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
//        waitfortheelement();

    }

    public void clickOpportunitydropdown(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("(//lightning-icon[@class='slds-icon-utility-chevrondown slds-icon_container'])[5]")).click();
        waitfortheelement();
    }

    public void enterAllDetails(WebDriver driver, String fieldName, String fieldValue) {
        driver.findElement(By.xpath("//div[contains(@class,'uiInput')]//span[contains(text(),'" + fieldName + "')]/../..//input")).sendKeys(fieldValue);
    }


    public void scrollDown(WebDriver driver) {
        waitfortheelement();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        waitfortheelement();

    }

    public void scrollUP(WebDriver driver) {
        waitfortheelement();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_UP).build().perform();
        waitfortheelement();

    }

    public void insertRecords(WebDriver driver, String fieldSalutation, String fieldFirstName, String fieldLastName, String fieldPhone, String firstName, String lastName, String Salutation, String Phone) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'uiInputSelect')]//span[contains(text(),'" + fieldSalutation + "')]/../..//div[contains(@class,'salutation')]//a")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'select-options')]//a[contains(@title,'" + Salutation + "')]")).click();
        waitfortheelement();
        enterAllDetails(driver, fieldFirstName, firstName);
        enterAllDetails(driver, fieldLastName, lastName);
        enterAllDetails(driver, fieldPhone, Phone);


    }

    public void iProvideRM(WebDriver driver, String filedName, String ManagerName, String Page) {
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-grid slds-col slds-is-editing')]//div[@class='slds-form-element__control']//span[text()='" + filedName + "']/../..//input")).click();
        driver.findElement(By.xpath("//div[contains(@class,'slds-m-left--smalllabels slds')]//div[text()='" + ManagerName + "']")).click();
    }

    public void clickOnSave(WebDriver driver, String buttonName) {

        driver.findElement(By.xpath("//button[text()='" + buttonName + "']")).click();
//        driver.findElement(By.xpath("//button[@title='" + buttonName + "']//span[@class=' label bBody']")).click();

        try {
            String duplicateMessage = driver.findElement(By.xpath("//div[@class ='slds-col slds-align-middle']")).getText();

            if (duplicateMessage != null && duplicateMessage.contains("duplicate")) {
                driver.findElement(By.xpath("//button[@title='" + buttonName + "']//span[@class=' label bBody']")).click();
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Second Save Button Doesn't Exist");
        }
    }

    public void clickNext(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//span[@class=' label bBody'][text()='Next']")).click();
        waitfortheelement();
    }


    public void recentlyViewedDropdown(WebDriver driver, String dropdown) {
        waitfortheelement();
        driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--reset d')][contains(@title,'" + dropdown + "')]")).click();
        waitfortheelement();
//        Actions act1 = new Actions(driver);
//        waitfortheelement();
//        act1.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
//        waitfortheelement();
    }

    public void allContacts(WebDriver driver, String contact) {
        waitfortheelement();
        driver.findElement(By.xpath("//span[@class= ' virtualAutocompleteOptionText'][contains(text(),'" + contact + "')]")).click();
        waitfortheelement();
        Actions act1 = new Actions(driver);
        waitfortheelement();
        act1.sendKeys(Keys.ENTER).build().perform();
        waitfortheelement();
    }

    public void contactNameInSearch(WebDriver driver, String name) {
        waitfortheelement();
//        String contactName = getConfigurationDetails("Salesforce.contactName");
        String contactName = name;
        driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys(contactName);
        waitfortheelement();
        Actions act1 = new Actions(driver);
        waitfortheelement();
        act1.sendKeys(Keys.ENTER).build().perform();
        waitfortheelement();
    }

    public void searchContactName(WebDriver driver, String fieldName) {

        waitfortheelement();
        WebElement e = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]//tbody"));

        waitfortheelement();

        List<WebElement> rows = e.findElements(By.tagName("tr"));
        WebElement firstRecordFound = rows.get(0);

        firstRecordFound.findElement(By.tagName("th")).findElement(By.tagName("a")).click();
        waitfortheelement();
        waitfortheelement();
        waitfortheelement();
    }
//    public void enterTagName(WebDriver driver, String TagName, String TagValue) {
//        driver.findElement(By.xpath("//li[@class ='tabs__item uiTabItem']//a[@title='"+  TagName +" ']")).sendKeys(TagValue);

    public void clickTabName(WebDriver driver, String tagName) {
        waitfortheelement();
        //String filedTagName =TagName;
        WebElement elem = driver.findElement(By.xpath("(//div[@class='uiTabBar']//a[@class='tabHeader'][@title='" + tagName + "']//span[@class='title'][text()='" + tagName + "'])[1]"));
        elem.click();
        waitfortheelement();
        waitfortheelement();
    }

    public void clickTabUnderPayment(WebDriver driver, String tabName) {
        waitfortheelement();
        WebElement elem = driver.findElement(By.xpath("(//a[@class='tabHeader'][@title='" + tabName + "']//span[@class='title'][text()='" + tabName + "'])[3]"));
        elem.click();
    }

    public void clickNewEngagementPlans(WebDriver driver, String buttonName) {
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("(//li[@class='slds-button slds-button--neutral slds-truncate']//a[contains(@title,'" + buttonName + "')])[4]")).click();
        waitfortheelement();
    }

    public void clickNewEngagementPlansTemplet(WebDriver driver) {
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("(//li[@class='slds-button slds-button--neutral slds-truncate']//a[contains(@title,'New')])[10]")).click();
        waitfortheelement();
//    (//li[@class='slds-button slds-button--neutral slds-truncate']//a[contains(@title,'New')])[10]
    }

    public void clickTextboxOfEngagementPlan(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'createNew itemContainer')]")).click();
        waitfortheelement();
        waitfortheelement();

    }

    public void verifyDetails(WebDriver driver, String ept, String contactName) {
        waitfortheelement();
        waitfortheelement();
        String eptNum = driver.findElement(By.xpath("//div//span[text()='Engagement Plan Template']/../..//div//a")).getText();
        String cName = driver.findElement(By.xpath("//div//span[text()='Contact']/../..//div//a")).getText();


//        System.out.println("-----------------------------------------------------");
//        System.out.println(eptNum);
//        System.out.println(eptNum.getText());
//        System.out.println("-----------------------------------------------------");

//        System.out.println(cName);
//        System.out.println(cName.getText());
//        System.out.println("-----------------------------------------------------");

        Assert.assertEquals(eptNum, ept);
        Assert.assertEquals(contactName, cName);
    }

    public void clickOnEpn(WebDriver driver, String filedEPN) {
        waitfortheelement();
        waitfortheelement();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,50)");
//        driver.findElement(By.tagName("table"));
//        String el =driver.findElement(By.xpath("//th[@class='initialSortAsc '][text()='"+ filedEPN +"']/../../..//div[@class='outputLookupContainer forceOutputLookupWithPreview']//a")).getText();
//        WebElement e= driver.findElement(By.xpath("//th[@class='initialSortAsc '][text()='"+ filedEPN +"']/../../..//div[@class='outputLookupContainer forceOutputLookupWithPreview']//a"));
//        System.out.println("----------------------------------------------");
//        System.out.println(el);
//        System.out.println("-----------------------------------------------");
//        List<WebElement> rows = e.findElements(By.tagName("tr"));
//        WebElement firstRecordFound = rows.get(0);
//
//        firstRecordFound.findElement(By.tagName("th")).findElement(By.tagName("a")).click();
//        //driver.findElement(By.xpath("//th[@class='initialSortAsc '][text()='"+ filedEPN +"']/../../..//div[@class='outputLookupContainer forceOutputLookupWithPreview']//a[text()='"+ filedNumer +"']")).click();
//        String e =driver.findElement(By.xpath("(//table[contains(@class,'forceRecordLayout slds-table')])[1]")).getText();
//        System.out.println("----------------------------------------------");
//        System.out.println(e);
//        System.out.println("----------------------------------------------");
//        System.out.println(driver.findElement(By.xpath("(//table[contains(@class,'forceRecordLayout slds-table')])[1]")).findElements(By.tagName("tr")));
//
//        System.out.println("-----------------------------------------------");
        driver.findElement(By.xpath("(//tr//th[@class='initialSortAsc ']/../../..//div[@class='outputLookupContainer forceOutputLookupWithPreview']//a)[1]")).click();
        waitfortheelement();
    }

    public void clickOnEditButton(WebDriver driver, String ERMButton) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,350)");
        driver.findElement(By.xpath("//button[@title='" + ERMButton + "']")).click();
        waitfortheelement();
    }

    public void clickOnRelationshipManager(WebDriver driver, String Name) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");
        driver.findElement(By.xpath(".//span[@class='pillText'][text()='" + Name + "']")).click();
        waitfortheelement();
    }

    public void verifyURM(WebDriver driver, String ManagerName, String RnManager) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,-250)");
        String Name = driver.findElement(By.xpath("//label[@class='label inputLabel uiLabel-left form-element__label uiLabel']//span[text()='" + RnManager + "']/../..//span[@class='pillText']")).getText();
        waitfortheelement();
        System.out.println("-----------------------------------------------------");
        System.out.println(Name);
        System.out.println("-----------------------------------------------------");

        Assert.assertEquals(Name, ManagerName);
    }

    //    public void enterRelationshipManager(WebDriver driver,String FirstName,String LastName){
//        waitfortheelement();
//        WebElement Name = driver.findElement(By.xpath("//span[text()='"+ ManagerName +"']"));
//        waitfortheelement();
//
//    }
    public void crossMark(WebDriver driver, String RnManager) {
        waitfortheelement();
        driver.findElement(By.xpath("//span[text()='" + RnManager + "']/../..//a[@class='deleteAction']//span[@class='deleteIcon']")).click();
        waitfortheelement();
        waitfortheelement();
    }

    public void ClosePopUp(WebDriver driver, String close) {
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='container']//div//button[@title='" + close + "']")).click();
        waitfortheelement();
    }


    public WebElement getVisibility(WebDriver driver, By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public void clickElementWhenClickable(WebDriver driver, By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }


    public void verifyPRM(WebDriver driver, String previousManager) {
        waitfortheelement();
        waitfortheelement();
        String xPath = "//span[text()='Previous Relationship Manager']/../..//a[@class=' textUnderline outputLookupLink slds-truncate forceOutputLookup']";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,-250)");
        String testname = driver.findElement(By.xpath(xPath)).getText();

        Assert.assertEquals(previousManager, testname);

    }

    public void clickOnURM(WebDriver driver, String UndoRM) {
        waitfortheelement();
        waitfortheelement();
        String OldManager = driver.findElement(By.xpath("//span[text()='" + UndoRM + "']/../..//a[@class=' textUnderline outputLookupLink slds-truncate forceOutputLookup']")).getText();
        System.out.println("Old Manager=" + OldManager);
        waitfortheelement();
    }

    public void verifyDetailsUnderContact(WebDriver driver, String fullName, String filedPhone) {
        waitfortheelement();
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,-250)");
        String fullName1 = driver.findElement(By.xpath("//span[text()='Name']/../..//span[@class='uiOutputText']")).getText();
        String PhoneNo = driver.findElement(By.xpath("//span[text()='Phone']/../..//span[@class='uiOutputPhone']")).getText();
        System.out.println("-------------------Name-----------------------");
        System.out.println(fullName);
        System.out.println("--------------------Phone----------------------");
        System.out.println(PhoneNo);
        waitfortheelement();
        Assert.assertEquals(fullName1, fullName);
        Assert.assertEquals(PhoneNo, filedPhone);
    }

    public void verifyCorrectErrorMessage(WebDriver driver, String message) {
        waitfortheelement();
        waitfortheelement();
        String msg = driver.findElement(By.xpath("//li[text()='" + message + "']")).getText();
        waitfortheelement();

        Assert.assertEquals(msg, message);
    }

    public void verifyErrorMessage(WebDriver driver, String message) {
        waitfortheelement();
        waitfortheelement();
        String msg = driver.findElement(By.xpath("//li[@class='form-element__help']")).getText();
//        System.out.println("=--------------------------------------------");
//        System.out.println(msg);
//        System.out.println("----------------------------------------------");
        waitfortheelement();

        Assert.assertEquals(msg, message);
    }

    public void clickCheckBox(WebDriver driver, String checkbox) {
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//label//span[text()='" + checkbox + "']/../..//input[@type='checkbox']")).click();
        waitfortheelement();
    }

    public void selectDonation(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath(".//span[@class='slds-radio--faux']/../..//div[@class='changeRecordTypeOptionRightColumn']//span[contains(text(),'Donation')]")).click();
        waitfortheelement();
    }

    public void insertdetails(WebDriver driver, String fieldOpportunityName, String fieldPrimaryCampaignSource, String filedCloseDate, String filedStage, String opportunityName, String primaryCampaignSource, String closeDate, String stage) {
        waitfortheelement();
        enterAllDetails(driver, fieldOpportunityName, opportunityName);
        driver.findElement(By.xpath("//label[contains(@class,'label inputLabel uiLabel-left form-element__label uiLabel')]//span[text()='Primary Campaign Source']")).click();

        //driver.findElement(By.xpath("//span[text()='Primary Campaign Source']/../..//div[contains(@class,'primaryLabel slds')]")).sendKeys(fieldPrimaryCampaignSource);
        driver.findElement(By.xpath("//span[text()='Primary Campaign Source']/../..//div[contains(@class,'primaryLabel slds')][@title='" + primaryCampaignSource + "']")).click();
        waitfortheelement();
        WebElement element = driver.findElement(By.xpath("//label[contains(@class,'label inputLabel')]//span[text()='Close Date']/../..//a[contains(@class, 'datePicker-openIcon')]//span[@class='assistiveText']"));
        waitfortheelement();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        waitfortheelement();
        driver.findElement(By.xpath("//td[@class='uiDayInMonthCell'][@data-datevalue='" + closeDate + "']")).click();
        // driver.findElement(By.xpath("//td[@class='uiDayInMonthCell']//span[text()='"+ closeDate +"']")).click();
        waitfortheelement();
        String e = driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='Stage']")).getText();
        System.out.println(e);

        driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='Stage']/../..//a[@class='select']")).click();
        driver.findElement(By.xpath(".//li[@class='uiMenuItem uiRadioMenuItem']//a[@title='Posted']")).click();
    }

    public void provideEngagementPlanName(WebDriver driver) {
        waitfortheelement();
        waitfortheelement();
        waitfortheelement();
//        int size =driver.findElements(By.tagName("iframe")).size();
//        System.out.println("------------------------------");
//        System.out.println(size);
//        System.out.println("=======------------------------");
//        for(int i=0; i<=size; i++){
//            driver.switchTo().frame(i);
//            int total=driver.findElements(By.xpath("html/body/a/img")).size();
//            System.out.println(total);
//            driver.switchTo().defaultContent();}
        driver.switchTo().frame(0);
        waitfortheelement();
        driver.findElement(By.xpath("//label[@class='slds-form-element__label']//abbr/../..//input[@class='slds-input']")).sendKeys("High Value Prospect Journey");

    }

    public void MEPTSave(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//input[@class='btn slds-button slds-button_brand']")).click();
        driver.switchTo().defaultContent();
    }

    public void selectRelated(WebDriver driver) {
        driver.findElement(By.xpath("(//a[@class='tabHeader'][@title='Related']//span[@class ='title'][text()='Related'])[2]")).click();
    }

    public void selectAccount(WebDriver driver, String filedAccount) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@title='Search Accounts']")).click();
        driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@title='Search Accounts']/..//div//div[@title='" + filedAccount + "']")).click();
    }

    public void selectEngagementPlan(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='primaryLabel slds-truncate slds-lookup__result-text']")).click();
        waitfortheelement();
    }

    public void verfyopportunitydetails(WebDriver driver, String filedOpportunityName, String filedPrimaryCampaignSource, String filedCloseDate, String filedStage) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,250)");
        String Opprtunity = driver.findElement(By.xpath("//div//span//span[@class='uiOutputText']")).getText();
        waitfortheelement();
        System.out.println("----------------------------------------");
        System.out.println(Opprtunity);
        System.out.println("-----------------------------------------");
        String PrimaryCampaignSource = driver.findElement(By.xpath("//div[contains(@class,'test-id__field-label')]//span[text()='Primary Campaign Source']/../..//div//a[contains(@class,'textUnderline outputLookup')]")).getText();
        System.out.println("----------------------------------------");
        System.out.println(PrimaryCampaignSource);
        System.out.println("----------------------------------------");
        String Date = driver.findElement(By.xpath("//div//span//span[@class='uiOutputDate']")).getText();
        System.out.println("----------------------------------------");
        System.out.println(filedCloseDate);
        System.out.println("----------------------------------------");
        String Stage = driver.findElement(By.xpath("//div//span[@class='test-id__field-label'][text()='Stage']/../..//span[contains(@class,'test-id__field-value slds-form-element__static slds-grow')]//span")).getText();

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String strDate= formatter.format(filedCloseDate);
//        System.out.println("-------------------------------");
//        System.out.println(strDate);
//        System.out.println("--------------------------------");


        Assert.assertEquals(Opprtunity, filedOpportunityName);
        Assert.assertEquals(PrimaryCampaignSource, filedPrimaryCampaignSource);
        Assert.assertEquals(filedCloseDate, Date);
        Assert.assertEquals(Stage, filedStage);
    }

    public void verifyAnonymousSupporter(WebDriver driver, String filedPhone, String fullName) {
        waitfortheelement();
        waitfortheelement();
        String fullName1 = driver.findElement(By.xpath("//span[text()='Name']/../..//span[@class='uiOutputText']")).getText();
        String PhoneNo = driver.findElement(By.xpath("//span[text()='Phone']/../..//span[@class='uiOutputPhone']")).getText();
//        List list = driver.findElements(By.name("radioButton"));
//
//        // Create a boolean variable to store true/false.
//        Boolean is_selected = list.get(0).isSelected();
        Assert.assertEquals(fullName1, fullName);
        Assert.assertEquals(PhoneNo, filedPhone);

    }

    public void clickNewTask(WebDriver driver, String task) {
        waitfortheelement();
        driver.findElement(By.xpath("//a//div[@title='" + task + "']")).click();
        waitfortheelement();
    }

    public void newTaskDetails(WebDriver driver, String Assignedto, String Subject, String Status, String Priorty) {
        waitfortheelement();
//        driver.findElement(By.xpath("//lightning-icon[contains(@class,'lightning-primitive-icon')]")).click();
        driver.findElement(By.xpath("//li[@class='pillContainerListItem']//a//span[@class='pillText']")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//label[text()='Subject']/..//div//input[@class='slds-input slds-combobox__input']")).click();
        //WebElement element =
        driver.findElement(By.xpath("//label[text()='Subject']/..//div//input[@class='slds-input slds-combobox__input']/../..//span[@title='" + Subject + "']")).click();
        waitfortheelement();
        WebElement element = driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='Status']/../..//div[@class='uiMenu']//div[@class='uiPopupTrigger']"));
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        element.click();
        waitfortheelement();
        driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='Status']/../..//div[@class='uiMenu']//a")).click();
//        driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='Status']/../..//div[@class='uiMenu']//div[@class='uiPopupTrigger']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='select-options']//ul[@class='scrollable']//li//a[text()='" + Status + "']")).click();
        ////div//ul[@class='scrollable']//li//a[@title='In Progress']
        driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='Priority']/../..//div[@class='uiMenu']//div[@class='uiPopupTrigger']")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='select-options']//ul[@class='scrollable']//li//a[text()='" + Priorty + "']")).click();
    }

    public void clickActivityHistory(WebDriver driver) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        // driver.findElement(By.xpath("//table[contains(@class,'forceRecordLayout slds-table')]//tr//th//div//a[@class='textUnderline outputLookupLink slds-truncate forceOutputLookup']")).click();
        driver.findElement(By.xpath("(//tr//th//div[@class='outputLookupContainer forceOutputLookupWithPreview']//a)[1]")).click();

    }

    public void verifywarningMessage(WebDriver driver, String filedMessage) {
        String msg = driver.findElement(By.xpath("//div//div[@class ='slds-col slds-align-middle']")).getText();

        System.out.println("----------------------------------");
        System.out.println(msg);
        System.out.println("----------------------------------");
        Assert.assertEquals(msg, filedMessage);
    }

    public void enterphone(WebDriver driver, String filedPhone) {
        waitfortheelement();
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        driver.findElement(By.xpath("//label//span[text()='Phone']/../..//input[contains(@class,'input')]")).clear();
        waitfortheelement();
        driver.findElement(By.xpath("//label//span[text()='Phone']/../..//input[contains(@class,'input')]")).sendKeys(filedPhone);

    }

    public void verifyDetailsContactPage(WebDriver driver, String name, String phoneNo) {
        waitfortheelement();
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,-250)");
        String fullName1 = driver.findElement(By.xpath("//span[text()='Name']/../..//span[@class='uiOutputText']")).getText();
        String PhoneNo = driver.findElement(By.xpath("//span[text()='Phone']/../..//span[@class='uiOutputPhone']")).getText();

        waitfortheelement();
        Assert.assertEquals(fullName1, name);
        Assert.assertEquals(PhoneNo, phoneNo);
    }

    public void verifyBequestorStatusDropdown(WebDriver driver, String Status) {
        waitfortheelement();
        driver.findElement(By.xpath("//span[text()='Bequestor Status']/../..//div[@class='uiMenu']//div[@class='uiPopupTrigger']")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='select-options']//ul[@class='scrollable']//li//a[@title='" + Status + "']")).click();
        waitfortheelement();
    }

    public void verifyBequestorStatus(WebDriver driver, String filedStatus) {
        waitfortheelement();
        waitfortheelement();
//        String status = driver.findElement(By.xpath("//div[@class='select-options']//ul[@class='scrollable']//li//a[@title='"+ filedStatus +"']")).getText();
        String status = driver.findElement(By.xpath("//div//span//span[text()='" + filedStatus + "']")).getText();
        waitfortheelement();
        System.out.println("*********************");
        System.out.println(status);
        System.out.println("**********************");

        Assert.assertEquals(filedStatus, status);
    }

    public void selectBequestManager(WebDriver driver, String filedManager) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[contains(@class,' default input uiInpu')][@title='Search Accounts']")).click();
        waitfortheelement();
        String e = driver.findElement(By.xpath("//div[contains(@class,'undefined lookup__menu uiAbstractList')]//div//div[contains(@class,'primaryLabel slds')][text()='" + filedManager + "']")).getText();

        System.out.println("----------------------------");
        System.out.println(e);
        System.out.println("----------------------------");
        driver.findElement(By.xpath("//div[contains(@class,'undefined lookup__menu uiAbstractList')]//div//div[contains(@class,'primaryLabel slds')][text()='Suma Srinivasaiah']")).click();
    }

//    public void clickOpportunitybutton(WebDriver driver, String fieldValue) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//div//a[@class='rlql-relatedListLink']//span[@title='" + fieldValue + "']")).click();
//        waitfortheelement();
//    }

    public void clickopportunityname(WebDriver driver, String opportunityName) {
        waitfortheelement();
        String e = driver.findElement(By.xpath("//th//span[@class='slds-grid slds-grid--align-spread']//a[@title='" + opportunityName + "']")).getText();
        driver.findElement(By.xpath("//th//span[@class='slds-grid slds-grid--align-spread']//a[@title='" + opportunityName + "']")).click();
        waitfortheelement();

        System.out.println("----------------------------");
        System.out.println(e);
//        System.out.println("----------------------------");
    }

    public void clickOnPayment(WebDriver driver) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        driver.findElement(By.xpath("//th//div//a[contains(@class,'textUnderline outputLookupLink')]")).click();
        waitfortheelement();
//        driver.findElement(By.xpath("//div[@class='showPreview forceRelatedListQuickLinksContainer']//span[@class='rlql-label'][text()='"+Payclick+"']")).click();

    }

//    public void verifyPSandRT(WebDriver driver, String filedPS, String filedRT) {
//        waitfortheelement();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
////        js.executeScript("scrollBy(0,100)");
////        WebElement checkbox = driver.findElement(By.xpath("//div[contains(@class,'slds-col slds-grid slds-has')]//span[text()='Paid']/../..//span[@class='uiImage uiOutputCheckbox']"));
////        System.out.println("The checkbox is selection state is - " + checkbox.isSelected());
//
//        String PaymentStatus = driver.findElement(By.xpath("//div[contains(@class,'slds-col slds-grid slds-has')]//span[text()='" + filedPS + "']")).getText();
//        String RecordType = driver.findElement(By.xpath("//span[text()='"+ filedRT +"']/../..//div[@class='recordTypeName slds-grow slds-truncate']//span")).getText();
//        waitfortheelement();
//        System.out.println("*************************");
//        System.out.println(PaymentStatus);
//        System.out.println("**************************");
//        System.out.println(RecordType);
//        System.out.println("*********");
//
////        Assert.assertEquals(filedPS, PaymentStatus);
//        Assert.assertEquals(filedRT, RecordType);
//    }

//    public void searchPaymentId(WebDriver driver, String filedPUID) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//div[@class='uiInput uiAutocomplete uiInput--default']//input")).sendKeys(filedPUID);
//        waitfortheelement();
//        Actions act1 = new Actions(driver);
//        waitfortheelement();
//        act1.sendKeys(Keys.ENTER).build().perform();
//    }

    public void searchSalesforceTextbox(WebDriver driver) {
        driver.findElement(By.xpath("//div[@class='uiInput uiAutocomplete uiInput--default']//input")).click();
        waitfortheelement();
    }

    public void clickPaymentNumber(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//th//span//a[@class='slds-truncate outputLookupLink slds-truncate forceOutputLookup']")).click();
        waitfortheelement();
    }

    public void verifyPaymentRecord(WebDriver driver, String filedPaymentID, String filedrecordType) {
        waitfortheelement();
        String PaymentId = driver.findElement(By.xpath("//span[text()='" + filedPaymentID + "']/../..//span[@class='uiOutputText']")).getText();
        String RecordType = driver.findElement(By.xpath("//span[text()='" + filedrecordType + "']/../..//div[@class='recordTypeName slds-grow slds-truncate']//span")).getText();
        waitfortheelement();
        System.out.println("*************************");
        System.out.println(PaymentId);
        System.out.println("**************************");
        System.out.println(RecordType);
        System.out.println("*********");

        Assert.assertEquals(filedPaymentID, PaymentId);
        Assert.assertEquals(filedrecordType, RecordType);
    }

//    public void provideDetails(WebDriver driver, String fieldOpportunityName, String fieldPrimaryCampaignSource, String filedCloseDate, String filedStage, String filedPrimaryContact, String filedAmount, String opportunityName, String primaryCampaignSource, String closeDate, String stage, String primaryContact, String amount) {
//        waitfortheelement();
//        enterAllDetails(driver, fieldOpportunityName, opportunityName);
//        driver.findElement(By.xpath("//label[contains(@class,'label inputLabel uiLabel-left form-element__label uiLabel')]//span[text()='Primary Campaign Source']")).click();
//
//        driver.findElement(By.xpath("//span[text()='Primary Campaign Source']/../..//div[contains(@class,'primaryLabel slds')][@title='" + primaryCampaignSource + "']")).click();
//        waitfortheelement();
//        WebElement element = driver.findElement(By.xpath("//label[contains(@class,'label inputLabel')]//span[text()='" + filedCloseDate + "']/../..//a[contains(@class, 'datePicker-openIcon')]//span[@class='assistiveText']"));
//        waitfortheelement();
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element).click().build().perform();
//        waitfortheelement();
//        driver.findElement(By.xpath("//td[@class='uiDayInMonthCell'][@data-datevalue='" + closeDate + "']")).click();
//        waitfortheelement();
//        String e = driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='" + filedStage + "']")).getText();
//        System.out.println(e);
//
//        driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='" + filedStage + "']/../..//a[@class='select']")).click();
//        driver.findElement(By.xpath(".//li[@class='uiMenuItem uiRadioMenuItem']//a[@title='" + stage + "']")).click();
//        driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@title='Search Contacts']")).click();
//        driver.findElement(By.xpath("//div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']//div[@title='" + primaryContact + "']")).click();
//        driver.findElement(By.xpath("//div//input[@class='input uiInput uiInput--default uiInput--input']")).sendKeys(amount);
//    }

    public void selectCheckbox(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//label//span[text()='Do Not Automatically Create Payment']/../..//input")).click();
    }

    public void provideContactName(WebDriver driver, String ContactName, String filedName) {
        waitfortheelement();
        driver.findElement(By.xpath("//label[text()='" + filedName + "']/..//input[contains(@class,'slds-lookup__search-input')]")).click();
        driver.findElement(By.xpath("//label[text()='" + filedName + "']/..//input[contains(@class,'slds-lookup__search-input')]")).sendKeys(ContactName);
        waitfortheelement();
        driver.findElement(By.xpath("//li[@class='slds-listbox__item']//span[contains(@class,'slds-listbox__option-text')][contains(text(),'" + ContactName + "')]")).click();
//        driver.findElement(By.xpath("//span[@class='slds-media__body']//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'][text()='"+ filedName +"']")).click();
//        waitfortheelement();
    }

//    public void provideDetailsOnce(WebDriver driver, String fieldCampaignAppealName, String fieldBatchId, String filedChannel, String filedReceipting, String filedDonationAmount, String filedPayment, String campaignAppealName, String batchId, String channel, String receipting, String donationAmount, String payment) {
//
//        driver.findElement(By.xpath("//label[text()='Campaign/Appeal Name']/..//input[contains(@class,'slds-lookup__search-input slds')]")).click();
//        driver.findElement(By.xpath("//li//span[@class='slds-media__body']//span[text()='" + campaignAppealName + "']")).click();
//
//        driver.findElement(By.xpath("//label[text()='Batch Id']/..//input[contains(@class,'slds-lookup__search-input slds')]")).click();
//        driver.findElement(By.xpath("//span[@class='slds-media__body']//span[text()='" + batchId + "']")).click();
//
//        driver.findElement(By.xpath("//label//span[text()='Channel']/../..//div[@class='slds-select_container']")).click();
//        WebElement testDropDown = driver.findElement(By.xpath("//label//span[text()='Channel']/../..//select[@class='slds-select']"));
//        Select dropdown = new Select(testDropDown);
//        dropdown.selectByVisibleText(channel);
//
//        driver.findElement(By.xpath("//label//span[text()='Receipting']/../..//div[@class='slds-select_container']")).click();
//        WebElement testDropDown2 = driver.findElement(By.xpath("//label//span[text()='Receipting']/../..//select[@class='slds-select']"));
//        Select dropdown2 = new Select(testDropDown2);
//        dropdown2.selectByVisibleText(receipting);
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,150)");
//        driver.findElement(By.xpath("//label[text()='Donation Amount']/..//input[@name='expenseclient']")).click();
//        driver.findElement(By.xpath("//label[text()='Donation Amount']/..//input[@name='expenseclient']")).sendKeys(donationAmount);
//
//        driver.findElement(By.xpath("//label//span[text()='Payment Method']/../..//div[@class='slds-select_container']")).click();
//        WebElement testDropDown3 = driver.findElement(By.xpath("//label//span[text()='Payment Method']/../..//select[@class='slds-select']"));
//        Select dropdown3 = new Select(testDropDown3);
//        dropdown3.selectByVisibleText(payment);
//        waitfortheelement();
//    }
//
//    public void clickCreatePayment(WebDriver driver) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand slds-m-top--medium']")).click();
//    }

    public void selectRadioButton(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//label[@class='slds-radio__label']//span[contains(text(),'Stark opp - 50.00')]")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand'][@title='Donation Selected']")).click();
    }

    public void clickDonationLink(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-modal__content slds-p-around_medium']//a")).click();

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String curWindow : allWindows) {
            driver.switchTo().window(curWindow);
        }
    }

    public void clicksplitPayment(WebDriver driver, String filedValue) {
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//button[@class='slds-button'][text()='" + filedValue + "']")).click();

    }

    public void addAction(WebDriver driver) {
        driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-add slds-icon_container']//lightning-primitive-icon")).click();
    }

    public void clickOnCheckbox(WebDriver driver, String filedCheckbox, String filedEditPaid) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,200)");
        driver.findElement(By.xpath("//div[contains(@class,'slds-col slds-grid slds-has-flexi')]//span[text()='" + filedCheckbox + "']/../..//button[contains(@class,'slds-button test-id')][@title='" + filedEditPaid + "']")).click();
        driver.findElement(By.xpath("//div[contains(@class,'slds-col slds-grid slds-has-flexi')]/..//label//span[text()='" + filedCheckbox + "']/../..//input[@type='checkbox']")).click();
    }

    public void selectPaymentDate(WebDriver driver, String filedPaymentDate, String Date) {
        driver.findElement(By.xpath("//label[contains(@class,'label inputLabel')]//span[text()='" + filedPaymentDate + "']/../..//a[contains(@class, 'datePicker-openIcon')]")).click();
        waitfortheelement();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String strDate =dateFormat.format(date);

        driver.findElement(By.xpath("//td[@class='uiDayInMonthCell'][@data-datevalue='" + Date + "']")).click();

    }

    public void clickRadioButton(WebDriver driver, String filedName) {
        waitfortheelement();
        driver.findElement(By.xpath("//div//span[@class='slds-radio']/..//label[@class='slds-radio__label']//span[text()='" + filedName + "']")).click();
    }

    public void chargebackValues(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String filedChargebackReferenceNumber, String filedChargebackResponseBy, String filedChargebackActualResponseDate, String filedChargebackSSTResponse, String filedChargebackBankFinalNotificationDate, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber, String chargebackResponseBy, String chargebackActualResponseDate, String chargebackSSTResponse, String chargebackBankFinalNotificationDate) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();

        driver.findElement(By.xpath("//li[@class='slds-listbox__item']//span[@class='slds-media__body']//span[text()='" + fieldChargebackReceivedOn + "']")).click();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String strDate =dateFormat.format(date);

        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackReceivedOn + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackReferenceNumber + "']/..//input")).sendKeys(chargebackReferenceNumber);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,200)");
        waitfortheelement();
        // driver.findElement(By.xpath("//label[text()='"+ fieldChargebackBankAction +"']/..//span[@title='"+ chargebackBankAction +"']")).click();


//
//        driver.findElement(By.xpath("//label[text()='"+ fieldChargebackBankAction +"']/..//span[@title='"+ chargebackBankAction +"']")).click();
//        System.out.println("----------------------------------------------------------------------------------");
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackResponseBy + "']/..//input")).click();
//        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//        Date date2 = new Date();
//        String strDate2 =dateFormat.format(date);

        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackResponseBy + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackActualResponseDate + "']/..//input")).click();
//        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//        Date date2 = new Date();
//        String strDate2 =dateFormat.format(date);

        driver.findElement(By.xpath("//td[@data-value='" + chargebackActualResponseDate + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackSSTResponse + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackSSTResponse + "']/..//span[@title='" + chargebackSSTResponse + "']")).click();

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,300)");
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackBankFinalNotificationDate + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value='" + chargebackBankFinalNotificationDate + "']")).click();

    }

    public void verifySuccessMessage(WebDriver driver, String message) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,100)");
        String msg = driver.findElement(By.xpath("//div[contains(@class,'uiMessage')]//div[@class='bBody']")).getText();
        //  "[text()='Chargeback has been initiated and is in progress.']")).getText();
        waitfortheelement();
        message = message.replaceAll("<new_line>", "\\n");

        System.out.println("-----------Coming from salesforce-----------");
        System.out.println(msg);
        System.out.println("-----------------------");
        System.out.println("-----------Coming from sscript-----------");
        System.out.println(message);
        System.out.println("-----------------------");

//        boolean contains = msg.contains(message);
//        System.out.println(contains);
        Assert.assertEquals(message, msg);
        waitfortheelement();
    }

    public void verifyRecord(WebDriver driver) {
        String record = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='Related Payment Record']/../..//a")).getText();

        System.out.println(record);
    }

    public void verifyTaskDetails(WebDriver driver, String assignedto, String subject, String status, String priority) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        String Assigned_To = driver.findElement(By.xpath("//div//span[text()='Assigned To']/../..//div[@class='ownerName']//a")).getText();
        String Sub = driver.findElement(By.xpath("//div//span[text()='Subject']/../..//span[@class='uiOutputText']")).getText();
        String Stat = driver.findElement(By.xpath("//div//span[text()='Status']/../..//span[text()='" + status + "']")).getText();
        String Prio = driver.findElement(By.xpath("//div//span[text()='Priority']/../..//span[text()='" + priority + "']")).getText();

        Assert.assertEquals(assignedto, Assigned_To);
        Assert.assertEquals(subject, Sub);
        Assert.assertEquals(status, Stat);
        Assert.assertEquals(priority, Prio);
    }

    public void ProvideChargebackValues(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String filedChargebackReferenceNumber, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackReceivedOn + "']/..//input")).click();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String strDate =dateFormat.format(date);

        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackReceivedOn + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackReferenceNumber + "']/..//input")).sendKeys(chargebackReferenceNumber);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,200)");
    }

    public void verifyChargebackSSTResponse(WebDriver driver, String filedChargebackResponseBy) {
        // driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackResponseBy + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackResponseBy + "']/..//input")).click();

    }

    public void chargebackSave(WebDriver driver, String filedName, String saveButton) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");
        driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand'][text()='" + saveButton + "']")).click();
//        driver.findElement(By.xpath("//button[text()='Save'][@class='slds-button slds-button_brand']")).click();

    }

    public void clickRequiredPaymentUnderPayment(WebDriver driver, String filedPayment) {
        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,200)");
        //  driver.findElement(By.xpath("//div[@class='slds-card__header slds-grid']//span[@title='"+filedPayment+"']/../../../../../..//table[contains(@class,'forceRecordLayout slds')]")).click();
        WebElement e = driver.findElement(By.xpath("//div[@class='slds-card__header slds-grid']//span[@title='" + filedPayment + "']/../../../../../..//table[contains(@class,'forceRecordLayout slds')]//tbody"));

        waitfortheelement();

        List<WebElement> rows = e.findElements(By.tagName("tr"));
        WebElement firstRecordFound = rows.get(0);

        firstRecordFound.findElement(By.tagName("th")).findElement(By.tagName("a")).click();
        waitfortheelement();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,200)");
    }

    public void editChargebackInformation(WebDriver driver, String filedAction, String filedStatus1, String filedStatus2) {
        waitfortheelement();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+ filedAction +"']")).click();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+ filedAction +"']/..//span[@title='"+ filedStatus2 +"']")).click();
        driver.findElement(By.xpath(".//div[@class='slds-form-element__control']//span[text()='" + filedAction + "']/../..//div[@class='uiMenu']")).click();
        String s = driver.findElement(By.xpath("//div[contains(@class,'uiPopupTarget')]//div//li//a[text()='" + filedStatus2 + "']")).getText();
        System.out.println("-------------------");
        System.out.println(filedStatus2);
        System.out.println("--------------------");
        driver.findElement(By.xpath("//div[contains(@class,'uiPopupTarget')]//div//li//a[text()='" + filedStatus2 + "']")).click();
    }

    public void verifyRadioButton(WebDriver driver) {
        waitfortheelement();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,-200)");
    }

    public void verifyChargebackNotAllowNew(WebDriver driver) {
        waitfortheelement();
    }

    public void verifyChargebacCanCreate(WebDriver driver) {
        waitfortheelement();
    }

    public void refundDetails(WebDriver driver, String filedCaseOrigin, String filedRefundType, String Subject, String Status, String filedRefundAmount, String filedRefundInstrument, String filedBankAccountNo, String fieldBankBSB, String caseOrigin, String refundType, String subject, String status, String refundAmount, String refundInstrument, String bankAccountNo, String bankBSB) {
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedCaseOrigin + "']")).click();
        // driver.findElement(By.xpath("//form[@class='slds-form']//label[text()='"+ filedCaseOrigin +"']/..//div[contains(@class,'slds-combobox slds-dropdown')]//lightning-icon[contains(@class,'slds-icon-utility-down slds-input')]")).click();
        driver.findElement(By.xpath("//lightning-base-combobox-item[contains(@class,'slds-media slds-listbox')]//span[@title='" + caseOrigin + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedRefundType + "']/..//div[@class='slds-form-element__control']")).click();
        driver.findElement(By.xpath("//lightning-base-combobox-item[contains(@class,'slds-media slds-listbox')]//span[@title='" + refundType + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + Subject + "']"));

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + Status + "']"));

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedRefundAmount + "']")).click();
        String st = driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedRefundAmount + "']")).getText();

        if (st.equals("Partial")) {
            driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedRefundAmount + "']/..//div[@class='slds-form-element__control slds-grow']//input")).sendKeys(refundAmount);
        }
        // driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+ filedRefundAmount +"']/..//div[@class='slds-form-element__control slds-grow']//input")).sendKeys(refundAmount);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,200)");

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedRefundInstrument + "']/..//div[@class='slds-form-element__control']")).click();
        driver.findElement(By.xpath("//lightning-base-combobox-item[contains(@class,'slds-media slds-listbox')]//span[@title='" + refundInstrument + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedBankAccountNo + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedBankAccountNo + "']/..//div[@class='slds-form-element__control slds-grow']//input")).sendKeys(bankAccountNo);

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankBSB + "']")).click();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+ fieldBankBSB +"']")).sendKeys(bankBSB);
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankBSB + "']/..//div[@class='slds-form-element__control slds-grow']//input")).sendKeys(bankBSB);
    }

    public void clickrequiredTab(WebDriver driver, String fieldName) {
        driver.findElement(By.xpath("//div[@class='slds-card__header slds-grid']//span[@title='" + fieldName + "']/../../../../../..//div[contains(@class,'outputLookupContainer forceOutputLookupWithPreview')]//a")).click();
    }

    public void selectrequiest(WebDriver driver, String fieldbutton) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-col slds-no-flex slds-grid')]//li//a//div[@title='" + fieldbutton + "']")).click();

    }

    public void clickOnCase(WebDriver driver, String fieldbutton) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'forceModalActionContainer')]//button[contains(@class,'slds-button slds-button')]//span[text()='" + fieldbutton + "']")).click();

    }

    public void verifyCaseApproval(WebDriver driver, String tagName, String status) {
        waitfortheelement();
        String st = driver.findElement(By.xpath("//div[contains(@class,'slds-form-element slds')]//span[text()='" + tagName + "']/../..//span[text()='" + status + "']")).getText();

        Assert.assertEquals(status, st);
    }

    public void clickOnTab(WebDriver driver, String tabName) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-col slds-no-flex slds')]//li//a[@title='" + tabName + "']")).click();
    }

    public void clickEditInPayment(WebDriver driver, String fieldEdit, String filedPayment) {
        waitfortheelement();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,100)");
        driver.findElement(By.xpath("//div[@class='flexipagePage oneRecordHomeFlexipage']//span[text()='" + filedPayment + "']/../..//button[@title='" + fieldEdit + "']")).click();

    }

    public void selectPayment(WebDriver driver, String fieldValue, String filedPayment) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='flexipagePage oneRecordHomeFlexipage']//span[text()='" + filedPayment + "']/../..//div[@class='uiMenu']//a")).click();

    }

    public void clickPayments(WebDriver driver, String Payclick) {
        waitfortheelement();
        // driver.findElement(By.xpath("//div//a//span[@class='rlql-label'][text()='" + Payclick + "']")).click();
        driver.findElement(By.xpath("//div[@class='showPreview forceRelatedListQuickLinksContainer']//span[@class='rlql-label'][text()='" + Payclick + "']")).click();
        waitfortheelement();
    }

    public String verifyPayRecord(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String fieldChargebackReferenceNumber, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber) throws ParseException, ParseException {

        waitfortheelement();
        waitfortheelement();

        String act = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='" + fieldChargebackBankAction + "']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
        String date = driver.findElement(By.xpath("//span[text()='" + fieldChargebackReceivedOn + "']/../..//span[@class='uiOutputDate']")).getText();
        String num = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='" + fieldChargebackReferenceNumber + "']/../..//span[@class='uiOutputText']")).getText();

//        String act = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='"+chargebackBankAction+"']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
        //      String date = driver.findElement(By.xpath("//span[text()='"+chargebackReceivedOn+"']/../..//span[@class='uiOutputDate']")).getText();
        //     String num = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='"+chargebackReferenceNumber+"']/../..//span[@class='uiOutputText']")).getText();
////       String str = "Chargeback initiated";
////        String str1= "2019-08-27";
////        String str2= "CN100102";
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-mm-dd");
        Date data = sdfd.parse(chargebackReceivedOn);
        // String strDate= sdf.format(data);
        String formattedTime = sdfd.format(data);

////        Assert.assertEquals(chargebackBankAction, act);
////        Assert.assertEquals(chargebackReceivedOn, formattedTime);
//        Assert.assertEquals(chargebackReferenceNumber, num);

        return act;
    }

    //    public void chargebackValues(WebDriver driver, String fieldChargebackBankAction,String fieldChargebackResponseBy, String fieldChargebackActualResponseDate, String fieldChargebackSSTResponse, String fieldChargebackBankFinalNotificationDate, String chargebackBankAction, String chargebackChargebackResponseBy,String chargebackChargebackActualResponseDate, String chargebackChargebackSSTResponse, String chargebackChargebackBankFinalNotificationDate) {
//        waitfortheelement();
//
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();
//
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+fieldChargebackResponseBy+"']/..//input")).click();
////        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        Date date = new Date();
////        String strDate = dateFormat.format(date);
//        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackResponseBy + "']")).click();
//        waitfortheelement();
//
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+fieldChargebackActualResponseDate+"']/..//input")).click();
//        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackActualResponseDate + "']")).click();
//
//        waitfortheelement();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackSSTResponse + "']")).click();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']")).click();
//        waitfortheelement();
//
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='"+fieldChargebackBankFinalNotificationDate+"']/..//input")).click();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();
//
//        waitfortheelement();
//    }
    public String verifyPaymentsRecord(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String fieldChargebackReferenceNumber, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber) throws ParseException {

        waitfortheelement();
        //String act = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='Chargeback Bank Action']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
//        String date = driver.findElement(By.xpath("//span[text()='Chargeback Received On']/../..//span[@class='uiOutputDate']")).getText();
//        String num = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='Chargeback Reference Number']/../..//span[@class='uiOutputText']")).getText();

        String act = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + fieldChargebackBankAction + "']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
        String date = driver.findElement(By.xpath("//div[@class='column left-col']//span[text()='" + fieldChargebackReceivedOn + "']/../..//span[@class='uiOutputDate']")).getText();
        String num = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + fieldChargebackReferenceNumber + "']/../..//span[@class='uiOutputText']")).getText();
//       String str = "Chargeback initiated";
        //  String str1= "2019-08-27";
//        String str2= "CN100102";


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date data = sdf.parse(chargebackReceivedOn);
        // String strDate= sdf.format(data);
        String formattedTime = sdf.format(data);

//        System.out.println("-------------------------------");
        //         System.out.println(act);
//        System.out.println("--------------------------------");
//        System.out.println(formattedTime);
//        System.out.println("--------------------------------");
//        System.out.println(num);
//        System.out.println("--------------------------------");

//        Assert.assertEquals(chargebackBankAction, act);
//        Assert.assertEquals(chargebackReceivedOn, formattedTime);
//        Assert.assertEquals(chargebackReferenceNumber, num);
        return act;
    }

    public void clickOpportunitybutton(WebDriver driver, String opportunity) {
        waitfortheelement();
        //driver.findElement(By.xpath("//div//a[@class='rlql-relatedListLink']//span[@title='"+opportunity+"']")).click();
        driver.findElement(By.xpath("//div[@class='id-rlql-linkFarm slds-card__body slds-card__body--inner']//span[text()='" + opportunity + "']")).click();
        waitfortheelement();
    }

//    public void clickopportunityname(WebDriver driver, String OppName) {
//        waitfortheelement();
//        //driver.findElement(By.xpath("//th//span[@class='slds-grid slds-grid--align-spread']//a[@title='" + OppName + "']")).click();
//        driver.findElement(By.xpath("//div[@class='center oneCenterStage lafSinglePaneWindowManager']//span[@class='slds-grid slds-grid--align-spread']//a[@title='"+OppName+"']")).click();
//        waitfortheelement();
//    }

//    public void clickOnPayment(WebDriver driver) {
//        waitfortheelement();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,50)");
//        // driver.findElement(By.xpath("//th//div//a[@class='textUnderline outputLookupLink slds-truncate forceOutputLookup']")).click();
//        driver.findElement(By.xpath("//div[@class='listViewContent']//a[@class='textUnderline outputLookupLink slds-truncate forceOutputLookup']")).click();
//        waitfortheelement();
//    }

    public String verifyPSandRT(WebDriver driver, String filedPS, String filedRT) {
        waitfortheelement();
        String PaymentStatus = driver.findElement(By.xpath("//div//span//span[@class='uiOutputTextArea'][text()='" + filedPS + "']")).getText();
        String RecordType = driver.findElement(By.xpath("//span[text()='Record Type']/../..//div[@class='recordTypeName slds-grow slds-truncate']//span")).getText();
        waitfortheelement();
//        System.out.println("*************************");
//        System.out.println(PaymentStatus);
//        System.out.println("**************************");
//        System.out.println(RecordType);
//        System.out.println("*********");

//        Assert.assertEquals(filedPS, PaymentStatus);
//        Assert.assertEquals(filedRT, RecordType);
        return PaymentStatus;
    }

    public void searchPaymentId(WebDriver driver, String filedPUID) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='uiInput uiAutocomplete uiInput--default']//input")).sendKeys(filedPUID);
        waitfortheelement();
        Actions act1 = new Actions(driver);
        waitfortheelement();
        act1.sendKeys(Keys.ENTER).build().perform();
    }

//    public void searchSalesforceTextbox(WebDriver driver) {
//        driver.findElement(By.xpath("//div[@class='uiInput uiAutocomplete uiInput--default']//input")).click();
//        waitfortheelement();
//    }

    public void clickPaymentNumber(WebDriver driver, String numberPay) {
        waitfortheelement();
        driver.findElement(By.xpath("(//th//span//a[@class='slds-truncate outputLookupLink slds-truncate forceOutputLookup'])[2]")).click();
        waitfortheelement();
    }

//    public void verifyPaymentRecord(WebDriver driver, String filedPaymentID, String filedrecordType) {
//        waitfortheelement();
//        String PaymentId = driver.findElement(By.xpath("//span[text()='Payment Unique Id']/../..//span[@class='uiOutputText']")).getText();
//        String RecordType = driver.findElement(By.xpath("//span[text()='Record Type']/../..//div[@class='recordTypeName slds-grow slds-truncate']//span")).getText();
//        waitfortheelement();
//        System.out.println("*************************");
//        System.out.println(PaymentId);
//        System.out.println("**************************");
//        System.out.println(RecordType);
//        System.out.println("*********");
//
//        Assert.assertEquals(filedPaymentID, PaymentId);
//        Assert.assertEquals(filedrecordType, RecordType);
//    }

    public void provideDetails(WebDriver driver, String fieldOpportunityName, String fieldPrimaryCampaignSource, String filedCloseDate, String filedStage, String filedPrimaryContact, String filedAmount, String opportunityName, String primaryCampaignSource, String closeDate, String stage, String primaryContact, String amount) {
        waitfortheelement();
        enterAllDetails(driver, fieldOpportunityName, opportunityName);
        driver.findElement(By.xpath("//label[contains(@class,'label inputLabel uiLabel-left form-element__label uiLabel')]//span[text()='Primary Campaign Source']")).click();

        driver.findElement(By.xpath("//span[text()='Primary Campaign Source']/../..//div[contains(@class,'primaryLabel slds')][@title='" + primaryCampaignSource + "']")).click();
        waitfortheelement();
        WebElement element = driver.findElement(By.xpath("//label[contains(@class,'label inputLabel')]//span[text()='" + filedCloseDate + "']/../..//a[contains(@class, 'datePicker-openIcon')]//span[@class='assistiveText']"));
        waitfortheelement();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        waitfortheelement();
        driver.findElement(By.xpath("//td[@class='uiDayInMonthCell'][@data-datevalue='" + closeDate + "']")).click();
        waitfortheelement();
        String e = driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='" + filedStage + "']")).getText();
        System.out.println(e);

        driver.findElement(By.xpath("//span[contains(@class,'label inputLabel')]//span[text()='" + filedStage + "']/../..//a[@class='select']")).click();
        driver.findElement(By.xpath(".//li[@class='uiMenuItem uiRadioMenuItem']//a[@title='" + stage + "']")).click();
        driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']//input[@title='Search Contacts']")).click();
        driver.findElement(By.xpath("//div[@class='slds-m-left--smalllabels slds-truncate slds-media__body']//div[@title='" + primaryContact + "']")).click();
        driver.findElement(By.xpath("//div//input[@class='input uiInput uiInput--default uiInput--input']")).sendKeys(amount);
    }

//    public void selectCheckbox(WebDriver driver) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//label//span[text()='Do Not Automatically Create Payment']/../..//input")).click();
//    }
//
//    public void provideContactName(WebDriver driver, String filedName) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//label[text()='Contact Name']/..//input[contains(@class,'slds-lookup__search-input')]")).click();
//        driver.findElement(By.xpath("//label[text()='Contact Name']/..//input[contains(@class,'slds-lookup__search-input')]")).sendKeys(filedName);
////        driver.findElement(By.xpath("//span[@class='slds-media__body']//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'][text()='"+ filedName +"']")).click();
////        waitfortheelement();
//    }

    public void provideDetailsOnce(WebDriver driver, String fieldCampaignAppealName, String fieldBatchId, String filedChannel, String filedReceipting, String filedDonationAmount, String filedPayment, String campaignAppealName, String batchId, String channel, String receipting, String donationAmount, String payment) {

        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldCampaignAppealName + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).click();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldCampaignAppealName + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).sendKeys(campaignAppealName);
        // driver.findElement(By.xpath("//li//span[@class='slds-media__body']//span[text()='" + campaignAppealName + "']")).sendKeys(campaignAppealName);
        driver.findElement(By.xpath("//li[@class='slds-listbox__item']//span[@class='slds-media__body']//span[contains(@class,'slds-listbox__option-text')][text()='" + campaignAppealName + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldBatchId + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).click();
        driver.findElement(By.xpath("//span[@class='slds-media__body']//span[text()='" + batchId + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-grid']//label//span[text()='" + filedChannel + "']/../..//div[@class='slds-select_container']")).click();
        WebElement testDropDown = driver.findElement(By.xpath("//label//span[text()='Channel']/../..//select[@class='slds-select']"));
        Select dropdown = new Select(testDropDown);
        dropdown.selectByVisibleText(channel);

        driver.findElement(By.xpath("//div[@class='slds-grid']//label//span[text()='" + filedReceipting + "']/../..//div[@class='slds-select_container']")).click();
        WebElement testDropDown2 = driver.findElement(By.xpath("//div[@class='slds-grid']//label//span[text()='Receipting']/../..//select[@class='slds-select']"));
        Select dropdown2 = new Select(testDropDown2);
        dropdown2.selectByVisibleText(receipting);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + filedDonationAmount + "']/..//input[@name='expenseclient']")).click();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + filedDonationAmount + "']/..//input[@name='expenseclient']")).sendKeys(donationAmount);

        driver.findElement(By.xpath("//label//span[text()='" + filedPayment + "']/../..//div[@class='slds-select_container']")).click();
        WebElement testDropDown3 = driver.findElement(By.xpath("//label//span[text()='" + filedPayment + "']/../..//select[@class='slds-select']"));
        Select dropdown3 = new Select(testDropDown3);
        dropdown3.selectByVisibleText(payment);
        waitfortheelement();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,250)");
        waitfortheelement();

    }

    public void clickCreatePayment(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand slds-m-top--medium']")).click();
    }

//    public void selectRadioButton(WebDriver driver) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//label[@class='slds-radio__label']//span[contains(text(),'Stark opp - 50.00')]")).click();
//        waitfortheelement();
//        driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand'][@title='Donation Selected']")).click();
//    }

//    public void clickDonationLink(WebDriver driver) {
//        waitfortheelement();
//        driver.findElement(By.xpath("//div[@class='slds-modal__content slds-p-around_medium']//a")).click();
//    }

    public void clickOnCheckbox(WebDriver driver, String filedCheckbox) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='column left-col']//button[contains(@class,'slds-button test-id')][@title='Edit Paid']")).click();
        // driver.findElement(By.xpath("//label//span[text()='" + filedCheckbox + "']/../..//input[@type='checkbox']")).click();
        driver.findElement(By.xpath("//div[@class='column left-col']//label//span[text()='Paid']//..//..//input[@type='checkbox']")).click();
    }

//    public void selectPaymentDate(WebDriver driver, String fieldPaymentDate,String fieldDate) {
//        driver.findElement(By.xpath("//div[@class='column left-col']//span[text()='"+fieldPaymentDate+"']/..//..//a[@class='datePicker-openIcon display']")).click();
//        //  driver.findElement(By.xpath("//label[@class='label inputLabel uiLabel-left form-element__label uiLabel']//span[text()='"+fieldPaymentDate+"']/..//..//a[@class='datePicker-openIcon display']")).click();
//        waitfortheelement();
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        Date date = new Date();
////        String strDate = dateFormat.format(date);
//
//        driver.findElement(By.xpath("//td[@class='uiDayInMonthCell'][@data-datevalue='"+fieldDate+"']")).click();
//
//    }
//
//    public void clickRadioButton(WebDriver driver, String filedName) {
//        waitfortheelement();
//        // driver.findElement(By.xpath("//div//span[@class='slds-radio']//input[@value='"+filedName+"']/..//label[@class='slds-radio__label']")).click();
//        driver.findElement(By.xpath("//div[@class='slds-card__body']//span[@class='slds-radio']//input[@value='"+filedName+"']/..//label[@class='slds-radio__label']")).click();
//    }

    public void chargebackValue(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String filedChargebackReferenceNumber, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber) {

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackReceivedOn + "']/..//input")).click();
//          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//          Date date = new Date();
//          String strDate = dateFormat.format(date);

        driver.findElement(By.xpath("//td[@data-value='" + chargebackReceivedOn + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackReferenceNumber + "']/..//input")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackReferenceNumber + "']/..//input")).sendKeys(chargebackReferenceNumber);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");
    }

    public void chargebackValues(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackResponseBy, String fieldChargebackActualResponseDate, String fieldChargebackSSTResponse, String fieldChargebackBankFinalNotificationDate, String chargebackBankAction, String chargebackChargebackResponseBy, String chargebackChargebackActualResponseDate, String chargebackChargebackSSTResponse, String chargebackChargebackBankFinalNotificationDate) {
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackResponseBy + "']/..//input")).click();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String strDate = dateFormat.format(date);
        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackResponseBy + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackActualResponseDate + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackActualResponseDate + "']")).click();

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackSSTResponse + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankFinalNotificationDate + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();

        waitfortheelement();
    }

    //    public void verifySuccessMessage(WebDriver driver, String message) {
//        waitfortheelement();
//        String msg = driver.findElement(By.xpath("//div[@class='confirm uiMessage']//div[@class='bBody'][text()='Chargeback has been initiated and is in progress.']")).getText();
//        waitfortheelement();
//
//        Assert.assertEquals(message, msg);
//    }
//
//    public void verifyRecord(WebDriver driver) {
//        String record = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='Related Payment Record']/../..//a")).getText();
//
//        System.out.println(record);
//    }
//
//    public void verifyTaskDetails(WebDriver driver, String assignedto, String subject, String status, String priority) {
//        waitfortheelement();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,150)");
//        String Assigned_To = driver.findElement(By.xpath("//div//span[text()='Assigned To']/../..//div[@class='ownerName']//a")).getText();
//        String Sub = driver.findElement(By.xpath("//div//span[text()='Subject']/../..//span[@class='uiOutputText']")).getText();
//        String Stat = driver.findElement(By.xpath("//div//span[text()='Status']/../..//span[text()='" + status + "']")).getText();
//        String Prio = driver.findElement(By.xpath("//div//span[text()='Priority']/../..//span[text()='" + priority + "']")).getText();
//
//        Assert.assertEquals(assignedto, Assigned_To);
//        Assert.assertEquals(subject, Sub);
//        Assert.assertEquals(status, Stat);
//        Assert.assertEquals(priority, Prio);
//    }
//
//    public void clickOpportunity(WebDriver driver, String oppoName) {
//
//        waitfortheelement();
//        waitfortheelement();
//        driver.findElement(By.xpath("//div[@class='column left-col']//a[@class=' textUnderline outputLookupLink slds-truncate forceOutputLookup'][text()='"+oppoName+"']")).click();
//        waitfortheelement();
//    }
//
//    public void clickPayments(WebDriver driver, String Payclick) {
//        waitfortheelement();
//        // driver.findElement(By.xpath("//div//a//span[@class='rlql-label'][text()='" + Payclick + "']")).click();
//        driver.findElement(By.xpath("//div[@class='showPreview forceRelatedListQuickLinksContainer']//span[@class='rlql-label'][text()='"+Payclick+"']")).click();
//        waitfortheelement();
//    }
//
//    public String verifyPaymentsRecord(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String fieldChargebackReferenceNumber, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber) throws ParseException {
//
//        waitfortheelement();
//        //String act = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='Chargeback Bank Action']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
////        String date = driver.findElement(By.xpath("//span[text()='Chargeback Received On']/../..//span[@class='uiOutputDate']")).getText();
////        String num = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='Chargeback Reference Number']/../..//span[@class='uiOutputText']")).getText();
//
//        String act = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='"+fieldChargebackBankAction+"']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
//        String date = driver.findElement(By.xpath("//div[@class='column left-col']//span[text()='"+fieldChargebackReceivedOn+"']/../..//span[@class='uiOutputDate']")).getText();
//        String num = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='"+fieldChargebackReferenceNumber+"']/../..//span[@class='uiOutputText']")).getText();
////       String str = "Chargeback initiated";
//        //  String str1= "2019-08-27";
////        String str2= "CN100102";
//
//
//
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
//        Date data= sdf.parse(chargebackReceivedOn);
//        // String strDate= sdf.format(data);
//        String formattedTime = sdf.format(data);
//
////        System.out.println("-------------------------------");
//        //         System.out.println(act);
////        System.out.println("--------------------------------");
////        System.out.println(formattedTime);
////        System.out.println("--------------------------------");
////        System.out.println(num);
////        System.out.println("--------------------------------");
//
////        Assert.assertEquals(chargebackBankAction, act);
////        Assert.assertEquals(chargebackReceivedOn, formattedTime);
////        Assert.assertEquals(chargebackReferenceNumber, num);
//        return act;
//    }
//
    public String verifyPaymentRecords(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackResponseBy, String fieldChargebackActualResponseDate, String fieldChargebackSSTResponse, String fieldChargebackBankFinalNotificationDate, String chargebackBankAction, String chargebackChargebackResponseBy, String chargebackChargebackActualResponseDate, String chargebackChargebackSSTResponse, String chargebackChargebackBankFinalNotificationDate) throws ParseException {

        waitfortheelement();
        waitfortheelement();
        //        String act = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='Chargeback Bank Action']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
//        String response = driver.findElement(By.xpath("//span[text()='Chargeback Response By']/../..//span[@class='uiOutputDate']")).getText();
//        String actual = driver.findElement(By.xpath("//span[text()='Chargeback Actual Response Date']/../..//span[@class='uiOutputDate']")).getText();
//        String sst = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='Chargeback SST Response']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
//        String finalbank = driver.findElement(By.xpath("//span[text()='Chargeback Bank Final Notification Date']/../..//span[@class='uiOutputDate']")).getText();

        String act = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + fieldChargebackBankAction + "']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
        String response = driver.findElement(By.xpath("//div[@class='column left-col']//span[text()='" + fieldChargebackResponseBy + "']/../..//span[@class='uiOutputDate']")).getText();
        String actual = driver.findElement(By.xpath("//div[@class='column left-col']//span[text()='" + fieldChargebackActualResponseDate + "']/../..//span[@class='uiOutputDate']")).getText();
        String sst = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + fieldChargebackSSTResponse + "']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
        String finalbank = driver.findElement(By.xpath("//div[@class='column left-col']//span[text()='" + fieldChargebackBankFinalNotificationDate + "']/../..//span[@class='uiOutputDate']")).getText();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date d = sdf.parse(chargebackChargebackResponseBy);
        String formattedResponseTime = sdf.format(d);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = sdf1.parse(chargebackChargebackActualResponseDate);
        String formattedActualResponseTime = sdf1.format(date1);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
        Date date2 = sdf2.parse(chargebackChargebackBankFinalNotificationDate);
        String formattedFinalBankTime = sdf2.format(date2);


        System.out.println("--------------------000000---------------------------");
        System.out.println(act);
        System.out.println("--------------------------------");
        System.out.println(formattedResponseTime);
        System.out.println("--------------------------------");
        System.out.println(formattedActualResponseTime);
        System.out.println("--------------------------------");
        System.out.println(sst);
        System.out.println("--------------------------------");
        System.out.println(formattedFinalBankTime);
        System.out.println("--------------------------------------------");
//
//        Assert.assertEquals(chargebackBankAction,act);
//        Assert.assertEquals(chargebackChargebackResponseBy,formattedResponseTime);
//        Assert.assertEquals(chargebackChargebackActualResponseDate,formattedActualResponseTime);
//        Assert.assertEquals(chargebackChargebackSSTResponse,sst);
//        Assert.assertEquals(chargebackChargebackBankFinalNotificationDate,formattedFinalBankTime);

        return act;
    }
//
//    public String verifyPayRecord(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String fieldChargebackReferenceNumber, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber) throws ParseException {
//
//        waitfortheelement();
//        waitfortheelement();
//
//        String act = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='"+fieldChargebackBankAction+"']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
//        String date = driver.findElement(By.xpath("//span[text()='"+fieldChargebackReceivedOn+"']/../..//span[@class='uiOutputDate']")).getText();
//        String num = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='"+fieldChargebackReferenceNumber+"']/../..//span[@class='uiOutputText']")).getText();
//
////        String act = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='"+chargebackBankAction+"']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();
////        String date = driver.findElement(By.xpath("//span[text()='"+chargebackReceivedOn+"']/../..//span[@class='uiOutputDate']")).getText();
////       String num = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='"+chargebackReferenceNumber+"']/../..//span[@class='uiOutputText']")).getText();
////       String str = "Chargeback initiated";
////        String str1= "2019-08-27";
////        String str2= "CN100102";
//        SimpleDateFormat sdfd= new SimpleDateFormat("yyyy-mm-dd");
//        Date data= sdfd.parse(chargebackReceivedOn);
//        // String strDate= sdf.format(data);
//        String formattedTime = sdfd.format(data);
//
////        Assert.assertEquals(chargebackBankAction, act);
////        Assert.assertEquals(chargebackReceivedOn, formattedTime);
////        Assert.assertEquals(chargebackReferenceNumber, num);
//
//        return act;
//    }

    public void clickOnPaymentNumber(WebDriver driver) {

        waitfortheelement();
        waitfortheelement();
        waitfortheelement();
        WebElement e = driver.findElement(By.xpath("//h1[@title='Payments']/../../../../../..//table[contains(@class,'slds-table forceRecord')]//tbody"));
        // WebElement e= driver.findElement(By.xpath("//th[@class='slds-cell-edit cellContainer']//a[@class='slds-truncate outputLookupLink slds-truncate forceOutputLookup']"));
        e.click();

        waitfortheelement();

        List<WebElement> rows = e.findElements(By.tagName("tr"));
        WebElement SecRecordFound = rows.get(1);

        SecRecordFound.findElement(By.tagName("th")).findElement(By.tagName("a")).click();
        waitfortheelement();
        waitfortheelement();

    }


    public String verifyEmptyField(WebDriver driver, String empty) {

        waitfortheelement();
        waitfortheelement();

        String str = driver.findElement(By.xpath("//div[@class='error uiMessage']//div[@class='uiBlock']//div[@class='bBody']")).getText();

        String[] tokens = empty.split("/n");
        int i;
        try {
            for (i = 0; i < tokens.length; i++)
//            System.out.println(tokens[i]);

//        System.out.println("----------------------");
                System.out.println(tokens[i]);
            System.out.println("----------------------");
            System.out.println(tokens[i]);
            System.out.println("----------------------");
            System.out.println(str);
            System.out.println("-----------------------");
            Assert.assertEquals(tokens[i], str);
        } catch (ArrayIndexOutOfBoundsException exp) {
            System.out.println(str);
        }

        return str;
//        System.out.println("----------------------------------------");
//        //String actual= str.substring(0,50);
//        System.out.println(str);
//        System.out.println("----------------------------------");
//        waitfortheelement();
//        waitfortheelement();

//        String data= "Error\n".concat(empty);
//
//        Assert.assertEquals(empty,data);
        // Assert.assertTrue(Boolean.parseBoolean(actual.concat("Error For chargeback processing all fields are mandatory")));
//        if(empty==str)
//        {
//            System.out.println("----------------------------------------");
//            System.out.println(str);
//        }
//        else
//        {
//            System.out.println("----------------------------------");
//        }
        //Assert.assertTrue(str.contains(str1));

    }

    public void chargeback(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackResponseBy, String fieldChargebackActualResponseDate, String fieldChargebackSSTResponse, String fieldChargebackBankFinalNotificationDate, String chargebackBankAction, String chargebackChargebackResponseBy, String chargebackChargebackActualResponseDate, String chargebackChargebackSSTResponse, String chargebackChargebackBankFinalNotificationDate) {


        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackResponseBy + "']/..//input")).click();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String strDate = dateFormat.format(date);
        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackResponseBy + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackActualResponseDate + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackChargebackActualResponseDate + "']")).click();

        waitfortheelement();
        //driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']"));
        // driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']"));
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankFinalNotificationDate + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();

        waitfortheelement();


    }

    public void givedetails(WebDriver driver, String fieldChargebackBankAction, String fieldChargebackReceivedOn, String filedChargebackReferenceNumber, String fieldChargebackResponseBy, String fieldChargebackActualResponseDate, String fieldChargebackSSTResponse, String fieldChargebackBankFinalNotificationDate, String chargebackBankAction, String chargebackReceivedOn, String chargebackReferenceNumber, String chargebackChargebackResponseBy, String chargebackChargebackActualResponseDate, String chargebackChargebackSSTResponse, String chargebackChargebackBankFinalNotificationDate) {

        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankAction + "']/..//span[@title='" + chargebackBankAction + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackReceivedOn + "']/..//input")).click();
//          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//          Date date = new Date();
//          String strDate = dateFormat.format(date);

        driver.findElement(By.xpath("//td[@data-value='" + chargebackReceivedOn + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackReferenceNumber + "']/..//input")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + filedChargebackReferenceNumber + "']/..//input")).sendKeys(chargebackReferenceNumber);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackResponseBy + "']/..//input")).click();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String strDate = dateFormat.format(date);
        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackResponseBy + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackActualResponseDate + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackActualResponseDate + "']")).click();

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackSSTResponse + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldChargebackBankFinalNotificationDate + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();

        waitfortheelement();

    }

    public void recordType(WebDriver driver, String fieldType, String record) {

        waitfortheelement();
        waitfortheelement();

        //  System.out.println("\n"+record);
        String rc = driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='" + fieldType + "']//..//..//div[@class='recordTypeName slds-grow slds-truncate']//span[text()='" + record + "']")).getText();
        // String rc=driver.findElement(By.xpath("//span[@class='test-id__field-label'][text()='"+chargebackRecordType+"']//..//..//div[@class='recordTypeName slds-grow slds-truncate']//span[text()='"+record+"']")).getText();
        waitfortheelement();
        System.out.println("-------------------------------");
        System.out.println(rc);
        System.out.println("--------------------------------");

        Assert.assertEquals(record, rc);
    }

    public void opportunityClick(WebDriver driver, String clickOpportunity) {

        waitfortheelement();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='flexipageComponent']//span[@class='slds-card__header-title slds-truncate slds-m-right--xx-small'] [@title='" + clickOpportunity + "']")).click();
        //driver.findElement(By.xpath("//span[@class='slds-card__header-title slds-truncate slds-m-right--xx-small'] [@title='"+ clickOpportunity +"']")).click();

    }

    public void relatedClick(WebDriver driver, String relatedClick) {

        waitfortheelement();
        WebElement elem = driver.findElement(By.xpath("//div[@class='column region-main']//a[@class='tabHeader']//span[text()='" + relatedClick + "']"));
        //  WebElement elem = driver.findElement(By.xpath("//a[@class='tabHeader'][@title='"+relatedClick+"']//span[@class='title'][text()='"+relatedClick+"']"));
        elem.click();
        waitfortheelement();
        waitfortheelement();

    }

    public String validOpportunities(WebDriver driver, String OpportunityName, String AccountName) {

        waitfortheelement();
        waitfortheelement();

        waitfortheelement();
        //  JavascriptExecutor js = (JavascriptExecutor) driver;
        //  js.executeScript("scrollBy(0,-250)");
        String OppoName = driver.findElement(By.xpath("//div[@class='test-listViewManager slds-grid slds-grid--vertical forceListViewManager']//a[@title='" + OpportunityName + "']")).getText();
        String AccName = driver.findElement(By.xpath("//div[@class='test-listViewManager slds-grid slds-grid--vertical forceListViewManager']//td//span[@class='slds-grid slds-grid--align-spread']//a[@title='" + AccountName + "']")).getText();

        // String OppoName = driver.findElement(By.xpath("//th//span[@class='slds-grid slds-grid--align-spread']//a[@title='"+OpportunityName+"']")).getText();
        // String AccName = driver.findElement(By.xpath("//td//span[@class='slds-grid slds-grid--align-spread']//a[@title='"+AccountName+"']")).getText();
        //    String act = driver.findElement(By.xpath(".//span[@class='test-id__field-label'][text()='"+fieldChargebackBankAction+"']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']//span")).getText();

        waitfortheelement();
//        System.out.println("-------------------------------------------");
//        System.out.println(OppoName);
//        System.out.println("----------------------------------");
//        System.out.println(AccName);
//        System.out.println("--------------------------------------------");


        waitfortheelement();
        Assert.assertEquals(OpportunityName, OppoName);
        Assert.assertEquals(AccountName, AccName);

        return OppoName;
    }


    public String Recordclick(WebDriver driver, String recordType) {

        waitfortheelement();
        String type = driver.findElement(By.xpath("//div[@class='slds-grid listViewContainer safari-workaround']//span[@class='slds-truncate uiOutputText'][@title='" + recordType + "']")).getText();
        //String type= driver.findElement(By.xpath("//span[@class='slds-truncate uiOutputText'][@title='"+ recordType +"']")).getText();
//        System.out.println("----------------------------------");
//        System.out.println(type);
//        System.out.println("----------------------------------");
//        waitfortheelement();

        //  Assert.assertEquals(recordType,type);


        return type;
    }

    public void clickReund(WebDriver driver, String fieldCaseOrigin, String fieldRefundType, String fieldRefundInstrument, String fieldBankAccountNo, String fieldBankBSB, String refundCaseOrigin, String refundRefundType, String refundRefundInstrument, String refundBankAccountNo, String refundBankBSB) {

        waitfortheelement();

        // driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldCaseOrigin + "']")).click();
        driver.findElement(By.xpath("//form[@class='slds-form']//label[@class='slds-form-element__label'][text()='" + fieldCaseOrigin + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldCaseOrigin + "']/..//span[@title='" + refundCaseOrigin + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']/..//span[@title='" + refundRefundType + "']")).click();
//        driver.findElement(By.xpath("//label[text()='"+fieldChargebackResponseBy+"']/..//input")).click();
////        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        Date date = new Date();
////        String strDate = dateFormat.format(date);
//        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackResponseBy + "']")).click();
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundInstrument + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundInstrument + "']/..//span[@title='" + refundRefundInstrument + "']")).click();

        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankAccountNo + "']/..//input")).sendKeys(refundBankAccountNo);
//        driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']")).click();
//        driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']")).click();
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankBSB + "']/..//input")).sendKeys(refundBankBSB);
//        driver.findElement(By.xpath("//label[text()='"+fieldChargebackBankFinalNotificationDate+"']/..//input")).click();
//        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");
        waitfortheelement();
        waitfortheelement();


    }

    public String validSuccessMessage(WebDriver driver, String message) {

        waitfortheelement();
        waitfortheelement();

        String strg = driver.findElement(By.xpath("//div[@class='confirm uiMessage']//div[@class='uiBlock']//div[@class='bBody']")).getText();

        return strg;
    }

    public void resave(WebDriver driver, String btn) {

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//button[@class='slds-button slds-button_brand'][text()='" + btn + "']")).click();
        waitfortheelement();
        waitfortheelement();
    }

    public void casesClick(WebDriver driver, String cases) {

        waitfortheelement();
        driver.findElement(By.xpath(".//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[@class='slds-truncate'][text()='" + cases + "']]]")).click();


    }

    public void caseNumber(WebDriver driver, String casenumber) {

        waitfortheelement();
        //driver.findElement(By.xpath("//input[@name='Case-search-input']")).sendKeys(casenumber);
        driver.findElement(By.xpath("//div[@class='slds-col slds-no-flex slds-grid slds-align-bottom slds-shrink']//input[@name='Case-search-input']")).sendKeys(casenumber);
        waitfortheelement();
        Actions act1 = new Actions(driver);
        waitfortheelement();
        act1.sendKeys(Keys.ENTER).build().perform();
        waitfortheelement();


    }

    public void paymentRefund(WebDriver driver) {

        waitfortheelement();
        waitfortheelement();
        waitfortheelement();

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,50)");
        // driver.findElement(By.xpath("//th//div//a[@class='textUnderline outputLookupLink slds-truncate forceOutputLookup']")).click();
//        driver.findElement(By.xpath("//div[@class='listViewContent']//a[@class='textUnderline outputLookupLink slds-truncate forceOutputLookup']")).click();
//        WebElement e= driver.findElement(By.xpath("//div[@class='column region-main']//div[@class='test-id__field-label-container slds-form-element__label']//span[text()='Refund Payment']"));
//        // WebElement e= driver.findElement(By.xpath("//th[@class='slds-cell-edit cellContainer']//a[@class='slds-truncate outputLookupLink slds-truncate forceOutputLookup']"));
//        e.click();
//
//        waitfortheelement();
//
//        List<WebElement> rows = e.findElements(By.tagName("tr"));
//        WebElement SecRecordFound = rows.get(1);
//
//        SecRecordFound.findElement(By.tagName("th")).findElement(By.tagName("a")).click();
//        waitfortheelement();
//        waitfortheelement();

//        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-form-element slds-form-element')]//div[contains(@class,'test-id__field-label-container')]/span[text()='Refund Payment']/../..//a")).click();

    }


    public void searchCaseNumber(WebDriver driver) {

        waitfortheelement();
        waitfortheelement();
        waitfortheelement();
        WebElement e = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]//tbody"));

        waitfortheelement();

        List<WebElement> rows = e.findElements(By.tagName("tr"));
        WebElement firstRecordFound = rows.get(0);

        firstRecordFound.findElement(By.tagName("th")).findElement(By.tagName("a")).click();
        waitfortheelement();
        waitfortheelement();
        waitfortheelement();
    }

    public String Refundstatus(WebDriver driver, String fieldRefundStatus, String Refundstatus) {

        waitfortheelement();
        waitfortheelement();

        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
        String reDate = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + fieldRefundStatus + "']//..//..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']")).getText();
        waitfortheelement();

        System.out.println(reDate);

        waitfortheelement();
        // Assert.assertEquals(Refundstatus,reDate);

        return reDate;
    }

    public String refundDateDislayed(WebDriver driver, String completionDate, String refundRefundCompletionDate) throws ParseException {

        waitfortheelement();
        waitfortheelement();

        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
        String reDate = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + completionDate + "']//..//..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']")).getText();
        waitfortheelement();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date datab = sdf.parse(refundRefundCompletionDate);
        String formattedTime = sdf.format(datab);

        System.out.println(formattedTime);

        waitfortheelement();
        //   Assert.assertEquals(refundRefundCompletionDate,reDate);

        // return reDate;
        return formattedTime;
    }

    public String refundDecisionDate(WebDriver driver, String fieldDecisionDate, String refundDecisionDate) throws ParseException {

        waitfortheelement();
        waitfortheelement();

        waitfortheelement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,1000)");
        String reDate = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + fieldDecisionDate + "']//..//..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']")).getText();
        waitfortheelement();

        //Assert.assertEquals(fieldDeisionDate,reDate);
        System.out.println(reDate);

        waitfortheelement();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date datab = sdf.parse(refundDecisionDate);
        String formattedTime = sdf.format(datab);

        System.out.println(formattedTime);

        return formattedTime;
        //return reDate;
    }

    public void statusEdit(WebDriver driver, String statusEdit) {

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='column region-main']//button[@title='" + statusEdit + "']")).click();

    }

    public void myCases(WebDriver driver, String mycases) {

        waitfortheelement();
        driver.findElement(By.xpath("//span[@class= ' virtualAutocompleteOptionText'][contains(text(),'" + mycases + "')]")).click();
        waitfortheelement();
        Actions act1 = new Actions(driver);
        waitfortheelement();
        act1.sendKeys(Keys.ENTER).build().perform();
        waitfortheelement();
    }

    public void payDone(WebDriver driver, String fieldStatus, String payDone) {

        waitfortheelement();

        waitfortheelement();

        driver.findElement(By.xpath("//span[@class='label inputLabel uiPicklistLabel-left form-element__label uiPicklistLabel']//span[text()='" + fieldStatus + "']")).click();
        driver.findElement(By.xpath("//span[@class='label inputLabel uiPicklistLabel-left form-element__label uiPicklistLabel']//span[text()='" + fieldStatus + "']//..//..//a[@class='select'][text()='" + payDone + "']")).click();


        //  driver.findElement(By.xpath("//div[@class='column region-main']//a[@class='select'][text()='" +fieldStatus+ "']")).click();
        waitfortheelement();
//        Actions act1 = new Actions(driver);
//        waitfortheelement();
//        act1.sendKeys(Keys.ENTER).build().perform();
//        waitfortheelement();
        // driver.findElement(By.xpath("//div[@class='column region-main']//a[@class='select'][text()='"+payDone+"']")).click();
        waitfortheelement();

    }


    public void donePayment(WebDriver driver, String done) {
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='uiMenu']//a[@class='select']//..//..//a")).click();
        driver.findElement(By.xpath("//div[@class='select-options']//li[@class='uiMenuItem uiRadioMenuItem']//a[@title='" + done + "']")).click();

    }

    public void refNumber(WebDriver driver, String fieldReferenceNumber, String refnum) {

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-form-element slds-hint-parent']//label[@class='label inputLabel uiLabel-left form-element__label uiLabel']//span[text()='" + fieldReferenceNumber + "']//..//..//input[@type='text']")).sendKeys(refnum);
        waitfortheelement();
    }

    public void saveForm(WebDriver driver, String form) {

        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='groupDependentFieldEnabled forceInlineEdit']//button[@title='" + form + "']")).click();
        waitfortheelement();
    }

    public String refundStatus(WebDriver driver, String reStatus) {

        waitfortheelement();
        waitfortheelement();

        waitfortheelement();
        //  JavascriptExecutor js = (JavascriptExecutor) driver;
        //  js.executeScript("scrollBy(0,-250)");
        driver.findElement(By.xpath("//div[@class='column region-main']//div[@class='test-id__field-label-container slds-form-element__label']//span[@class='test-id__field-label'][text()='" + reStatus + "']")).getText();

        return reStatus;
    }

    public void provideRefundField(WebDriver driver, String fieldCaseOrigin, String fieldRefundType, String fieldRefundAmount, String fieldRefundInstrument, String fieldBankAccountNo, String fieldBankBSB, String refundCaseOrigin, String refundRefundType, String refundRefundAmount, String refundRefundInstrument, String refundBankAccountNo, String refundBankBSB) {

        waitfortheelement();

        // driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldCaseOrigin + "']")).click();
        driver.findElement(By.xpath("//form[@class='slds-form']//label[@class='slds-form-element__label'][text()='" + fieldCaseOrigin + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldCaseOrigin + "']/..//span[@title='" + refundCaseOrigin + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']/..//span[@title='" + refundRefundType + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundAmount + "']/..//input")).sendKeys(refundRefundAmount);

        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundInstrument + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundInstrument + "']/..//span[@title='" + refundRefundInstrument + "']")).click();

        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankAccountNo + "']/..//input")).sendKeys(refundBankAccountNo);
//        driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']")).click();
//        driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']")).click();
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankBSB + "']/..//input")).sendKeys(refundBankBSB);
//        driver.findElement(By.xpath("//label[text()='"+fieldChargebackBankFinalNotificationDate+"']/..//input")).click();
//        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");
        waitfortheelement();
        waitfortheelement();


    }

    public void relatedTab(WebDriver driver, String related) {
        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='column left-col']//div[@class='uiTabBar']//span[text()='" + related + "']")).click();
        waitfortheelement();

    }

    public void caseLink(WebDriver driver) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='container']//h3[@class='primaryField']//a")).click();
        waitfortheelement();

    }

    public void approve(WebDriver driver) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("(//div[@class='slds-card__body']//h3[@class='primaryField']//a)[2]")).click();
        waitfortheelement();

    }

    public void requestApproval(WebDriver driver, String approve) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-grid primaryFieldRow']//div[@class='slds-truncate'][@title='" + approve + "']")).click();
        waitfortheelement();

    }

    public void caseApprove(WebDriver driver) {

        String text = "Approval case is accepting amount 200 to 1000 under approve case";
        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='actionContent runtime_approval_processActionContent']//textarea")).sendKeys(text);
        // driver.findElement(By.xpath("//div[@class='actionContent runtime_approval_processActionContent']//textarea")).sendKeys("Approval case is accepting amount 200 to 1000 under approve case");
        waitfortheelement();

    }

    public void approveCase(WebDriver driver, String app) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//span[text()='" + app + "']")).click();
        waitfortheelement();

    }

    public String approvedStatus(WebDriver driver, String approved) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='column region-main']//div[@class='slds-form-element__control slds-grid itemBody']//span[text()='" + approved + "']")).getText();
        waitfortheelement();


        return approved;
    }

    public String paidUnchecked(WebDriver driver, String recordType) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='column left-col']//div[@class='slds-form-element__control slds-grid itemBody']//span[@class='uiImage uiOutputCheckbox']"));
        String RecordType = driver.findElement(By.xpath("//span[text()='" + recordType + "']/../..//div[@class='recordTypeName slds-grow slds-truncate']//span")).getText();

        waitfortheelement();
        return RecordType;
    }

    public void typeRefund(WebDriver driver, String fieldRefundType, String refundRefundType) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']/..//span[@title='" + refundRefundType + "']")).click();
        waitfortheelement();

    }

    public String GAUVisible(WebDriver driver, String alloc) {
        waitfortheelement();

        String typo = driver.findElement(By.xpath("//div[@class='slds-card__body']//div[@class='slds-truncate'][text()='" + alloc + "']")).getText();

        return typo;
    }

    public void back(WebDriver driver, String back) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//button[@class='slds-button slds-button_brand'][text()='" + back + "']")).click();
        waitfortheelement();
        waitfortheelement();
    }

    public String radioPaymentType(WebDriver driver, String paymentType) {

        waitfortheelement();
        //waitfortheelement();
        String radio = driver.findElement(By.xpath("//div[@class='slds-card__body']//legend[@class='slds-form-element__legend slds-form-element__label'][text()='" + paymentType + "']")).getText();
        waitfortheelement();

        return radio;
    }


    public String relatedPaymentRecord(WebDriver driver, String RPaymentType) {


        waitfortheelement();
        //waitfortheelement();
        String radio = driver.findElement(By.xpath("//div[@class='column left-col']//span[@class='test-id__field-label'][text()='" + RPaymentType + "']")).getText();
        waitfortheelement();
        return radio;
    }

    public void searchRecord(WebDriver driver, String number) {

        waitfortheelement();
        //waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-global-header__item slds-global-header__item--search']//input[@data-aura-class='uiInput uiInputTextForAutocomplete uiInput--{remove}']")).sendKeys(number);
        waitfortheelement();
        Actions act1 = new Actions(driver);
        waitfortheelement();
        act1.sendKeys(Keys.ENTER).build().perform();
    }

    public void manualRefund(WebDriver driver, String runforcause, String flood) {

        waitfortheelement();
        //waitfortheelement();
        //driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'"+runforcause+"')]")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'" + runforcause + "')]")).sendKeys("200");
        waitfortheelement();
        waitfortheelement();
        //  driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'Flood Relief')]")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'" + flood + "')]")).sendKeys("40");
        waitfortheelement();

    }

    public void GAUclick(WebDriver driver, String arg1, String arg2) {

        waitfortheelement();
        //waitfortheelement();
        //driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'Running For Cause')]//div[@class='slds-form-element__control slds-grow']")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//table[@class='slds-table']//input[@type='checkbox'][contains(@name,'" + arg1 + "')]")).click();
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//table[@class='slds-table']//input[@type='checkbox'][contains(@name,'" + arg2 + "')]")).click();
        waitfortheelement();
        waitfortheelement();

    }

    public String productsError(WebDriver driver, String error) {
        waitfortheelement();
        String err = driver.findElement(By.xpath("//div[@class='error uiMessage']//div[@class='uiBlock']//div[@class='bBody']")).getText();
        waitfortheelement();

        return err;
    }

    public String GauAmount(WebDriver driver, String argument) {

        waitfortheelement();
        String err = driver.findElement(By.xpath("//div[@class='slds-card__body']//div[@class='slds-form-element__help']")).getText();
        waitfortheelement();
        return err;
    }

    public void Campagin(WebDriver driver, String argument1, String argument2) {

        waitfortheelement();
        //waitfortheelement();
        //driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'"+runforcause+"')]")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'" + argument1 + "')]")).sendKeys("500");
        waitfortheelement();
        waitfortheelement();
        //  driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'Flood Relief')]")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'" + argument2 + "')]")).sendKeys("600");
        waitfortheelement();

    }

    public void testCampaign(WebDriver driver, String arg1, String arg2) {

        waitfortheelement();
        //waitfortheelement();
        //driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'"+runforcause+"')]")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'" + arg1 + "')]")).sendKeys("200");
        waitfortheelement();
        waitfortheelement();
        //  driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'Flood Relief')]")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body' ]//lightning-input[contains(@title,'" + arg2 + "')]")).sendKeys("50");
        waitfortheelement();

    }

    public void sameRefundAmt(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//tr[@class='highlightCss']//th[@class='paddingLeft']")).getText();

    }

    public String negativeRefundAmt(WebDriver driver, String arg1) {

        driver.findElement(By.xpath("//div[@class='error uiMessage']//div[@class='uiBlock']//div[@class='bBody']")).getText();

        return arg1;
    }

    public void informationRefund(WebDriver driver, String fieldCaseOrigin, String fieldRefundType, String fieldRefundAmount, String fieldRefundInstrument, String fieldBankAccountNo, String fieldBankBSB, String refundCaseOrigin, String refundRefundType, String refundRefundAmount, String refundRefundInstrument, String refundBankAccountNo, String refundBankBSB) {

        waitfortheelement();

        // driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldCaseOrigin + "']")).click();
        driver.findElement(By.xpath("//form[@class='slds-form']//label[@class='slds-form-element__label'][text()='" + fieldCaseOrigin + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldCaseOrigin + "']/..//span[@title='" + refundCaseOrigin + "']")).click();
        waitfortheelement();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundType + "']/..//span[@title='" + refundRefundType + "']")).click();

        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundAmount + "']/..//input")).clear();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundAmount + "']/..//input")).sendKeys(refundRefundAmount);

        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundInstrument + "']")).click();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldRefundInstrument + "']/..//span[@title='" + refundRefundInstrument + "']")).click();

        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankAccountNo + "']/..//input")).sendKeys(refundBankAccountNo);
//        driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']")).click();
//        driver.findElement(By.xpath("//label[text()='" + fieldChargebackSSTResponse + "']/..//span[@title='" + chargebackChargebackSSTResponse + "']")).click();
        waitfortheelement();
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//label[text()='" + fieldBankBSB + "']/..//input")).sendKeys(refundBankBSB);
//        driver.findElement(By.xpath("//label[text()='"+fieldChargebackBankFinalNotificationDate+"']/..//input")).click();
//        driver.findElement(By.xpath("//td[@data-value='" + chargebackChargebackBankFinalNotificationDate + "']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,50)");
        waitfortheelement();
        waitfortheelement();

    }

    public void provideCaseNumber(WebDriver driver, String caseId) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-form-element__control slds-grow')]//input")).sendKeys(caseId);
        waitfortheelement();
        Actions act1 = new Actions(driver);
        act1.sendKeys(Keys.ENTER).build().perform();
    }

    public void clickRefundPayment(WebDriver driver, String Payment) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-form-element slds-form')]//span[text()='" + Payment + "']/../..//a")).click();
        waitfortheelement();
    }

    public void verifyWrittenOff(WebDriver driver) {
        waitfortheelement();
//        driver.findElement(By.xpath("//div[contains(@class,'slds-form-element slds-form-element')]//span[text()='Written Off']/../..//span[@class='slds-assistive-text'][text()='Edit Written Off']")).click();

        WebElement checkbox = driver.findElement(By.xpath("//div[contains(@class,'slds-form-element slds-form-element')]//span[text()='Written Off']/../..//span[@class='uiImage uiOutputCheckbox']"));
        System.out.println("The checkbox is selection state is - " + checkbox.isSelected());
    }

    public void provideDetailsAdj(WebDriver driver, String fieldCampaignAppealName, String fieldAmount, String fieldReason, String campaignName, String amount, String reason) {
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,50)");
        driver.findElement(By.xpath("(//table[contains(@class,'slds-table slds')]//tr//span[@class='slds-pill__label']/../..//lightning-primitive-icon/../..//button)[1]")).click();
//        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='"+ fieldCampaignAppealName +"']/..//input[contains(@class,'slds-lookup__search-input slds')]")).sendKeys(campaignName);
        waitfortheelement();
        driver.findElement(By.xpath("(//table[contains(@class,'slds-table slds')]//tr//input[contains(@class,'slds-lookup__search-input slds-input')])[1]")).sendKeys(campaignName);
        waitfortheelement();

        driver.findElement(By.xpath("//div[contains(@class,'container-fluid')]//th//div[@title='" + fieldAmount + "']/../../../..//tr//input[@name='allAmount']")).clear();
        driver.findElement(By.xpath("//div[contains(@class,'container-fluid')]//th//div[@title='" + fieldAmount + "']/../../../..//tr//input[@name='allAmount']")).sendKeys(amount);

//        driver.findElement(By.xpath("//label[text()='" + fieldReason + "']/..//div[contains(@class,'slds-combobox slds-dropdown')]")).click();
//        driver.findElement(By.xpath("//div[contains(@class,'slds-listbox slds-listbox')]//span[@title='" + reason + "']")).click();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("scrollBy(0,150)");
    }

    public void clickAdd(WebDriver driver) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-float_right slds-p-bottom_small']//button[@class='slds-button']")).click();
    }

    public void primaryContact(WebDriver driver, String contact) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-card__body']//button[@title='Clear Selection']")).click();
//        driver.findElement(By.xpath("  //div[@class='slds-card__body']//label[text()='Primary Contact']/..//div[contains(@class,'slds-combobox slds-dropdown')]//input")).click();
//        driver.findElement(By.xpath("  //div[@class='slds-card__body']//label[text()='Primary Contact']/..//div[contains(@class,'slds-combobox slds-dropdown')]//input")).clear();
//        driver.findElement(By.xpath("  //div[@class='slds-card__body']//label[text()='Primary Contact']/..//div[contains(@class,'slds-combobox slds-dropdown')]//input")).sendKeys(contact);
        driver.findElement(By.xpath("//div[@class='slds-card__body']//div[@class='slds-combobox__form-element slds-input-has-icon slds-input-has-icon_right']")).sendKeys(contact);
        driver.findElement(By.xpath("//div[@class='slds-card__body']//lightning-base-combobox-item[@class='slds-media slds-listbox__option slds-media_center slds-listbox__option_entity']//span[@title='" + contact + "']")).click();

    }

    public void enterspiltdetails(WebDriver driver, String filedAppeal, String filedAmount, String appeal, String amount) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='container-fluid cSplitPayment']//span[contains(@class,'slds-pill slds-pil')]/../../../..//label[text()='Appeal']/..//button")).click();
        driver.findElement(By.xpath("//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAppeal + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAppeal + "']/..//input")).sendKeys(appeal);
        driver.findElement(By.xpath("//ul[contains(@class,'slds-listbox slds-listbox')]//li[@class='slds-listbox__item']//span[@class='slds-media__body']//span[text()='" + appeal + "']")).click();

        driver.findElement(By.xpath("//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAmount + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAmount + "']/..//input")).clear();
        driver.findElement(By.xpath("//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAmount + "']/..//input")).sendKeys(amount);
//        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldCampaignAppealName + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).click();
//        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldCampaignAppealName + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).sendKeys(campaignAppealName);
//        driver.findElement(By.xpath("//li[@class='slds-listbox__item']//span[@class='slds-media__body']//span[contains(@class,'slds-listbox__option-text')][contains(text(),'" + campaignAppealName + "')]")).click();
//        driver.findElement(By.xpath("//lightning-icon[@class='slds-icon-utility-add slds-icon_container']//lightning-primitive-icon")).click();

    }

    public void enterRDDetails(WebDriver driver, String fieldCampaignAppealName, String filedChannel, String fieldRDFrequency, String filedRDStartDate, String filedDonationAmount, String filedPayment, String campaignAppealName, String channel, String rdFrequency, String RDStartDate, String donationAmount, String payment) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldCampaignAppealName + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).click();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + fieldCampaignAppealName + "']/..//input[contains(@class,'slds-lookup__search-input slds')]")).sendKeys(campaignAppealName);
        driver.findElement(By.xpath("//li[@class='slds-listbox__item']//span[@class='slds-media__body']//span[contains(@class,'slds-listbox__option-text')][contains(text(),'" + campaignAppealName + "')]")).click();

        driver.findElement(By.xpath("//div[@class='slds-grid']//label//span[text()='" + filedChannel + "']/../..//div[@class='slds-select_container']")).click();
        WebElement testDropDown = driver.findElement(By.xpath("//div[@class='slds-grid']//label//span[text()='" + filedChannel + "']/../..//select[@class='slds-select']"));
        Select dropdown = new Select(testDropDown);
        dropdown.selectByVisibleText(channel);

        driver.findElement(By.xpath("//div[@class='paymentBlock']//div[contains(@class,'slds-size_1-of-2 slds-max')]//label//span[text()='" + fieldRDFrequency + "']/../..//div[@class='slds-select_container']//select[@class='slds-select']")).click();
        WebElement testDropDown1 = driver.findElement(By.xpath("//div[@class='paymentBlock']//div[contains(@class,'slds-size_1-of-2 slds-max')]//label//span[text()='" + fieldRDFrequency + "']/../..//div[@class='slds-select_container']//select[@class='slds-select']"));
        Select dropdown1 = new Select(testDropDown1);
        dropdown1.selectByVisibleText(rdFrequency);

        driver.findElement(By.xpath("//div[@class='paymentBlock']//div[contains(@class,'slds-size_1-of-2 slds-max')]//label[text()='" + filedRDStartDate + "']/..//input[@name='RDSdate']")).click();
        driver.findElement(By.xpath("//div[contains(@class,'slds-datepicker slds-drop')]//button[text()='" + RDStartDate + "']")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,150)");
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + filedDonationAmount + "']/..//input[@name='expenseclient']")).click();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + filedDonationAmount + "']/..//input[@name='expenseclient']")).sendKeys(donationAmount);

        driver.findElement(By.xpath("//label//span[text()='" + filedPayment + "']/../..//div[@class='slds-select_container']")).click();
        WebElement testDropDown3 = driver.findElement(By.xpath("//label//span[text()='" + filedPayment + "']/../..//select[@class='slds-select']"));
        Select dropdown3 = new Select(testDropDown3);
        dropdown3.selectByVisibleText(payment);
        waitfortheelement();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,250)");
        waitfortheelement();
    }

    public void enteredspiltdetails(WebDriver driver, String filedAppeal, String filedAmount, String appeal, String amount) {
        waitfortheelement();
        driver.findElement(By.xpath("(//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAppeal + "']/..//input)[2]")).click();
        driver.findElement(By.xpath("(//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAppeal + "']/..//input)[2]")).sendKeys(appeal);
        driver.findElement(By.xpath("(//ul[contains(@class,'slds-listbox slds-listbox')]//li[@class='slds-listbox__item']//span[@class='slds-media__body']//span[text()='" + appeal + "'])[2]")).click();


        driver.findElement(By.xpath("(//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAmount + "']/..//input)[2]")).click();
        driver.findElement(By.xpath("(//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAmount + "']/..//input)[2]")).clear();
        driver.findElement(By.xpath("(//div[@class='container-fluid cSplitPayment']//label[text()='" + filedAmount + "']/..//input)[2]")).sendKeys(amount);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,150)");
    }

    public void newEventDetails(WebDriver driver, String filedAssigne, String filedSubject, String filedPeople, String filedRelated, String Assignedto, String Subject, String People, String Related) {
        waitfortheelement();
//        driver.findElement(By.xpath("//lightning-icon[contains(@class,'lightning-primitive-icon')]")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//label[text()='Subject']/..//div//input[@class='slds-input slds-combobox__input']")).click();
        //WebElement element =
        driver.findElement(By.xpath("//label[text()='Subject']/..//div//input[@class='slds-input slds-combobox__input']/../..//span[@title='" + Subject + "']")).click();
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-form slds-form_stac')]//div[contains(@class,'uiInput forceSearchInputLookupDesktop')]//label//span[text()='" + filedRelated + "']/../..//input")).click();
        driver.findElement(By.xpath("//div[contains(@class,'slds-form slds-form_stac')]//div[contains(@class,'uiInput forceSearchInputLookupDesktop')]//label//span[text()='" + filedRelated + "']/../..//input")).sendKeys(Related);
        waitfortheelement();
        driver.findElement(By.xpath("//li[contains(@class,'lookup__item  default uiAutocomplete')]//div[@title='" + Related + "']")).click();
        driver.findElement(By.xpath("//li[@class='pillContainerListItem']//a//span[@class='pillText']")).click();

    }

    public void clickOnAllFolder(WebDriver driver, String fieldAllFolder, String Folder) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[contains(@class,'slds-nav-vertical__section navItems')]//h2[text()='" + Folder + "']/..//a[text()='" + fieldAllFolder + "']")).click();
    }

    public void clickOnFolder(WebDriver driver, String Name) {
        waitfortheelement();
        driver.findElement(By.xpath(".//div[@class='slds-scrollable_y']/../..//div[contains(@class,'slds-table_header-fixed_container')]//table//tbody//div[@class='slds-truncate']//a[text()='Key Supporter Reports']")).click();
    }

    public void provideRDSkipdetails(WebDriver driver, String SkipSource, String SkipReason, String StopRD, String RestartRD, String skipSource, String skipReason, String stopRD, String restartRD) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + SkipSource + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + SkipSource + "']/..//input/../..//lightning-base-combobox-item[@data-value='" + skipSource + "']")).click();

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + SkipReason + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + SkipReason + "']/..//input/../..//lightning-base-combobox-item[@data-value='" + skipReason + "']")).click();
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,200)");

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + StopRD + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value = '" + stopRD + "']")).click();

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + RestartRD + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value = '" + restartRD + "']")).click();
    }

    public void provideRDCanceldetails(WebDriver driver, String CancellationDate, String CancellationSource, String CancellationReason, String StopRD, String cancellationDate, String cancellationSource, String cancellationReason, String stopRD) {

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + CancellationDate + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value = '" + cancellationDate + "']")).click();

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + CancellationSource + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + CancellationSource + "']/..//input/../..//lightning-base-combobox-item[@data-value='" + cancellationSource + "']")).click();

        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,200)");

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + CancellationReason + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + CancellationReason + "']/..//input/../..//lightning-base-combobox-item[@data-value='" + cancellationReason + "']")).click();

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + StopRD + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value = '" + stopRD + "']")).click();
    }

    public void provideRDUpgradedetails(WebDriver driver, String Campaign, String DateStart, String DateEnd, String Amount, String Reason, String Source, String campaign, String dateStart, String dateEnd, String amount, String reason, String source) {
        waitfortheelement();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + Campaign + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='slds-grid']//label[text()='" + Campaign + "']/..//input")).sendKeys(campaign);
        driver.findElement(By.xpath("//div[contains(@class,'slds-listbox slds-listbox_vertical')]//span[@class='slds-media__body']//span//strong[text()='" + campaign + "']")).click();

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + DateStart + "']/..//input")).click();
        driver.findElement(By.xpath("//td[@data-value = '" + dateStart + "']")).click();

//        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + DateEnd + "']/..//input")).click();
//        driver.findElement(By.xpath("//td[@data-value = '" + dateEnd + "']")).click();

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + Amount + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + Amount + "']/..//input")).sendKeys(amount);

        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("scrollBy(0,250)");

        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + Reason + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + Reason + "']/..//input/../..//lightning-base-combobox-item[@data-value='" + reason + "']")).click();


        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + Source + "']/..//input")).click();
        driver.findElement(By.xpath("//div[@class='mainBlock']//div[@class='slds-grid']//label[text()='" + Source + "']/..//input/../..//lightning-base-combobox-item[@data-value='" + source + "']")).click();

    }
}

