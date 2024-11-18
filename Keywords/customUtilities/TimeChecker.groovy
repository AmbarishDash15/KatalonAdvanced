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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeChecker {

	public static boolean isWithinFiveMinutesPast(String timeString) {
		// Define the date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Australia/Sydney")); // Handles both AEST and AEDT

		try {
			// Parse the input time string to a Date object
			Date inputTime = dateFormat.parse(timeString);
			// Get the current time in AEST/AEDT
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Australia/Sydney"));
			Date currentTime = calendar.getTime();
			System.out.println("Current Time: " + currentTime);

			// Calculate the difference in milliseconds
			long differenceInMillis = currentTime.getTime() - inputTime.getTime();
			// Convert milliseconds to minutes
			long differenceInMinutes = differenceInMillis / (1000 * 60);
			System.out.println("Difference in Minutes: " + differenceInMinutes);

			// Check if the input time is within 10 minutes in the past
			return differenceInMinutes >= 0 && differenceInMinutes <= 5;
		} catch (ParseException e) {
			e.printStackTrace();
			return false; // In case of a parse error, return false
		}
	}

	public static void main(String[] args) {
		String timeToCheck = "16 Oct 2024, 15:58:42";
		boolean result = isWithinFiveMinutesPast(timeToCheck);
		System.out.println("Is the time within the last 10 minutes? " + result);
	}
}
