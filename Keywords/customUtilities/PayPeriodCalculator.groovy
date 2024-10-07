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
import java.util.Locale;

public class PayPeriodCalculator {

    public static String calculatePeriod(String startDateStr, String endDateStr, String targetDateStr) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);

        try {
            // Parse the start and end dates
            Date startDate = format1.parse(startDateStr);
            Date endDate = format1.parse(endDateStr);
            Date targetDate = format2.parse(targetDateStr);

            // Ensure the end date is after the start date
            if (endDate.before(startDate)) {
                throw new IllegalArgumentException("End date must be after the start date.");
            }

            // Calculate the desired period in days
            long desiredPeriod = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24); // in days
            if (desiredPeriod <= 0) {
                throw new IllegalArgumentException("The desired period must be greater than zero.");
            }

            // Calculate the number of days from the start date to the target date
            long totalDays = (targetDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24); // in days
            if (totalDays < 0) {
                return "0"; // The target date is before the start date
            }

            // Determine which period the target date falls into
            long periodNumber = totalDays / desiredPeriod;

            return String.valueOf(periodNumber); // Return period number starting from 1

        } catch (ParseException e) {
            e.printStackTrace();
            return "Error in date format.";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        // Example usage
        String startDate = "22.06.2024"; // Start of first period
        String endDate = "05.07.2024";   // End of first period
        String targetDate = "17 Jan 2025"; // Date to check

        String periodNumber = calculatePeriod(startDate, endDate, targetDate);
        System.out.println("The date falls into period number: " + periodNumber);
    }
}
