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
import customUtilities.DateConverter as DateConverter
import customUtilities.TimeSubtractor as TimeSubtractor
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import customUtilities.reusableFunctions as reusableFunctions

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : ApproverName, ('employeeName') : ApproverName], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Approvals Label'), 10)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Homepage/Approvals Label'), 10)

int retryCount = 0

while (retryCount <= 5) {
    try {
        if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Approval Time Off View All'), 
            10)) {
            reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Homepage/Approval Time Off View All'))

            WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/All Approval Card Popup Header'), 
                0)

            List<WebElement> Cards = WebUI.findWebElements(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Individual Card'), 
                0)

            for (WebElement indCard : Cards) {
                def cardContentID = indCard.getAttribute('id')

                def cardID = cardContentID.split('-cardContent')[0]

                if (((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                        [('cardID') : cardID])) == EmployeeName) && (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                        [('fieldName') : 'Period', ('cardID') : cardID])) == (LeaveStartDate + (' - ' + LeaveEndDate)))) && 
                ((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                        [('fieldName') : 'Time Type', ('cardID') : cardID])) == LeaveType) && (WebUiBuiltInKeywords.getText(
                    findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                        [('fieldName') : 'Duration', ('cardID') : cardID])) == GlobalVariable.LeaveDeducted))) {
                    WebUI.takeFullPageScreenshot()

                    if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                            [('cardID') : cardID]), 10)) {
                        reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                                [('cardID') : cardID]))

                        retryCount = 5

                        break
                    }
                }
            }
        }
    }
    catch (Exception e) {
        retryCount++

        WebUI.delay(1)
    } 
}

KeywordUtil.logInfo('Verifying Leave Approval Request at Approver : ' + ApproverName)

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/ApprovalQuestionBanner'), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'User']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Name Value (var)', [('fieldName') : 'User']), 
    EmployeeName)

KeywordUtil.markPassed('Verified User Name as : ' + EmployeeName)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Time Type']), 0)

leaveType = WebUI.getText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Time Type Value (var)', 
        [('fieldName') : 'Time Type']), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(leaveType, LeaveType + '.*', true)

KeywordUtil.markPassed('Verified Leave Type as : ' + LeaveType)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Start Date']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithQuestionMark Value (var)', 
        [('fieldName') : 'Start Date']), DateConverter.convertDateFormat(LeaveStartDate))

KeywordUtil.markPassed('Verified Start Date as : ' + DateConverter.convertDateFormat(LeaveStartDate))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'End Date']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithQuestionMark Value (var)', 
        [('fieldName') : 'End Date']), customUtilities.DateConverter.convertDateFormat(LeaveEndDate))

KeywordUtil.markPassed('Verified End Date as : ' + DateConverter.convertDateFormat(LeaveEndDate))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Time Off Used']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithOutQuestionMark Value (var)', 
        [('fieldName') : 'Time Off Used']), GlobalVariable.LeaveDeducted)

KeywordUtil.markPassed('Verified Time Off Used as : ' + GlobalVariable.LeaveDeducted)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Balance as of ' + customUtilities.DateConverter.convertDateFormat(LeaveEndDate)]), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithOutQuestionMark Value (var)', 
        [('fieldName') : 'Balance as of ' + customUtilities.DateConverter.convertDateFormat(LeaveEndDate)]), TimeSubtractor.subtractTime(
        GlobalVariable.EndDateLeaveBalance, GlobalVariable.LeaveDeducted))

KeywordUtil.markPassed((('Verified Balance as of ' + customUtilities.DateConverter.convertDateFormat(LeaveEndDate)) + ' as : ') + 
    TimeSubtractor.subtractTime(GlobalVariable.EndDateLeaveBalance, GlobalVariable.LeaveDeducted))

GlobalVariable.RemainingLeaveBalance = TimeSubtractor.subtractTime(GlobalVariable.EndDateLeaveBalance, GlobalVariable.LeaveDeducted)

WebUI.takeFullPageScreenshot()

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Time Calendar Banner'), 
    0)

WebUI.takeFullPageScreenshot()

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approve Button'), 
    10)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approve Button'))
}

KeywordUtil.markPassed('Leave Approved by : ' + ApproverName)

WebUI.delay(1)

WebUI.takeFullPageScreenshot()

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

