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
import customUtilities.DateRangeChecker as DateRangeChecker
import com.kms.katalon.core.webui.keyword.builtin.GetTextKeyword as GetTextKeyword
import java.lang.String as String
import customUtilities.DateRangeFormatter as DateRangeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : GlobalVariable.EmployeeManager, ('employeeName') : GlobalVariable.EmployeeManager], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/tileButton_View Team Absences'), 0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tileButton_View Team Absences'))

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/panelheader_My Reporting Hierarchy'), 
    0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_My Drirect Reports tab'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_Half Month tab'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/My Reporting Hierarchy - EmployeeName (var)', 
        [('employeeName') : EmployeeName]), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Reporting Hierarchy_Checkbox (var)', 
        [('employeeName') : EmployeeName]), 0)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Reporting Hierarchy_Checkbox (var)', 
        [('employeeName') : EmployeeName]), 0)

WebUI.check(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Reporting Hierarchy_Checkbox (var)', [('employeeName') : EmployeeName]))

WebUI.delay(1)

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Reporting Hierarchy_Checkbox (var)', 
        [('employeeName') : EmployeeName]), 'aria-checked', 'true', 0)

while (!(DateRangeChecker.isDateInRange(LeaveStartDate, WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_Date Range'))))) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_Calendar Forward button'))
}

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_LeaveTitle'), 
    0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_LeaveTitle'), 
    LeaveType)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_LeaveDate'), 
    0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Team Absence Calendar/Team Absence Calendar_LeaveDate'), 
    DateRangeFormatter.formatDateRangeTeamAbsence(LeaveStartDate, LeaveEndDate))

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

