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
import customUtilities.MultiplyTimePeriod as MultiplyTimePeriod
import customUtilities.TimeDifference as TimeDifference
import customUtilities.WorkingDaysCalculator as WorkingDaysCalculator
import customUtilities.TimeDifferenceChecker as TimeDifferenceChecker

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_Request Time Off'))

WebUI.delay(2)

WebUI.waitForElementNotPresent(findTestObject('Page_SuccessFactors Home/Homepage/busyIndicator'), 0)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/dialogBox'), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/dialogHeader'), 'Request Time Off')

WebUI.takeFullPageScreenshot()

WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeType'), LeaveType, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeTypeOption (var)', [('timeType') : LeaveType]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'), 10)

WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveStartDate'), LeaveStartDate)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'))

WorkingHours = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/plannedWorkingTime'), 
    'title')

WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveEndTime'), LeaveEndDate)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'))

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'), 'value', 
    GlobalVariable.LeaveBalance, 0)

if (NumberOfLeaveDays == '') {
    NumberOfLeaveDays = WorkingDaysCalculator.calculateWorkingDays(LeaveStartDate, LeaveEndDate)
}



valueOnApp = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leavesToBeDeducted'), 'value')

if (TimeDifferenceChecker.checkTimeDifference(GlobalVariable.LeaveDeducted, valueOnApp)) {
    assert true
}

WebUI.takeFullPageScreenshot()

GlobalVariable.LeaveDeducted = valueOnApp

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/submitButton'))

WebUI.delay(2)

WebUI.takeFullPageScreenshot()

