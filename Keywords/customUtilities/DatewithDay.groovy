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
import java.util.Calendar;

public class DatewithDay {

	public static String formatDateRange(String dateStr1, String dateStr2) {
		// Define the input date format
		SimpleDateFormat inputFormat = new SimpleDateFormat("d MMM yyyy");
		// Define the output date format for different years
		SimpleDateFormat outputFormatFull = new SimpleDateFormat("EEE d MMM yyyy");
		// Define the output date format for the same year
		SimpleDateFormat outputFormat = new SimpleDateFormat("EEE d MMM");

		try {
			// Parse the input date strings to Date objects
			Date date1 = inputFormat.parse(dateStr1);
			Date date2 = inputFormat.parse(dateStr2);

			// Format the dates to the desired output format
			String formattedDate1 = outputFormat.format(date1);
			String formattedDate2 = outputFormat.format(date2);

			// Get the years using Calendar
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			int year1 = cal1.get(Calendar.YEAR);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			int year2 = cal2.get(Calendar.YEAR);

			// Check if the two dates are the same
			if (date1.equals(date2)) {
				return formattedDate1; // Return single date format
			}
			else if (year1 != year2) {
				return outputFormatFull.format(date1) + " – " + outputFormatFull.format(date2);
			}
			else {
				return formattedDate1 + " – " + formattedDate2; // Return date range format with hyphen
			}
		} catch (ParseException e) {
			e.printStackTrace(); // Handle parsing errors
			return null; // Return null if parsing fails
		}
	}

	public static void main(String[] args) {
		String date1 = "14 Nov 2024";
		String date2 = "15 Nov 2024";

		String result = formatDateRange(date1, date2);
		System.out.println("Formatted Date Range: " + result); // Output: Formatted Date Range: Thu 14 Nov - Fri 15 Nov
	}
}
