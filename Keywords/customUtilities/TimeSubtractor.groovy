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

import java.util.StringTokenizer;

public class TimeSubtractor {

	public static String subtractTime(String time1, String time2) {
		// Parse the first time string "hh:mm hours"
		String[] parts1 = time1.split(" ");
		String[] hhmm1 = parts1[0].split(":");
		int hours1 = Integer.parseInt(hhmm1[0]);
		int minutes1 = Integer.parseInt(hhmm1[1]);

		// Parse the second time string "hh hours mm minutes" or "hh hours"
		StringTokenizer tokenizer = new StringTokenizer(time2, " ");
		int hours2 = 0;
		int minutes2 = 0;

		if (tokenizer.hasMoreTokens()) {
			hours2 = Integer.parseInt(tokenizer.nextToken());
			tokenizer.nextToken(); // skip "hours"
		}
		if (tokenizer.hasMoreTokens()) {
			minutes2 = Integer.parseInt(tokenizer.nextToken());
			tokenizer.nextToken(); // skip "minutes"
		}

		// Convert both times to total minutes
		int totalMinutes1 = (hours1 * 60) + minutes1;
		int totalMinutes2 = (hours2 * 60) + minutes2;

		// Calculate the difference in total minutes
		int diffMinutes = totalMinutes1 - totalMinutes2;

		// If the difference is negative, handle it appropriately
		if (diffMinutes < 0) {
			diffMinutes = 0; // or you could throw an exception, depending on requirements
		}

		// Convert back to hours and minutes
		int finalHours = diffMinutes / 60;
		int finalMinutes = diffMinutes % 60;

		// Format and return the result
		return String.format("%02d:%02d hours", finalHours, finalMinutes);
	}

	public static void main(String[] args) {
		String time1 = "14:30 hours"; // 14 hours and 30 minutes
		String time2 = "1 hours 45 minutes"; // 1 hour and 45 minutes
		String result = subtractTime(time1, time2);
		//System.out.println("Resulting Time: " + result); // Output: Resulting Time: 12:45 hours
	}
}
