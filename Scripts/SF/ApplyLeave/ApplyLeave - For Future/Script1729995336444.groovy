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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_Request Time Off'))

WebUI.waitForElementNotPresent(findTestObject('Page_SuccessFactors Home/Homepage/busyIndicator'), 5)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/dialogBox'), 0)

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/dialogHeader'), 'Request Time Off')

WebUI.takeFullPageScreenshot()

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeType'), 0)

attempts = 0

while (attempts < 5) {
    try {
        WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeType'), LeaveType)

        break
    }
    catch (Exception e) {
        attempts++

        WebUI.delay(1)
    } 
}

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/timeTypeOption (var)', [('timeType') : LeaveType]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Returning to Work on'))

if (LeaveType == 'Compassionate Leave') {
    WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/compassionateLeaveReason'), 0)

    def reasonString = ''

    switch (LeaveReason) {
        case 'Death':
            reasonString = 'Death (Death)'

            break
        case 'Illness':
            reasonString = 'Illness / Injury (Il/INJ)'

            break
        case 'Injury':
            reasonString = 'Illness / Injury (Il/INJ)'

            break
        case 'Non-Immediate Family Member':
            reasonString = 'Non-Immediate Family Member (Non-ImmFM)'

            break
        default:
            reasonString = 'Others (OTH)'

            break
    }
    
    attempts = 0

    while (attempts < 5) {
        try {
            WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/compassionateLeaveReason'), LeaveReason)

            break
        }
        catch (Exception e) {
            attempts++

            WebUI.delay(1)
        } 
    }
    
    WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Reason Code Options (var)', [('reasonCode') : reasonString]))
}

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveStartDate'), 0)

attempts = 0

while (attempts < 5) {
    try {
        WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveStartDate'), LeaveStartDate)

        break
    }
    catch (Exception e) {
        attempts++

        WebUI.delay(1)
    } 
}

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Returning to Work on'))

if (FullDayOrHalfDay == 'FullDay') {
    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveEndDate'), 0)

    attempts = 0

    while (attempts < 5) {
        try {
            WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveEndDate'), LeaveEndDate)

            break
        }
        catch (Exception e) {
            attempts++

            WebUI.delay(1)
        } 
    }
    
    WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Returning to Work on'))

    WebUI.delay(2)
} else if (FullDayOrHalfDay == 'HalfDay') {
    if (WebUiBuiltInKeywords.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/fullDayChkBx'), 
        'aria-checked', 'true', 0)) {
        WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/fullDayChkBx'), 0)

        WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/fullDayChkBx'))

        WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveStartTime'), 0)

        WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveStartTime'), GlobalVariable.LeaveStartTime)

        WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveEndTime'), 0)

        WebUI.setText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leaveEndTime'), GlobalVariable.LeaveEndTime)
    }
}

if (GlobalVariable.leaveBalanceCheckRequired == 'Yes') {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'))

    WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/availableBalance'), 
        'value', GlobalVariable.LeaveBalance, 0)

    KeywordUtil.markPassed((('Verified Leave Balance of Leave Type - ' + LeaveType) + ' is : ') + GlobalVariable.LeaveBalance)
}

valueOnApp = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/leavesToBeDeducted'), 'value')

WebUI.verifyMatch(valueOnApp, GlobalVariable.LeaveDeducted, false)

KeywordUtil.markPassed('Verified Leave to be Deducted is : ' + GlobalVariable.LeaveDeducted)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/submitButton'))

KeywordUtil.markPassed('Leave Applied')

WebUI.delay(2)

WebUI.takeFullPageScreenshot()

