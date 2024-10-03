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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorkingDaysCalculator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java WorkingDaysCalculator 'DD MMM YYYY' 'DD MMM YYYY'");
            return;
        }

        String startDateStr = args[0];
        String endDateStr = args[1];

        try {
            int workingDays = calculateWorkingDays(startDateStr, endDateStr);
            System.out.println("Working days between " + startDateStr + " and " + endDateStr + ": " + workingDays);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int calculateWorkingDays(String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        if (startCal.after(endCal)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        int workingDays = 0;

        while (!startCal.after(endCal)) {
            int dayOfWeek = startCal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDays++;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return workingDays;
    }
}
