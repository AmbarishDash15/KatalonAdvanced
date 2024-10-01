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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.google.com/')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/FirstTrial/Page_Google/textarea_Sign in_q'), 'om shree ganeshaya namah')

WebUI.click(findTestObject('FirstTrial/Page_Google/img_Sign in_lnXdpd'))

WebUI.click(findTestObject('FirstTrial/Page_Google/input_Report inappropriate predictions_btnK'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(1)

WebUI.verifyElementText(findTestObject('Object Repository/FirstTrial/Page_om shree ganeshaya namah - Google Search/div_Om Shri Ganeshay Namah'), 
    'Om Shri Ganeshay Namah')

WebUI.closeBrowser()

