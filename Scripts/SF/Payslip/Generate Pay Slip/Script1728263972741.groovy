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
import customUtilities.PayPeriodCalculator as PayPeriodCalculator
import customUtilities.DateRangeChecker as DateRangeChecker
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import customUtilities.DateFormatConverter as DateFormatConverter
import customUtilities.TimeConverter as TimeConverter
import customUtilities.GetNameFromDesignation as GetNameFromDesignation
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

def textToVerify = ''

def payrollAdmin = GetNameFromDesignation.getApproverName('Payroll Admin')

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : payrollAdmin, ('employeeName') : payrollAdmin], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Payslip/Check Data Replication'), [('EmployeeName') : EmployeeName, ('EmployeeID') : EmployeeID], 
    FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('SF/Payslip/Check Data Replication'), [('EmployeeName') : EmployeeName, ('EmployeeID') : EmployeeID], 
//    FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tileButton_Payroll'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Payroll Popup Header'), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/Payroll Popup - Payroll Simulation link'), 
    0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/Payroll Popup - Payroll Simulation link'))

WebUI.delay(5)

WebUI.switchToWindowTitle('Payroll Driver Australia')

WebUI.verifyElementPresent(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Window Header'), 0)

WebUI.verifyElementPresent(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period Label'), 0)

WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Area Time Period - input'), 'F1')

KeywordUtil.logInfo('Entered F1 as Payroll Area Time Period')

WebUI.click(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period - Other Period'))

WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period -Other period - payperiod - input'), 
    '1')

WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period-Other priod - year - input'), '2024')

WebUI.sendKeys(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period-Other priod - year - input'), 
    Keys.chord(Keys.ENTER))

WebUI.delay(2)

GlobalVariable.payPeriod1StartDate = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Period - Start Date'), 
    'value')

GlobalVariable.payPeriod1EndDate = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period - End Date'), 
    'value')

if (!(DateRangeChecker.isDateInPayPeriodRange(GlobalVariable.payPeriod1StartDate, GlobalVariable.payPeriod1EndDate, LeaveEndDate))) {
    GlobalVariable.leaveEndPayPeriod = PayPeriodCalculator.calculatePeriod(GlobalVariable.payPeriod1StartDate, GlobalVariable.payPeriod1EndDate, 
        LeaveEndDate)

    GlobalVariable.leaveStartPayPeriod = PayPeriodCalculator.calculatePeriod(GlobalVariable.payPeriod1StartDate, GlobalVariable.payPeriod1EndDate, 
        LeaveStartDate)

    KeywordUtil.logInfo('Calculated Leave End Pay Period as : ' + GlobalVariable.leaveEndPayPeriod)

    WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period -Other period - payperiod - input'), 
        GlobalVariable.leaveEndPayPeriod)

    WebUI.sendKeys(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period -Other period - payperiod - input'), 
        Keys.chord(Keys.ENTER))

    KeywordUtil.logInfo('Entered Other Pay Period as : ' + GlobalVariable.leaveEndPayPeriod)

    WebUI.delay(2)
}

payPeriodStart = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Period - Start Date'), 
    'value')

payPeriodEnd = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period - End Date'), 
    'value')

if (DateRangeChecker.isDateInPayPeriodRange(payPeriodStart, payPeriodEnd, LeaveEndDate)) {
    WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Selection - Personnel Number - input'), EmployeeID)

    KeywordUtil.logInfo('Entered Personnel number as : ' + EmployeeID)

    WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Selection - Payroll area - input'), 'F1')

    KeywordUtil.logInfo('Entered F1 as Selection - Payroll area')

    WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/General program control - Schema - input'), 
        'ZQ01')

    KeywordUtil.logInfo('Entered ZQ01 as General program control - Schema')

    WebUI.scrollToElement(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Remuneration - HR form name - option'), 
        0)

    if (!(WebUiBuiltInKeywords.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Remuneration - HR form name - option'), 
        'ti') == 0)) {
        WebUI.check(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Remuneration - HR form name - option'))
    }
    
    WebUI.delay(1)

    WebUI.verifyElementAttributeValue(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Remuneration - HR form name - input'), 
        'ti', '0', 0)

    WebUI.verifyElementClickable(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Remuneration - HR form name - input'))

    WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Remuneration - HR form name - input'), 'ZHR_PAYSLIP_NEW')

    KeywordUtil.logInfo('Entered ZHR_PAYSLIP_NEW as Remuneration - HR form name')

    WebUI.scrollToElement(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Area Time Period - input'), 
        0)

    WebUI.takeFullPageScreenshot()

    WebUI.verifyElementPresent(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Execute Button'), 0)

    WebUI.click(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Execute Button'))

    KeywordUtil.logInfo('Executed Payroll')

    WebUI.delay(2)
}

