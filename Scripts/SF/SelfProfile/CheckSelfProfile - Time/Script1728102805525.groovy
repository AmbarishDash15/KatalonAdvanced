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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import customUtilities.reusableFunctions as reusableFunctions

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.waitForPageLoad(10)

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_View My Profile'))

WebUI.waitForPageLoad(10)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/heading Full Name'), 30)

//reusableFunctions.verifyElementText(findTestObject('Page_SuccessFactors Home/My Profile/heading Full Name'), EmployeeName)

WebUI.takeFullPageScreenshot()

if (!(DateChecker.isTodayOrPast(LeaveStartDate))) {
	reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIcon'))

    WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Calendar'), 10)

    String[] parts = LeaveStartDate.split(' ')

    def paramDate = parts[0]

    def paramMonth = parts[1]

    def paramYear = parts[2]

    def fullMonthName = MonthConverter.getFullMonthName(paramMonth)

    while (!((fullMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) & 
    (paramYear == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Year Value'))))) {
		reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Next Month Button'))
    }
    
    if (fullMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) {
       reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Date Icon (var)', [('date') : paramDate]))
    }
    
    WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIconAfterDateChange'), 
        'title', 'As of ' + LeaveStartDate, 0)

	WebUI.takeFullPageScreenshot()
}

reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/SectionTabName (var)', [('tabName') : 'Time']))

if (WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time - Section Show More Button'), 10)) {
    WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 10)

    WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Time - Section Show More Button'), 10)

    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Time - Section Show More Button'))
}

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 10)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 10)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Type (var)', [('leaveType') : LeaveType]), 
    10)

def leaveBalance = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)', 
        [('leaveType') : LeaveType]))

def leaveBalanceEndDate = leaveBalance

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)', 
        [('leaveType') : LeaveType]), 10)

WebUI.verifyElementVisible(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)', 
        [('leaveType') : LeaveType]))

WebUI.takeFullPageScreenshot()

if (LeaveStartDate != LeaveEndDate) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIconAfterDateChange'))

    WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Calendar'), 10)

    String[] partsEnd = LeaveEndDate.split(' ')

    def paramEndDate = partsEnd[0]

    def paramEndMonth = partsEnd[1]

    def paramEndYear = partsEnd[2]

    def fullEndMonthName = MonthConverter.getFullMonthName(paramEndMonth)

    while (!((fullEndMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) & 
    (paramEndYear == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Year Value'))))) {
		reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Next Month Button'))
    }
    
    if (fullEndMonthName == WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Month Value'))) {
		reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/Calendar - Date Icon (var)', [('date') : paramEndDate]))
    }
    
    WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/My Profile/CalendarIconAfterDateChange'), 
        'title', 'As of ' + LeaveEndDate, 10)

    
	
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/My Profile/SectionTabName (var)', [('tabName') : 'Time']))

    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 10)

    WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - title'), 10)
	WebUI.delay(2)
	
	WebUI.enableSmartWait()

    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Type (var)', 
            [('leaveType') : LeaveType]), 10)

    leaveBalanceEndDate = WebUI.getText(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)', 
            [('leaveType') : LeaveType]))
	KeywordUtil.logInfo('Leave End Date Balance : ' + leaveBalanceEndDate)
	WebUI.verifyElementVisible(findTestObject('Page_SuccessFactors Home/My Profile/Time Off Balance - Leave Balance (var)', 
            [('leaveType') : LeaveType]))
	WebUI.takeFullPageScreenshot()
	
}

GlobalVariable.LeaveBalance = leaveBalance

KeywordUtil.logInfo((((('Leave Balance of Leave Type - ' + LeaveType) + 'as on Leave Start Date - ') + LeaveStartDate) + 
    ' is : ') + GlobalVariable.LeaveBalance)

GlobalVariable.EndDateLeaveBalance = leaveBalanceEndDate

KeywordUtil.logInfo((((('Leave Balance of Leave Type - ' + LeaveType) + 'as on Leave End Date - ') + LeaveEndDate) + ' is : ') + 
    GlobalVariable.EndDateLeaveBalance)

