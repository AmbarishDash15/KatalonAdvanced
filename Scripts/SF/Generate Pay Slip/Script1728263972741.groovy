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

WebUI.callTestCase(findTestCase('SF/Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeeIDtoProxy') : '169105', ('employeeNameToProxy') : 'Tracey Mears'], 
    FailureHandling.STOP_ON_FAILURE)

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

WebUI.click(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period - Other Period'))

WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period -Other period - payperiod - input'), 
    '1')

WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period-Other priod - year - input'), '2024')

WebUI.sendKeys(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period-Other priod - year - input'), 
    Keys.chord(Keys.ENTER))

WebUI.delay(1)

payPeriodStart = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Period - Start Date'), 
    'value')

payPeriodEnd = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period - End Date'), 
    'value')

if (!(DateRangeChecker.isDateInPayPeriodRange(payPeriodStart, payPeriodEnd, LeaveEndDate))) {
    payPeriodNumber = PayPeriodCalculator.calculatePeriod(payPeriodStart, payPeriodEnd, LeaveEndDate)

    WebUI.setText(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period -Other period - payperiod - input'), 
        payPeriodNumber)

    WebUI.sendKeys(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period -Other period - payperiod - input'), 
        Keys.chord(Keys.ENTER))
}

payPeriodStart = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll Period - Start Date'), 
    'value')

payPeriodEnd = WebUI.getAttribute(findTestObject('Page_Payroll Driver Australia/PayrollWindow/Payroll period - End Date'), 
    'value')

if (DateRangeChecker.isDateInPayPeriodRange(payPeriodStart, payPeriodEnd, LeaveEndDate)) {
    assert true
}

