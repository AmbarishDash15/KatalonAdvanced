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

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : ApproverName, ('employeeName') : ApproverName], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Approvals Label'), 0)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Homepage/Approvals Label'), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Approval Card TimeOff - Home Page'), 0)

def List<WebElement> ViewAll = WebUI.findWebElements(findTestObject('Page_SuccessFactors Home/Homepage/Approval Time Off View All'), 0)

if (!ViewAll.length == null) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/Approval Time Off View All'))

    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/All Approval Card Popup Header'), 
        0)

    def List<WebElement> Cards = WebUI.findWebElements(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Individual Card'), 
        0)

    for (WebElement indCard : Cards) {
        def cardContentID = indCard.getAttribute('id')

        def cardID = cardContentID.split('-cardContent')[0]

        logger.logInfo(cardID)

        if (((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                [('cardID') : cardID])) == EmployeeName) && (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                [('fieldName') : 'Period', ('cardID') : cardID])) == (LeaveStartDate + (' - ' + LeaveEndDate)))) && 
        ((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                [('fieldName') : 'Time Type', ('cardID') : cardID])) == LeaveType) && (WebUiBuiltInKeywords.getText(
            findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Details Field Value (var)', 
                [('fieldName') : 'Duration', ('cardID') : cardID])) == GlobalVariable.LeaveDeducted))) {
            WebUI.takeFullPageScreenshot()

            WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/All Time Off Approval Popup/Approval Popup Card Initiator', 
                    [('cardID') : cardID]))
        }
    }
}

else {
    WebUI.takeFullPageScreenshot()

    WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/Approval Card TimeOff - Home Page'))

}
/*
WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Homepage/Approval Card Initiator'), 'title', 
    EmployeeName, 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Aprroval Card Details Field Name (var)', [('fieldName') : 'Period']), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Homepage/Approval Card Details Field Value (var)', 
        [('fieldName') : 'Period']), 'title', LeaveStartDate + (' - ' + LeaveEndDate), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Aprroval Card Details Field Name (var)', [('fieldName') : 'Time Type']), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Homepage/Approval Card Details Field Value (var)', 
        [('fieldName') : 'Time Type']), 'title', LeaveType, 0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/Approval Card TimeOff - Home Page'))
*/
WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/ApprovalQuestionBanner'), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'User']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Name Value (var)', [('fieldName') : 'User']), 
    EmployeeName)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Time Type']), 0)

leaveType = WebUI.getText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Time Type Value (var)', 
        [('fieldName') : 'Time Type']), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(leaveType, LeaveType + '.*', true)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Start Date']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithQuestionMark Value (var)', 
        [('fieldName') : 'Start Date']), DateConverter.convertDateFormat(LeaveStartDate))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'End Date']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithQuestionMark Value (var)', 
        [('fieldName') : 'End Date']), customUtilities.DateConverter.convertDateFormat(LeaveEndDate))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Time Off Used']), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithOutQuestionMark Value (var)', 
        [('fieldName') : 'Time Off Used']), GlobalVariable.LeaveDeducted)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Field Name (var)', 
        [('fieldName') : 'Balance as of ' + customUtilities.DateConverter.convertDateFormat(LeaveEndDate)]), 0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request FieldWithOutQuestionMark Value (var)', 
        [('fieldName') : 'Balance as of ' + customUtilities.DateConverter.convertDateFormat(LeaveEndDate)]), TimeSubtractor.subtractTime(
        GlobalVariable.EndDateLeaveBalance , GlobalVariable.LeaveDeducted))

GlobalVariable.RemainingLeaveBalance = TimeSubtractor.subtractTime(GlobalVariable.EndDateLeaveBalance, GlobalVariable.LeaveDeducted)

WebUI.takeFullPageScreenshot()

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approval Request Time Calendar Banner'), 
    0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/WorkFlowDetails/Approve Button'))

WebUI.delay(1)

WebUI.takeFullPageScreenshot()

WebUI.waitForPageLoad(0, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Page_SuccessFactors Home/Homepage/Approvals Label'), 0)

