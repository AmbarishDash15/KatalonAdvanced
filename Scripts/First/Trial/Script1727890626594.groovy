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
import customUtilities.MultiplyTimePeriod as MultiplyTimePeriod
import customUtilities.TimeDifference as TimeDifference
import customUtilities.WorkingDaysCalculator as WorkingDaysCalculator
import customUtilities.TimeDifferenceChecker as TimeDifferenceChecker
import org.openqa.selenium.Keys as Keys
import java.lang.Integer as Integer

WebUI.openBrowser('')

WebUI.navigateToUrl('https://and0ewpth.accounts.cloud.sap/saml2/idp/sso?sp=https://www.successfactors.com/snowyhydroT1&idp=https://and0ewpth.accounts.ondemand.com')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Page_SuccessFactors - snowyhydroT1 Sign In/input_User Name_j_username'), 
    'adash')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_SuccessFactors - snowyhydroT1 Sign In/input_Password_j_password'), 
    'LHKz3RaeJz/y9i0fuiHHdA==')

WebUI.click(findTestObject('Object Repository/Page_SuccessFactors - snowyhydroT1 Sign In/div_Continue'))

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/button_ProfileButton'), 0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/button_ProfileButton'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/button_Proxy Now'), 0)

WebUI.click(findTestObject('Page_SuccessFactors Home/button_Proxy Now'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/input_UserName'), 0)

WebUI.setText(findTestObject('Page_SuccessFactors Home/input_UserName'), '166020')

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/ProxyPopupSearchResult'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/ProxyPopupSearchResult'), 'title', 'Ben Skewes', 
    0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/ProxyPopupSearchResult'))

WebUI.click(findTestObject('Page_SuccessFactors Home/ProxyPopup_OkButton'))

WebUI.delay(10)

WebUI.waitForPageLoad(15)

title = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/button_ProfileButton'), 'title')

WebUI.verifyMatch(title, '.*Ben Skewes.*', true)

WebUI.click(findTestObject('Page_SuccessFactors Home/tilebutton_Request Time Off'))

WebUI.delay(2)

WebUI.waitForElementNotPresent(findTestObject('Page_SuccessFactors Home/busyIndicator'), 0)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/dialogBox'), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/dialogHeader'), 'Request Time Off')

WebUI.takeFullPageScreenshot()

WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeType'), LeaveType, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeTypeOption', [('timeType') : LeaveType]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'), 10)

WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveStartDate'), LeaveStartDate)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'))

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'), 'value', 
    LeaveBalance, 0)

WorkingHours = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/plannedWorkingTime'), 
    'title')

WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveEndTime'), LeaveEndDate)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'))

if (NumberOfLeaveDays == '') {
    NumberOfLeaveDays = WorkingDaysCalculator.calculateWorkingDays(LeaveStartDate, LeaveEndDate)
}

LeaveDeducted = MultiplyTimePeriod.multiplyTimePeriod(TimeDifference.calculateTimeDifference(WorkingHours), NumberOfLeaveDays)

valueOnApp = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leavesToBeDeducted'), 'value')

if (TimeDifferenceChecker.checkTimeDifference(LeaveDeducted, valueOnApp)) {
    assert true
}

WebUI.takeFullPageScreenshot()

