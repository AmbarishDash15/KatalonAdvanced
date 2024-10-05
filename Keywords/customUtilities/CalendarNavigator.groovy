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


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarNavigator {

	public static int monthsToNext(String dateString) {
		// Define the formatter for the input date string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

		try {
			// Parse the input date string to a LocalDate
			LocalDate targetDate = LocalDate.parse(dateString, formatter);
			
			// Get the current date
			ZonedDateTime nowInAEST = ZonedDateTime.now(ZoneId.of("Australia/Sydney"));
			LocalDate currentDate = nowInAEST.toLocalDate();

			// Get the first day of the current month
			LocalDate firstDayOfCurrentMonth = currentDate.withDayOfMonth(1);
			
			// Count how many months to click the next button
			int monthsToClick = 0;

			// Increment months until we reach or exceed the target date
			while (firstDayOfCurrentMonth.isBefore(targetDate)) {
				monthsToClick++;
				firstDayOfCurrentMonth = firstDayOfCurrentMonth.plusMonths(1);
			}

			return monthsToClick;
		} catch (DateTimeParseException e) {
			// Handle the exception if the input date string is invalid
			System.err.println("Invalid date format: " + e.getMessage());
			return -1; // Indicates an error
		}
	}

	public static void main(String[] args) {
		// Example usage
		String dateString = "15 Nov 2024"; // Adjust this to test different dates
		int result = monthsToNext(dateString);
		
		
	}
}

