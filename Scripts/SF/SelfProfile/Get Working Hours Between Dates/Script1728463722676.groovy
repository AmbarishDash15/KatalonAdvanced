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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.time.temporal.ChronoUnit as ChronoUnit
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import customUtilities.DateRangeChecker as DateRangeChecker
import customUtilities.TimeConverter as TimeConverter
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

WebUI.callTestCase(findTestCase('SF/Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeeIDtoProxy') : '174159', ('employeeNameToProxy') : 'Nikki Scholes'], 
    FailureHandling.STOP_ON_FAILURE)

KeywordLogger logger = new KeywordLogger()

String[] arrLeaveStart = LeaveStartDate.split(' ')

String[] arrLeaveEnd = LeaveEndDate.split(' ')

def dateStart = arrLeaveStart[0]

def dateEnd = arrLeaveEnd[0]

def leaveDeducted = '0'

def currentCalDate = '0'

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_View My Profile'))

WebUI.waitForPageLoad(0)

WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/SectionTabName (var)', [('tabName') : 'Employment Information']))

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Section Show More button'), 
    0)) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Section Show More button'))
}

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Job Information -Work Schedule label'), 0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Work Schedule details link'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Header'), 0)

def scheduleName = WebUI.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Work Schedule Name'))

WebUI.verifyMatch(scheduleName, WorkSchedule + '.*', true)

WebUI.click(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Preview tab'))

while (!(DateRangeChecker.isDateInValueRange(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Selected Week')), 
    LeaveStartDate))) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'))
}

if (LeaveStartDate == LeaveEndDate) {
    dayType = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Day Type (var)', [
                ('date') : dateStart]), 'class')

    if (dayType == 'workingDay') {
        String[] tempArr = WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)', 
                [('date') : dateStart])).split('_')

        leaveDeducted = TimeConverter.convertDecimalToHoursAndMinutes(tempArr[0])
    } else {
        logger.logFailed('Leave Date is a Non-Working Day')

        assert false
    }
} else {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern('d MMM yyyy')

    LocalDate startDate = LocalDate.parse(LeaveStartDate.trim(), formatter)

    LocalDate endDate = LocalDate.parse(LeaveEndDate.trim(), formatter)

    LocalDate currentDate = startDate

    while (!(currentDate.isAfter(endDate))) {
        currentCalDate = currentDate.getDayOfMonth().toString()

        logger.logInfo(currentCalDate)

        dayType = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Day Type (var)', 
                [('date') : currentCalDate]), 'class')

        if (dayType == 'workingDay') {
            String[] tempArr = WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)', 
                    [('date') : currentCalDate])).split('_')

            leaveDeducted = (leaveDeducted.toDouble() + (tempArr[0]).toDouble()).toString()
        }
        
        if (currentDate.getDayOfWeek().getValue() == 7) {
            WebUI.click(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'))

            WebUI.delay(1)
        }
        
        currentDate = currentDate.plusDays(1)
    }
}

GlobalVariable.LeaveDeducted = TimeConverter.convertDecimalToHoursAndMinutes(leaveDeducted)

logger.logInfo("Total working hours : " + leaveDeducted)

