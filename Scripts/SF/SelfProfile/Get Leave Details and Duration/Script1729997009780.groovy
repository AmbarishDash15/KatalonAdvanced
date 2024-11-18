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
import customUtilities.PublicHolidays as PublicHolidays
import customUtilities.LeaveDetails as LeaveDetails
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import customUtilities.reusableFunctions as reusableFunctions

LeaveDetails.getLeaveDetails(LeaveType)

if (FullDayOrHalfDay == 'HalfDay') {
    LeaveEndDate = LeaveStartDate
}

String[] arrLeaveStart = LeaveStartDate.split(' ')

String[] arrLeaveEnd = LeaveEndDate.split(' ')

def dateStart = arrLeaveStart[0]

def dateEnd = arrLeaveEnd[0]

def leaveDeductedinMin = '0'

def leaveDeductedinDays = '0'

def leaveDeductedinMinforTheDay = '0'

def currentCalDate = '0'

def leaveArray = []

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'), 10)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))
}

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_View My Profile'), 0)

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_View My Profile'), 
    10)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_View My Profile'))
}

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Basic Profile First Name Value'), 0)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Basic Profile First Name Value'), 0)

GlobalVariable.EmployeeFirstName = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Basic Profile First Name Value'))

KeywordUtil.logInfo('Retrieved Employee FirstName as : ' + GlobalVariable.EmployeeFirstName)

GlobalVariable.EmployeeLastName = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Basic Profile Last Name Value'))

KeywordUtil.logInfo('Retrieved Employee LastName as : ' + GlobalVariable.EmployeeLastName)

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/SectionTabName (var)', [('tabName') : 'Employment Information']))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Organisational Information Group Value'), 
    0)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Organisational Information Group Value'), 0)

GlobalVariable.EmployeeGroup = (WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Organisational Information Group Value')).split(
    ' \\(')[0])

KeywordUtil.logInfo('Retrieved Employee Group as : ' + GlobalVariable.EmployeeGroup)

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Section Show More button'), 
    0)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Section Show More button'))
}

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Manager Name'), 0)

GlobalVariable.EmployeeManager = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Manager Name'))

KeywordUtil.logInfo('Retrieved Employee Manager as : ' + GlobalVariable.EmployeeManager)

WebUI.takeFullPageScreenshot()

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Work Schedule details link'))

int retryCount = 0
while (retryCount <= 5) {
	try {
		if (WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Header'), 10)) {
			break
		}
	}
	catch (Exception E) {
		reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Job Information - Work Schedule details link'))
		retryCount++
		WebUI.delay(1)
	}
}



WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Work Schedule Name'), 10)

def scheduleName = WebUI.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Work Schedule Name'))

WebUI.verifyMatch(scheduleName, WorkSchedule + '.*', true)

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Preview tab'))

while (!(DateRangeChecker.isDateInValueRange(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Selected Week')), 
    LeaveStartDate))) {
    if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'), 
        0)) {
        reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'))
    }
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
        
        if (FullDayOrHalfDay == 'HalfDay') {
            def workingTime = WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)', 
                    [('date') : dateStart]))

            String[] arrTime = workingTime.split(' ')

            GlobalVariable.LeaveStartTime = (arrTime[0])

            GlobalVariable.LeaveEndTime = TimeRangeCalculator.calculateNewEndTime(workingTime)

            leaveDeductedinMin = TimeDifference.calculateTimeDifference(((arrTime[0]) + ' – ') + GlobalVariable.LeaveEndTime)
        } else {
            if (GlobalVariable.leaveUnit == 'Hours') {
                leaveDeductedinMin = TimeDifference.calculateTimeDifference(WebUiBuiltInKeywords.getText(findTestObject(
                            'Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)', [('date') : dateStart])))
            } else {
                leaveDeductedinDays = String.valueOf(Integer.valueOf(leaveDeductedinDays) + 1)
            }
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
            if (!(PublicHolidays.checkPublicHoliday(currentDate.toString()))) {
                leaveDeductedinMinforTheDay = String.valueOf(TimeDifference.calculateTimeDifference(WebUiBuiltInKeywords.getText(
                            findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Working Hours Text (var)', 
                                [('date') : currentCalDate]))))

                if (GlobalVariable.leaveUnit == 'Hours') {
                    leaveDeductedinMin = String.valueOf(Integer.valueOf(leaveDeductedinMin) + Integer.valueOf(leaveDeductedinMinforTheDay))

                    leaveArray << [currentDate.toString(), TimeConverter.convertMinutesToDecimalHours(leaveDeductedinMinforTheDay)]
                } else {
                    leaveDeductedinDays = String.valueOf(Integer.valueOf(leaveDeductedinDays) + 1)

                    leaveArray << [currentDate.toString(), '1']
                }
            }
        }
        
        if (currentDate.getDayOfWeek().getValue() == 7) {
            if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'), 
                0)) {
                reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Next Week Button'))
            }
            
            WebUI.delay(1)
        }
        
        currentDate = currentDate.plusDays(1)
    }
}

GlobalVariable.workMinutes = leaveDeductedinMin

GlobalVariable.workDays = leaveDeductedinDays

GlobalVariable.LeaveArray = leaveArray

def totalMins = Integer.valueOf(leaveDeductedinMin)

if (totalMins != 0) {
    int totminConvertedToHours = totalMins / 60

    int totminConvertedToMin = totalMins % 60

    if (totminConvertedToMin == 0) {
        if (totminConvertedToHours == 1) {
            GlobalVariable.LeaveDeducted = (totminConvertedToHours + ' hour')
        } else {
            GlobalVariable.LeaveDeducted = (totminConvertedToHours + ' hours')
        }
    } else if (totminConvertedToHours == 0) {
        GlobalVariable.LeaveDeducted = (totminConvertedToMin + ' minutes')
    } else {
        if (totminConvertedToHours == 1) {
            GlobalVariable.LeaveDeducted = (((totminConvertedToHours + ' hour ') + totminConvertedToMin) + ' minutes')
        } else {
            GlobalVariable.LeaveDeducted = (((totminConvertedToHours + ' hours ') + totminConvertedToMin) + ' minutes')
        }
    }
}

if (leaveDeductedinDays != '0') {
    if (Integer.valueOf(leaveDeductedinDays) == 1) {
        GlobalVariable.LeaveDeducted = (leaveDeductedinDays + ' day')
    } else {
        GlobalVariable.LeaveDeducted = (leaveDeductedinDays + ' days')
    }
}

KeywordUtil.markPassed('Total working hours : ' + GlobalVariable.LeaveDeducted)

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Work Schedule Details Popup/Popup Close Button'))

