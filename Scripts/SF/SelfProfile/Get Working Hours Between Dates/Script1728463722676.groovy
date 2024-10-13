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
import customUtilities.TimeDifference as TimeDifference
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import java.lang.Integer as Integer
import customUtilities.DateChecker as DateChecker
import customUtilities.TimeRangeCalculator as TimeRangeCalculator

KeywordLogger logger = new KeywordLogger()

if (!FullDay) {
	LeaveEndDate = LeaveStartDate
}

String[] arrLeaveStart = LeaveStartDate.split(' ')

String[] arrLeaveEnd = LeaveEndDate.split(' ')

def dateStart = arrLeaveStart[0]

def dateEnd = arrLeaveEnd[0]

def leaveDeductedinMin = '0'

def leaveDeductedinMinforTheDay = '0'

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

GlobalVariable.EmployeeManager = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Manager Name'))

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
    WebUI.takeFullPageScreenshot()

    dayType = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Day Type (var)', [
                ('date') : dateStart]), 'class')
	
	

    if (dayType == 'workingDay') {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern('d MMM yyyy')
		
		LocalDate startDate = LocalDate.parse(LeaveStartDate.trim(), formatter)
		if (DateChecker.checkPublicHoliday(currentCalDate.toString())) {
			logger.logFailed('Leave Date is a Public Holiday')
			
			assert false
		}
		
		if (!FullDay) {
			def workingTime = WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)',
				[('date') : dateStart]))
			String[] arrTime = workingTime.split(' ')
			GlobalVariable.LeaveStartTime = arrTime[0]
			GlobalVariable.LeaveEndTime = TimeRangeCalculator.calculateNewEndTime(workingTime)
			leaveDeductedinMin = TimeDifference.calculateTimeDifference(arrTime[0] + " – " +GlobalVariable.LeaveEndTime)
		}
		else {
			leaveDeductedinMin = TimeDifference.calculateTimeDifference(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)',
				[('date') : dateStart])))
		}
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

        dayType = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Day Type (var)', 
                [('date') : currentCalDate]), 'class')

        if (dayType == 'workingDay') {
            if (!DateChecker.checkPublicHoliday(currentDate.toString())) {
				leaveDeductedinMinforTheDay = String.valueOf(TimeDifference.calculateTimeDifference(WebUiBuiltInKeywords.getText(findTestObject(
					'Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)', [('date') : currentCalDate]))))

				leaveDeductedinMin = String.valueOf(Integer.valueOf(leaveDeductedinMin) + Integer.valueOf(leaveDeductedinMinforTheDay))
			}
        }
        
        if (currentDate.getDayOfWeek().getValue() == 7) {
            WebUI.takeFullPageScreenshot()

            WebUI.click(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'))

            WebUI.delay(1)
        }
        
        currentDate = currentDate.plusDays(1)
    }
}

GlobalVariable.workMinutes = leaveDeductedinMin

def totalMins = Integer.valueOf(leaveDeductedinMin)

int totminConvertedToHours = totalMins / 60

int totminConvertedToMin = totalMins % 60

if (totminConvertedToMin == 0) {
    GlobalVariable.LeaveDeducted = totminConvertedToHours + ' hours'
}
else if (totminConvertedToHours == 0) {
	GlobalVariable.LeaveDeducted = totminConvertedToMin + ' minutes'
}
else {
    GlobalVariable.LeaveDeducted = ((totminConvertedToHours + ' hours ') + totminConvertedToMin) + ' minutes'
}

logger.logInfo('Total working hours : ' + GlobalVariable.LeaveDeducted)

WebUI.click(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Popup Close Button'))

