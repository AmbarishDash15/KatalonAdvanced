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

public class DateFormatConverter {
	public static void main(String[] args) {
		String inputDate = "8 Oct 2024"; // Input date in "d MMM yyyy" format
		String outputDate = convertDateFormat(inputDate);
		System.out.println("Converted Date: " + outputDate);
	}

	public static String convertDateFormat(String inputDate) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("d MMM yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");

		try {
			Date date = inputFormat.parse(inputDate); // Parse the input date
			return outputFormat.format(date); // Format to the desired output format
		} catch (ParseException e) {
			e.printStackTrace();
			return null; // Return null if there's an error
		}
	}
	public static String convertDateFormatToyyyymmdd(String dateStr) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("d MMM yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = inputFormat.parse(dateStr);
			return outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "Invalid date format";
		}
	}
}
