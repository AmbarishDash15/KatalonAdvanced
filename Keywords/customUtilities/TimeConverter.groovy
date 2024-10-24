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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeConverter {
	public static void main(String[] args) {
		String input = "15 hours"; // Input string for testing
		String result = convertToDecimalHours(input);
		System.out.println("Converted Time: " + result);
	}

	public static String convertToDecimalHours2Decimal(String input) {
		String[] parts = input.split(" ");
        double hours = 0;
        double minutes = 0;

        // Parse the hours
        if (parts.length > 0 && parts[1].startsWith("hour")) {
            hours = Double.parseDouble(parts[0]);
        }

        // Parse the minutes
        if (parts.length > 2 && parts[3].startsWith("minute")) {
            minutes = Double.parseDouble(parts[2]);
        }

        // Calculate total in decimal format
        double totalDecimal = hours + (minutes / 60);

        // Format the output to 2 decimal places
        return String.format("%.2f", totalDecimal);
	}

	public static String convertToDecimalHoursWhole(String input) {
		int hours = 0;
		int minutes = 0;

		// Regular expression to extract hours and minutes
		Pattern pattern = Pattern.compile("(\\d+)\\s*hours?|\\d+\\s*minutes?");
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String match = matcher.group();
			if (match.contains("hour")) {
				hours = Integer.parseInt(match.replaceAll("\\D", "")); // Extracting the number of hours
			} else if (match.contains("minute")) {
				minutes = Integer.parseInt(match.replaceAll("\\D", "")); // Extracting the number of minutes
			}
		}

		// Convert minutes to decimal hours
		double decimalHours = hours + (minutes / 60.0);

		// Return as an integer if there's no fractional part
		if (decimalHours % 1 == 0) {
			return String.valueOf((int) decimalHours); // Convert to integer
		} else {
			return String.format("%.1f", decimalHours); // Return as decimal
		}
	}

	public static String convertDecimalToHoursAndMinutes(String decimalHoursStr) {
		try {
			double decimalHours = Double.parseDouble(decimalHoursStr); // Parse the string to double
			int hours = (int) decimalHours; // Get the whole number of hours
			int minutes = (int) ((decimalHours - hours) * 60); // Calculate minutes

			if (minutes == 0) {
				return hours + " hours";
			}
			else {
				return hours + " hours " + minutes + " minutes";
			}
		} catch (NumberFormatException e) {
			return "Invalid input"; // Handle invalid input
		}
	}
	public static String convertMinutesToDecimalHours(String minutesString) {
		// Parse the input string to an integer
		int minutes = Integer.parseInt(minutesString);

		// Calculate total hours in decimal format
		double totalDecimalHours = minutes / 60.0;

		// Format the output, removing trailing zeros
		return String.format("%.2f", totalDecimalHours)
                     .replaceAll('0+$', '')
                     .replaceAll('\\.$', '')
	}
}

