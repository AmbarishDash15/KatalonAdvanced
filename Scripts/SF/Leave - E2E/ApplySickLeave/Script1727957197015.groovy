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

WebUI.callTestCase(findTestCase('SF/Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeeIDtoProxy') : EmployeeID, ('employeeNameToProxy') : EmployeeName], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/SelfProfile/CheckSelfProfile - Time'), [('EmployeeName') : EmployeeName, ('EmployeeID') : EmployeeID
        , ('LeaveStartDate') : LeaveStartDate, ('LeaveType') : LeaveType, ('LeaveBalance') : ''], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/ApplyLeave/ApplyLeave - calculated in hours'), [('LeaveType') : LeaveType, ('LeaveStartDate') : LeaveStartDate
        , ('LeaveEndDate') : LeaveEndDate, ('LeaveBalance') : '', ('LeaveDeducted') : '', ('FullDay') : true, ('WorkingHours') : ''
        , ('StartTime') : '', ('EndTime') : '', ('NumberOfLeaveDays') : ''], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Approval/Approve Leave'), [('ApproverID') : ApproverID, ('ApproverName') : ApproverName
        , ('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName, ('LeaveType') : LeaveType, ('LeaveStartDate') : LeaveStartDate
        , ('LeaveEndDate') : LeaveEndDate], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/ApplyLeave/Check Team Absence by Manager'), [('EmployeeName') : EmployeeName, ('LeaveType') : LeaveType
        , ('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeeIDtoProxy') : EmployeeID, ('employeeNameToProxy') : EmployeeName], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/ApplyLeave/Verify Approved Leave on Self Profile'), [('EmployeeName') : EmployeeName
        , ('EmployeeID') : EmployeeID, ('LeaveStartDate') : LeaveStartDate, ('LeaveType') : LeaveType, ('LeaveEndDate') : LeaveEndDate], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Payslip/Generate Pay Slip'), [('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate
        , ('EmployeeID') : EmployeeID, ('textToVerify') : '', ('LeaveType') : LeaveType, ('LeaveDeducted') : GlobalVariable.LeaveDeducted], 
    FailureHandling.STOP_ON_FAILURE)

