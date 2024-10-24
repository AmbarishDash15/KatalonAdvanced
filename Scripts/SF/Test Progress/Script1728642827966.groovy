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
import customUtilities.LeaveDetails as LeaveDetails

WebUI.callTestCase(findTestCase('SF/Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : EmployeeID, ('employeeName') : EmployeeName], 
    FailureHandling.STOP_ON_FAILURE)

LeaveDetails.getLeaveDetails(LeaveType)

WebUI.callTestCase(findTestCase('SF/SelfProfile/Get Working Hours Between Dates'), [('WorkSchedule') : WorkSchedule, ('LeaveStartDate') : LeaveStartDate
        , ('LeaveEndDate') : LeaveEndDate, ('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName, ('FullDayOrHalfDay') : FullDayOrHalfDay], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Payslip/Generate Pay Slip'), [('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate
        , ('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Payslip/Verify Payslip'), [('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName
        , ('LeaveType') : LeaveType, ('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate], FailureHandling.STOP_ON_FAILURE)

