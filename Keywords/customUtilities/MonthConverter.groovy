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

import java.util.HashMap;
import java.util.Map;

public class MonthConverter {

	private static final Map<String, String> monthMap = new HashMap<>();

	static {
		monthMap.put("jan", "January");
		monthMap.put("feb", "February");
		monthMap.put("mar", "March");
		monthMap.put("apr", "April");
		monthMap.put("may", "May");
		monthMap.put("jun", "June");
		monthMap.put("jul", "July");
		monthMap.put("aug", "August");
		monthMap.put("sep", "September");
		monthMap.put("oct", "October");
		monthMap.put("nov", "November");
		monthMap.put("dec", "December");
	}

	public static String getFullMonthName(String shortMonth) {
		if (shortMonth == null || shortMonth.length() != 3) {
			return "Invalid input. Please provide the first three letters of a month.";
		}

		// Convert the input to lowercase to ensure case insensitivity
		String monthKey = shortMonth.toLowerCase();

		// Get the full month name from the map
		return monthMap.getOrDefault(monthKey, "Invalid month abbreviation.");
	}

	public static void main(String[] args) {
		// Example usage
		String shortMonth = "Feb"; // Change this for testing
		String fullMonth = getFullMonthName(shortMonth);
	}
}
