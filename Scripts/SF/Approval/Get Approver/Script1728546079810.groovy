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

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/CompanyIcon'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/tilebutton_Request Time Off'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'), 0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Request Time Off Popup/Time Off Link'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Page Title'), 'Time Off')

if (WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Show All - UpcomingTimeOff'), 0)) {
    WebUI.click(findTestObject('Page_SuccessFactors Home/Time Off Page/Show All - UpcomingTimeOff'))

    WebUI.verifyElementText(findTestObject('Page_SuccessFactors Home/Time Off Page/Absences and Holiday Popup/Popup Header'), 
        'Absences and Holidays')

    def leaveDateRange = DateRangeFormatter.dateRangewithDay(LeaveStartDate, LeaveEndDate)

    WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Time Off Page/Absences and Holiday Popup/LeaveRequestStatus (var)', 
            [('dateFormatWithDay') : leaveDateRange]), 0)

    WebUI.scrollToElement(findTestObject('Page_SuccessFactors Home/Time Off Page/Absences and Holiday Popup/LeaveRequestStatus (var)', 
            [('dateFormatWithDay') : leaveDateRange]), 0)

    GlobalVariable.LeaveStatus = WebUI.getText(findTestObject('Page_SuccessFactors Home/Time Off Page/Absences and Holiday Popup/LeaveRequestStatus (var)', 
            [('dateFormatWithDay') : leaveDateRange]), FailureHandling.STOP_ON_FAILURE)

    if (GlobalVariable.LeaveStatus == 'Pending') {
        WebUI.click(findTestObject('Page_SuccessFactors Home/Time Off Page/Absences and Holiday Popup/LeaveRequestStatus (var)', 
                [('dateFormatWithDay') : leaveDateRange]))
    }
}

