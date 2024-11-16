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

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : EmployeeID, ('employeeName') : EmployeeName], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/SelfProfile/Get Leave Details and Duration'), [('WorkSchedule') : WorkSchedule, ('LeaveStartDate') : LeaveStartDate
        , ('LeaveEndDate') : LeaveEndDate, ('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName, ('FullDayOrHalfDay') : FullDayOrHalfDay
        , ('LeaveType') : LeaveType], FailureHandling.STOP_ON_FAILURE)

if (GlobalVariable.leaveBalanceCheckRequired == 'Yes') {
    WebUI.callTestCase(findTestCase('SF/SelfProfile/CheckSelfProfile - Time'), [('EmployeeName') : EmployeeName, ('EmployeeID') : EmployeeID
            , ('LeaveStartDate') : LeaveStartDate, ('LeaveType') : LeaveType, ('LeaveEndDate') : LeaveEndDate], FailureHandling.STOP_ON_FAILURE)
}

if (Initiator == 'Employee') {
    WebUI.callTestCase(findTestCase('SF/ApplyLeave/ApplyLeave - For Future'), [('LeaveType') : LeaveType, ('LeaveStartDate') : LeaveStartDate
            , ('LeaveEndDate') : LeaveEndDate, ('FullDayOrHalfDay') : FullDayOrHalfDay, ('LeaveReason') : LeaveReason], 
        FailureHandling.STOP_ON_FAILURE)
}

while (GlobalVariable.LeaveStatus != 'Approved') {
    WebUI.callTestCase(findTestCase('SF/Approval/Approve Leave by gettting Leave Status and Approver'), [('LeaveType') : LeaveType
            , ('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate, ('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName], 
        FailureHandling.STOP_ON_FAILURE)
}

WebUI.callTestCase(findTestCase('SF/ApplyLeave/Verify Approved Leave on Self Profile'), [('EmployeeName') : EmployeeName
        , ('EmployeeID') : EmployeeID, ('LeaveStartDate') : LeaveStartDate, ('LeaveType') : LeaveType, ('LeaveEndDate') : LeaveEndDate], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Payslip/Generate Pay Slip'), [('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate
        , ('EmployeeID') : EmployeeID, ('EmployeeName') : EmployeeName], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Payslip/Verify Payslip'), [('EmployeeID') : EmployeeID], FailureHandling.STOP_ON_FAILURE)

