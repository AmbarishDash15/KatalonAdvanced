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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateRangeChecker {

	public static boolean isDateInRange(String dateStr, String dateRangeStr) {
		// Define the date format for the input strings
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat dateRangeFormat = new SimpleDateFormat("dd MMMM yyyy");

		try {
			// Parse the single date
			Date date = dateFormat.parse(dateStr);

			// Split the date range into start and end dates
			String[] dateRangeParts = dateRangeStr.split(" - ");
			Date startDate = dateRangeFormat.parse(dateRangeParts[0].trim());
			Date endDate = dateRangeFormat.parse(dateRangeParts[1].trim());

			// Check if the date is within the range
			return !date.before(startDate) && !date.after(endDate);
		} catch (ParseException e) {
			e.printStackTrace(); // Handle parsing errors
			return false; // Return false if parsing fails
		}
	}
	
	public static boolean isDateInPayPeriodRange(String startDateStr, String endDateStr, String targetDateStr) {
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);

		try {
			// Parse the start and end dates
			Date startDate = format1.parse(startDateStr);
			Date endDate = format1.parse(endDateStr);
			Date targetDate = format2.parse(targetDateStr);

			// Check if the target date is between start and end dates
			return !targetDate.before(startDate) && !targetDate.after(endDate);

		} catch (ParseException e) {
			e.printStackTrace();
			return false; // Return false if there's a parsing error
		}
	}

	public static void main(String[] args) {
		String dateStr = "14 Nov 2024";
		String dateRangeStr = "11 November 2024 - 24 November 2024";

		boolean result = isDateInRange(dateStr, dateRangeStr);
		System.out.println("Is the date in range? " + result); // Output: true
	}
}
