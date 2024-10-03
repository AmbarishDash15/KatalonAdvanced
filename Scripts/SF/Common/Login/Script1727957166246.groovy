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
import customUtilities.MultiplyTimePeriod as MultiplyTimePeriod
import customUtilities.TimeDifference as TimeDifference
import customUtilities.WorkingDaysCalculator as WorkingDaysCalculator
import customUtilities.TimeDifferenceChecker as TimeDifferenceChecker
import org.openqa.selenium.Keys as Keys
import java.lang.Integer as Integer

WebUI.openBrowser('')

WebUI.navigateToUrl('https://and0ewpth.accounts.cloud.sap/saml2/idp/sso?sp=https://www.successfactors.com/snowyhydroT1&idp=https://and0ewpth.accounts.ondemand.com')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Page_SuccessFactors - snowyhydroT1 Sign In/input_User Name_j_username'), 
    'adash')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_SuccessFactors - snowyhydroT1 Sign In/input_Password_j_password'), 
    'LHKz3RaeJz/y9i0fuiHHdA==')

WebUI.click(findTestObject('Object Repository/Page_SuccessFactors - snowyhydroT1 Sign In/div_Continue'))

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('Page_SuccessFactors Home/button_ProfileButton'), 0)

WebUI.takeFullPageScreenshot()

