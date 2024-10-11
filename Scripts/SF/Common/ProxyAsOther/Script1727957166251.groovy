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

WebUI.waitForPageLoad(10)

WebUI.waitForElementClickable(findTestObject('Page_SuccessFactors Home/TitleBar/button_ProfileButton'), 10)

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/button_ProfileButton'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/button_Proxy Now'), 0)

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/button_Proxy Now'))

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/input_UserName'), 0)

WebUI.setText(findTestObject('Page_SuccessFactors Home/Homepage/input_UserName'), employeetoProxy)

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/Homepage/ProxyPopupSearchResult'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SuccessFactors Home/Homepage/ProxyPopupSearchResult'), 'title', employeeName, 
    0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/ProxyPopupSearchResult'))

WebUI.click(findTestObject('Page_SuccessFactors Home/Homepage/ProxyPopup_OkButton'))

WebUI.delay(10)

title = WebUI.getAttribute(findTestObject('Page_SuccessFactors Home/TitleBar/button_ProfileButton'), 'title')

WebUI.verifyMatch(title, '.*on behalf of ' + (employeeName + '.*'), true)

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/button_ProfileButton'))

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_SuccessFactors Home/TitleBar/button_ProfileButton'))

