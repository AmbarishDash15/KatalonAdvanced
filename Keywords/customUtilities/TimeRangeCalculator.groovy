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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeRangeCalculator {
	public static void main(String[] args) {
		String timeRange = "08:00 – 16:00"; // Input time range
		String newEndTime = calculateNewEndTime(timeRange);
		System.out.println("New end time: " + newEndTime); // Output the new end time
	}

	public static String calculateNewEndTime(String timeRange) {
		// Split the time range into start and end times
		String[] times = timeRange.split(" – "); // Using the specific non-breaking space
		LocalTime startTime = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HH:mm"));
		LocalTime endTime = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HH:mm"));

		// Calculate duration in minutes
		long durationMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
		long halfDurationMinutes = durationMinutes / 2;

		// Calculate new end time
		LocalTime newEndTime = startTime.plusMinutes(halfDurationMinutes);

		// Return the new end time as a string
		return newEndTime.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
}
