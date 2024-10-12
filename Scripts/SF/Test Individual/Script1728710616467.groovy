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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

KeywordLogger logger = new KeywordLogger()

WebUI.callTestCase(findTestCase('SF/Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : 'Bronwyn Burbury', ('employeeName') : 'Bronwyn Burbury'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Homepage/Approvals Label'), 0)

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Approval Time Off View All'), 
    0)) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/Approval Time Off View All'))

    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/All Approval Card Popup Header'), 
        0)

    List<WebElement> Cards = WebUI.findWebElements(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Individual Card'), 
        0)

    for (WebElement indCard : Cards) {
        def cardContentID = indCard.getAttribute('id')

        def cardID = cardContentID.split('-cardContent')[0]

        logger.logInfo(cardID)

        if (((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                [('cardID') : cardID])) == 'Ben Kurnof') && (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                [('fieldName') : 'Period', ('cardID') : cardID])) == '3 Sept 2024 - 18 Sept 2024')) && ((WebUiBuiltInKeywords.getText(
            findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                [('fieldName') : 'Time Type', ('cardID') : cardID])) == 'Leave Without Pay') && (WebUiBuiltInKeywords.getText(findTestObject(
                'Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                [('fieldName') : 'Duration', ('cardID') : cardID])) == '90 hours'))) {
            WebUI.takeFullPageScreenshot()

            WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                    [('cardID') : cardID]))
        }
    }
}

