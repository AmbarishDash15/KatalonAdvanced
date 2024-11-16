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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import customUtilities.DateRangeFormatter as DateRangeFormatter
import customUtilities.DateFormatConverter as DateFormatConverter
import customUtilities.MonthConverter as MonthConverter
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import customUtilities.GetNameFromDesignation as GetNameFromDesignation
import customUtilities.reusableFunctions as reusableFunctions

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'), 10)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))
}

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_Request Time Off'), 
    10)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_Request Time Off'))
}

WebUI.waitForElementNotPresent(findTestObject('Page_SuccessFactors Home/Homepage/busyIndicator'), 5)

WebUI.waitForElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/dialogBox'), 0)

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/dialogHeader'), 'Request Time Off')

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'), 0)

WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'), 0)

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'), 
    10)) {
    reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'))
}

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Page Title'), 'Time Off')

String[] arrStartDate = LeaveStartDate.split(' ')

def leaveDate = arrStartDate[0]

def leaveMonth = MonthConverter.getFullMonthName(arrStartDate[1])

def leaveYear = arrStartDate[2]

while (!((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Left Calendar Month')) == 
leaveMonth) && (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Left Calendar Year')) == 
leaveYear))) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Time Off Page/Month Forward Button'))
}

def convertedStartDate = DateFormatConverter.convertDateFormatToyyyymmdd(LeaveStartDate)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', [('startdate') : convertedStartDate]), 
    10)

if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', 
        [('startdate') : convertedStartDate]), 20)) {
	WebUI.waitForElementClickable(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', 
        [('startdate') : convertedStartDate]), 20)    
	reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', 
        [('startdate') : convertedStartDate]))
}

int waitCount = 0

while (waitCount <= 5) {
	try {
		if (WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Link on Popup'), 10)) {
			break
		}
	}
	catch (Exception E) {
		WebUI.click((findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', 
        [('startdate') : convertedStartDate])))
		WebUI.delay(1)
		waitCount++
	}
}



WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Link on Popup'), 10)

if (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Status on Popup')) == 'Approved') {
    GlobalVariable.LeaveStatus = 'Approved'

    GlobalVariable.NextApprover = ''

    KeywordUtil.markPassed('Leave is Approved')

    WebUI.takeFullPageScreenshot()
} else if (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Status on Popup')) == 
'Pending') {
    GlobalVariable.LeaveStatus = 'Pending'

    if (WebUiBuiltInKeywords.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Link on Popup'), 
        10)) {
        reusableFunctions.clickElementonScreen(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Link on Popup'))
    }
    
    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/View Absence Label'), 0)

    WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Value in Time Type'), LeaveType)

    WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Value in Date'), DateRangeFormatter.dateRangewithDay(
            LeaveStartDate, LeaveEndDate))

    GlobalVariable.NextApprover = WebUI.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Value in To Be Approved By'))

    if ((GlobalVariable.NextApprover == 'HR Admin') || (GlobalVariable.NextApprover == 'HR Admin')) {
        GlobalVariable.NextApprover = GetNameFromDesignation.getApproverName(GlobalVariable.NextApprover)
    }
    
    KeywordUtil.logInfo('Leave Status is Pending and Next Approver is : ' + GlobalVariable.NextApprover)

    WebUI.callTestCase(findTestCase('SF/Common/Approve Leave'), [('ApproverName') : GlobalVariable.NextApprover, ('EmployeeID') : EmployeeID
            , ('EmployeeName') : EmployeeName, ('LeaveType') : LeaveType, ('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate], 
        FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : EmployeeID, ('employeeName') : EmployeeName], 
        FailureHandling.STOP_ON_FAILURE)
}

