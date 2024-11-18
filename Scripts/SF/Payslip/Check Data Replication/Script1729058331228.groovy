import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import customUtilities.TimeChecker as TimeChecker
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import customUtilities.reusableFunctions as reusableFunctions

KeywordLogger logger = new KeywordLogger()

def refreshCounter = 0

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), 10)

reusableFunctions.setTextinElement(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), 'Data Replication Monitor')

WebUI.delay(1)

WebUI.sendKeys(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), Keys.chord(Keys.ENTER))

WebUI.delay(2)

WebUI.waitForPageLoad(10)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Page Header'), 10)

reusableFunctions.setTextinElement(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Input - Employee ID'), 
    EmployeeID)

WebUI.delay(2)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Input Selection Employee Name'), 
    10)

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Input Selection Employee Name'))

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status Dropdown Icon'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status - Successful - Checkbox'), 
    10)

if (WebUiBuiltInKeywords.getAttribute(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status - Successful - Checkbox'), 
    'aria-selected') == 'false') {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status - Successful - Checkbox'))
}

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Go Button'))

WebUI.delay(2)

while (refreshCounter < 5) {
    try {
        if (WebUiBuiltInKeywords.findWebElement(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Employee Absence Data'), 
            10)) {
            break
        }
    }
    catch (Exception E) {
        while (waitCounter <= 5) {
            try {
                if (WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Section'), 
                    10)) { 
                        break
                    }
            }
            catch (Exception E1) {
                WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Expand Search Section'))
            } 
        }
        
        reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Go Button'))

        WebUI.waitForPageLoad(0)
    } 
}

if (refreshCounter >= 50) {
    logger.FAILED('Data Replication timed out')

    assert false
}

reusableFunctions.verifyElementText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Employee Name'), 
    EmployeeName)

refreshCounter = 0

while (!(TimeChecker.isWithinFiveMinutesPast(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Last Replicated Time'))))) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Go Button'))

    WebUI.waitForPageLoad(0)

    WebUI.delay(2)

    refreshCounter = (refreshCounter + 1)

    if (refreshCounter == 10) {
        reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result Row Checkbox'))
		reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Reprocess Button'))
    }
    
    if (refreshCounter == 50) {
        logger.FAILED('Data Replication timed out')

        assert false

        break
    }
}

reusableFunctions.verifyElementText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Status'), 
    'Successful')

WebUI.takeFullPageScreenshot()

