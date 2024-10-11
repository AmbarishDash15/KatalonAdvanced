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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

KeywordLogger logger = new KeywordLogger()

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_Request Time Off'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'), 0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Page Title'), 'Time Off')

String[] arrStartDate = LeaveStartDate.split(' ')

def leaveDate = arrStartDate[0]

def leaveMonth = MonthConverter.getFullMonthName(arrStartDate[1])

def leaveYear = arrStartDate[2]

while (!((WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Left Calendar Month')) == 
leaveMonth) && (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Left Calendar Year')) == 
leaveYear))) {
    logger.logInfo(leaveMonth)

    logger.logInfo(leaveYear)

    logger.logInfo(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Left Calendar Month')))

    logger.logInfo(WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Left Calendar Year')))

    WebUI.click(findTestObject('Page_SuccessFactors Home/Time Off Page/Month Forward Button'))
}

def convertedStartDate = DateFormatConverter.convertDateFormatToyyyymmdd(LeaveStartDate)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', [('startdate') : convertedStartDate]), 
    0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Day on Left Calendar (var)', [('startdate') : convertedStartDate]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Link on Popup'), 0)

if (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Status on Popup')) == 'Approved') {
    GlobalVariable.LeaveStatus = 'Approved'

    GlobalVariable.NextApprover = ''
} else if (WebUiBuiltInKeywords.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Status on Popup')) == 
'Pending') {
    GlobalVariable.LeaveStatus = 'Pending'

    while (GlobalVariable.LeaveStatus == 'Pending') {
        WebUI.click(findTestObject('Page_SuccessFactors Home/Time Off Page/Leave Link on Popup'))

        WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/View Absence Label'), 0)

        WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Value in Time Type'), LeaveType)

        WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Value in Date'), DateRangeFormatter.dateRangewithDay(
                LeaveStartDate, LeaveEndDate))

        GlobalVariable.NextApprover = WebUI.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Value in To Be Approved By'))

        WebUI.callTestCase(findTestCase('SF/Common/Approve Leave'), [('ApproverName') : GlobalVariable.NextApprover, ('EmployeeID') : EmployeeID
                , ('EmployeeName') : EmployeeName, ('LeaveType') : LeaveType, ('LeaveStartDate') : LeaveStartDate, ('LeaveEndDate') : LeaveEndDate], 
            FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('SF/Common/ProxyAsOther'), [('employeetoProxy') : EmployeeID, ('employeeName') : EmployeeName], 
            FailureHandling.STOP_ON_FAILURE)
    }
}

