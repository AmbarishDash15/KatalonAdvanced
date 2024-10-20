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

public class LeaveDetails {
	public static void main(String[] args) {
		// Example usage
		String leaveType = "Workers Compensation Leave"; // Adjust this to test different dates
	}
	
	public static void getLeaveDetails(String leaveType) {
		switch (leaveType) {
			case 'Annual Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = 'Yes'
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Compassionate Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Carers Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = 'Yes'
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Carers Leave Without Pay' :
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = 'Yes'
			GlobalVariable.halfPay = ''
			break;
		case 'Jury Duty' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Leave Without Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Parental Leave - Primary Carer' :
			GlobalVariable.leaveUnit = 'DAYS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Parental Leave - Primary Carer Half Pay' :
			GlobalVariable.leaveUnit = 'DAYS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.halfPay = 'Yes'
			GlobalVariable.withoutPay = ''
			break;
		case 'Parental Leave - Primary Carer Without Pay' :
			GlobalVariable.leaveUnit = 'DAYS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = 'Yes'
			GlobalVariable.halfPay = ''
			break;
		case 'Miscellaneous Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Miscellaneous Leave Without Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = 'Yes'
			GlobalVariable.halfPay = ''
			break;
		case 'Military Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Purchased Additional Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = 'Yes'
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Parental Leave - Non-Primary Carer' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Parental Leave - Non-Primary Carer Without Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = 'Yes'
			GlobalVariable.halfPay = ''
			break;
		case 'Sick Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = 'Yes'
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Sick Leave Half Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = 'Yes'
			GlobalVariable.halfPay = 'Yes'
			GlobalVariable.withoutPay = ''
			break;
		case 'Sick Leave Without Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = 'Yes'
			GlobalVariable.halfPay = ''
			break;
		case 'Special Leave With Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Study Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Workers Compensation Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Workers Compensation Leave Other Employment' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Family and Domestic Abuse Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = 'Yes'
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Long Term Leave Without Pay' :
			GlobalVariable.leaveUnit = 'DAYS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = 'Yes'
			GlobalVariable.halfPay = ''
			break;
		case 'Casuals Long Service Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Adoption Leave' :
			GlobalVariable.leaveUnit = 'DAYS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Annual Leave - Leave Loading' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Long Service Leave' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.withoutPay = ''
			GlobalVariable.halfPay = ''
			break;
		case 'Long Service Half Pay' :
			GlobalVariable.leaveUnit = 'HOURS'
			GlobalVariable.leaveBalanceCheckRequired = ''
			GlobalVariable.halfPay = 'Yes'
			GlobalVariable.withoutPay = ''
			break;
		}
	}
}
