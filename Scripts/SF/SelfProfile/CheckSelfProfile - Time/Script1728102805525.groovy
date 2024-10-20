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
import customUtilities.DateChecker as DateChecker
import customUtilities.CalendarNavigator as CalendarNavigator
import customUtilities.MonthConverter as MonthConverter


WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_View My Profile'))

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/heading Full Name'), 10)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/My Profile/heading Full Name'), EmployeeName)

WebUI.takeFullPageScreenshot()

if (!(DateChecker.isTodayOrPast(LeaveStartDate))) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIcon'))

    WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Calendar'), 0)

    String[] parts = LeaveStartDate.split(' ')

    def paramDate = parts[0]

    def paramMonth = parts[1]

    def paramYear = parts[2]

    def fullMonthName = MonthConverter.getFullMonthName(paramMonth)

    while (!((fullMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) & 
    (paramYear == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Year Value'))))) {
        WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Next Month Button'))
    }
    

    if (fullMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) {
        WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Date Icon (var)', [('date') : paramDate]))
    }
    
    WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIconAfterDateChange'), 
        'title', 'As of ' + LeaveStartDate, 0)

    WebUI.takeFullPageScreenshot()
}

WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/SectionTabName (var)', [('tabName') : 'Time']))

if (WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time - Section Show More Button'), 0)) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Time - Section Show More Button'))
}

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 0)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 0)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Type (var)', [('leaveType') : LeaveType]), 
    0)

def leaveBalance = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)', 
        [('leaveType') : LeaveType]))

def leaveBalanceEndDate = leaveBalance

WebUI.takeFullPageScreenshot()

if (LeaveStartDate != LeaveEndDate) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIconAfterDateChange'))

    WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Calendar'), 0)

    String[] partsEnd = LeaveEndDate.split(' ')

    def paramEndDate = partsEnd[0]

    def paramEndMonth = partsEnd[1]

    def paramEndYear = partsEnd[2]

    def fullEndMonthName = MonthConverter.getFullMonthName(paramEndMonth)

    while (!((fullEndMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) & 
    (paramEndYear == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Year Value'))))) {
        WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Next Month Button'))
    }

    if (fullEndMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) {
        WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Date Icon (var)', [('date') : paramEndDate]))
    }
    
    WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIconAfterDateChange'), 
        'title', 'As of ' + LeaveEndDate, 0)

    WebUI.takeFullPageScreenshot()
	
	WebUI.click(findTestObject('Page_SuccessFactors Home/My Profile/SectionTabName (var)', [('tabName') : 'Time']))
	
	
	WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 0)
	
	WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 0)
	
	WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Type (var)', [('leaveType') : LeaveType]),
		0)
	
	leaveBalanceEndDate = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)',
			[('leaveType') : LeaveType]))
}

GlobalVariable.LeaveBalance = leaveBalance
GlobalVariable.EndDateLeaveBalance = leaveBalanceEndDate




