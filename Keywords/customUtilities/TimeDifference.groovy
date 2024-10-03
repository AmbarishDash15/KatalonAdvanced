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
import java.time.Duration;

public class TimeDifference {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java TimeDifference 'HH:mm - HH:mm'");
			return;
		}

		String timeRange = args[0];
		String result = calculateTimeDifference(timeRange);
		System.out.println("Time difference: " + result);
	}

	public static String calculateTimeDifference(String timeRange) {
		String[] times = timeRange.split(" - ");
		LocalTime start = LocalTime.parse(times[0]);
		LocalTime end = LocalTime.parse(times[1]);

		Duration duration = Duration.between(start, end);
		long totalMinutes = duration.toMinutes() - 30; // Deduct 30 minutes

		long hours = totalMinutes / 60;
		long minutes = totalMinutes % 60;

		return hours + " hours " + minutes + " minutes";
	}
}

