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

public class DateConverter {

	public static String convertDateFormat(String dateString) {
		// Define the input and output date formats
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Parse the input date string to a Date object
			Date date = inputFormat.parse(dateString);
			// Format the Date object to the desired output format
			return outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace(); // Handle parsing errors
			return null; // Return null if parsing fails
		}
	}

	public static void main(String[] args) {
		String dateStr = "14 Nov 2024";
		String convertedDate = convertDateFormat(dateStr);
		//System.out.println("Converted Date: " + convertedDate); // Output: Converted Date: 14/11/2024
	}
}
