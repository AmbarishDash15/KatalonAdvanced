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

public class PayPeriodDates {

	public static String getPayPeriodDates(String startDateStr, String endDateStr, int periodNumber) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// Parse the start and end dates
			Date startDate = inputFormat.parse(startDateStr);
			Date endDate = inputFormat.parse(endDateStr);

			// Calculate the length of the period in milliseconds
			long periodLengthMillis = endDate.getTime() - startDate.getTime() + (1000 * 60 * 60 * 24); // +1 day to include end date

			// Calculate the start date of the requested period
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_YEAR, (int) (periodLengthMillis / (1000 * 60 * 60 * 24) * (periodNumber - 1)));

			Date periodStartDate = calendar.getTime();

			// Calculate the end date of the requested period
			calendar.add(Calendar.DAY_OF_YEAR, (int) (periodLengthMillis / (1000 * 60 * 60 * 24) - 1)); // End date is last day of the period
			Date periodEndDate = calendar.getTime();

			// Format the output
			String periodStartStr = outputFormat.format(periodStartDate);
			String periodEndStr = outputFormat.format(periodEndDate);

			return periodStartStr + ":" + periodEndStr;
		} catch (ParseException e) {
			e.printStackTrace();
			return "Error in date format.";
		}
	}

	public static void main(String[] args) {
		// Example usage
		String startDate = "22.06.2024"; // Start of first period
		String endDate = "05.07.2024";   // End of first period
		int periodNumber = 2; // Period number to calculate

		String periodDates = getPeriodDates(startDate, endDate, periodNumber);
		System.out.println("The dates for period number " + periodNumber + " are: " + periodDates);
	}
}
