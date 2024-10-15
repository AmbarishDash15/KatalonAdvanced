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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import customUtilities.DateRangeFormatter as DateRangeFormatter
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.time.temporal.ChronoUnit as ChronoUnit
import customUtilities.DateRangeChecker as DateRangeChecker
import customUtilities.LeaveTypeOnPayslip as LeaveTypeOnPayslip
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
KeywordLogger logger = new KeywordLogger()

def textToVerify = ''

textToVerify = LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType).length() > 20 ? LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType).substring(0, 20) : LeaveTypeOnPayslip.getLeaveTypeonPayslip(LeaveType)
logger.logInfo('Verified Leave Type as ' + textToVerify)
