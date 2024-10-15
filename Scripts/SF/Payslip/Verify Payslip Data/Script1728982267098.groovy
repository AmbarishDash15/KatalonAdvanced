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
import customUtilities.LeaveTypeOnPayslip as LeaveTypeOnPayslip
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
KeywordLogger logger = new KeywordLogger()

def textToVerify = ''

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/Print Preview title'), 0)

WebUI.click(findTestObject('Page_Print Preview/Last Document button'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfElement'), 0)

textToVerify = ('00' + EmployeeID)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfText', [('pdfText') : textToVerify]), 0)

logger.logInfo('Verified Employee ID as ' + textToVerify)

textToVerify = LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType).length() > 20 ? LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType).substring(0, 20) : LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfText', [('pdfText') : textToVerify]), 0)

logger.logInfo('Verified Leave Type as ' + textToVerify)
/*
textToVerify = customUtilities.DateFormatConverter.convertDateFormat(LeaveStartDate)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfText', [('pdfText') : textToVerify]), 0)

logger.logInfo('Verified Leave Start Date as ' + textToVerify)

textToVerify = customUtilities.DateFormatConverter.convertDateFormat(LeaveEndDate)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfText', [('pdfText') : textToVerify]), 0)

logger.logInfo('Verified Leave End Date as ' + textToVerify)

textToVerify = customUtilities.TimeConverter.convertToDecimalHoursWhole(GlobalVariable.LeaveDeducted)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfText', [('pdfText') : textToVerify]), 0)

logger.logInfo('Verified Earnings Leave Hours as ' + textToVerify)

textToVerify = customUtilities.TimeConverter.convertToDecimalHours2Decimal(GlobalVariable.LeaveDeducted)

WebUI.verifyElementPresent(findTestObject('Page_Print Preview/pdfText', [('pdfText') : textToVerify]), 0)

logger.logInfo('Verified Absences Leave Hours as ' + textToVerify)
*/
WebUI.takeFullPageScreenshot()

