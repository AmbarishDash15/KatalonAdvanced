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

public class TimeDifferenceChecker {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TimeDifferenceChecker 'X hours Y minutes' 'A hours B minutes'");
            return;
        }

        String startTime = args[0]; // First argument
        String endTime = args[1];   // Second argument

        // Call the method and print the boolean result
        boolean isLessThanTenMinutes = checkTimeDifference(startTime, endTime);
        System.out.println(isLessThanTenMinutes);
    }

    public static boolean checkTimeDifference(String startTimeStr, String endTimeStr) {
        int startTotalMinutes = parseTimeString(startTimeStr);
        int endTotalMinutes = parseTimeString(endTimeStr);

        // Calculate the absolute difference in minutes
        int difference = Math.abs(endTotalMinutes - startTotalMinutes);

        // Return true if the difference is less than 10 minutes
        return difference < 10;
    }

    private static int parseTimeString(String timeStr) {
        String[] parts = timeStr.split(" ");
        int hours = 0;
        int minutes = 0;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("hours")) {
                hours = Integer.parseInt(parts[i - 1]);
            } else if (parts[i].equals("minutes")) {
                minutes = Integer.parseInt(parts[i - 1]);
            }
        }

        // Convert to total minutes
        return (hours * 60) + minutes;
    }
}


