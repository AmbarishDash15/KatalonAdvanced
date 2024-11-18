package customUtilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class reusableFunctions {

	public static void main(String[] args) {
		// Example usage
		String dateString = "15 Nov 2024"; // Adjust this to test different dates
		//int result = monthsToNext(dateString);
	}
	public static void clickElementonScreen(object) {
		int retryCount = 0
		while (retryCount <= 5) {
			try {
				WebUI.scrollToElement(object, 10)
				WebUI.click(object)
				break;
			}
			catch (Exception e) {
				WebUI.delay(1)
				WebUI.waitForPageLoad(10)
				retryCount++
			}
		}
	}

	public static void setTextinElement(Object object, String textToSet) {
		int retryCount = 0
		while (retryCount <= 10) {
			try {
				WebUI.scrollToElement(object, 10)
				WebUI.setText(object, textToSet)
				break;
			}
			catch (Exception e) {
				WebUI.delay(1)
				WebUI.waitForPageLoad(10)
				retryCount++
			}
		}
	}

	public static void verifyElementonScreen(object) {
		int retryCount = 0
		while (retryCount <= 5) {
			try {
				WebUI.scrollToElement(object, 10)
				break;
			}
			catch (Exception e) {
				WebUI.delay(1)
				WebUI.waitForPageLoad(10)
				retryCount++
			}
		}
	}

	public static void verifyElementText(Object object, String textToVerify) {
		int retryCount = 0
		while (retryCount <= 10) {
			try {
				WebUI.scrollToElement(object, 10)
				WebUI.verifyElementText(object.getText(), textToVerify)
				break;
			}
			catch (Exception e) {
				WebUI.delay(1)
				WebUI.waitForPageLoad(10)
				retryCount++
			}
		}
	}
}
