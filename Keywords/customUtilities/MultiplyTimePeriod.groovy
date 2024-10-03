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

public class MultiplyTimePeriod {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MultiplyTime 'X hours Y minutes' multiplier");
            return;
        }

        String timeStr = args[0] + " " + args[1];
        int multiplier = Integer.parseInt(args[2]);

        String result = multiplyTime(timeStr, multiplier);
        System.out.println("Total time: " + result);
    }

    public static String multiplyTimePeriod(String timeStr, int multiplier) {
        String[] timeParts = timeStr.split(" ");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[2]);

        int totalMinutes = (hours * 60) + minutes;
        totalMinutes *= multiplier;

        int newHours = totalMinutes / 60;
        int newMinutes = totalMinutes % 60;

        return newHours + " hours " + newMinutes + " minutes";
    }
}

