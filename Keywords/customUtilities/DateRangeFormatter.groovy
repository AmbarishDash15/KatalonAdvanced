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

public class DateRangeFormatter {

    public static String formatDateRange(String dateStr1, String dateStr2) {
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

        try {
            // Parse the date strings into Date objects
            Date date1 = dateFormat.parse(dateStr1);
            Date date2 = dateFormat.parse(dateStr2);

            // Check if both dates are the same
            if (date1.equals(date2)) {
                return dateStr1; // Return the same date
            }

            // Extract day, month, and year information
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

            String day1 = dayFormat.format(date1);
            String day2 = dayFormat.format(date2);
            String month1 = monthFormat.format(date1);
            String month2 = monthFormat.format(date2);
            String year1 = yearFormat.format(date1);
            String year2 = yearFormat.format(date2);

            // Check if the years are the same
            if (year1.equals(year2)) {
                // Check if the months are the same
                if (month1.equals(month2)) {
                    return String.format("%s\u2013%s %s", day1, day2, month1 + " " + year1);
                } else {
                    return String.format("%s %s\u2013%s %s", day1, month1, day2, month2) + " " + year1;
                }
            } else {
                // Different years
                return String.format("%s %s %s\u2013%s %s %s", day1, month1, year1, day2, month2, year2);
            }

        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing errors
            return null; // Return null if parsing fails
        }
    }

    public static void main(String[] args) {
        // Test cases
        String date1 = "14 Oct 2024";
        String date2 = "15 Oct 2024";
        //System.out.println(formatDateRange(date1, date2)); // Output: 14-15 Oct 2024

        date1 = "14 Oct 2024";
        date2 = "15 Nov 2024";
        //System.out.println(formatDateRange(date1, date2)); // Output: 14 Oct-15 Nov 2024

        date1 = "14 Oct 2024";
        date2 = "15 Nov 2025";
        System.out.println(formatDateRange(date1, date2)); // Output: 14 Oct 2024-15 Nov 2025

        date1 = "14 Oct 2024";
        date2 = "14 Oct 2024";
        System.out.println(formatDateRange(date1, date2)); // Output: 14 Oct 2024
    }
}
