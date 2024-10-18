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

KeywordLogger logger = new KeywordLogger()

def refreshCounter = 0

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), 0)

WebUI.setText(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), 'Data Replication Monitor')

WebUI.sendKeys(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), Keys.chord(Keys.ENTER))

WebUI.sendKeys(findTestObject('Page_SuccessFactors Home/TitleBar/Search Input'), Keys.chord(Keys.ENTER))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Page Header'), 0)

WebUI.setText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Input - Employee ID'), EmployeeID)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Input Selection Employee Name'), 
    0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Input Selection Employee Name'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status Dropdown Icon'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status - Successful - Checkbox'), 
    0)

if (WebUiBuiltInKeywords.getAttribute(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status - Successful - Checkbox'), 
    'aria-selected') == 'false') {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Status - Successful - Checkbox'))
}

WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Go Button'))

while (refreshCounter < 6) {
    try {
        if (WebUiBuiltInKeywords.findWebElement(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Employee Absence Data'), 
            10)) {
            break
        }
    }
    catch (Exception E) {
        WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Go Button'))

        WebUI.waitForPageLoad(0)

        refreshCounter = (refreshCounter + 1)
    } 
}

if (refreshCounter == 6) {
    logger.FAILED('Data Replication timed out')

    assert false
}

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Employee Name'), 
    EmployeeName)

refreshCounter = 0

while (!(TimeChecker.isWithinTenMinutesPast(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Last Replicated Time'))))) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Go Button'))

    WebUI.waitForPageLoad(0)

    WebUI.delay(2)

    refreshCounter = (refreshCounter + 1)

    if (refreshCounter == 30) {
        logger.FAILED('Data Replication timed out')

        assert false

        break
    }
}

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Data Replication Monitor/Search Result - Status'), 'Successful')

WebUI.takeFullPageScreenshot()

