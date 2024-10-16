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

public class PublicHolidays {
	
	public static boolean checkPublicHoliday(String dateString) {
		boolean pubHol = false
		switch (dateString) {
			case '2024-10-07' :
				pubHol = true
				break;
			case '2024-12-25' :
				pubHol = true
				break;
			case '2024-12-26' :
				pubHol = true
				break;
			case '2025-01-01' :
				pubHol = true
				break;
			case '2025-01-27' :
				pubHol = true
				break;
			case '2025-04-18' :
				pubHol = true
				break;
			case '2025-04-19' :
				pubHol = true
				break;
			case '2025-04-20' :
				pubHol = true
				break;
			case '2025-04-21' :
				pubHol = true
				break;
			case '2025-04-25' :
				pubHol = true
				break;
			case '2025-06-09' :
				pubHol = true
				break;
			case '2025-10-06' :
				pubHol = true
				break;
			default :
				pubHol = false
		}
		return pubHol
	}
	
	public static void main(String[] args) {
		// Example usage
		String dateString = "04 Oct 2024"; // Adjust this to test different dates
	}
}
