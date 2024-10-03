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
    public static boolean main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TimeDifferenceChecker 'X hours Y minutes' 'A hours B minutes'");
            return;
        }

        String startTime = args[0]; // First argument
        String endTime = args[1];   // Second argument

        // Call the method and print the boolean result
        boolean isLessThanTenMinutes = checkTimeDifference(startTime, endTime);
        return isLessThanTenMinutes;
    }

    public static boolean checkTimeDifference(String startTimeStr, String endTimeStr) {
        String[] startParts = startTimeStr.split(" ");
        String[] endParts = endTimeStr.split(" ");
        
        int startHours = Integer.parseInt(startParts[0]);
        int startMinutes = Integer.parseInt(startParts[2]);
        
        int endHours = Integer.parseInt(endParts[0]);
        int endMinutes = Integer.parseInt(endParts[2]);
        
        // Convert both times to total minutes
        int startTotalMinutes = (startHours * 60) + startMinutes;
        int endTotalMinutes = (endHours * 60) + endMinutes;
        
        // Calculate the absolute difference in minutes
        int difference = Math.abs(endTotalMinutes - startTotalMinutes);
        
        // Return true if the difference is less than 10 minutes
        return difference < 10;
    }
}

