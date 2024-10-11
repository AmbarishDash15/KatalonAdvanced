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

import java.text.DecimalFormat;

public class StringMultiplier {

    public static void main(String[] args) {
        String doubleStr = "3.14"; // Example double as string
        String numberStr = "2.5";  // Example number as string

        String result = multiplyStrings(doubleStr, numberStr);
        System.out.println(result); // Output: 7.85
    }

    public static String multiplyStrings(String doubleStr, String numberStr) {
        try {
            // Convert strings to double
            double doubleValue = Double.parseDouble(doubleStr);
            double numberValue = Double.parseDouble(numberStr);

            // Multiply the values
            double result = doubleValue * numberValue;

            // Format the result to 2 decimal places
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(result);
        } catch (NumberFormatException e) {
            return "Invalid input"; // Handle exceptions for invalid inputs
        }
    }
}
